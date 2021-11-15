package ooga.model.game_handling.turn_manager;

import ooga.model.data.player.Player;
import ooga.model.game_handling.GameHandlingTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TurnManagerTest extends GameHandlingTest {



  @Test
  void testStartTurn() {
    assertEquals(null, myGameData.getCurrentPlayer());

    myTurnManager.startTurn();
    assertNotEquals(null, myGameData.getCurrentPlayer());

    assertEquals(0, myGameData.getNumRolls());
    myTurnManager.roll();
    assertNotEquals(0, myGameData.getNumRolls());
    myTurnManager.startTurn();
    assertEquals(0, myGameData.getNumRolls());

  }

  @Test
  void testRoll() {
    myTurnManager.startTurn();
    Player currentPlayer = myGameData.getCurrentPlayer();
    int location1 = currentPlayer.getLocation();

    assertEquals(0, myGameData.getNumRolls());
    myTurnManager.roll();
    assertEquals(1, myGameData.getNumRolls());

    int location2 = currentPlayer.getLocation();
    System.out.println(location2);
    assertNotEquals(location1, location2);

  }

}
