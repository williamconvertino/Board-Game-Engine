package model.data.tiles;

import java.util.*;

/**
 * 
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
    public Property property;

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