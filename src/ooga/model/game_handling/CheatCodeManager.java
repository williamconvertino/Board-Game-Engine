package ooga.model.game_handling;

import java.lang.reflect.Method;
import java.util.Map;
import javafx.scene.input.KeyCode;
import ooga.model.data.cards.Card;
import ooga.model.data.deck.Deck;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.die.Die;
import ooga.model.die.DoublesDie;

/**
 * A class to store cheat codes and their desired effects.
 *
 * @author William Convertino
 */
public class CheatCodeManager {

  public static Map<KeyCode, String> CODE_MAP = Map.ofEntries(
      Map.entry(KeyCode.DIGIT1, "giveMoney"),
      Map.entry(KeyCode.DIGIT2, "bankrupt"),
      Map.entry(KeyCode.DIGIT3, "exodus"),
      Map.entry(KeyCode.DIGIT4, "setOwner"),
      Map.entry(KeyCode.DIGIT5, "buyAllProperties"),
      Map.entry(KeyCode.DIGIT6, "buyHouse"),
      Map.entry(KeyCode.DIGIT7, "executeCard"),
      Map.entry(KeyCode.DIGIT8, "jail"),
      Map.entry(KeyCode.DIGIT9, "winGame"),
      Map.entry(KeyCode.DIGIT0, "teleportToSelectedTile"),
      Map.entry(KeyCode.SEMICOLON, "forceEndTurn"),
      Map.entry(KeyCode.COMMA, "rollDoubles")
  );

  private final FunctionExecutor myFunctionExecutor;

  private final GameData gameData;

  private final TurnManager myTurnManager;

  private final Die doublesDie;

  public CheatCodeManager(TurnManager turnManager, FunctionExecutor myFunctionExecutor,
      GameData gameData) {
    this.myFunctionExecutor = myFunctionExecutor;
    this.gameData = gameData;
    this.myTurnManager = turnManager;
    doublesDie = new DoublesDie();
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
        System.out.println(cheatMethod.getName());
        cheatMethod.invoke(this);
      } catch (Exception e) {
      }
    }
  }

  /**
   * Gives the player $99,999.
   */
  public void giveMoney() {
    myFunctionExecutor.addMoney(gameData.getCurrentPlayer(), 9999999);
  }

  /**
   * Makes a player go REALLY negative.
   */
  public void exodus() {
    myFunctionExecutor.loseMoney(gameData.getCurrentPlayer(), 999999999);
  }

  /**
   * Makes a player lose all their money.
   */
  public void bankrupt() {
    gameData.getCurrentPlayer().setBalance(0);
  }


  /**
   * Makes the player win the game.
   */
  public void winGame() {
    for (Player p : gameData.getPlayers()) {
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
    }
  }

  /**
   * Sets the owner of the selected tile to the player.
   */
  public void setOwner() {
    try {
      ((PropertyTileModel) myTurnManager.getSelectedTile()).getProperty()
          .setOwner(gameData.getCurrentPlayer());
    } catch (Exception e) {
    }
  }

  /**
   * Buys all the properties on the board.
   */
  public void buyAllProperties() {
    for (TileModel tile : gameData.getBoard().getTiles()) {
      if (tile instanceof PropertyTileModel) {
        gameData.getCurrentPlayer().giveProperty(((PropertyTileModel) tile).getProperty());
        Property prop = ((PropertyTileModel) tile).getProperty();
        prop.setMonopoly();
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
   * Hard resets the turn if you press semicolon enough.
   */
  public void forceEndTurn() {
    numPress++;
    if (numPress > 4) {
      myTurnManager.endTurn();
      numPress = 0;
    }
  }

  /**
   * Sends the current player to jail.
   */
  public void jail() {
    myFunctionExecutor.goToJail(gameData.getCurrentPlayer());
  }

  /**
   * Rolls with a special die that always gets doubles.
   */
  public void rollDoubles() {
    myTurnManager.rollWithDie(doublesDie);
  }

}
