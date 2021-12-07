package ooga.display.game_board.right;

import static ooga.display.communication.EventManager.EVENT_NAMES.ROLL;

import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ooga.display.communication.EventManager.EVENT_NAMES;
import ooga.display.communication.TMEvent;
import ooga.display.game_board.GameBoardDisplay;
import ooga.display.ui_tools.UIBuilder;
import ooga.model.data.gamedata.GameData;

/**
 * This is the right display element of the game display
 *
 * @author Aaric Han
 */
public class Right {

  private final GameBoardDisplay myGameBoardDisplay;
  private final VBox rightComponent;
  private final ResourceBundle myLanguage;
  private final UIBuilder myBuilder;
  private final Map<EVENT_NAMES, TMEvent> eventMap;
  private final GameData gameData;

  /**
   * The constructor for the right display element
   */
  public Right(GameBoardDisplay gameBoardDisplay, ResourceBundle language,
      Map<EVENT_NAMES, TMEvent> eventMap, GameData gameData) {
    myLanguage = language;
    myBuilder = new UIBuilder(myLanguage);
    myGameBoardDisplay = gameBoardDisplay;
    rightComponent = new VBox();
    rightComponent.setPrefWidth(200);
    this.eventMap = eventMap;
    this.gameData = gameData;
    makeRightComponent();
  }

  private void makeRightComponent() {
    Label playerLabel = new Label(gameData.getCurrentPlayer().getName());
    Button rollDiceButton = myBuilder.makeTextButton("RollDice", e -> rollDice());
    rightComponent.getChildren().add(playerLabel);
    rightComponent.getChildren().add(rollDiceButton);
    rightComponent.getChildren().add(new Label(""));
    rightComponent.getChildren().add(new VBox());
  }

  private void rollDice() {
    eventMap.get(ROLL).execute();
    int[] myRoll = gameData.getDie().diceResult();
    Label rolledVals = (Label) rightComponent.getChildren().get(2);
    if (myRoll.length == 2) {
      rolledVals.setText(myRoll[0] + " " + myRoll[1]);
    } else {
      rolledVals.setText(String.format("%s", myRoll[0]));
    }
    rightComponent.getChildren().set(2, rolledVals);
    myGameBoardDisplay.update();
    beginTurn();
  }

  private void beginTurn() {
    Button roll = (Button) rightComponent.getChildren().get(1);
    roll.setDisable(gameData.getMaxRolls() == gameData.getNumRolls());
    TurnChoices turnChoices = new TurnChoices(myGameBoardDisplay, myLanguage, myBuilder);
    rightComponent.getChildren().set(3, turnChoices.getMyTurnChoices());
  }

  /**
   * Ends turn and updates the right component's turn choices element
   */
  public void endTurn() {
    Button roll = (Button) rightComponent.getChildren().get(1);
    roll.setDisable(false);
    rightComponent.getChildren().set(3, new VBox());
    Label playerLabel = (Label) rightComponent.getChildren().get(0);
    playerLabel.setText(gameData.getCurrentPlayer().getName());
  }

  /**
   * Returns the right component VBox
   *
   * @return
   */
  public VBox getComponent() {
    return rightComponent;
  }
}
