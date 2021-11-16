package ooga.exceptions;

public class AttributeNotFoundException extends Throwable {

  String attributeName;

  public AttributeNotFoundException(String attribute){
    this.attributeName = attribute;
  }
}
