package ooga.model.data.cards;


import ooga.model.data.player.Player;

/**
 * A class to represent any non-property card and its action.
 */
public abstract class Card {

    /**
     * Default constructor
     */
    public Card() {
    }

    /**
     * 
     */
    public abstract void execute(Player player);

}