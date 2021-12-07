package ooga.model.data.tilemodels;

import ooga.model.data.deck.Deck;
import ooga.model.data.player.Player;

/**
 * This class represents a tile that corresponds to a specific deck of cards. When a player lands on
 * this tile, they draw a card from the deck and execute that card.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class CardTileModel extends TileModel {

  private Deck myDeck;

  /**
   * Constructs a new tile with the specified name.
   *
   * @param myName the name of the tile.
   */
  public CardTileModel(String myName) {
    super(myName, "Card");
  }

  public CardTileModel(String myName, Deck myDeck) {
    this(myName);
    this.myDeck = myDeck;
  }

  /**
   * Does nothing when a player passes through the tile.
   *
   * @param player the player who is passing through the tile.
   */
  @Override
  public void executePassThrough(Player player) {
    //Do nothing.
  }

  /**
   * Has a player draw and execute a card from the deck.
   *
   * @param player the player who landed on the tile.
   */
  @Override
  public void executeLandOn(Player player) {
    if (myDeck.isEmpty()) {
      return;
    }
    myDeck.drawCard().execute(player);
  }

}