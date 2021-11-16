package ooga.model.game_handling.parsers;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ooga.exceptions.AttributeNotFoundException;
import ooga.model.data.properties.Property;
import java.io.File;
import java.io.IOException;
import org.w3c.dom.Attr;

/**
 * Parser class responsible for converting all Monopoly properties, cards into Property and Card objects.
 *
 * @author Casey Goldstein
 *
 * @since 0.0.1
 */

public class ConfigParser {

  private static final String DEFAULT_DATA_PACKAGE = "data/";

  /**
   * Creates ConfigParser
   */
  public ConfigParser(){

  }

  /**
   * Accesses the property folder, and calls to create Property object for each file.
   *
   * @param propertyFolderPath
   * @return ArrayList of all Monopoly Properties
   * @throws AttributeNotFoundException
   */
  public ArrayList<Property> parseProperties(String propertyFolderPath) throws AttributeNotFoundException {
    ArrayList<Property> result = new ArrayList<>();
    File propertyFolder = new File(DEFAULT_DATA_PACKAGE + propertyFolderPath);
    File filesList[] = propertyFolder.listFiles();
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
  public Property parsePropertyFile(File propertyFile) throws AttributeNotFoundException {

    Properties propertyProperties = convertToPropertiesObject(propertyFile);

    String propertyName = tryProperty(propertyProperties,"Name");
    int propertyCost = Integer.parseInt(tryProperty(propertyProperties,"Cost"));
    int[] propertyRentCosts = StringArrayToIntArray(tryProperty(propertyProperties,"RentCost").split(","));
    int propertyHouseCost = Integer.parseInt(tryProperty(propertyProperties,"HouseCost"));
    ArrayList<String> propertyNeighbors = new ArrayList(Arrays.asList(tryProperty(propertyProperties,"Neighbors").split(",")));
    int propertyMortgageCost = Integer.parseInt(tryProperty(propertyProperties,"Mortgage"));
    String propertyColor = tryProperty(propertyProperties,"Color").toLowerCase(Locale.ROOT);

    return new Property (propertyName,propertyCost,propertyRentCosts,propertyHouseCost,propertyNeighbors,propertyMortgageCost,propertyColor);
  }

  //takes file and converts to Java property object. Shows error message if file doesn't work.
  private Properties convertToPropertiesObject (File propertiesFile){
    try {
      FileReader propertiesReader = new FileReader(propertiesFile);
      Properties properties = new Properties();
      properties.load(propertiesReader);
      return properties;
    }
    catch (IOException e){
      Alert errorAlert = new Alert(AlertType.ERROR);
      errorAlert.setHeaderText("Incorrect Format");
      errorAlert.setContentText("Please check the format for file at path: " + propertiesFile);
      errorAlert.showAndWait();
      return null;
    }
  }

  //tries to access the value for a given key in properties object. If null, throws exception.
  private String tryProperty(Properties prop, String key) throws AttributeNotFoundException {
    if (prop.getProperty(key) != null){
      return prop.getProperty(key);
    }
    else{
      throw new AttributeNotFoundException(key);
    }
  }

  //code from: https://stackoverflow.com/questions/18838781/converting-string-array-to-an-integer-array
  public int[] StringArrayToIntArray(String[] stringArray) {
    return Arrays.stream(stringArray).mapToInt(Integer::parseInt).toArray();
  }


}
