package ooga.display.communication;

import java.util.HashMap;
import java.util.Map;
import javafx.event.EventHandler;
import model.turn_manager.GameFunctionManager;
import ooga.display.DisplayManager;
import ooga.model.game_handling.turn_manager.TurnManager;

/**
 * This class creates the buttons for the game and send them to the DisplayManager
 */
public class EventManager {

    enum EVENT_NAMES {
        START_TURN,
        END_TURN,
        ROLL,
        SELECT_PROPERTY,
        BUY_PROPERTY,
        MORTGAGE_PROPERTY,
        BUY_HOUSE,
        SELL_HOUSE,
        POST_BAIL,
    }

    Map<EVENT_NAMES, EventHandler> myEvents;

    /**
     * Default constructor
     */
    public EventManager(GameFunctionManager functions) {
        myEvents = new HashMap<>();
        initializeDefaultHandlers(functions);
    }

    private void initializeDefaultHandlers(GameFunctionManager functions) {

    }

    public Map<EVENT_NAMES, EventHandler> getMyEvents() {
        return myEvents;
    }
}