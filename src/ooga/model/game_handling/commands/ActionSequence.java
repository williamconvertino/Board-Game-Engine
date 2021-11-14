package ooga.model.game_handling.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores a sequence of commands and runs the sequence when prompted.
 *
 * @author William Convertino
 *
 * @since 0.0.1
 */
public class ActionSequence {

  //A list of the commands to be run.
  private List<Runnable> mySequence;

  /**
   * Constructs a new ActionSequence and starts it with the specified sequence.
   *
   * @param mySequence the starting sequence.
   */
  public ActionSequence(List<Runnable> mySequence) {
    this.mySequence = mySequence;
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
  public void add(Runnable command) {
    mySequence.add(command);
  }

  /**
   * Executes each element of the sequence in order.
   */
  public void execute() {
    for (Runnable command: mySequence) {
      command.run();
    }
  }

}
