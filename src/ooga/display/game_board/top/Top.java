package ooga.display.game_board.top;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import ooga.display.DisplayManager;
import ooga.display.game_board.GameBoardDisplay;
import ooga.display.ui_tools.UIBuilder;

import java.util.ResourceBundle;

/**
 * This is the top display element of the game display
 *
 * @author Aaric Han
 * @author Henry Huynh
 */

public class Top {

  private HBox topComponent;
  private GameBoardDisplay myGameBoardDisplay;
  private DisplayManager myDisplayManager;
  private ResourceBundle myLanguage;
  private UIBuilder myBuilder;


  /**
   * The constructor for the top display element
   */
  public Top(GameBoardDisplay gameBoardDisplay, DisplayManager displayManager, ResourceBundle language) {
    myLanguage = language;
    myBuilder = new UIBuilder(myLanguage);
    myGameBoardDisplay = gameBoardDisplay;
    myDisplayManager = displayManager;
    topComponent = new HBox();
    makeGoHomeButton();
  }

  private void makeGoHomeButton() {
    Button homeButton = new Button();
    homeButton.setText(myLanguage.getString("GotoHome"));
    homeButton.setOnAction(e -> pressedHomeButton());
    topComponent.getChildren().add(homeButton);
  }

  private void pressedHomeButton() {
    myDisplayManager.goStartMenu();
  }

  /**
   * Gets the top component
   * @return topComponent
   */
  public HBox getTopComponent() {
    return topComponent;
  }
}
