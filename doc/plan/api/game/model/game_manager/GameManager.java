package model.game_manager;

import java.util.*;

/**
 * This class initializes and manages the game.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public abstract class GameManager {

    /**
     * Default constructor
     */
    public GameManager() {
    }

    /**
     * 
     */
    public DisplayManager myDisplayManager;

    /**
     * 
     */
    public TurnManager myTurnManager;









    /**
     * 
     */
    public void Operation1() {
        // TODO implement here
    }

    /**
     * 
     */
    private abstract void initializeButtons();

    /**
     * 
     */
    private abstract void initializeGameData();

}