package ooga.model.game_handling.parsers;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import ooga.exceptions.AttributeNotFoundException;
import ooga.exceptions.InvalidFileFormatException;
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

  public TileParser(ActionSequenceParser sequenceParser){
    super(sequenceParser);
  }


  /**
   * Accesses the property folder, and calls to create Property object for each file.
   *
   * @param tileFolderPath
   * @return ArrayList of all Monopoly Properties
   * @throws AttributeNotFoundException
   */
  public ArrayList<TileModel> parseNonPropertyTiles(String tileFolderPath)
      throws AttributeNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InvalidFileFormatException {

    //TODO: get rid of need for substring
    ArrayList<TileModel> result = new ArrayList<>();
    File[] filesList = getFileList(tileFolderPath);
    for (File file : filesList) {
      result.add(parseTileFile(file));
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
  public TileModel parseTileFile(File propertyFile)
      throws AttributeNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InvalidFileFormatException {
    Properties tileProperties = convertToPropertiesObject(propertyFile);
    String tileType = tryProperty(tileProperties,"Type");
    Method parseMethod = this.getClass().getMethod(PARSE_TILE_METHOD_PREFIX + tileType + PARSE_TILE_METHOD_SUFFIX,Properties.class);
    return (TileModel) parseMethod.invoke(this,tileProperties);
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
      throws AttributeNotFoundException, InvalidFileFormatException {

    String tileName = tryProperty(props,"Name");
    String tileImage = tryProperty(props,"Image");

    return new CardTileModel(tileName);
  }


  public List<PropertyTileModel> parsePropertyTiles (List<Property> propertyList){
    List<PropertyTileModel> result = new ArrayList<>();
    for (Property prop: propertyList){
      result.add(new PropertyTileModel(prop.getName(), prop, new ActionSequence()));
    }
    return result;
  }


}
