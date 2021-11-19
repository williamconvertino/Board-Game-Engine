package ooga.exceptions;

public class PropertyOwnedException extends Exception {

  String ownerName;

  public PropertyOwnedException(String ownerName) {
    this.ownerName = ownerName;
  }

}
