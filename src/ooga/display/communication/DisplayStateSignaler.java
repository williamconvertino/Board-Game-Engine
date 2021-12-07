package ooga.display.communication;

import ooga.display.DisplayManager;

/**
 * Class that signals to the display when states happen in the back end
 *
 * @author William Convertino
 * @author Aaric Han
 * @author Henry Huynh
 */
public class DisplayStateSignaler {

  DisplayManager myDisplayManager;

  public enum State {

    //Display prompts.
    PLAYER_WIN,
    GO_TO_JAIL,
    PAY_MONEY_TO_PLAYER,

    //Button Prompts
    READY_TO_ROLL,
    BUY_PROPERTY,
    BUY_HOUSES,
    SELL_HOUSES,
    MORTGAGE_PROPERTY

  }

  /**
   * Constructor for the DisplayStateSignaler which stores a display manager
   *
   * @param displayManager
   */
  public DisplayStateSignaler(DisplayManager displayManager) {
    myDisplayManager = displayManager;
  }

  /**
   * Signal Display with a state
   *
   * @param s
   */
  public void signalDisplay(State s) {
    if (s.equals(State.PLAYER_WIN)) {
      myDisplayManager.showVictoryScreen();
    }
  }
}
