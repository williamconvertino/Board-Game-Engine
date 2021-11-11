package ooga.model.game_manager;

import ooga.display.DisplayManager;
import ooga.model.turn_manager.TurnManager;

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