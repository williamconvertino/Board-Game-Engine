package model.data.collectables.cards;

import java.util.*;

/**
 * 
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
    public List<Card> cards;


    /**
     * 
     */
    public abstract void shuffle();

    /**
     * @return
     */
    public abstract Card drawCard();

}