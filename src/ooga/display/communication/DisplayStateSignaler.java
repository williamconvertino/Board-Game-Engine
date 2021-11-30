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
    START_TURN,
    GO_TO_JAIL,
    ROLLING,
    TILE_CLICKED,
    LANDED,
    MOVING,
    DRAW_CARD,

    //Button prompts.
    READ_TO_ROLL,
    POST_BAIL,
    BUY_PROPERTY,
    MORTGAGE_PROPERTY,
    UNMORTGAGE_PROPERTY,
    BUY_HOUSE
  }

  /**
   * Constructor for the DisplayStateSignaler which stores a display manager
   * @param displayManager
   */
  public DisplayStateSignaler (DisplayManager displayManager) {
    myDisplayManager = displayManager;
  }

  /**
   * Signal Display with a state
   * @param s
   */
  public void signalDisplay (State s) {
    // ex: if s = buy property show buy prop button
    // myDisplayManager.signalState(s)
  }
}
