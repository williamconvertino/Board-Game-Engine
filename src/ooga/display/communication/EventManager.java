package ooga.display.communication;

import java.util.HashMap;
import java.util.Map;
import javafx.event.EventHandler;
import ooga.model.game_handling.FunctionExecutor;
import ooga.model.game_handling.turn_manager.TurnManager;
import static ooga.display.communication.EventManager.EVENT_NAMES.*;
/**
 * This class creates the buttons for the game and send them to the DisplayManager
 */
public class EventManager {

    public enum EVENT_NAMES {
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
    public EventManager(TurnManager turnManager) {
        myEvents = new HashMap<>();
        initializeDefaultHandlers(turnManager);
    }

    private void initializeDefaultHandlers(TurnManager turnManager) {
        myEvents.put(ROLL, e->turnManager.roll());
        myEvents.put(START_TURN, e->turnManager.startTurn());
    }

    public Map<EVENT_NAMES, EventHandler> getMyEvents() {
        return myEvents;
    }
}