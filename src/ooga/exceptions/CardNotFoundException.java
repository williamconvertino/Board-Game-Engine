package ooga.exceptions;

public class CardNotFoundException extends GameError {

  String cardName;

  public CardNotFoundException(String cardName) {
    this.cardName = cardName;
  }

}
