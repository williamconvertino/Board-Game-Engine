package ooga.display;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ooga.GameManager;
import ooga.display.communication.EventManager.EVENT_NAMES;
import ooga.display.communication.TMEvent;
import ooga.display.game_board.GameBoardDisplay;
import ooga.display.screens.EnterPlayersScreen;
import ooga.display.screens.GameCreatorScreen;
import ooga.display.screens.OptionsMenu;
import ooga.display.screens.StartMenu;
import ooga.display.screens.endgame.LossScreen;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;
import ooga.util.ProfileManager;

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

  private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static final String ORIGINAL_STYLE = STYLE_PACKAGE + "original.css";
  private static final String MONO_STYLE = STYLE_PACKAGE + "mono.css";
  private static final String DUKE_STYLE = STYLE_PACKAGE + "duke.css";
  private static final String DEFAULT_VARIATION_NAME = "original";
  private static final String VICTORY_MESSAGE = "Congrats! %s has won the game!";
  private static final String NO_VICTOR = "This is awkward... there was no winner!";

  private String selectedTheme = ORIGINAL_STYLE;

  private static final Map<String, String> STYLESHEETS = Map.of(
      "Original", ORIGINAL_STYLE,
      "Mono", MONO_STYLE,
      "Duke", DUKE_STYLE
  );

  private final Stage myStage;
  private final ArrayList<Display> allDisplays = new ArrayList<>();
  private GameData myGameData;
  private Map<EVENT_NAMES, TMEvent> myEventMap;
  private final EnterPlayersScreen myEnterPlayerScreen;
  private GameManager myGame;
  private boolean userLoggedIn;
  private final ProfileManager myProfileManager;
  private StartMenu myStartMenu;
  private String[] userData;
  private String variationName;


  /**
   * Constructs display manager, creating a list of displays with list of displays with indices: 0:
   * Start Menu 1: Options Menu 2: Player Screen (created later) 3: Game Creator Screen (created
   * later) 4: Game Screen
   */
  public DisplayManager(Stage stage) {
    myProfileManager = new ProfileManager();
    variationName = DEFAULT_VARIATION_NAME;
    myStage = stage;
    languageResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
    myStartMenu = new StartMenu(myStage, this, languageResource);
    allDisplays.add(myStartMenu);
    allDisplays.add(new OptionsMenu(myStage, this, languageResource));
    myEnterPlayerScreen = new EnterPlayersScreen(myStage, this, languageResource, selectedTheme);
    allDisplays.add(myEnterPlayerScreen);
    allDisplays.add(new GameCreatorScreen(myStage, this, languageResource));
    currDisplay = allDisplays.get(0);
    myStage.setScene(currDisplay.getScene());


  }

  /**
   * Switches to the player name screen
   */
  public void goPlayerScreen() {
//    myEnterPlayerScreen = new EnterPlayersScreen(myStage, this, languageResource, selectedTheme);
    currDisplay = allDisplays.get(2);
    myStage.setScene(currDisplay.getScene());
  }

  /**
   * Switch screens to the gameboard and starts game
   */
  public void startGame() {
    myGame = new GameManager(this, variationName);
    myGameData = myGame.getGameData();
    myEventMap = myGame.getEventMap();
    setPlayerNames();
    setPlayerColors();
    if(allDisplays.size()==4){
      allDisplays.add(new GameBoardDisplay(myStage, this, languageResource, myEventMap, myGameData,
          selectedTheme));
    }
    else{
      allDisplays.set(4,new GameBoardDisplay(myStage, this, languageResource, myEventMap, myGameData,
          selectedTheme));
    }
    currDisplay = allDisplays.get(4);
    myStage.setScene(currDisplay.getScene());

    establishCheatCodes();
  }

  private void establishCheatCodes() {
    Scene myScene = myStage.getScene();
    myScene.addEventFilter(KeyEvent.KEY_PRESSED,
        (KeyEvent e) -> {
          myEventMap.get(EVENT_NAMES.CHEAT_CODE).execute(e.getCode());
          GameBoardDisplay gameBoardDisplay = (GameBoardDisplay) allDisplays.get(4);
          gameBoardDisplay.update();
        });
  }

  private void setPlayerNames() {
    List<Player> playerList = myGameData.getPlayers();
    int index = 0;
    for (Node node : myEnterPlayerScreen.getPlayerNameTextAreaInfo()) {
      TextField textArea = (TextField) node;
      playerList.get(index).setName(textArea.getText());
      index++;
    }
  }

  private void setPlayerColors() {
    List<Player> playerList = myGameData.getPlayers();
    int index = 0;
    for (Node node : myEnterPlayerScreen.getPlayerColors()) {
      ComboBox comboBox = (ComboBox) node;
      playerList.get(index).setColor((String) comboBox.getSelectionModel().getSelectedItem());
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
   * Switch screens to screens menu
   */
  public void goStartMenu() {
    currDisplay = allDisplays.get(0);
    myStage.setScene(currDisplay.getScene());
  }

  /**
   * Switch screens to Game Creator screen
   */
  public void goGameCreatorScreen() {
    //allDisplays.add(new GameCreatorScreen(myStage, this, languageResource));
    currDisplay = allDisplays.get(3);
    myStage.setScene(currDisplay.getScene());
  }

  /**
   * Changes the language of the app
   *
   * @param language
   */
  public void changeLanguage(String language) {
    languageResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    allDisplays.clear();
    myStartMenu = new StartMenu(myStage, this, languageResource);
    allDisplays.add(myStartMenu);
    allDisplays.add(new OptionsMenu(myStage, this, languageResource));
    allDisplays.add(new EnterPlayersScreen(myStage, this, languageResource, selectedTheme));
    allDisplays.add(new GameCreatorScreen(myStage, this, languageResource));
    currDisplay = allDisplays.get(1);
    myStage.setScene(currDisplay.getScene());
  }

  /**
   * Get index of currDisplay from allDisplays 0 - Start Menu 1 - Options Menu 2 - Player Name Menu
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


  /**
   * Loops through all the available displays, changes theme. All future added displays will also be
   * of said theme.
   *
   * @param theme the theme
   */
  public void changeTheme(String theme) {
    selectedTheme = STYLESHEETS.get(theme);
    for (Display display : allDisplays) {
      Scene scene = display.getScene();
      scene.getStylesheets().remove(0);
      scene.getStylesheets().add(getClass().getResource(selectedTheme).toExternalForm());
    }
  }

  public void rotateBoard() {
    //TODO: Will be added later - DO NOT DELETE
  }

  /**
   * Get stage
   *
   * @return myStage
   */
  public Stage getMyStage() {
    return myStage;
  }

  /**
   * Get GameData for testing
   *
   * @return myGameData
   */
  public GameData getGameData() {
    return myGameData;
  }

  /**
   * Get the language resource bundle
   *
   * @return languageResource
   */
  public ResourceBundle getLanguageResource() {
    return languageResource;
  }

  /**
   * Set Logged In
   */
  public void setLoggedIn(boolean loggedIn) {
    userLoggedIn = loggedIn;
  }

  /**
   * Check Logged In
   */
  public boolean checkLoggedIn() {
    return userLoggedIn;
  }

  /**
   * Set User Profile
   */
  public void setUserLoggedIn(String[] userdata) {
    userdata = userData;
  }

  /**
   * Return Profile Manager
   */
  public ProfileManager getProfileManager() {
    return myProfileManager;
  }

  /**
   * Update username
   */
  public void updateUser(String[] userdata) {
    userData = userdata;
    myStartMenu.setUpdateProfile(userData[0], userData[2], userData[3]);
  }

  public void setVariationName(String name) {
    variationName = name;
  }

  public String getVariationName() {
    return variationName;
  }

  /**
   * Displays an alert on the screen.
   *
   * @param type    the type of alert to show.
   * @param message the message displayed.
   */
  public void showAlert(String message, AlertType type) {
    Alert myAlert = new Alert(type);
    myAlert.setContentText(message);
    myAlert.show();
  }

  /**
   * Shows the victory screen.
   */
  public void showVictoryScreen() {
    Player victor = null;
    for (Player p : getGameData().getPlayers()) {
      if (p.isActive()) {
        victor = p;
      }
    }
    if (victor != null) {
      showAlert(String.format("%s%s", VICTORY_MESSAGE, victor.getName()), AlertType.INFORMATION);
    } else {
      showAlert(NO_VICTOR, AlertType.ERROR);
    }
  }

  public void goLossScreen() {
    allDisplays.add(new LossScreen(this, languageResource, selectedTheme, myGameData));
    int size = allDisplays.size();
    currDisplay = allDisplays.get(size - 1);
    myStage.setScene(currDisplay.getScene());
  }

  public void goToGame() {
    if (currDisplay.getClass() == LossScreen.class) {
      currDisplay = allDisplays.get(4);
      myStage.setScene(currDisplay.getScene());
    }
  }

  /**
   * Returns the display list
   *
   * @return
   */
  public List<Display> getAllDisplays(){
    return allDisplays;
  }


}