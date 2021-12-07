package ooga.display.communication;

import ooga.display.DisplayManager;
import ooga.display.communication.DisplayStateSignaler.State;
import ooga.model.data.cards.Card;

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

  /**
   *  Displays the specified card.
   *
   * @param card the card to display.
   */
  public void displayCard(Card card) {
    //Todo: replace with display
    System.out.println(String.format("Card drawn: [%s]", card.getDescription()));
  }

}
