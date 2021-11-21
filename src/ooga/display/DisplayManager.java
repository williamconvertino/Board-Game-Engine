package ooga.display;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ooga.display.communication.DisplayStateSignaler.State;
import ooga.display.communication.EventManager;
import ooga.display.communication.EventManager.EVENT_NAMES;
import ooga.display.communication.TMEvent;
import ooga.display.game_board.GameBoardDisplay;
import ooga.display.start.EnterPlayersScreen;
import ooga.display.start.OptionsMenu;
import ooga.display.start.StartMenu;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;
import ooga.model.game_handling.GameInitializer;

/**
 * This class manages the display elements of the program.
 *
 * @author William Convertino
 * @author Henry Huynh
 * @author Aaric Han
 * @since 0.0.1
 */
public class DisplayManager {

  private ResourceBundle languageResource; 
  private Display currDisplay;
  private static final String DEFAULT_RESOURCE_PACKAGE =
          Display.class.getPackageName() + ".resources.";
  private Stage myStage;
  private ArrayList<Display> allDisplays = new ArrayList<>();
  private GameInitializer myInitializer;
  private GameData myGameData;
  private Map<EVENT_NAMES, TMEvent> myEventMap;
  private EnterPlayersScreen myEnterPlayerScreen;



  /**
   * Default constructor
   */
  public DisplayManager(Stage stage, Map<EVENT_NAMES, TMEvent> eventMap, GameData gameData) {
    myStage = stage;
    myGameData = gameData;
    myEventMap = eventMap;
    myInitializer = new GameInitializer();
    languageResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
    allDisplays.add(new StartMenu(myStage, this, languageResource));
    allDisplays.add(new OptionsMenu(myStage, this, languageResource));
    currDisplay = allDisplays.get(0);
    myStage.setScene(currDisplay.getScene());
    //displayElement();
  }

  public void goPlayerScreen() {
    myEnterPlayerScreen = new EnterPlayersScreen(myStage, this, languageResource, myGameData);
    allDisplays.add(myEnterPlayerScreen);
    currDisplay = allDisplays.get(2);
    myStage.setScene(currDisplay.getScene());
    myInitializer.initialize();
  }

  public void startGame() {
    setPlayerNames();
    allDisplays.add(new GameBoardDisplay(myStage, this, languageResource, myEventMap, myGameData));
    currDisplay = allDisplays.get(3);
    myStage.setScene(currDisplay.getScene());
    myInitializer.initialize();
  }

  private void setPlayerNames() {
    List<Player> playerList = myGameData.getPlayers();
    int index = 0;
    for (Node node : myEnterPlayerScreen.getTextAreaInfo()) {
      TextField textArea = (TextField) node;
      playerList.get(index).setName(textArea.getText());
      index++;
    }
  }

  public void goOptions() {
    currDisplay = allDisplays.get(1);
    myStage.setScene(currDisplay.getScene());
  }

  public void goStartMenu() {
    currDisplay = allDisplays.get(0);
    myStage.setScene(currDisplay.getScene());
  }

  public void changePlayerCount() {

  }

  public void changeTheme(String e) {

  }

  public void rotateBoard() {
  }

  public void changeLanguage(String language) {
    languageResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    allDisplays.clear();
    allDisplays.add(new StartMenu(myStage, this, languageResource));
    allDisplays.add(new OptionsMenu(myStage, this, languageResource));
    currDisplay = allDisplays.get(1);
    myStage.setScene(currDisplay.getScene());
  }
}