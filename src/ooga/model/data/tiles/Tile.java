package ooga.model.data.tiles;

import ooga.model.data.player.Player;

/**
 * This class represents a tile on the board. A player can pass through or land on a tile when moving, which is reflected by the executePassThrough and executeLandOn methods.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public abstract class Tile {

    /**
     * Default constructor
     */
    public Tile() {
    }

    /**
     * @param player 
     * @param
     */
    public abstract void executePassThrough(Player player);

    /**
     * @param player
     */
    public abstract void executeLandOn(Player player);

    public abstract String getName();

}