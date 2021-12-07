package ooga.util.parsers;


import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import ooga.display.communication.DisplayComm;
import ooga.exceptions.AttributeNotFoundException;
import ooga.exceptions.InvalidFileFormatException;
import ooga.model.data.cards.Card;
import ooga.model.game_handling.commands.ActionSequence;
import ooga.model.game_handling.commands.ActionSequenceExecutor;

/**
 * Parser class responsible for converting all Monopoly cards.
 *
 * @author Casey Goldstein
 * @since 0.0.1
 */

public class CardParser extends FolderParser {


  /**
   * Alternate CardParser constructor
   *
   * @param sequenceParser
   */
  public CardParser(ActionSequenceExecutor sequenceParser, DisplayComm displayComm) {
    super(sequenceParser, displayComm);
  }

  /**
   * Accesses a specific card folder (e.g Chance), and calls to create Card object for each file.
   *
   * @param cardFolderPath
   * @return ArrayList of all Monopoly Properties
   * @throws AttributeNotFoundException
   */
  public ArrayList<Card> parseCards(String cardFolderPath)
      throws AttributeNotFoundException, InvalidFileFormatException {
    ArrayList<Card> result = new ArrayList<>();
    File[] filesList = getFileList(cardFolderPath);
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
  public Card parseCardFile(File propertyFile)
      throws AttributeNotFoundException, InvalidFileFormatException {

    Properties cardProperties = convertToPropertiesObject(propertyFile);

    String cardName = tryProperty(cardProperties, "Name");
    String cardDescription = tryProperty(cardProperties, "Description");
    ActionSequence actions = parseActionSequence(tryProperty(cardProperties, "ActionSequence"));

    return new Card(cardName, cardDescription, actions, displayComm);

  }


}
