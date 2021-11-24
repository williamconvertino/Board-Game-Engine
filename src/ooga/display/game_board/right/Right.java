package ooga.display.game_board.right;

import java.lang.reflect.Array;
import java.util.Arrays;
import javafx.event.Event;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ooga.display.DisplayManager;
import ooga.display.communication.DisplayStateSignaler.State;
import ooga.display.communication.EventManager;
import ooga.display.communication.EventManager.EVENT_NAMES;
import ooga.display.communication.TMEvent;
import ooga.display.game_board.GameBoardDisplay;
import ooga.display.ui_tools.UIBuilder;
import static ooga.display.communication.EventManager.EVENT_NAMES.*;

import java.util.ResourceBundle;
import ooga.model.data.gamedata.GameData;

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
  private Map<EVENT_NAMES, TMEvent> eventMap;
  private GameData gameData;
  /**
   * The constructor for the right display element
   */
  public Right(GameBoardDisplay gameBoardDisplay, DisplayManager displayManager, ResourceBundle language, Map<EVENT_NAMES, TMEvent> eventMap, GameData gameData) {
    myLanguage = language;
    myBuilder = new UIBuilder(myLanguage);
    myGameBoardDisplay = gameBoardDisplay;
    myDisplayManager = displayManager;
    rightComponent = new VBox();
    rightComponent.setPrefWidth(200);
    this.eventMap = eventMap;
    this.gameData = gameData;
    makeRightComponent();
  }

  private void makeRightComponent() {
    Label playerLabel = new Label(gameData.getCurrentPlayer().getName());
    Button rollDiceButton = myBuilder.makeButton("RollDice", e -> rollDice());
    rightComponent.getChildren().add(playerLabel);
    rightComponent.getChildren().add(rollDiceButton);
    rightComponent.getChildren().add(new Label(""));
    rightComponent.getChildren().add(new VBox());
  }


  //FIXME: Refactor to use state signaler
  private void rollDice() {
    eventMap.get(ROLL).execute();
    int[] myRoll = gameData.getDie().diceResult();
    Label rolledVals = (Label) rightComponent.getChildren().get(2);
    rolledVals.setText(myRoll[0] + " " + myRoll[1]);
    rightComponent.getChildren().set(2, rolledVals);
    myGameBoardDisplay.updatePlayerLocation();
    myGameBoardDisplay.updateLeftInfo();
    eventMap.get(END_TURN).execute();
    myGameBoardDisplay.updateRightInfo();
  }

  //FIXME: End turn implementation
  private void endTurn() {
  }

  /**
   * Update the right component
   */
  public void updateInfo() {
    Label playerLabel = (Label) rightComponent.getChildren().get(0);
    playerLabel.setText(gameData.getCurrentPlayer().getName());
    TurnChoices playerChoices = new TurnChoices(myGameBoardDisplay, myLanguage, myBuilder, eventMap, gameData);
    rightComponent.getChildren().set(3, playerChoices.getMyTurnChoices());
  }

  /**
   * Returns the right component VBox
   * @return
   */
  public VBox getComponent() {
    return rightComponent;
  }


}
