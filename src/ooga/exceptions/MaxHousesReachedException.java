package ooga.exceptions;

public class MaxHousesReachedException extends PlayerWarning {

  public MaxHousesReachedException() {
    super("The max number of houses has been reached.");
  }
}
