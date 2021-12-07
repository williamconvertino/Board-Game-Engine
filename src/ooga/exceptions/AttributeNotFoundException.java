package ooga.exceptions;

public class AttributeNotFoundException extends GameError {

  String attributeName;

  public AttributeNotFoundException(String attribute) {
    this.attributeName = attribute;
  }
}
