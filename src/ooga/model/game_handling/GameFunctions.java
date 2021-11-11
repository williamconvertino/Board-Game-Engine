package ooga.model.game_handling;

import java.lang.reflect.Method;
import ooga.model.data.collectables.Collectable;
import ooga.model.data.GameData;
import ooga.model.data.player.Player;
import ooga.model.game_handling.die.Die;

/**
 * This class modifies the player and game state variables.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public class GameFunctions {


    private GameData gameData;
    private Method endTurn;
    private Die myDie;

    /**
     * Default constructor
     */
    public GameFunctions(GameData gameData, Die die, Method endTurn) {
        this.gameData = gameData;
        this.endTurn = endTurn;
        this.myDie = die;
    }


    /**
     * @param player 
     * @param location
     */
    public void movePlayerTo(Player player, int location) {
        player.setLocation(location);
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
        player.addMoney(amount);
    }

    /**
     * @param player 
     * @param amount
     */
    public void movePlayerFd(Player player, int amount) {

    }

    /**
     * @param player 
     * @param collectable
     */
    public void givePlayerCollectable(Player player, Collectable collectable) {
        player.addCollectable(collectable);
    }

}