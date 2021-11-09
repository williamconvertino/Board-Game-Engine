package model.turn_manager;

import java.util.*;

/**
 * 
 */
public abstract class GameFunctionManager {

    /**
     * Default constructor
     */
    public GameFunctionManager() {
    }

    /**
     * 
     */
    private void gameData;



    /**
     * @param player 
     * @param location
     */
    public abstract void movePlayerTo(Player player, int location);

    /**
     * @param player
     */
    public abstract void rollDie(Player player);

    /**
     * @param player 
     * @param amount
     */
    public abstract void addMoney(Player player, int amount);

    /**
     * @param player 
     * @param amount
     */
    public abstract void movePlayerFd(Player player, int amount);

    /**
     * @param player 
     * @param collectable
     */
    public void givePlayerCollectable(Player player, Collectable collectable) {
        // TODO implement here
    }

}