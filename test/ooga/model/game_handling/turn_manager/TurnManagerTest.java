package ooga.model.game_handling.turn_manager;

import ooga.model.data.player.Player;
import ooga.model.game_handling.GameHandlingTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TurnManagerTest extends GameHandlingTest {



  @Test
  void testStartTurn() {

    myTurnManager.endTurn();
    assertNotEquals(null, myGameData.getCurrentPlayer());

    assertEquals(0, myGameData.getNumRolls());
    myTurnManager.roll();
    assertNotEquals(0, myGameData.getNumRolls());
    myTurnManager.endTurn();
    assertEquals(0, myGameData.getNumRolls());

  }

  @Test
  void testRoll() {
    myTurnManager.endTurn();
    Player currentPlayer = myGameData.getCurrentPlayer();
    int location1 = currentPlayer.getLocation();

    assertEquals(0, myGameData.getNumRolls());
    myTurnManager.roll();
    assertEquals(1, myGameData.getNumRolls());

    int location2 = currentPlayer.getLocation();
    assertNotEquals(location1, location2);

    if (myDie.lastRollDouble()) {
      myTurnManager.roll();
      assertNotEquals(location2, currentPlayer.getLocation());
    } else {
      myTurnManager.roll();
      assertEquals(location2, currentPlayer.getLocation());
    }



  }

}
