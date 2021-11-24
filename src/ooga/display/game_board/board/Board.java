package ooga.display.game_board.board;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import ooga.display.DisplayManager;
import ooga.display.communication.EventManager;
import ooga.display.communication.TMEvent;
import ooga.display.game_board.GameBoardDisplay;
import ooga.display.popup.PropertyInfoPopUp;
import ooga.display.game_board.right.TurnChoices;
import ooga.display.ui_tools.UIBuilder;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.tilemodels.TileModel;

import static ooga.display.communication.EventManager.EVENT_NAMES.*;

/**
 * Makes a game board
 *
 * @author Aaric Han
 * @author Henry Huynh
 */
public class Board {
  private static final double RECT_WIDTH = 50;
  private static final double RECT_HEIGHT = 70;
  private static final double PREF_WIDTH_BOARD = 600;
  private static final double PREF_HEIGHT_BOARD = 600;
  private static final int RADIUS = 10;
  private GameBoardDisplay myGameBoardDisplay;
  private DisplayManager myDisplayManager;
  private ResourceBundle myLanguage;
  private UIBuilder myBuilder;
  private final int BOARD_SIZE = 11;
  private final int SIDE_LENGTH = 9;

  private ArrayList<Circle> allCirclePieces = new ArrayList<Circle>();

  private VBox boardComponent;
  private GameData gameData;
  private Map<EventManager.EVENT_NAMES, TMEvent> eventMap;
  private TurnChoices myTurnChoices;

  /**
   * Constructor for the game board
   */
  public Board(GameBoardDisplay gameBoardDisplay, DisplayManager displayManager, ResourceBundle language, GameData gameData,
               Map<EventManager.EVENT_NAMES, TMEvent> eventMap) {
    this.eventMap = eventMap;
    myLanguage = language;
    myBuilder = new UIBuilder(myLanguage);
    myGameBoardDisplay = gameBoardDisplay;
    myDisplayManager = displayManager;
    boardComponent = new VBox();
    this.gameData = gameData;
    createBoard();
    startPieces();
  }

  private void createBoard() {
    GridPane gameBoard = new GridPane();
    gameBoard.setPrefSize(PREF_WIDTH_BOARD, PREF_HEIGHT_BOARD);
    int gameBoardTileCount = gameData.getBoard().getTiles().size();
    int makeTileIndex = gameData.getBoard().getTiles().size() - gameBoardTileCount;
    // Top Left Corner
    if (gameBoardTileCount > 0) {
      gameBoard.add(makeCornerProperty(makeTileIndex), 0, 0);
      makeTileIndex++;
    }

    // Top Row
    for (int i = 0; i < SIDE_LENGTH; i++) {
      if (gameBoardTileCount > 0) {
        gameBoard.add(makeTopBottomProperty(makeTileIndex), i + 1, 0);
        makeTileIndex++;
      }
      else {
        break;
      }
    }

    // Top Right Corner
    if (gameBoardTileCount > 0) {
      gameBoard.add(makeCornerProperty(makeTileIndex), BOARD_SIZE-1, 0);
      makeTileIndex++;
    }

    // Right Column
    for (int i = 0; i < SIDE_LENGTH; i++) {
      if (gameBoardTileCount > 0) {
        gameBoard.add(makeLeftRightProperty(makeTileIndex), BOARD_SIZE-1, i + 1);
        makeTileIndex++;
      }
      else {
        break;
      }
    }

    // Bottom Right Corner
    if (gameBoardTileCount > 0) {
      gameBoard.add(makeCornerProperty(makeTileIndex), BOARD_SIZE-1, BOARD_SIZE-1);
      makeTileIndex++;
    }

    // Bottom Row
    for (int i = 0; i < SIDE_LENGTH; i++) {
      if (gameBoardTileCount > 0) {
        gameBoard.add(makeTopBottomProperty(makeTileIndex), SIDE_LENGTH-i, BOARD_SIZE-1);
        makeTileIndex++;
      }
      else {
        break;
      }
    }

    // Bottom Left Corner
    if (gameBoardTileCount > 0) {
      gameBoard.add(makeCornerProperty(makeTileIndex), 0, BOARD_SIZE-1);
      makeTileIndex++;
    }

    // Left Column
    for (int i = 0; i < SIDE_LENGTH; i++) {
      if (gameBoardTileCount > 0) {
        gameBoard.add(makeLeftRightProperty(makeTileIndex), 0, SIDE_LENGTH-i);
        makeTileIndex++;
      }
      else {
        break;
      }
    }
    boardComponent.getChildren().add(gameBoard);
  }

  private StackPane makeTopBottomProperty(int gameBoardTileCount) {
    Rectangle tile = getRectangle(RECT_WIDTH, RECT_HEIGHT, gameBoardTileCount);
    StackPane stackPane = new StackPane();
    stackPane.setId(String.format("Tile%d", gameBoardTileCount));
    Label name = new Label(gameData.getBoard().getTileAtIndex(gameBoardTileCount).getName());
    stackPane.getChildren().addAll(tile, name);
    PropertyInfoPopUp popup = new PropertyInfoPopUp(gameData.getBoard().getTileAtIndex(gameBoardTileCount), myBuilder, myLanguage);
    stackPane.setOnMouseClicked(e -> popup.showPopup(myDisplayManager.getMyStage()));
    return stackPane;
  }


  private StackPane makeLeftRightProperty(int gameBoardTileCount) {
    Rectangle tile = getRectangle(RECT_HEIGHT, RECT_WIDTH, gameBoardTileCount);
    StackPane stackPane = new StackPane();
    stackPane.setId(String.format("Tile%d", gameBoardTileCount));
    Label name = new Label(gameData.getBoard().getTileAtIndex(gameBoardTileCount).getName());
    stackPane.getChildren().addAll(tile, name);
    PropertyInfoPopUp popup = new PropertyInfoPopUp(gameData.getBoard().getTileAtIndex(gameBoardTileCount), myBuilder, myLanguage);
    stackPane.setOnMouseClicked(e -> popup.showPopup(myDisplayManager.getMyStage()));
    return stackPane;
  }

  private StackPane makeCornerProperty(int gameBoardTileCount) {
    Rectangle tile = getRectangle(RECT_HEIGHT, RECT_HEIGHT, gameBoardTileCount);
    StackPane stackPane = new StackPane();
    stackPane.setId(String.format("Tile%d", gameBoardTileCount));
    Label name = new Label(gameData.getBoard().getTileAtIndex(gameBoardTileCount).getName());
    name.setWrapText(true);
    name.setMaxWidth(RECT_HEIGHT);
    stackPane.getChildren().addAll(tile, name);
    PropertyInfoPopUp popup = new PropertyInfoPopUp(gameData.getBoard().getTileAtIndex(gameBoardTileCount), myBuilder, myLanguage);
    stackPane.setOnMouseClicked(e -> popup.showPopup(myDisplayManager.getMyStage()));
    return stackPane;
  }

  private Rectangle getRectangle(double width, double height, int gameBoardTileCount) {
    Rectangle tile = new Rectangle(width, height);
    tile.setFill(Color.WHITE);
    tile.setStroke(Color.BLACK);
    tile.setOnMouseClicked(e->showDetails(gameData.getBoard().getTileAtIndex(gameData.getBoard().getTiles().size() - gameBoardTileCount)));
    return tile;
  }

  private void showDetails(TileModel tile) {
    eventMap.get(SELECT_TILE).execute(tile);
  }


  private void startPieces() {
    GridPane board = (GridPane) boardComponent.getChildren().get(0);
    StackPane stackPane = (StackPane) board.getChildren().get(0);
    for(int i = 0; i < gameData.getPlayers().size(); i++) {

      if(i == 0) {
        allCirclePieces.add(new Circle(RADIUS, Color.BLACK));
        stackPane.getChildren().add(allCirclePieces.get(i));
        StackPane.setAlignment(allCirclePieces.get(i), Pos.TOP_LEFT);
      }
      else if(i == 1) {
        allCirclePieces.add(new Circle(RADIUS, Color.RED));
        stackPane.getChildren().add(allCirclePieces.get(i));
        StackPane.setAlignment(allCirclePieces.get(i), Pos.TOP_RIGHT);
      }
      else if(i == 2) {
        allCirclePieces.add(new Circle(RADIUS, Color.GREEN));
        stackPane.getChildren().add(allCirclePieces.get(i));
        StackPane.setAlignment(allCirclePieces.get(i), Pos.BOTTOM_RIGHT);
      }
      else if(i == 3) {
        allCirclePieces.add(new Circle(RADIUS, Color.BLUE));
        stackPane.getChildren().add(allCirclePieces.get(i));
        StackPane.setAlignment(allCirclePieces.get(i), Pos.BOTTOM_LEFT);
      }

    }
  }

  /**
   * Update the circle piece of the player
   */
  public void updateLocation() {
    GridPane board = (GridPane) boardComponent.getChildren().get(0);
    int playerPos = gameData.getCurrentPlayer().getLocation();
    int currPlayer = gameData.getPlayers().indexOf(gameData.getCurrentPlayer());
    StackPane stackPane = (StackPane) board.getChildren().get(playerPos);
    stackPane.getChildren().add(allCirclePieces.get(currPlayer));
  }


  /**
   * Return this component
   * @return boardComponent
   */
  public VBox getComponent() {
    return boardComponent;
  }

  /**
   * Get the list of circle pieces
   * @return allCirclePieces
   */
  public ArrayList<Circle> getAllCirclePieces() {
    return allCirclePieces;
  }
}
