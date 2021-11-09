package model.data.tiles;

import java.util.*;
import model.data.collectables.properties.Property;
import model.data.game_data.Player;
import model.turn_manager.GameFunctionManager;

/**
 * This class represents a tile corresponding to a property card. When a player lands on it, they may buy it if it is unowned, or pay rent for it if it is owned by another player.
 */
public abstract class PropertyTile extends Tile {

    /**
     * Default constructor
     */
    public PropertyTile() {
    }

    /**
     * 
     */
    protected Property property;

    /**
     * @param player 
     * @param functionManager
     */
    public abstract void executePassThrough(Player player, GameFunctionManager functionManager);

    /**
     * @param player 
     * @param functionManager
     */
    public abstract void executeLandOn(Player player, GameFunctionManager functionManager);

}