package ooga.model.game_handling.commands;

import java.util.ArrayList;
import java.util.List;
import ooga.display.communication.DisplayComm;
import ooga.model.data.player.Player;

/**
 * This class stores a sequence of commands and runs the sequence when prompted.
 *
 * @author William Convertino
 *
 * @since 0.0.1
 */
public class ActionSequence {

  //A list of the commands to be run.
  private List<Command> mySequence;

  DisplayComm displayComm;

  /**
   * Constructs a new ActionSequence and starts it with the specified sequence.
   *
   * @param mySequence the starting sequence.
   */
  public ActionSequence(List<Command> mySequence, DisplayComm displayComm) {
    this.mySequence = mySequence;
    this.displayComm = displayComm;
  }

  /**
   * Constructs an empty ActionSequence.
   */
  public ActionSequence() {
    this.mySequence = new ArrayList<>();
  }

  /**
   * Adds a new command to the sequence.
   *
   * @param command the command to add to the sequence.
   */
  public void add(Command command) {
    mySequence.add(command);
  }

  /**
   * Executes each element of the sequence in order.
   */
  public void execute(Player p) {
    try {
      for (Command command: mySequence) {
        command.execute(p);
      }
    } catch (Exception e) {
      displayComm.showException(e);
    }

  }

}
