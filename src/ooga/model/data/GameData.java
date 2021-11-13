package ooga.model.data;

import java.util.List;
import ooga.model.data.board.Board;
import ooga.model.data.player.Player;
import ooga.model.data.player.PlayerManager;
import ooga.model.data.tiles.Tile;

/**
 * A class to store the data of the current game. This includes players and all their associated info, as well as the board and its state.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public abstract class GameData {

    //The current game board.
    private Board myBoard;

    //A structure to keep track of the players in the game.
    private PlayerManager myPlayers;

    //The player whose turn it currently is.
    private Player currentPlayer;

}