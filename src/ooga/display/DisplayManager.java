package ooga.display;

import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import ooga.display.communication.DisplayStateSignaler.State;
import ooga.display.communication.EventManager;
import ooga.display.game_board.GameBoardDisplay;
import ooga.display.start.StartMenu;
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
  /**
   * Default constructor
   */
  public DisplayManager(Stage stage, Map<State, EventHandler> eventMap) {
    myStage = stage;
    languageResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
    allDisplays.add(new StartMenu(myStage, this, languageResource));
    allDisplays.add(new GameBoardDisplay(myStage, this));
    currDisplay = allDisplays.get(1);
    //displayElement();
  }

  public void startGame() {
  }

  private void displayElement() {

  }

  public void goOptions() {
  }

  public void goStartMenu() {
    currDisplay = allDisplays.get(0);
    myStage.setScene(currDisplay.getScene());
  }
}