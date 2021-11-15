package ooga.display.game_board.right;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ooga.display.DisplayManager;
import ooga.display.game_board.GameBoardDisplay;

/**
 * This is the right display element of the game display
 *
 * @author Aaric Han
 */
public class Right {

  private GameBoardDisplay myGameBoardDisplay;
  private DisplayManager myDisplayManager;
  private VBox rightComponent;
  /**
   * The constructor for the top display element
   */
  public Right(GameBoardDisplay gameBoardDisplay, DisplayManager displayManager) {
    myGameBoardDisplay = gameBoardDisplay;
    myDisplayManager = displayManager;
    rightComponent = new VBox();
    makeRightComponent();
  }

  private void makeRightComponent() {
    Label playerLabel = new Label("Player 1");
    Button rollDiceButton = new Button("Roll Dice");
    rollDiceButton.setOnAction(e -> rollDice());

    rightComponent.getChildren().add(playerLabel);
    rightComponent.getChildren().add(rollDiceButton);
  }

  private void rollDice() {
    // TODO: Add event handler to roll dice
    System.out.println("Dice Roll Button clicked");
  }

  /**
   * Returns the right component VBox
   * @return
   */
  public VBox getComponent() {
    return rightComponent;
  }

}
