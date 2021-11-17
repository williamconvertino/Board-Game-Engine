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
import ooga.display.DisplayManager;
import ooga.display.communication.DisplayStateSignaler.State;
import ooga.display.communication.EventManager;
import ooga.display.communication.EventManager.EVENT_NAMES;
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
  private Map<EVENT_NAMES, EventHandler> eventMap;
  private GameData gameData;
  /**
   * The constructor for the top display element
   */
  public Right(GameBoardDisplay gameBoardDisplay, DisplayManager displayManager, ResourceBundle language, Map<EVENT_NAMES, EventHandler> eventMap, GameData gameData) {
    myLanguage = language;
    myBuilder = new UIBuilder(myLanguage);
    myGameBoardDisplay = gameBoardDisplay;
    myDisplayManager = displayManager;
    rightComponent = new VBox();
    this.eventMap = eventMap;
    makeRightComponent();
    this.gameData = gameData;
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
    eventMap.get(ROLL).handle(null);
    //ArrayList<Integer> returned_rolls = myGameBoardDisplay.rollDice();
    int[] myRoll = gameData.getDie().diceResult();
    System.out.println(myRoll);
    Label rolled_vals = (Label) rightComponent.getChildren().get(2);
    rolled_vals.setText(myRoll[0] + " " + myRoll[1]);
    rightComponent.getChildren().set(2, rolled_vals);
    myGameBoardDisplay.updatePlayerLocation();
    //eventMap.get(START_TURN).handle(null);
  }

  /**
   * Returns the right component VBox
   * @return
   */
  public VBox getComponent() {
    return rightComponent;
  }


}
