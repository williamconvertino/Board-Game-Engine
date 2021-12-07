package ooga.model.die;

/**
 * An abstract class representing a roll-able die.
 *
 * @author William Convertino
 * @since 1.0.1
 */
public class BowserDie extends Die {

  //The possible values on the die.
  private static final int[] ROLLS = {1, 1, 1, 8, 9, 10};

  /**
   * Constructs a new BowserDie.
   */
  public BowserDie() {
    super(2);
  }

  /**
   * @see Die#roll()
   */
  @Override
  public int roll() {
    int roll1 = ROLLS[myRandom.nextInt(6)];
    int roll2 = ROLLS[myRandom.nextInt(6)];
    lastRollDouble = roll1 == roll2;
    myLastRoll[0] = roll1;
    myLastRoll[1] = roll2;
    return roll1 + roll2;
  }

}
