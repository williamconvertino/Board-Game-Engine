package ooga.exceptions;

public class InsufficientBalanceException extends PlayerWarning {

  public InsufficientBalanceException() {
    super("You cannot afford to do this.");
  }
}
