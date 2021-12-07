package ooga.display.communication;

import static ooga.display.communication.EventManager.EVENT_NAMES.BUY_HOUSE;
import static ooga.display.communication.EventManager.EVENT_NAMES.BUY_PROPERTY;
import static ooga.display.communication.EventManager.EVENT_NAMES.CHEAT_CODE;
import static ooga.display.communication.EventManager.EVENT_NAMES.END_TURN;
import static ooga.display.communication.EventManager.EVENT_NAMES.ROLL;
import static ooga.display.communication.EventManager.EVENT_NAMES.SELECT_TILE;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.game_handling.TurnManager;

/**
 * This class creates the buttons for the game and send them to the DisplayManager
 *
 * @author William Convertino
 * @author Aaric Han
 * @author Henry Huynh
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
    SELECT_TILE,
    CHEAT_CODE
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
    myEvents.put(ROLL, e -> turnManager.roll());
    myEvents.put(END_TURN, e -> turnManager.endTurn());
    myEvents.put(SELECT_TILE, e -> turnManager.setSelectedTile((TileModel) e[0]));
    myEvents.put(BUY_PROPERTY, e -> turnManager.buyProperty((TileModel) e[0]));
    myEvents.put(CHEAT_CODE, e -> turnManager.executeCheatCode((KeyCode) e[0]));
    myEvents.put(BUY_HOUSE, e -> turnManager.buyHouse());
  }

  /**
   * Get the events map
   *
   * @return myEvents
   */
  public Map<EVENT_NAMES, TMEvent> getMyEvents() {
    return myEvents;
  }
}