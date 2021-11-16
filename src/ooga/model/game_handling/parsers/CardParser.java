package ooga.model.game_handling.parsers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import ooga.exceptions.AttributeNotFoundException;
import ooga.model.data.properties.Property;
import java.io.File;

/**
 * Parser class responsible for converting all Monopoly cards.
 *
 * @author Casey Goldstein
 *
 * @since 0.0.1
 */

public class CardParser extends Parser {

  /**
   * Creates CardParser
   */
  public CardParser(){

  }

  /**
   * Accesses the property folder, and calls to create Property object for each file.
   *
   * @param cardFolderPath
   * @return ArrayList of all Monopoly Properties
   * @throws AttributeNotFoundException
   */
  public ArrayList<Property> parseProperties(String cardFolderPath) throws AttributeNotFoundException {
    ArrayList<Property> result = new ArrayList<>();
    File [] filesList = getFileList(cardFolderPath);
    for (File file : filesList) {
      result.add(parseCardFile(file));
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
  public Property parseCardFile(File propertyFile) throws AttributeNotFoundException {

    Properties cardProperties = convertToPropertiesObject(propertyFile);

    String cardName = tryProperty(cardProperties,"Name");

    return new Property (propertyName,propertyCost,propertyRentCosts,propertyHouseCost,propertyNeighbors,propertyMortgageCost,propertyColor);
  }


}
