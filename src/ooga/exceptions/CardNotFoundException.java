package ooga.exceptions;

public class CardNotFoundException extends Exception {

  String cardName;

  public CardNotFoundException (String cardName) {
    this.cardName = cardName;
  }

}
