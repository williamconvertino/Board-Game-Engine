package ooga.exceptions;

public class AttributeNotFoundException extends Exception {

  String attributeName;

  public AttributeNotFoundException(String attribute){
    this.attributeName = attribute;
  }
}
