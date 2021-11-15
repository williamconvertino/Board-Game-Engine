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
      myFunctionExecutor.movePlayerToTile(p1, "t5");
    } catch (Exception e) {
      assertTrue(false);
    }
    int location2 = p1.getLocation();
    assertEquals(5, p1.getLocation());

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
      myFunctionExecutor.advancePlayerToTile(p1, "t5");
    } catch (Exception e) {
      assertTrue(false);
    }
    int location2 = p1.getLocation();
    assertEquals(5, p1.getLocation());

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

}
