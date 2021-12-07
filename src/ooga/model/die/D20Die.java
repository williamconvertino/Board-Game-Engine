package ooga.model.die;

/**
 * An implementation of the die class that rolls one 20-sided dice.
 *
 * @author William Convertino
 * @since 1.0.1
 */
public class D20Die extends Die {

  /**
   * Constructs a new D20 die.
   */
  public D20Die() {
    super(1);
  }

  /**
   * @see Die#roll()
   */
  @Override
  public int roll() {
    int myRoll = myRandom.nextInt(20) + 1;
    myLastRoll[0] = myRoll;
    return myRoll;
  }
}
