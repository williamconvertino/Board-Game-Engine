package ooga.model.data.gamedata;

import ooga.model.game_handling.board_manager.BoardManager;
import ooga.model.data.player.Player;
import ooga.model.data.player.PlayerManager;

/**
 * A class to store the data of the current game. This includes players and all their associated info, as well as the board_manager and its state.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public class GameData {

    //The current game board_manager.
    private BoardManager myBoard;

    //A structure to keep track of the players in the game.
    private PlayerManager myPlayers;

    //The player whose turn it currently is.
    private Player currentPlayer;

    /**
     * Constructs a new GameData with the specified board and players.
     *
     * @param myPlayers
     * @param myBoard
     */
    public GameData(PlayerManager myPlayers, BoardManager myBoard) {
        this.myPlayers = myPlayers;
        this.myBoard = myBoard;
    }


}