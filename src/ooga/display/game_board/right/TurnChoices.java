package ooga.display.game_board.right;

import java.util.ResourceBundle;
import javafx.scene.layout.VBox;
import ooga.display.game_board.GameBoardDisplay;
import ooga.display.ui_tools.UIBuilder;

/**
 * Right Panel add-on turn choices
 *
 * @author Aaric Han
 */
public class TurnChoices {

  private VBox myTurnChoices;
  private final ResourceBundle myLanguage;
  private final UIBuilder myBuilder;
  private final GameBoardDisplay myGameBoardDisplay;

  /**
   * Constructor for the turn choices
   */
  public TurnChoices(GameBoardDisplay gameBoardDisplay, ResourceBundle language,
      UIBuilder uiBuilder) {
    myLanguage = language;
    myBuilder = uiBuilder;
    myGameBoardDisplay = gameBoardDisplay;
    myTurnChoices = new VBox();
    makeTurnButtons();
  }

  private void makeTurnButtons() {
    myTurnChoices = new VBox();
    buyProperty();
    buyHouse();
    endTurn();
  }

  /**
   * Make buy property button
   */
  private void buyProperty() {
    myTurnChoices.getChildren()
        .add(myBuilder.makeTextButton("BuyProperty", e -> myGameBoardDisplay.buyProp()));
  }

  /**
   * Make end turn button
   */
  private void endTurn() {
    myTurnChoices.getChildren()
        .add(myBuilder.makeTextButton("EndTurn", e -> myGameBoardDisplay.endTurn()));
  }

  /**
   * Make buy house button
   */
  private void buyHouse() {
    myTurnChoices.getChildren()
        .add(myBuilder.makeTextButton("BuyHouse", e -> myGameBoardDisplay.buyHouse()));
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
