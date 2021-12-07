package ooga.model.die;

import java.util.Arrays;
import java.util.Random;

/**
 * An abstract class representing a roll-able die.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public abstract class Die {

  //The random object with which this class's rolls are made.
  protected Random myRandom;

  //Keeps track of whether the previous roll was a double.
  protected boolean lastRollDouble;

  //The last roll of this die.
  protected int[] myLastRoll;

  /**
   * Constructs a new Die class with the specified number of dice.
   *
   * @param numberOfDice the number of dice to roll.
   */
  public Die(int numberOfDice) {
    this.myLastRoll = new int[numberOfDice];
    this.myRandom = new Random();
  }

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
  public boolean lastRollDouble() {
    return lastRollDouble;
  }

  /**
   * Returns an array with each die's result.
   *
   * @return an integer array containing each of the die's results.
   */
  public int[] diceResult() {
    int[] copy = Arrays.copyOf(myLastRoll, myLastRoll.length);
    return copy;
  }

}
