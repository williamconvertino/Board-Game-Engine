package ooga.exceptions;

public class NoHousesToSellException extends PlayerWarning {

  public NoHousesToSellException() {
    super("You have no houses to sell.");
  }
}
