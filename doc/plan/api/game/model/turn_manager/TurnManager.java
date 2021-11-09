package model.turn_manager;

import java.util.*;
import model.data.game_data.Player;
import model.data.tiles.Tile;

/**
 * This class manages the current turn and keeps track of all turn-based data.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public abstract class TurnManager {

    /**
     * Default constructor
     */
    public TurnManager() {
    }

    /**
     * 
     */
    private Player activePlayer;

    /**
     * 
     */
    private int numRolls;

    /**
     * 
     */
    private int maxRolls;

    /**
     * 
     */
    private Tile selectedTile;

    /**
     * 
     */
    private GameFunctionManager gameFunctionManager;

    /**
     * 
     */
    private int currentRoll;



    /**
     * @param player
     */
    public abstract void startTurn(Player player);

    /**
     * @param player
     */
    public abstract void endTurn(Player player);

    /**
     * 
     */
    public abstract void postBail();

    /**
     * 
     */
    public abstract void buyProperty();

    /**
     * 
     */
    public abstract void buyHouse();

    /**
     * @param tile
     */
    public abstract void selectTile(Tile tile);

    /**
     * 
     */
    public abstract void roll();

    /**
     * @param otherPlayer
     */
    public abstract void initiateTrade(Player otherPlayer);

}