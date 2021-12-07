package ooga.model.die;

/**
 * An implementation of the die class that rolls exclusively doubles on a 6-sided dice.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class DoublesDie extends Die {


  /**
   * Constructs a new DoublesDie object.
   */
  public DoublesDie() {
    super(2);
  }

  /**
   * @see Die#roll()
   */
  @Override
  public int roll() {

    int roll = myRandom.nextInt(6) + 1;

    myLastRoll[0] = roll;
    myLastRoll[1] = roll;
    return 2 * roll;
  }

  /**
   * @see Die#lastRollDouble()
   */
  @Override
  public boolean lastRollDouble() {
    return true;
  }

}
