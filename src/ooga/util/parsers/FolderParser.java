package ooga.util.parsers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ooga.display.communication.DisplayComm;
import ooga.exceptions.AttributeNotFoundException;
import ooga.exceptions.InvalidFileFormatException;
import ooga.model.game_handling.commands.ActionSequence;
import ooga.model.game_handling.commands.ActionSequenceExecutor;

/**
 * Abstract Parser class used by PropertyParser and CardParser to parse multiple files in folder
 *
 * @author Casey Goldstein
 * @since 0.0.1
 */
public abstract class FolderParser {

  protected static final String DEFAULT_DATA_PACKAGE = "data/";
  protected static ActionSequenceExecutor actionSequenceParser;
  protected DisplayComm displayComm;

  /**
   * Super constructor for child classes to call
   *
   * @param sequenceParser
   */
  public FolderParser(ActionSequenceExecutor sequenceParser, DisplayComm displayComm) {
    actionSequenceParser = sequenceParser;
    this.displayComm = displayComm;
  }

  /**
   * Default constructor for child classes
   */
  public FolderParser() {
  }

  //returns list of files in a given folder
  protected File[] getFileList(String folderPath) {
    File fileFolder = new File(DEFAULT_DATA_PACKAGE + folderPath);
    return fileFolder.listFiles();
  }

  //takes file and converts to Java property object. Shows error message if file doesn't work.
  protected Properties convertToPropertiesObject(File propertiesFile) {
    try {
      FileReader propertiesReader = new FileReader(propertiesFile);
      Properties properties = new Properties();
      properties.load(propertiesReader);
      return properties;
    } catch (IOException e) {
      Alert errorAlert = new Alert(AlertType.ERROR);
      errorAlert.setHeaderText("Incorrect Format");
      errorAlert.setContentText("Please check the format for file at path: " + propertiesFile);
      errorAlert.showAndWait();
      return null;
    }
  }

  //tries to access the value for a given key in properties object. If null, throws exception.
  protected String tryProperty(Properties prop, String key) throws AttributeNotFoundException {
    if (prop.getProperty(key) != null) {
      return prop.getProperty(key);
    } else {
      System.out.println("missing key: " + key);
      throw new AttributeNotFoundException(key);
    }
  }

  //takes a list of actions as a string and returns Action Sequence object.
  protected ActionSequence parseActionSequence(String sequenceText)
      throws InvalidFileFormatException {
    ActionSequence result = new ActionSequence(actionSequenceParser, displayComm);
    String[] sequenceArray = sequenceText.split(",");
    for (String action : sequenceArray) {
      action = action.substring(1, action.length() - 1);
      result.add(action);
    }
    return result;
  }

  //code from: https://stackoverflow.com/questions/18838781/converting-string-array-to-an-integer-array
  public int[] StringArrayToIntArray(String[] stringArray) {
    return Arrays.stream(stringArray).mapToInt(Integer::parseInt).toArray();
  }

}
