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
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.game_handling.commands.ActionSequence;

public class TileParser extends FolderParser{

  public static final String PARSE_TILE_METHOD_PREFIX = "parse";
  public static final String PARSE_TILE_METHOD_SUFFIX = "Tile";


  /**
   * Accesses the property folder, and calls to create Property object for each file.
   *
   * @param tileFolderPath
   * @return ArrayList of all Monopoly Properties
   * @throws AttributeNotFoundException
   */
  public ArrayList<TileModel> parseNonPropertyTiles(String tileFolderPath)
      throws AttributeNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {

    //TODO: get rid of need for substring
    tileFolderPath = tileFolderPath.substring(4);
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
      throws AttributeNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    Properties tileProperties = convertToPropertiesObject(propertyFile);
    String tileType = tryProperty(tileProperties,"Type");
    Method parseMethod = this.getClass().getMethod(PARSE_TILE_METHOD_PREFIX + tileType + PARSE_TILE_METHOD_SUFFIX,Properties.class);
    return (TileModel) parseMethod.invoke(this,tileProperties);
  }

  private TileModel parseActionTile(Properties props) throws AttributeNotFoundException {
    String tileName = tryProperty(props,"Name");
    int tileDescription = Integer.parseInt(tryProperty(props,"Description"));
    int propertyMortgageCost = Integer.parseInt(tryProperty(props,"Mortgage"));
    String tileImage = tryProperty(props,"Color").toLowerCase(Locale.ROOT);

    return null;
  }


/*
  Name=Free Parking
  Type=Action
      Description=
      Image=
          PassThroughActionSequence=[]
  LandOnActionSequence=[]

 */



  public List<PropertyTileModel> parsePropertyTiles (List<Property> propertyList){
    List<PropertyTileModel> result = new ArrayList<>();
    for (Property prop: propertyList){
      result.add(new PropertyTileModel(prop.getName(), prop, new ActionSequence()));
    }
    return result;
  }


}
