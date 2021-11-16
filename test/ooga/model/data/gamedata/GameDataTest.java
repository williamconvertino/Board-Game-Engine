package ooga.model.data.gamedata;

import java.util.ArrayList;
import java.util.List;
import ooga.model.data.player.OriginalPlayerManager;
import ooga.model.data.player.Player;
import ooga.model.data.player.PlayerManager;
import ooga.model.data.tilemodels.EmptyTileModel;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.die.OriginalDice;
import ooga.model.game_handling.board_manager.BoardManager;
import ooga.model.game_handling.board_manager.OriginalBoardManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameDataTest {

  List<Player> myPlayers;
  Player p1 = new Player("p1");
  Player p2 = new Player("p2");
  Player p3 = new Player("p3");

  List<TileModel> myTiles;
  TileModel t1 = new EmptyTileModel("t1");
  TileModel t2 = new EmptyTileModel("t2");
  TileModel t3 = new EmptyTileModel("t3");


  PlayerManager playerManager;
  BoardManager boardManager;

  GameData gameData;

  @BeforeEach
  void reset() {
    myPlayers = new ArrayList<>();
    myPlayers.add(p1);
    myPlayers.add(p2);
    myPlayers.add(p3);
    playerManager = new OriginalPlayerManager(myPlayers);

    myTiles = new ArrayList<>();
    myTiles.add(t1);
    myTiles.add(t2);
    myTiles.add(t3);

    boardManager = new OriginalBoardManager(myTiles);
    gameData = new GameData(playerManager, boardManager, new OriginalDice());
  }


  @Test
  void testSetNextPlayer() {
    Player player1 = gameData.getCurrentPlayer();
    gameData.setNextPlayer();
    Player player2 = gameData.getCurrentPlayer();
    assertNotEquals(player1, player2);
  }

  @Test
  void testResetTurnData() {
    gameData.addRoll();
    int numRolls1 = gameData.getNumRolls();
    gameData.resetTurnData();
    int numRolls2 = gameData.getNumRolls();
    assertEquals(1, numRolls1);
    assertEquals(0, numRolls2);
    assertNotEquals(numRolls1, numRolls2);
  }

  @Test
  void testGetCurrentPlayer() {
    List<Player> myList = new ArrayList<>();
    myList.add(p1);
    PlayerManager pm = new OriginalPlayerManager(myList);
    GameData gameData = new GameData(pm, boardManager, new OriginalDice());
    gameData.setNextPlayer();
    assertEquals(p1, gameData.getCurrentPlayer());
  }

  @Test
  void testGetNumRollsAndAddRoll() {
    assertEquals(0,gameData.getNumRolls());
    gameData.addRoll();
    assertEquals(1,gameData.getNumRolls());
    gameData.addRoll();
    assertEquals(2,gameData.getNumRolls());
    gameData.addRoll();
    assertEquals(3,gameData.getNumRolls());
    gameData.resetTurnData();
    assertEquals(0,gameData.getNumRolls());
  }

}
