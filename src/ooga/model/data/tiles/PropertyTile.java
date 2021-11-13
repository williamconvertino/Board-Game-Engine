package ooga.model.data.tiles;

import ooga.display.communication.DisplayStateSignaler;
import ooga.model.data.player.Player;
import ooga.model.data.properties.Property;

/**
 * This class represents a tile corresponding to a property card. When a player lands on it,
 * they must pay rent for it if it is owned by another player. If the property is unowned, they
 * may buy it.
 *
 *
 *
 */
public abstract class PropertyTile extends Tile {

    //The property that this tile represents.
    private Property myProperty;

    /**
     * Constructs a new tile with the specified name.
     *
     * @param myName the name of the tile.
     */
    public PropertyTile(String myName) {
        super(myName);
    }

    /**
     * Constructs a new tile with the specified name and property.
     *
     * @param myName the name of the tile.
     */
    public PropertyTile(String myName, Property myProperty) {
        super(myName);
        this.myProperty = myProperty;
    }

    /**
     * Does nothing when a player passes through the tile.
     *
     * @param player the player who is passing through the tile.
     */
    @Override
    public void executePassThrough(Player player) {
        //Do Nothing.
    }

    /**
     * Selects the current property.
     *
     * @param player the player who landed on the tile.
     */
    @Override
    public void executeLandOn(Player player) {
        //TODO:
    }


}