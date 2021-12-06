package ooga;

import java.util.List;
import ooga.display.communication.DisplayComm;
import ooga.display.communication.DisplayStateSignaler;
import ooga.display.communication.EventManager;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;
import ooga.model.data.player.PlayerManager;
import ooga.model.game_handling.FunctionExecutor;
import ooga.model.game_handling.TurnManager;
import ooga.model.game_handling.board_manager.BoardManager;
import ooga.model.game_initialization.GameDataInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class OriginalTest {

  public GameData gameData;

  public DisplayComm myDisplayComm;

  public FunctionExecutor functionExecutor;

  public EventManager eventManager;

  public TurnManager turnManager;

  public BoardManager board;

  public PlayerManager players;

  public Player p1;
  public Player p2;
  public Player p3;
  public Player p4;


  @BeforeEach
  public void initialize() {

    myDisplayComm = new DisplayComm(null) {

      @Override
      public void showException(Exception e) {
        //doNothing
      }
      @Override
      public void signalState(DisplayStateSignaler.State state) {
        //doNothing
      }
    };
    try {
      String variationName = GameManager.DEFAULT_VARIATION_NAME;
      gameData = (new GameDataInitializer()).generateGameData(variationName, myDisplayComm);
      functionExecutor = new FunctionExecutor();
      functionExecutor.initializeWithGameValues(gameData, gameData.getDie(), myDisplayComm);
      turnManager = new TurnManager(gameData, functionExecutor, myDisplayComm);
      eventManager = new EventManager(turnManager);

      List<Player> playerList = players.getPlayers();
      p1 = playerList.get(0);
      p2 = playerList.get(1);
      p3 = playerList.get(2);
      p4 = playerList.get(3);

    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }
  }

  @Test
  void testInitialization () {
    //System.out.println(gameData.getBoard().getTiles());
  }


}
