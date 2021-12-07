package ooga.model.data.deck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import ooga.exceptions.DeckNotFoundException;

/**
 * A class to keep track of the active decks in the game.
 *
 * @author William Convertino
 * @author Casey Goldstein
 * @since 0.0.1
 */
public class DeckManager {

  //A map of all the active decks and their names.
  private final Map<String, Deck> activeDecks;

  /**
   * Constructs an empty DeckManager.
   */
  public DeckManager() {
    activeDecks = new HashMap<>();
  }

  /**
   * Returns the deck with the specified name.
   *
   * @param name the name of the desired deck.
   * @return the deck with the desired name.
   * @throws DeckNotFoundException if the deck cannot be found.
   */
  public Deck getDeck(String name) throws DeckNotFoundException {
    Deck deck = activeDecks.get(name);
    if (deck == null) {
      throw new DeckNotFoundException();
    }
    return deck;
  }

  /**
   * Adds deck to deck map.
   *
   * @param deck
   */
  public void addDeck(Deck deck) {
    activeDecks.putIfAbsent(deck.getName(), deck);
  }

  /**
   * Returns a random deck of cards.
   *
   * @return a random deck of cards.
   */
  public Deck getRandomDeck() {
    Random rd = new Random();
    return activeDecks.get(
        new ArrayList<>(activeDecks.keySet()).get(rd.nextInt(activeDecks.keySet().size())));
  }

}
