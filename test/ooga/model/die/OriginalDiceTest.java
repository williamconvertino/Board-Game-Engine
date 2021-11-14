package ooga.model.die;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OriginalDiceTest extends DieTest {

  Die myDie;

  @BeforeEach
  void reset() {
    myDie = new OriginalDice();
  }

  @Test
  void testRoll() {
    for (int i = 0; i < 50; i++) {
      int roll = myDie.roll();
      assertTrue(roll >= 2 && roll <= 12);
    }
  }

  @Test
  void testDoubles() {
    int numDoubles = 0;
    for (int i = 0; i < 50; i++) {
      int roll = myDie.roll();
      if (myDie.lastRollDouble()) {
        assertTrue(roll % 2 == 0);
        numDoubles++;
      }
    }
    assertTrue(numDoubles > 0);
  }

  @Test
  void testDiceResult() {
    for (int i = 0; i < 50; i++) {
      int roll = myDie.roll();
      int[] results = myDie.diceResult();

      int sum = 0;
      for (int r: results) {
        sum+= r;
        assertTrue(r > 0 && r < 7);
      }
      assertEquals(roll, sum);
    }
  }

}
