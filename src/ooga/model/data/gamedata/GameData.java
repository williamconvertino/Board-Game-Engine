package ooga.model.data.gamedata;

import ooga.exceptions.NoRemainingPlayersException;
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

    //The number of rolls this turn.
    private int numRolls;

    //The previous roll of this turn.
    private int previousRoll;

    /**
     * Constructs a new GameData with the specified board and players.
     *
     * @param myPlayers
     * @param myBoard
     */
    public GameData(PlayerManager myPlayers, BoardManager myBoard) {
        this.myPlayers = myPlayers;
        this.myBoard = myBoard;
        resetTurnData();
    }

    /**
     * Switches the current player to the next player in the order.
     *
     * @throws NoRemainingPlayersException if no players remain in the game.
     */
    public void setNextPlayer() {
        try {
            this.currentPlayer = myPlayers.getNextPlayer();
        } catch (NoRemainingPlayersException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resets the data pertaining to the current turn.
     */
    public void resetTurnData() {
        this.numRolls = 0;
        this.previousRoll = -1;
    }

    /**
     * Returns the current player.
     *
     * @return the current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the number of rolls this turn.
     *
     * @return the number of rolls this turn.
     */
    public int getNumRolls() {
        return numRolls;
    }

    /**
     * Adds to the total number of rolls.
     */
    public void addRoll() {
        this.numRolls++;
    }

    /**
     * Returns the previous roll this turn.
     *
     * @return the previous roll this turn.
     */
    public int getPreviousRoll() {
        return previousRoll;
    }


}