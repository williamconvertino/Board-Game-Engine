package model.data.game_data;

import java.util.*;

/**
 * 
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