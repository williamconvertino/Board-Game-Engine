package ooga.model.data;

import java.util.List;
import ooga.model.data.board.Board;
import ooga.model.data.player.Player;
import ooga.model.data.tiles.Tile;

/**
 * A class to store the data of the current game. This includes players and all their associated info, as well as the board and its state.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public abstract class GameData {

    /**
     * 
     */
    private Board myBoard;

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