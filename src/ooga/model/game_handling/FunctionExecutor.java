package ooga.model.game_handling;

import java.lang.reflect.Method;
import ooga.display.communication.DisplayComm;
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

    //The display communication class.
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

    /**
     * Moves the specified player to the next tile with the given name.
     *
     * @param player the player to move.
     * @param tileName the name of the tile to which the player should be moved.
     * @throws TileNotFoundException if the tile with the given name cannot be found.
     */
    public void movePlayerToTile(Player player, String tileName) throws TileNotFoundException {
        try {
            gameData.getBoard().movePlayerToTile(player, tileName, false);
        } catch (Exception e) {
            displayComm.showException(e);
            throw e;
        }
    }

    /**
     * Advances the player to the nearest tile with the specified name. This executes any
     * pass-through commands of tiles that it advances through.
     *
     * @param player the player to move.
     * @param tileName the name of the tile to which the player should be moved.
     * @throws TileNotFoundException if the tile with the given name cannot be found.
     */
    public void advancePlayerToTile(Player player, String tileName) throws TileNotFoundException {
        try {
            gameData.getBoard().movePlayerToTile(player, tileName, true);
        } catch (Exception e) {
            displayComm.showException(e);
            throw e;
        }
    }

    /**
     *  Moves a player forward by the specified number of spaces.
     *
     * @param player the player to move.
     * @param spaces the number of spaces to move.
     */
    public void movePlayerFd(Player player, int spaces) {
        gameData.getBoard().movePlayerFd(player, spaces);
    }

    /**
     *  Moves a player bakward by the specified number of spaces.
     *
     * @param player the player to move.
     * @param spaces the number of spaces to move.
     */
    public void movePlayerBk(Player player, int spaces) {
        gameData.getBoard().movePlayerBk(player, spaces);
    }

    /**
     * Rolls the die and returns the result.
     */
    public int rollDie() {
        return(myDie.roll());
    }

    /**
     * Gives the specified player a specified amount of money.
     *
     * @param player the player who is to receive the money.
     * @param amount the amount of money that player should receive.
     */
    public void addMoney(Player player, int amount) {
        //player.addMoney(amount);
    }

    /**
     * Takes away a specified amount of money from the specified player and returns
     * any debt that player takes on.
     *
     * @param player the player who is to lose the money.
     * @param amount the amount of money that player should lose.
     * @return the amount of debt the player takes on after this loss of money.
     */
    public int loseMoney(Player player, int amount) {
        return 0;
    }

    /**
     * Sends the specified player to jail.
     *
     * @param player the player to send to jail.
     */
    public void goToJail(Player player) {
        player.setJailStatus(true);
    }

    /**
     * Gives the player a card with the specified name.
     *
     * @param player the player who receives the card.
     * @param cardName the name of the card that the player receives.
     */
    public void givePlayerCard(Player player, String cardName) {

    }

}