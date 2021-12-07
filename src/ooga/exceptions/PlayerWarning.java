package ooga.exceptions;

public abstract class PlayerWarning extends Exception {

  String message;

  public PlayerWarning(String message) {
    this.message = message;
  }

  public String getDescription() {
    return message;
  }

}

