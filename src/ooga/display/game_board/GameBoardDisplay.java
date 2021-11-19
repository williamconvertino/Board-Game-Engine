package ooga.display.game_board;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.communication.DisplayStateSignaler.State;
import ooga.display.communication.EventManager.EVENT_NAMES;
import ooga.display.game_board.board.Board;
import ooga.display.game_board.bottom.Bottom;
import ooga.display.game_board.left.Left;
import ooga.display.game_board.right.Right;
import ooga.display.game_board.top.Top;

import java.util.Random;
import java.util.ResourceBundle;

import ooga.display.ui_tools.UIBuilder;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;
import ooga.model.data.tilemodels.TileModel;

/**
 * This class displays all elements of the Game BoardManager
 *
 * @author Aaric Han
 * @author Henry Huynh
 */
public class GameBoardDisplay extends Display {

  private static final String DEFAULT_RESOURCE_PACKAGE = Display.class.getPackageName() + ".resources.";
  private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static final String DEFAULT_STYLE = STYLE_PACKAGE + "gameboard.css";

  private BorderPane theGameBoard;
  private Board theBoard;
  private Top theTop;
  private Right theRight;
  private Left theLeft;
  private Bottom theBottom;

  private Stage myStage;
  private DisplayManager myDisplayManager;
  private Scene scene;
  private GameData myGameData;
  private UIBuilder myUIBuilder;

  private ResourceBundle myLanguage;
  /**
   * This constructor makes theGameBoard borderpane with all
   * elements top, left, right, bottom, and center
   */
  public GameBoardDisplay(Stage stage, DisplayManager displayManager, ResourceBundle language,
      Map<EVENT_NAMES, EventHandler> eventMap, GameData gameData) {
    myUIBuilder = new UIBuilder(language);
    myLanguage = language;
    myStage = stage;
    myGameData = gameData;
    myDisplayManager = displayManager;
    theTop = new Top(this, myDisplayManager, myLanguage);
    theRight = new Right(this, myDisplayManager, myLanguage, eventMap, gameData);
    theLeft = new Left(this, myDisplayManager, myLanguage, eventMap, gameData);
    theBottom = new Bottom(this, myDisplayManager, myLanguage);
    theBoard = new Board(this, myDisplayManager, myLanguage, gameData);

    theGameBoard = new BorderPane();
    theGameBoard.setCenter(theBoard.getComponent());
    theGameBoard.setBottom(theBottom.getComponent());
    theGameBoard.setRight(theRight.getComponent());
    theGameBoard.setLeft(theLeft.getComponent());
    theGameBoard.setTop(theTop.getTopComponent());

    makeScene();



    //List<TileModel> myTiles = gameData.getBoard().getTiles();
    //myTiles.get(0).getName();

    //List<Player> myPlayers = gameData.getPlayers();

    //gameData.getCurrentPlayer().getLocation();
    //gameData.getCurrentPlayer().getBalance();
  }

  private void makeScene() {
    scene = new Scene(theGameBoard, 1280, 800);
    scene.getStylesheets().add(DEFAULT_STYLE);
  }
  /**
   * Return theGameBoard borderpane
   */
  public BorderPane getTheGameBoard() {
    return theGameBoard;
  }

  public void updateInfo() {
    updateLeftInfo();
    updateRightInfo();
  }


  public void updateLeftInfo() {
    int currPlayer = myGameData.getPlayers().indexOf(myGameData.getCurrentPlayer());
    VBox leftComp = theLeft.getComponent();
    TabPane tabPane = (TabPane) leftComp.getChildren().get(0);
    Tab currTab = tabPane.getTabs().get(currPlayer);
    VBox tempTabVBox = (VBox) currTab.getContent();
    tempTabVBox.getChildren().set(1, new Label(String.valueOf(myGameData.getPlayers().get(currPlayer).getLocation())));
    tempTabVBox.getChildren().set(3, new Label(String.valueOf(myGameData.getPlayers().get(currPlayer).getBalance())));
    tempTabVBox.getChildren().set(5, theLeft.makePlayerTabProperties(currPlayer));
    tempTabVBox.getChildren().set(7, theLeft.makePlayerTabCards(currPlayer));
  }

  public void updateRightInfo() {
    VBox rightComp = theRight.getComponent();
    Label playerLabel = (Label) rightComp.getChildren().get(0);
    playerLabel.setText(myGameData.getNextPlayer().getName());
  }

  public void updatePlayerLocation() {
    theBoard.updateLocation();
  }


  /**
   * Get the scene
   * @return scene
   */
  @Override
  public Scene getScene() {
    return scene;
  }

}
