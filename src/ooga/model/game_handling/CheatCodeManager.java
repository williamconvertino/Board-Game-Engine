package ooga.model.game_handling.turn_manager;

import java.util.Map;
import javafx.scene.input.KeyCode;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.game_handling.FunctionExecutor;

/**
 * A class to store cheat codes and their desired effects.
 *
 * @author William Convertino
 */
public class CheatCodeManager {

  public static Map<KeyCode, Code> CODE_MAP =
      Map.of(
          KeyCode.DIGIT1, Code.GIVE_MONEY,
          KeyCode.DIGIT2, Code.WIN_GAME,
          KeyCode.DIGIT3, Code.TELEPORT,
          KeyCode.DIGIT4, Code.SET_OWNER,
          KeyCode.DIGIT5, Code.BUY_ALL_PROPERTIES
      );

  public enum Code {
    GIVE_MONEY,
    WIN_GAME,
    TELEPORT,
    SET_OWNER,
    BUY_ALL_PROPERTIES,
  }

  private FunctionExecutor myFunctionExecutor;

  private GameData gameData;

  private TurnManager myTurnManager;

  public CheatCodeManager (TurnManager turnManager, FunctionExecutor myFunctionExecutor, GameData gameData) {
    this.myFunctionExecutor = myFunctionExecutor;
    this.gameData = gameData;
    this.myTurnManager = turnManager;
  }

  /**
   * Executes the specified cheat code.
   *
   * @param myCode the code to execute.
   */
  public void executeCheatCode(Code myCode) {
    switch (myCode) {

      case GIVE_MONEY -> myFunctionExecutor.addMoney(gameData.getCurrentPlayer(), 99999);

      case WIN_GAME -> {
        for (Player p: gameData.getPlayers()) {
          if (p != gameData.getCurrentPlayer()) {
            p.setActiveStatus(false);
          }
        }
        myTurnManager.endTurn();
      }

      case TELEPORT ->  {
        try {
          myFunctionExecutor.movePlayerToTile(gameData.getCurrentPlayer(), myTurnManager.getSelectedTile().getName());
        } catch (Exception e) {}
      }

      case SET_OWNER -> {
        try {
          ((PropertyTileModel)myTurnManager.getSelectedTile()).getProperty().setOwner(gameData.getCurrentPlayer());
        } catch (Exception e) {}
      }

      case BUY_ALL_PROPERTIES -> {

        for (TileModel tile: gameData.getBoard().getTiles()) {
          if (tile instanceof PropertyTileModel) {
            ((PropertyTileModel)tile).getProperty().setOwner(gameData.getCurrentPlayer());
          }
        }
      }

    }
  }

}
