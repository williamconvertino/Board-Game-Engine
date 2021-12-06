package ooga.exceptions;

public class PropertyOwnedException extends PlayerWarning {

  String ownerName;

  public PropertyOwnedException(String ownerName) {
    this.ownerName = ownerName;
  }

}
