package ooga.exceptions;


public class MortgageException extends PlayerWarning {

  public MortgageException() {
    super("You cannot mortgage this property.");
  }
}
