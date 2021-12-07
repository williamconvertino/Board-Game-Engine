package ooga.model.game_handling.commands;

import java.util.ArrayList;
import java.util.List;
import ooga.display.communication.DisplayComm;
import ooga.exceptions.InvalidFileFormatException;
import ooga.model.data.player.Player;

/**
 * This class stores a sequence of commands and runs the sequence when prompted.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class ActionSequence {

  //A list of the commands to be run.
  private final List<String> mySequence;

  //The action sequence parser with which the commands should be executed.
  private final ActionSequenceExecutor myParser;

  //The display communication module of the sequence.
  private final DisplayComm displayComm;

  /**
   * Constructs an empty ActionSequence.
   */
  public ActionSequence(ActionSequenceExecutor parser, DisplayComm dc) {
    this.mySequence = new ArrayList<>();
    this.myParser = parser;
    this.displayComm = dc;
  }

  /**
   * Adds a new command to the sequence.
   *
   * @param command the command to add to the sequence.
   */
  public void add(String command) {
    mySequence.add(command);
  }

  /**
   * Executes each element of the sequence in order.
   */
  public void execute(Player p) {
    try {
      for (String command : mySequence) {
        myParser.executeCommand(command);
      }
    } catch (Exception e) {
      displayComm.showException(new InvalidFileFormatException());
    }

  }

  /**
   * Indicated whether the sequence is empty.
   *
   * @return true if the sequence is empty.
   */
  public boolean isEmpty() {
    return this.mySequence.isEmpty() || (this.mySequence.size() == 1
        && this.mySequence.get(0).length() == 0);
  }

}
