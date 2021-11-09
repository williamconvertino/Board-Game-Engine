package display;

import java.util.*;
import model.turn_manager.TurnManager;

/**
 * This class creates the buttons for the game and send them to the DisplayManager
 */
public abstract class ButtonInitializer {

    /**
     * Default constructor
     */
    public ButtonInitializer() {
    }

    /**
     * @param displayManager 
     * @param turnManager
     */
    public abstract void initializeButtons(DisplayManager displayManager, TurnManager turnManager);

}