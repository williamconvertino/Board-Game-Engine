package ooga.model.game_handling;

import java.lang.reflect.Method;
import ooga.display.communication.DisplayComm;
import ooga.display.communication.ExceptionHandler;
import ooga.exceptions.TileNotFoundException;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;
import ooga.model.die.Die;

/**
 * This class executes the game's functions. This includes any changes to the game data.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public class FunctionExecutor {

    //The game data to change.
    private GameData gameData;

    //A method with which this class can end the current player's turn.
    private Method endTurn;

    //The die to use when rolling.
    private Die myDie;

    private DisplayComm displayComm;

    /**
     * Constructs a new FunctionExecutor with the specified GameData, die, and endTurn method.
     */
    public FunctionExecutor(GameData gameData, Die die, DisplayComm displayComm) {
        this.gameData = gameData;
        this.myDie = die;
        this.displayComm = displayComm;
    }

    /**
     * Moves the player to the specified location.
     *
     * @param player the player to move.
     * @param location the location at which the player should be moved.
     */
    public void movePlayerToIndex(Player player, int location) {
        gameData.getBoard().movePlayerToIndex(player, location, false);
    }

    public void movePlayerToTile(Player player, String tileName) throws TileNotFoundException {
        try {
            gameData.getBoard().movePlayerToTile(player, tileName, false);
        } catch (Exception e) {
            displayComm.showException(e);
            throw e;
        }
    }

    public void advancePlayerToTile(Player player, String tileName) throws TileNotFoundException {
        try {
            gameData.getBoard().movePlayerToTile(player, tileName, true);
        } catch (Exception e) {
            displayComm.showException(e);
            throw e;
        }
    }

    /**
     *  Moves a player forward by the specified number of spaces. This triggers any
     *
     * @param player
     */
    public void movePlayerFd(Player player, int amount) {

    }

    public void movePlayerBk(Player player, int amount) {

    }

    /**
     * @param player
     */
    public void rollDie(Player player) {

        myDie.roll();

    }

    /**
     * @param player 
     * @param amount
     */
    public void addMoney(Player player, int amount) {
        //player.addMoney(amount);
    }

    public void loseMoney(Player player, int amount) {

    }

    public void goToJail() {

    }

    public void givePlayerCard(Player player, String cardName) {

    }

    public void transferMoney(Player donor, Player acceptor) {

    }


}