package ooga.model.data.tiles;

import ooga.display.communication.DisplayStateSignaler;
import ooga.model.data.player.Player;
import ooga.model.data.properties.Property;
import ooga.model.game_handling.commands.ActionSequence;

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

    //The action sequence to execute when this tile has been landed on.
    ActionSequence landOnPropertySequence;

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
     * @param name the name of the tile.
     * @param property the property associated with this tile.
     * @param landOnPropertySequence the action sequence to execute when this tile has been landed on.
     */
    public PropertyTile(String name, Property property, ActionSequence landOnPropertySequence) {
        this(name);
        this.myProperty = property;
        this.landOnPropertySequence = landOnPropertySequence;
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
        landOnPropertySequence.execute(player);
    }


}