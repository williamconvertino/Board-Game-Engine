package ooga.model.game_handling;

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


}
