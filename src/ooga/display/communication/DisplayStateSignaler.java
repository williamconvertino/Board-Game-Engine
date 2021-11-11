package ooga.display.communication;

import ooga.display.DisplayManager;

public class DisplayStateSignaler {

  DisplayManager myDisplayManager;

  public enum States {

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
    BUY_HOUSE

  }

  public DisplayStateSignaler (DisplayManager displayManager) {
    myDisplayManager = displayManager;
  }

  public void signalDisplay (States s) {
    myDisplayManager.signalState(s);
  }

}
