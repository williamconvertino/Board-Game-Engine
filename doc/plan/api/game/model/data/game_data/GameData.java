package model.data.game_data;

import java.util.*;
import model.data.tiles.Tile;

/**
 * A class to store the data of the current game. This includes players and all their associated info, as well as the board_manager and its state.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public abstract class GameData {

    /**
     * Default constructor
     */
    public GameData() {
    }

    /**
     * 
     */
    private List<Tile> board;

    /**
     * 
     */
    private List<Player> playerList;



    /**
     * @return
     */
    public abstract Player getNextPlayer();

    /**
     * @return
     */
    public abstract List<Tile> getTiles();

}