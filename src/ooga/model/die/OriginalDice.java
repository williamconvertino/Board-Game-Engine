package ooga.model.die;

import java.util.Random;

/**
 * An implementation of the die class that rolls two 6-sided dice.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class OriginalDice extends Die {

  /**
   * Constructs a new OriginalDice.
   */
  public OriginalDice() {
    super(2);
    this.myRandom = new Random();
    this.lastRollDouble = false;
    this.myLastRoll = new int[2];
  }

  /**
   * Rolls two 6-sided dice and returns the result.
   *
   * @return the result of the roll.
   */
  @Override
  public int roll() {
    lastRollDouble = false;
    int r1 = myRandom.nextInt(6) + 1;
    int r2 = myRandom.nextInt(6) + 1;
    if (r1 == r2) {
      lastRollDouble = true;
    }
    myLastRoll[0] = r1;
    myLastRoll[1] = r2;
    return r1 + r2;
  }

}
