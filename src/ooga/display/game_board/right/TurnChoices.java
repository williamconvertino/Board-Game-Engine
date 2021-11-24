package ooga.display.game_board.right;

import java.sql.SQLOutput;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import ooga.display.communication.EventManager.EVENT_NAMES;
import ooga.display.communication.TMEvent;
import ooga.display.game_board.GameBoardDisplay;
import ooga.display.ui_tools.UIBuilder;
import ooga.model.data.gamedata.GameData;

/**
 * Right Panel add-on turn choices
 *
 * @author Aaric Han
 */
public class TurnChoices {

  private VBox myTurnChoices;
  private ResourceBundle myLanguage;
  private UIBuilder myBuilder;
  private Map<EVENT_NAMES, TMEvent> myEventMap;
  private GameData myGameData;
  private GameBoardDisplay myGameBoardDisplay;

  /**
   * Constructor for the turn choices
   */
  public TurnChoices(GameBoardDisplay gameBoardDisplay, ResourceBundle language, UIBuilder uiBuilder, Map<EVENT_NAMES, TMEvent> eventMap, GameData gameData) {
    myLanguage = language;
    myBuilder = uiBuilder;
    myEventMap = eventMap;
    myGameData = gameData;
    myGameBoardDisplay = gameBoardDisplay;
    myTurnChoices = new VBox();
    makeTurnButtons();
  }

  private void makeTurnButtons() {
    myTurnChoices = new VBox();
    buyProperty();
    endTurn();
  }
  /**
   * Make buy property button
   */
  private void buyProperty() {
    myTurnChoices.getChildren().add(myBuilder.makeButton("BuyProperty", e->myGameBoardDisplay.buyProp()));
  }

  /**
   * Make end turn button
   */
  private void endTurn() {
    myTurnChoices.getChildren().add(myBuilder.makeButton("EndTurn", e->myGameBoardDisplay.endTurn()));
  }

  /**
   * Gets the turnChoices
   *
   * @return myTurnChoices VBox
   */
  public VBox getMyTurnChoices() {
    return myTurnChoices;
  }
}
