package model.game_manager;

import display.DisplayManager;
import java.util.*;
import model.turn_manager.TurnManager;

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
    private DisplayManager myDisplayManager;

    /**
     * 
     */
    private TurnManager myTurnManager;

    /**
     * 
     */
    protected abstract void initializeButtons();

    /**
     * 
     */
    protected abstract void initializeGameData();

}