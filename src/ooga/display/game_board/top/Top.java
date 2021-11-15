package ooga.display.game_board.top;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import ooga.display.DisplayManager;
import ooga.display.game_board.GameBoardDisplay;

public class Top {
  /**
   * This is the top display element of the game display
   *
   * @author Aaric Han
   */

  private HBox topComponent;
  private GameBoardDisplay myGameBoardDisplay;
  private DisplayManager myDisplayManager;

  /**
   * The constructor for the top display element
   */
  public Top(GameBoardDisplay gameBoardDisplay, DisplayManager displayManager) {
    myGameBoardDisplay = gameBoardDisplay;
    myDisplayManager = displayManager;
    topComponent = new HBox();
    makeGoHomeButton();

  }

  private void makeGoHomeButton() {
    Button homeButton = new Button();
    homeButton.setText("Home");
    homeButton.setOnAction(e -> pressed_homeButton());
    topComponent.getChildren().add(homeButton);
  }

  private void pressed_homeButton() {
    myDisplayManager.goStartMenu();
  }

  public HBox getTopComponent() {
    return topComponent;
  }
}
