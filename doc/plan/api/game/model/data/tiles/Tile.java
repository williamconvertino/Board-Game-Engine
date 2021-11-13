package model.data.tiles;

import java.util.*;
import model.data.game_data.Player;
import model.turn_manager.GameFunctionManager;

/**
 * This class represents a tile on the board_manager. A player can pass through or land on a tile when moving, which is reflected by the executePassThrough and executeLandOn methods.
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
     * @param functionManager
     */
    public abstract void executePassThrough(Player player, GameFunctionManager functionManager);

    /**
     * @param player 
     * @param functionManager
     */
    public abstract void executeLandOn(Player player, GameFunctionManager functionManager);

}