package model.turn_manager;

import java.util.*;
import model.data.collectables.Collectable;
import model.data.game_data.GameData;
import model.data.game_data.Player;

/**
 * This class contains the functions for modifying the state of the game.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
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
    private GameData gameData;



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
    public abstract void givePlayerCollectable(Player player, Collectable collectable);

}