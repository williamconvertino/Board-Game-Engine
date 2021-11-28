package ooga.model.game_handling.parsers;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import ooga.exceptions.AttributeNotFoundException;
import ooga.exceptions.DeckNotFoundException;
import ooga.exceptions.InvalidFileFormatException;
import ooga.model.data.cards.Card;
import ooga.model.data.deck.Deck;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.ActionTileModel;
import ooga.model.data.tilemodels.CardTileModel;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.game_handling.commands.ActionSequence;
import ooga.model.game_handling.commands.ActionSequenceParser;

public class TileParser extends FolderParser{

  public static final String PARSE_TILE_METHOD_PREFIX = "parse";
  public static final String PARSE_TILE_METHOD_SUFFIX = "Tile";

  private Deck chance;
  private Deck communityChest;
  private GameData myData;

  public TileParser(ActionSequenceParser sequenceParser, GameData data){
    super(sequenceParser);
    this.myData = data;
  }


  /**
   * Accesses the property folder, and calls to create Property object for each file.
   *
   * @param tileFolderPath
   * @return ArrayList of all Monopoly Properties
   * @throws AttributeNotFoundException
   */
  public Map<String, TileModel> parseNonPropertyTiles(String tileFolderPath)
      throws AttributeNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InvalidFileFormatException {

    Map<String,TileModel> result = new HashMap<>();
    File[] filesList = getFileList(tileFolderPath);
    for (File file : filesList) {
      result.putAll(parseTileFile(file));
    }
    return result;
  }

  /**
   * Takes .property file and creates Property object, throwing errors if missing information.
   *
   * @param propertyFile
   * @return
   * @throws AttributeNotFoundException
   */
  public HashMap<String,TileModel> parseTileFile(File propertyFile)
      throws AttributeNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InvalidFileFormatException {
    Properties tileProperties = convertToPropertiesObject(propertyFile);
    String tileName = tryProperty(tileProperties,"Name");
    String tileType = tryProperty(tileProperties,"Type");
    Method parseMethod = this.getClass().getMethod(PARSE_TILE_METHOD_PREFIX + tileType + PARSE_TILE_METHOD_SUFFIX,Properties.class);
    TileModel tileModel = (TileModel) parseMethod.invoke(this,tileProperties);
    return new HashMap<String, TileModel>() {{
      put(tileName,tileModel);
    }};
  }

  public ActionTileModel parseActionTile(Properties props)
      throws AttributeNotFoundException, InvalidFileFormatException {

    String tileName = tryProperty(props,"Name");
    String tileDescription = tryProperty(props,"Description");
    String tileImage = tryProperty(props,"Image");

    ActionSequence passThrough = parseActionSequence(tryProperty(props,"PassThroughActionSequence"));
    ActionSequence landOn = parseActionSequence(tryProperty(props,"LandOnActionSequence"));

    return new ActionTileModel(tileName,passThrough,landOn);
  }


  public CardTileModel parseCardTile(Properties props)
      throws AttributeNotFoundException, InvalidFileFormatException, DeckNotFoundException {

    String tileName = tryProperty(props,"Name");
    String tileImage = tryProperty(props,"Image");

    if (tileName.equals("Community Chest")){
      return new CardTileModel(tileName,myData.getDecks().getDeck("Community Chest"));
    }

    else if (tileName.equals("Chance")){
      return new CardTileModel(tileName,myData.getDecks().getDeck("Chance"));
    }

    return new CardTileModel(tileName);
  }


  public Map<String,PropertyTileModel> parsePropertyTiles (List<Property> propertyList){
    Map<String,PropertyTileModel> result = new HashMap<>();

    for (Property prop: propertyList){
      result.putIfAbsent(prop.getName(),new PropertyTileModel(prop.getName(), prop, new ActionSequence()));
    }
    return result;
  }


}
