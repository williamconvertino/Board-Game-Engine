package ooga;


import javafx.stage.Stage;
import ooga.display.DisplayManager;
import ooga.display.communication.DisplayComm;
import ooga.display.communication.EventManager;
import ooga.exceptions.ImproperlyFormattedFile;
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
    public static final String VARIATION_PATH = "data/monopoly_original";


    public GameManager(Stage myStage) {
        initialize(myStage);
    }

    private void initialize(Stage myStage) {
        try {
            DisplayComm myDisplayComm = new DisplayComm();
            GameData gameData = GameDataInitializer.generateGameData(VARIATION_PATH);
            FunctionExecutor myFunctionExecutor = new FunctionExecutor(gameData, gameData.getDie(), myDisplayComm);
            TurnManager myTurnManager = new TurnManager(gameData, myFunctionExecutor, myDisplayComm);
            EventManager myEventManager = new EventManager(myTurnManager);
            DisplayManager myDisplayManager = new DisplayManager(myStage, myEventManager.getMyEvents(), gameData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}