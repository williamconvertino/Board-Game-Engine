package ooga.model.game_handling.parsers;


import java.util.ArrayList;
import java.util.Properties;
import ooga.exceptions.AttributeNotFoundException;
import ooga.model.data.properties.Property;
import java.io.File;
import ooga.model.game_handling.commands.ActionSequence;
import ooga.model.game_handling.commands.ActionSequenceParser;

/**
 * Parser class responsible for converting all Monopoly cards.
 *
 * @author Casey Goldstein
 *
 * @since 0.0.1
 */

@Deprecated
public class CardParser extends FolderParser {

  private ActionSequenceParser myActionSequenceParser;

  /**
   * Creates CardParser
   */
  public CardParser(){
    //ActionSequenceParser myActionSequenceParser = new ActionSequenceParser();
  }

  /**
   * Accesses a specific card folder (e.g Chance), and calls to create Card object for each file.
   *
   * @param cardFolderPath
   * @return ArrayList of all Monopoly Properties
   * @throws AttributeNotFoundException
   */
  public ArrayList<Property> parseCards(String cardFolderPath) throws AttributeNotFoundException {
    ArrayList<Property> result = new ArrayList<>();
    File [] filesList = getFileList(cardFolderPath);
    for (File file : filesList) {
      result.add(parseCardFile(file));
    }
    return result;
  }

  /**
   * Takes .card file and creates Card object, throwing errors if missing information.
   *
   * @param propertyFile
   * @return
   * @throws AttributeNotFoundException
   */
  public Property parseCardFile(File propertyFile) throws AttributeNotFoundException {

    Properties cardProperties = convertToPropertiesObject(propertyFile);

    String cardName = tryProperty(cardProperties,"Name");
    String cardDescription = tryProperty(cardProperties,"Description");
    ActionSequence actions = new ActionSequence();
    String[] actionSequenceText = tryProperty(cardProperties,"ActionSequence").split(",");
    for (String action: actionSequenceText){
      action = action.substring(1,-1);
      System.out.println(action);
    }


    return null;
  }


}
