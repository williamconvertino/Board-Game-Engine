package ooga;


import java.util.Map;
import ooga.display.DisplayManager;
import ooga.display.communication.DisplayComm;
import ooga.display.communication.EventManager;
import ooga.display.communication.EventManager.EVENT_NAMES;
import ooga.display.communication.TMEvent;
import ooga.model.data.gamedata.GameData;
import ooga.model.game_handling.FunctionExecutor;
import ooga.model.game_handling.TurnManager;
import ooga.model.game_initialization.GameDataInitializer;

/**
 * This class initializes and manages the game.
 *
 * @author William Convertino
 * @author Aaric Han
 * @since 0.0.1
 */
public class GameManager {

  //TODO: Replace this with a file-picker
  public static final String DEFAULT_VARIATION_NAME = "original";

  //The display communication module of the game.
  private final DisplayComm myDisplayComm;

  //The gamedata of the game.
  private GameData myGameData;

  //The function executor with which the game is run.
  private FunctionExecutor myFunctionExecutor;

  //The turn manager that dictates the turn structure of a game.
  private TurnManager myTurnManager;

  //The event manager of the game.
  private EventManager myEventManager;

  /**
   * Constructs and runs a new instance of the game with the given display.
   *
   * @param display
   */
  public GameManager(DisplayManager display, String variationName) {
    myDisplayComm = new DisplayComm(display);
    try {
      myGameData = (new GameDataInitializer()).generateGameData(variationName, myDisplayComm);
      myFunctionExecutor = new FunctionExecutor(myGameData, myGameData.getDie(), myDisplayComm);
      myTurnManager = new TurnManager(myGameData, myFunctionExecutor, myDisplayComm);
      myEventManager = new EventManager(myTurnManager);

    } catch (Exception e) {
      e.printStackTrace();
      myDisplayComm.showException(e);
    }
  }

  /**
   * Returns the gamedata of the current game.
   *
   * @return the gamedata of the current game.
   */
  public GameData getGameData() {
    return myGameData;
  }

  /**
   * Returns the event map associated with the current game.
   *
   * @return a map of event names and their associated TurnManager events.
   */
  public Map<EVENT_NAMES, TMEvent> getEventMap() {
    return myEventManager.getMyEvents();
  }

}