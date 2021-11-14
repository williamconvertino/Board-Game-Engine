package ooga.model.die;

/**
 * An abstract class representing a roll-able die.
 *
 * @author William Convertino
 *
 * @since 0.0.1
 */
public abstract class Die {

  /**
   * Rolls the die and returns the result of the roll.
   *
   * @return the result of the roll.
   */
  public abstract int roll();

  /**
   * Returns whether or not the last roll was a double.
   *
   * @return true if the last roll was a double, and false otherwise.
   */
  public abstract boolean lastRollDouble();

  /**
   * Returns an array with each die's result.
   *
   * @return an integer array containing each of the die's results.
   */
  public abstract int[] diceResult();

}
