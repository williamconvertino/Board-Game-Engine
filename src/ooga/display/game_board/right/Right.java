package ooga.display.game_board.right;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ooga.display.DisplayManager;
import ooga.display.game_board.GameBoardDisplay;
import ooga.display.ui_tools.UIBuilder;

import java.util.ResourceBundle;

/**
 * This is the right display element of the game display
 *
 * @author Aaric Han
 */
public class Right {

  private GameBoardDisplay myGameBoardDisplay;
  private DisplayManager myDisplayManager;
  private VBox rightComponent;
  private ResourceBundle myLanguage;
  private UIBuilder myBuilder;
  /**
   * The constructor for the top display element
   */
  public Right(GameBoardDisplay gameBoardDisplay, DisplayManager displayManager, ResourceBundle language) {
    myLanguage = language;
    myBuilder = new UIBuilder(myLanguage);
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
    rightComponent.getChildren().add(new Label(""));
  }

  //FIXME: Hook up thru backend later
  private void rollDice() {
    ArrayList<Integer> returned_rolls = myGameBoardDisplay.rollDice();
    Label rolled_vals = (Label) rightComponent.getChildren().get(2);
    rolled_vals.setText(returned_rolls.get(0).toString() + " " + returned_rolls.get(1).toString());
    rightComponent.getChildren().set(2, rolled_vals);
  }

  /**
   * Returns the right component VBox
   * @return
   */
  public VBox getComponent() {
    return rightComponent;
  }

}
