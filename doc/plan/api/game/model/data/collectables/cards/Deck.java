package model.data.collectables.cards;

import java.util.*;

/**
 * A data structure that stores a list of executable cards.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public abstract class Deck {

    /**
     * Default constructor
     */
    public Deck() {
    }

    /**
     * 
     */
    private List<Card> cards;


    /**
     * 
     */
    public abstract void shuffle();

    /**
     * @return
     */
    public abstract Card drawCard();

}