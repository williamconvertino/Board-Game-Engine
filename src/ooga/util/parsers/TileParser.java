package ooga.util.parsers;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import ooga.display.communication.DisplayComm;
import ooga.exceptions.AttributeNotFoundException;
import ooga.exceptions.DeckNotFoundException;
import ooga.exceptions.InvalidFileFormatException;
import ooga.model.data.deck.Deck;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.ActionTileModel;
import ooga.model.data.tilemodels.CardTileModel;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.game_handling.commands.ActionSequence;
import ooga.model.game_handling.commands.ActionSequenceExecutor;

/**
 * Parser class responsible for creating all TileModel elements.
 *
 * @author Casey Goldstein
 * @since 0.0.1
 */
public class TileParser extends FolderParser {

  public static final String PARSE_TILE_METHOD_PREFIX = "parse";
  public static final String PARSE_TILE_METHOD_SUFFIX = "Tile";

  private Deck chance;
  private Deck communityChest;
  private final GameData myData;
  private final DisplayComm displayComm;

  /**
   * Creates TileParser
   *
   * @param sequenceParser
   * @param data           containing card decks
   */
  public TileParser(ActionSequenceExecutor sequenceParser, GameData data, DisplayComm displayComm) {
    super(sequenceParser, displayComm);
    this.myData = data;
    this.displayComm = displayComm;
  }

  /**
   * Takes list of properties and returns a map with key: propertyTileName and value:
   * propertyTileModel
   *
   * @param propertyList
   * @return
   */
  public Map<String, PropertyTileModel> parsePropertyTiles(List<Property> propertyList) {
    Map<String, PropertyTileModel> result = new HashMap<>();

    for (Property prop : propertyList) {
      result.putIfAbsent(prop.getName(), new PropertyTileModel(prop.getName(),
          (prop.getType() == null) ? "Property" : prop.getType(), prop,
          new ActionSequence(actionSequenceParser, displayComm), displayComm));
    }
    return result;
  }

  /**
   * Takes folder of tiles and creates a map with key: tileName and value: tileModel
   *
   * @param tileFolderPath
   * @return ArrayList of all Monopoly Properties
   * @throws AttributeNotFoundException
   */
  public Map<String, TileModel> parseNonPropertyTiles(String tileFolderPath)
      throws AttributeNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InvalidFileFormatException {

    Map<String, TileModel> result = new HashMap<>();

    File[] filesList = getFileList(tileFolderPath);
    for (File file : filesList) {
      result.putAll(parseTileFile(file));
    }
    return result;
  }


  /**
   * Takes .tile file and creates a single map element (tileName, tileModel)
   *
   * @param propertyFile
   * @return
   * @throws AttributeNotFoundException
   */
  public HashMap<String, TileModel> parseTileFile(File propertyFile)
      throws AttributeNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InvalidFileFormatException {

    Properties tileProperties = convertToPropertiesObject(propertyFile);

    String tileName = tryProperty(tileProperties, "Name");
    String tileType = tryProperty(tileProperties, "Type");

    //get proper parsing method based off tile type
    Method parseMethod = this.getClass()
        .getMethod(PARSE_TILE_METHOD_PREFIX + tileType + PARSE_TILE_METHOD_SUFFIX,
            Properties.class);
    TileModel tileModel = (TileModel) parseMethod.invoke(this, tileProperties);

    return new HashMap<String, TileModel>() {{
      put(tileName, tileModel);
    }};
  }

  /**
   * creates action tile (e.g Free Parking) from Properties object
   *
   * @param props
   * @return
   * @throws AttributeNotFoundException
   * @throws InvalidFileFormatException
   */
  public ActionTileModel parseActionTile(Properties props)
      throws AttributeNotFoundException, InvalidFileFormatException {

    String tileName = tryProperty(props, "Name");
    String tileDescription = tryProperty(props, "Description");
    String tileImage = tryProperty(props, "Image");

    ActionSequence passThrough = parseActionSequence(
        tryProperty(props, "PassThroughActionSequence"));
    ActionSequence landOn = parseActionSequence(tryProperty(props, "LandOnActionSequence"));

    return new ActionTileModel(tileName, tileDescription, passThrough, landOn, displayComm);
  }

  /**
   * creates card tile (e.g Chance) from Properties object
   *
   * @param props
   * @return
   * @throws AttributeNotFoundException
   */
  public CardTileModel parseCardTile(Properties props)
      throws AttributeNotFoundException, DeckNotFoundException {

    String tileName = tryProperty(props, "Name");
    String tileImage = tryProperty(props, "Image");

    if (tileName.equals("Community Chest") || tileName.equals("Chronicle")) {
      return new CardTileModel(tileName, myData.getDecks().getDeck("Community Chest"));
    } else if (tileName.equals("Chance") || tileName.equals("Email")) {
      return new CardTileModel(tileName, myData.getDecks().getDeck("Chance"));
    }

    return new CardTileModel(tileName);
  }


}
