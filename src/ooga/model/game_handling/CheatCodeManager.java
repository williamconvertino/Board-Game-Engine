package ooga.model.game_handling;

import java.lang.reflect.Method;
import java.util.Map;
import javafx.scene.input.KeyCode;
import ooga.model.data.cards.Card;
import ooga.model.data.deck.Deck;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.game_handling.FunctionExecutor;
import ooga.model.game_handling.TurnManager;

/**
 * A class to store cheat codes and their desired effects.
 *
 * @author William Convertino
 */
public class CheatCodeManager {

  public static Map<KeyCode, String> CODE_MAP =
      Map.of(
          KeyCode.DIGIT1, "giveMoney",
          KeyCode.DIGIT2, "winGame",
          KeyCode.DIGIT3, "teleportToSelectedTile",
          KeyCode.DIGIT4, "setOwner",
          KeyCode.DIGIT5, "buyAllProperties",
          KeyCode.DIGIT6, "executeCard",
          KeyCode.SEMICOLON, "bugggged"
      );

  private FunctionExecutor myFunctionExecutor;

  private GameData gameData;

  private TurnManager myTurnManager;

  public CheatCodeManager (TurnManager turnManager, FunctionExecutor myFunctionExecutor, GameData gameData) {
    this.myFunctionExecutor = myFunctionExecutor;
    this.gameData = gameData;
    this.myTurnManager = turnManager;
  }

  /**
   * Executes the cheat with the specified keycode.
   *
   * @param code the keycode of the desired cheatcode.
   */
  public void executeCheatCode(KeyCode code) {
    if (CODE_MAP.containsKey(code)) {
      try {
        Method cheatMethod = getClass().getMethod(CODE_MAP.get(code));
        cheatMethod.invoke(this);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Gives the player $99,999.
   */
  public void giveMoney() {
    myFunctionExecutor.addMoney(gameData.getCurrentPlayer(), 99999);
  }

  /**
   * Makes the player win the game.
   */
  public void winGame(){
    for (Player p: gameData.getPlayers()) {
      if (p != gameData.getCurrentPlayer()) {
        p.setActiveStatus(false);
      }
    }
    myTurnManager.endTurn();
  }

  /**
   * Teleports the player to the selected tile.
   */
   public void teleportToSelectedTile() {
     try {
       myFunctionExecutor.movePlayerToTile(gameData.getCurrentPlayer(),
           myTurnManager.getSelectedTile().getName());
     } catch (Exception e) {
       e.printStackTrace();
     }
   }

  /**
   * Sets the owner of the selected tile to the player.
   */
  public void setOwner() {
     try {
       ((PropertyTileModel)myTurnManager.getSelectedTile()).getProperty().setOwner(gameData.getCurrentPlayer());
     } catch (Exception e) {
       e.printStackTrace();
     }
  }

  /**
   * Buys all the properties on the board.
   */
  public void buyAllProperties() {
    for (TileModel tile: gameData.getBoard().getTiles()) {
      if (tile instanceof PropertyTileModel) {
        ((PropertyTileModel)tile).getProperty().setOwner(gameData.getCurrentPlayer());
      }
    }
  }

  /**
   * Selects a random card to execute.
   */
  public void executeCard() {
    Deck myDeck = gameData.getDecks().getRandomDeck();
    Card myCard = myDeck.getRandomCard();
    myCard.execute(gameData.getCurrentPlayer());
  }

  private int numPress = 0;

  /**
   *  Hard resets the turn if you press semicolon enough.
   */
  public void bugggged() {
    numPress++;
    if(numPress > 4) {
      myTurnManager.endTurn();
      numPress = 0;
    }
  }

}
