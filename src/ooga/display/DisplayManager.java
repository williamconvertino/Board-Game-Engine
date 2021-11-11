package ooga.display;

import javafx.stage.Stage;
import ooga.display.communication.DisplayStateSignaler;
import ooga.display.communication.EventManager;
import ooga.display.game_board.GameBoardDisplay;
import ooga.model.data.player.Player;

/**
 * This class manages the display elements of the program.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class DisplayManager {

  private Display currDisplay;
  /**
   * Default constructor
   */
  public DisplayManager(Stage stage) {
    currDisplay = new GameBoardDisplay(stage, this);
    displayElement();
  }

  private void displayElement() {

  }
}