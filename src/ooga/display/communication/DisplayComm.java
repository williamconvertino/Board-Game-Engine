package ooga.display.communication;

import ooga.display.DisplayManager;
import ooga.display.communication.DisplayStateSignaler.State;

/**
 * The display communication which shows exceptions
 *
 * @author William Convertino
 */
public class DisplayComm {

  private ExceptionHandler myExceptionHandler;
  private DisplayStateSignaler signaler;

  /**
   * The constructor to make a exception handler in DisplayComm
   */
  public DisplayComm(DisplayManager dm) {
    this.myExceptionHandler = new ExceptionHandler();
    this.signaler = new DisplayStateSignaler(dm);
  }

  /**
   * Handles showing the exception passed in
   * @param e
   */
  public void showException(Exception e) {
    myExceptionHandler.showException(e);
  }

  /**
   * Signals the display that a new state has been reached.
   *
   * @param state the state that has been reached.
   */
  public void signalState(State state) {
    signaler.signalDisplay(state);
  }

}
