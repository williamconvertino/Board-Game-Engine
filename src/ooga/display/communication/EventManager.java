package ooga.display.communication;

import java.util.HashMap;
import java.util.Map;
import javafx.event.EventHandler;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.TileModel;
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
        SELECT_TILE
    }

    Map<EVENT_NAMES, TMEvent> myEvents;

    /**
     * Default constructor
     */
    public EventManager(TurnManager turnManager) {
        myEvents = new HashMap<>();
        initializeDefaultHandlers(turnManager);
    }

    private void initializeDefaultHandlers(TurnManager turnManager) {

        myEvents.put(ROLL, e ->turnManager.roll());
        myEvents.put(END_TURN, e ->turnManager.endTurn());
        myEvents.put(SELECT_TILE, e->turnManager.setSelectedTile((TileModel)e[0]));
        myEvents.put(BUY_PROPERTY, e->turnManager.buyProperty((Property) e[0]));
    }

    public Map<EVENT_NAMES, TMEvent> getMyEvents() {
        return myEvents;
    }
}