package ooga;


import java.util.Map;
import javafx.stage.Stage;
import ooga.display.DisplayManager;
import ooga.display.communication.DisplayComm;
import ooga.display.communication.DisplayStateSignaler;
import ooga.display.communication.EventManager;
import ooga.display.communication.EventManager.EVENT_NAMES;
import ooga.display.communication.TMEvent;
import ooga.model.data.gamedata.GameData;
import ooga.model.game_handling.FunctionExecutor;
import ooga.model.game_handling.turn_manager.TurnManager;

/**
 * This class initializes and manages the game.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public class GameManager {

    //TODO: Replace this with a file-picker
    public static final String VARIATION_NAME = "monopoly_original";

    private DisplayComm myDisplayComm;
    private GameData myGameData;
    private FunctionExecutor myFunctionExecutor;
    private TurnManager myTurnManager;
    private EventManager myEventManager;
    private DisplayStateSignaler myDisplayStateSignaler;

    public GameManager(DisplayManager dm) {
        initialize(dm);
    }

    private void initialize(DisplayManager dm) {
        myDisplayComm = new DisplayComm(dm);
        try {
            myGameData = GameDataInitializer.generateGameData(VARIATION_NAME, myDisplayComm);
            myFunctionExecutor = new FunctionExecutor(myGameData, myGameData.getDie(), myDisplayComm);
            myTurnManager = new TurnManager(myGameData, myFunctionExecutor, myDisplayComm);
            myEventManager = new EventManager(myTurnManager);
        } catch (Exception e) {
            myDisplayComm.showException(e);
        }
    }

    public GameData getGameData() {
        return myGameData;
    }

    public Map<EVENT_NAMES, TMEvent> getEventMap() {
        return myEventManager.getMyEvents();
    }

}