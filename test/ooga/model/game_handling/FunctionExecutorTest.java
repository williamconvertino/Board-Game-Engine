package ooga.model.game_handling;

import ooga.model.data.player.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionExecutorTest extends GameHandlingTest {

  @Test
  void testMovePlayer() {
    int location1 = p1.getLocation();
    myFunctionExecutor.movePlayerToIndex(p1, 5);
    int location2 = p1.getLocation();

    assertNotEquals(location1, location2);
    assertEquals(5, location2);

    location1 = p2.getLocation();
    myFunctionExecutor.movePlayerFd(p2, 5);
    location2 = p2.getLocation();

    assertEquals(location1 + 5, location2);

    myFunctionExecutor.movePlayerBk(p2, 3);
    int location3 = p2.getLocation();
    assertEquals(location2 - 3, location3);
  }

  @Test
  void testMoveToTile() {

    int location1 = p1.getLocation();
    try {
      myFunctionExecutor.movePlayerToTile(p1, "t3");
    } catch (Exception e) {
      assertTrue(false);
    }
    int location2 = p1.getLocation();
    assertEquals(3, p1.getLocation());

    try {
      myFunctionExecutor.movePlayerToTile(p1, "NA");
      assertTrue(false);
    } catch (Exception e) {
      assertTrue(true);
    }

    try {
      myFunctionExecutor.movePlayerToTile(p1, "t1");
    } catch (Exception e) {
      assertTrue(false);
    }
    int location3 = p1.getLocation();
    assertEquals(1, p1.getLocation());

  }

  @Test
  void testAdvanceToTile() {
    int location1 = p1.getLocation();
    try {
      myFunctionExecutor.advancePlayerToTile(p1, "t3");
    } catch (Exception e) {

      assertTrue(false);
    }
    int location2 = p1.getLocation();
    assertEquals(3, p1.getLocation());

    try {
      myFunctionExecutor.advancePlayerToTile(p1, "NA");
      assertTrue(false);
    } catch (Exception e) {
      assertTrue(true);
    }

    try {
      myFunctionExecutor.advancePlayerToTile(p1, "t1");
    } catch (Exception e) {
      assertTrue(false);
    }
    int location3 = p1.getLocation();
    assertEquals(1, p1.getLocation());
  }

  @Test
  void testRollDie() {
    for (int i = 0; i < 50; i++) {
      int roll = myFunctionExecutor.rollDie();
      assertTrue(roll >= 2 && roll <= 12);
    }
  }

  @Test
  void testMoneyCommands() {
    assertEquals(1500, p1.getBalance());
    myFunctionExecutor.addMoney(p1,10);
    assertEquals(1510, p1.getBalance());
    myFunctionExecutor.loseMoney(p1, 10);
    assertEquals(1500, p1.getBalance());
  }

  @Test
  void testGoToJail() {
    assertNotEquals(10, p1.getLocation());
    assertFalse(p1.isInJail());
    myFunctionExecutor.goToJail(p1);
    assertEquals(10, p1.getLocation());
    assertTrue(p1.isInJail());
  }

  @Test
  void testGivePlayerCard() {

  }

}
