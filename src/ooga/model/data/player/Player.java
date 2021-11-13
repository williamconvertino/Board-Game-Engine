package ooga.model.data.player;

import java.util.List;
import ooga.model.data.tradeable.cards.Card;
import ooga.model.data.tradeable.properties.Property;

/**
 * A class to keep track of a single player's data.
 *
 * @author William Convertino
 *
 * @since 0.0.1
 */
public class Player {

    //The name of the player.
    private String name;

    //Keeps track of whether the player is actively in the game.
    private boolean isActive;

    //Keeps track of whether the player is in jail or not.
    private boolean isInJail;

    //Keeps track of the player's cash balance.
    private int balance;

    //Keeps track of the player's location on the board.
    private int location;

    //A list of the player's properties.
    private List<Property> myProperties;

    //A list of the player's cards.
    private List<Card> myCards;


    /**
     * Constructs a new Player with the given name.
     *
     * @param name the player's name.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * States whether the player is actively in the game or not.
     *
     * @return true if the player is active, false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the player's location to the number specified.
     *
     * @param location the location at which the player should be set.
     */
    public void setLocation(int location) {
        this.location = location;
    }

    /**
     * Returns the player's current location.
     *
     * @return the player's current location.
     */
    public int getLocation() {
        return location;
    }

}