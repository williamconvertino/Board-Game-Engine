package ooga.exceptions;

public class MaxRollsReachedException extends PlayerWarning {

  public MaxRollsReachedException() {
    super("You cannot roll again.");
  }
}
