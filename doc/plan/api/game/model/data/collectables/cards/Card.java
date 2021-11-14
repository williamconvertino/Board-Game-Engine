package model.data.collectables.cards;

import java.util.*;
import model.data.collectables.Collectable;

/**
 * A class to represent any non-property card and its commands.
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