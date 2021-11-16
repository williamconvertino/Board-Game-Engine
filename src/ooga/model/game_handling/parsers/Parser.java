package ooga.model.game_handling.parsers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ooga.exceptions.AttributeNotFoundException;

public abstract class Parser {

  protected static final String DEFAULT_DATA_PACKAGE = "data/";

  protected File[] getFileList(String folderPath){
    File fileFolder = new File(DEFAULT_DATA_PACKAGE + folderPath);
    return fileFolder.listFiles();
  }

  //takes file and converts to Java property object. Shows error message if file doesn't work.
  protected Properties convertToPropertiesObject (File propertiesFile){
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
  protected String tryProperty(Properties prop, String key) throws AttributeNotFoundException {
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
