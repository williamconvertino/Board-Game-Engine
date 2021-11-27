package ooga.model.game_handling.parsers;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import ooga.exceptions.AttributeNotFoundException;
import ooga.model.data.properties.Property;
import java.io.File;

/**
 * Parser class responsible for converting all Monopoly properties.
 *
 * @author Casey Goldstein
 *
 * @since 0.0.1
 */

public class PropertyParser extends FolderParser {

  public static final String PARSE_METHOD_PREFIX = "parse";
  public static final String PARSE_METHOD_SUFFIX = "Property";

  /**
   * Creates PropertyParser
   */
  public PropertyParser(){

  }

  /**
   * Accesses the property folder, and calls to create Property object for each file.
   *
   * @param propertyFolderPath
   * @return ArrayList of all Monopoly Properties
   * @throws AttributeNotFoundException
   */
  public ArrayList<Property> parseProperties(String propertyFolderPath)
      throws AttributeNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {

    //TODO: get rid of need for substring
    propertyFolderPath = propertyFolderPath.substring(4);
    ArrayList<Property> result = new ArrayList<>();
    File [] filesList = getFileList(propertyFolderPath);
    for (File file : filesList) {
      result.add(parsePropertyFile(file));
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
  public Property parsePropertyFile(File propertyFile)
      throws AttributeNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    Properties propertyProperties = convertToPropertiesObject(propertyFile);
    String propertyType = tryProperty(propertyProperties,"Type");
    //Class[] argTypes = new Class[] { Properties.class };
    Method parseMethod = this.getClass().getMethod(PARSE_METHOD_PREFIX + propertyType + PARSE_METHOD_SUFFIX,Properties.class);
    return (Property) parseMethod.invoke(this,propertyProperties);

  }

  public Property parseRailroadProperty(Properties props) throws AttributeNotFoundException {
    return parseSpecialProperty(props,"Railroads");
  }

  public Property parseUtilitiesProperty(Properties props) throws AttributeNotFoundException {
    return parseSpecialProperty(props,"Utilities");
  }

  public Property parseSpecialProperty(Properties props, String type)
      throws AttributeNotFoundException {
    String propertyName = tryProperty(props,"Name");
    int propertyCost = Integer.parseInt(tryProperty(props,"Cost"));
    int[] propertyRentCosts = StringArrayToIntArray(tryProperty(props,"RentCost").split(","));
    ArrayList<String> propertyNeighbors = new ArrayList(Arrays.asList(tryProperty(props,"Neighbors").split(",")));
    int propertyMortgageCost = Integer.parseInt(tryProperty(props,"Mortgage"));
    String propertyImage = tryProperty(props,"Image");
    return new Property (propertyName,type,propertyCost,propertyRentCosts,propertyNeighbors,propertyMortgageCost,propertyImage);
  }

  public Property parseRegularProperty(Properties props) throws AttributeNotFoundException {
    String propertyName = tryProperty(props,"Name");
    int propertyCost = Integer.parseInt(tryProperty(props,"Cost"));
    int[] propertyRentCosts = StringArrayToIntArray(tryProperty(props,"RentCost").split(","));
    int propertyHouseCost = Integer.parseInt(tryProperty(props,"HouseCost"));
    ArrayList<String> propertyNeighbors = new ArrayList(Arrays.asList(tryProperty(props,"Neighbors").split(",")));
    int propertyMortgageCost = Integer.parseInt(tryProperty(props,"Mortgage"));
    String propertyColor = tryProperty(props,"Color").toLowerCase(Locale.ROOT);

    return new Property (propertyName,"Regular",propertyCost,propertyRentCosts,propertyHouseCost,propertyNeighbors,propertyMortgageCost,propertyColor);
  }


}
