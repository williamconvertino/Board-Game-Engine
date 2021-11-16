package ooga.model.game_handling;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ooga.model.data.properties.Property;
import java.io.File;
import java.io.IOException;

/**
 * Parser class responsible for converting all Monopoly properties, cards into Property and Card objects.
 *
 * @author Casey Goldstein
 *
 * @since 0.0.1
 */

public class ConfigParser {

  private static final String DEFAULT_DATA_PACKAGE = "data/";
  private String[] propertyAttributes;


  public ConfigParser(){
    propertyAttributes = new String[] {"Name","Cost","RentCost","MaxHouses","Neighbors","Mortgage,Color"};
  }

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

  public Property parsePropertyFile(File propertyFile) throws IOException {

    Properties propertyProperties = convertToPropertiesObject(propertyFile);
    String propertyName = propertyProperties.getProperty("Name");
    int propertyCost = Integer.parseInt(propertyProperties.getProperty("Cost"));
    int[] propertyRentCosts = StringArrayToIntArray(propertyProperties.getProperty("RentCost").split(","));
    int propertyHouseCost = Integer.parseInt(propertyProperties.getProperty("HouseCost"));
    ArrayList<String> propertyNeighbors = new ArrayList(Arrays.asList(propertyProperties.getProperty("Neighbors").split(",")));
    int propertyMortgageCost = Integer.parseInt(propertyProperties.getProperty("Mortgage"));
    String propertyColor = propertyProperties.getProperty("Color").toLowerCase(Locale.ROOT);

    return new Property (propertyName,propertyCost,propertyRentCosts,propertyHouseCost,propertyNeighbors,propertyMortgageCost,propertyColor);
  }

  public ArrayList<Property> parseProperties(String propertyFolderPath) throws IOException {
    ArrayList<Property> result = new ArrayList<>();
    File propertyFolder = new File(DEFAULT_DATA_PACKAGE + propertyFolderPath);
    File filesList[] = propertyFolder.listFiles();
    for (File file : filesList) {
      result.add(parsePropertyFile(file));
    }
    return result;
  }

  //code from: https://stackoverflow.com/questions/18838781/converting-string-array-to-an-integer-array
  public int[] StringArrayToIntArray(String[] stringArray) {
    return Arrays.stream(stringArray).mapToInt(Integer::parseInt).toArray();
  }


}
