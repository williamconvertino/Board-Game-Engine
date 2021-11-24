package ooga.display;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ooga.display.communication.EventManager.EVENT_NAMES;
import ooga.display.communication.TMEvent;
import ooga.display.game_board.GameBoardDisplay;
import ooga.display.start.EnterPlayersScreen;
import ooga.display.start.OptionsMenu;
import ooga.display.start.StartMenu;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;

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
    languageResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
    allDisplays.add(new StartMenu(myStage, this, languageResource));
    allDisplays.add(new OptionsMenu(myStage, this, languageResource));
    currDisplay = allDisplays.get(0);
    myStage.setScene(currDisplay.getScene());
    //displayElement();
  }

  /**
   * Switches to the player name screen
   */
  public void goPlayerScreen() {
    myEnterPlayerScreen = new EnterPlayersScreen(myStage, this, languageResource, myGameData);
    allDisplays.add(myEnterPlayerScreen);
    currDisplay = allDisplays.get(2);
    myStage.setScene(currDisplay.getScene());
  }

  /**
   * Switch screens to the gameboard and starts game
   */
  public void startGame() {
    setPlayerNames();
    allDisplays.add(new GameBoardDisplay(myStage, this, languageResource, myEventMap, myGameData));
    currDisplay = allDisplays.get(3);
    myStage.setScene(currDisplay.getScene());
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

  /**
   * Switch screens to options menu
   */
  public void goOptions() {
    currDisplay = allDisplays.get(1);
    myStage.setScene(currDisplay.getScene());
  }

  /**
   * Switch screens to start menu
   */
  public void goStartMenu() {
    currDisplay = allDisplays.get(0);
    myStage.setScene(currDisplay.getScene());
  }

  /**
   * Changes the language of the app
   * @param language
   */
  public void changeLanguage(String language) {
    languageResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    allDisplays.clear();
    allDisplays.add(new StartMenu(myStage, this, languageResource));
    allDisplays.add(new OptionsMenu(myStage, this, languageResource));
    currDisplay = allDisplays.get(1);
    myStage.setScene(currDisplay.getScene());
  }

  /**
   * Get index of currDisplay from allDisplays
   * 0 - Start Menu
   * 1 - Options Menu
   * 2 - Player Name Menu
   * 3 - Game Board
   *
   * @return the currDisplay index
   */
  public int getCurrDisplayIndex() {
    return allDisplays.indexOf(currDisplay);
  }

  public void changePlayerCount() {
    //TODO: Will be added later - DO NOT DELETE
  }

  public void changeTheme(String e) {
    //TODO: Will be added later - DO NOT DELETE
  }

  public void rotateBoard() {
    //TODO: Will be added later - DO NOT DELETE
  }

  /**
   * Get stage
   * @return myStage
   */
  public Stage getMyStage() {
    return myStage;
  }

  /**
   * Get the language resource bundle
   * @return languageResource
   */
  public ResourceBundle getLanguageResource() {
    return languageResource;
  }
}