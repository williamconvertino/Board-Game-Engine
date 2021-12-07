package ooga.model.die;

import java.util.Random;

/**
 * An implementation of the Die class that flips 10 coins and moves based on the number of heads.
 * Doubles are achieved if at least half of them are heads.
 *
 * @author William Convertino
 * @since 1.0.1
 */
public class TenCoins extends Die {

  /**
   * Constructs a new TenCoins die.
   */
  public TenCoins() {
    super(10);
    super.lastRollDouble = false;
    this.myLastRoll = new int[10];
  }

  /**
   * @see Die#roll()
   */
  @Override
  public int roll() {
    Random myRandom = new Random();
    int total = 0;
    for (int i = 0; i < 10; i++) {
      int flip = myRandom.nextInt(2);
      total += flip;
      myLastRoll[i] = flip;
    }
    lastRollDouble = total >= 5;
    return total;
  }

}
