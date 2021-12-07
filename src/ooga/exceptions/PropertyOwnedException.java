package ooga.exceptions;

public class PropertyOwnedException extends PlayerWarning {

  String ownerName;

  public PropertyOwnedException(String ownerName) {
    super("This property is already owned by " + ownerName);
    this.ownerName = ownerName;
  }

}
