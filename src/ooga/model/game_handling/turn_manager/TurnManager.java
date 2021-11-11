package ooga.model.game_handling.turn_manager;

import ooga.model.data.player.Player;
import ooga.model.data.tiles.Tile;
import ooga.model.game_handling.DataFunctions;

/**
 * This class manages the current turn and keeps track of all turn-based data.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public abstract class TurnManager {


    private int numRolls;

    private Player activePlayer;

    private int maxRolls;

    private Tile selectedTile;

    private DataFunctions dataFunctions;

    private int currentRoll;

    /**
     * Default constructor
     */
    public TurnManager() {
    }







    /**
     * @param player
     */
    public void startTurn(Player player) {

    }

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