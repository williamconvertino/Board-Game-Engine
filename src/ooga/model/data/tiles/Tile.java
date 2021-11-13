package ooga.model.data.tiles;

import ooga.model.data.player.Player;

/**
 * This class represents a tile on the board_manager.A player can pass through or land on a tile when moving,
 * which is reflected by the executePassThrough and executeLandOn methods.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public abstract class Tile {

    private String myName;

    /**
     * Constructs a new tile with the specified name.
     *
     * @param myName the name of the tile.
     */
    public Tile(String myName) {
        this.myName = myName;
    }

    /**
     * Executes whenever a player passes through the tile.
     *
     * @param player the player who is passing through the tile.
     */
    public abstract void executePassThrough(Player player);

    /**
     * Executes whenever a player lands on a tile.
     *
     * @param player the player who landed on the tile.
     */
    public abstract void executeLandOn(Player player);

    /**
     * Returns the name of the tile.
     *
     * @return the name of the tile.
     */
    public abstract String getName();

}