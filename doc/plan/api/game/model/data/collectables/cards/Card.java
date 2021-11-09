package model.data.collectables.cards;

import java.util.*;

/**
 * A class to represent any non-property card and its action.
 */
public abstract class Card extends Collectable {

    /**
     * Default constructor
     */
    public Card() {
    }

    /**
     * 
     */
    public abstract void execute();

}