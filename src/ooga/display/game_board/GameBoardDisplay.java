package ooga.display.game_board;

import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.communication.EventManager.EVENT_NAMES;
import ooga.display.communication.TMEvent;
import ooga.display.game_board.board.GameBoard;
import ooga.display.game_board.bottom.Bottom;
import ooga.display.game_board.left.Left;
import ooga.display.game_board.right.Right;
import ooga.display.game_board.top.Top;
import ooga.display.ui_tools.UIBuilder;
import ooga.model.data.gamedata.GameData;

/**
 * This class displays all elements of the Game BoardManager
 *
 * @author Aaric Han
 * @author Henry Huynh
 */
public class GameBoardDisplay extends Display {

  private static final String DEFAULT_RESOURCE_PACKAGE =
      Display.class.getPackageName() + ".resources.";
  private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static final String DEFAULT_STYLE = STYLE_PACKAGE + "original.css";
  private static final String DUKE_STYLE = STYLE_PACKAGE + "duke.css";
  private static final String MONO_STYLE = STYLE_PACKAGE + "mono.css";

  private final BorderPane theGameBoard;
  private final GameBoard theBoard;
  private final Top theTop;
  private final Right theRight;
  private final Left theLeft;
  private final Bottom theBottom;

  private final Stage myStage;
  private final DisplayManager myDisplayManager;
  private Scene scene;
  private final GameData myGameData;
  private final UIBuilder myUIBuilder;
  private final Map<EVENT_NAMES, TMEvent> myEventMap;

  private final ResourceBundle myLanguage;
  private String myStyle = DEFAULT_STYLE;

  /**
   * This constructor makes theGameBoard borderpane with all elements top, left, right, bottom, and
   * center
   */
  public GameBoardDisplay(Stage stage, DisplayManager displayManager, ResourceBundle language,
      Map<EVENT_NAMES, TMEvent> eventMap, GameData gameData, String theme) {
    myStyle = theme;
    myUIBuilder = new UIBuilder(language);
    myLanguage = language;
    myStage = stage;
    myGameData = gameData;
    myEventMap = eventMap;
    myDisplayManager = displayManager;
    theTop = new Top(this, myDisplayManager, myLanguage);
    theRight = new Right(this, myLanguage, eventMap, gameData);
    theLeft = new Left(myLanguage, eventMap, gameData);
    theBottom = new Bottom(myLanguage, myDisplayManager);
    theBoard = new GameBoard(myDisplayManager, myLanguage, gameData, eventMap, theme);

    theGameBoard = new BorderPane();
    theGameBoard.setCenter(theBoard.getComponent());
    theGameBoard.setRight(theRight.getComponent());
    theGameBoard.setLeft(theLeft.getComponent());
    theGameBoard.setTop(theTop.getTopComponent());
    theGameBoard.setBottom(theBottom.getComponent());

    makeScene();
  }

  private void makeScene() {
    scene = new Scene(theGameBoard, 1200, 1000);
    scene.getStylesheets().add(myStyle);
  }

  /**
   * Return theGameBoard borderpane
   */
  public BorderPane getTheGameBoard() {
    return theGameBoard;
  }

  /**
   * Update left panel with new info
   */
  private void updateLeftInfo() {
    int currPlayer = myGameData.getPlayers().indexOf(myGameData.getCurrentPlayer());
    VBox leftComp = theLeft.getComponent();
    TabPane tabPane = (TabPane) leftComp.getChildren().get(0);
    Tab currTab = tabPane.getTabs().get(currPlayer);
    VBox tempTabVBox = (VBox) currTab.getContent();
    tempTabVBox.getChildren()
        .set(2, new Circle(20, myGameData.getPlayers().get(currPlayer).getColor()));
    tempTabVBox.getChildren().set(4, new Label(String.valueOf(
        myGameData.getBoard().getTileAtIndex(myGameData.getPlayers().get(currPlayer).getLocation())
            .getName())));
    tempTabVBox.getChildren()
        .set(6, new Label(String.valueOf(myGameData.getPlayers().get(currPlayer).getBalance())));
    tempTabVBox.getChildren().set(8, theLeft.makePlayerTabProperties(currPlayer));
    tempTabVBox.getChildren().set(10, theLeft.makePlayerTabCards(currPlayer));
  }

  /**
   * Buys Property and updates the board elements
   */
  public void buyProp() {
    myEventMap.get(EVENT_NAMES.BUY_PROPERTY)
        .execute(myGameData.getBoard().getTileAtIndex(myGameData.getCurrentPlayer().getLocation()));
    update();
  }

  /**
   * Ends the turn and updates the board elements
   */
  public void endTurn() {
    myEventMap.get(EVENT_NAMES.END_TURN).execute();
    update();
    theRight.endTurn();
  }

  /**
   * Buys a house and updates board elements
   */
  public void buyHouse() {
    myEventMap.get(EVENT_NAMES.BUY_HOUSE).execute();
    update();
  }

  /**
   * Update player location
   */
  private void updatePlayerLocation() {
    theBoard.updateLocation();
  }

  /**
   * Main update method
   */
  public void update() {
    updatePlayerLocation();
    updateLeftInfo();
    theBoard.updatePropertyPopups();
  }

  /**
   * Get the scene
   *
   * @return scene
   */
  @Override
  public Scene getScene() {
    return scene;
  }

  /**
   * Get the stage
   *
   * @return myStage
   */
  public Stage getMyStage() {
    return myStage;
  }

}
