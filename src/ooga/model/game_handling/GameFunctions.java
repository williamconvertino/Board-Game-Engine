package ooga.model.game_handling;

import java.lang.reflect.Method;
import ooga.model.data.collectables.Collectable;
import ooga.model.data.GameData;
import ooga.model.data.player.Player;

/**
 * This class contains the functions for modifying the state of the game.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public class GameFunctions {


    private GameData gameData;
    private Method endTurn;


    /**
     * Default constructor
     */
    public GameFunctions(GameData gameData, Method endTurn) {
        this.gameData = gameData;
        this.endTurn = endTurn;
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

    }

    /**
     * @param player 
     * @param amount
     */
    public void addMoney(Player player, int amount) {

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

    }

}