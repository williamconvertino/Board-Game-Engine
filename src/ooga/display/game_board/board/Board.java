package ooga.display.game_board.board;

import java.awt.Canvas;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import ooga.display.DisplayManager;
import ooga.display.game_board.GameBoardDisplay;
import ooga.display.ui_tools.UIBuilder;
import ooga.model.data.gamedata.GameData;

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
  private Canvas centerComponent;
  private ResourceBundle myLanguage;
  private UIBuilder myBuilder;
  private final int BOARD_SIZE = 11;
  private final int SIDE_LENGTH = 9;
  private final int BOARD_LENGTH = BOARD_SIZE*2 + (BOARD_SIZE - 2)*2;

  private List<Circle> allCirclePieces = new ArrayList<Circle>();
  private List<Integer> allPlayerLocation = new ArrayList<>();

  private VBox boardComponent;
  private GameData gameData;

  /**
   * Constructor for the game board
   */
  public Board(GameBoardDisplay gameBoardDisplay, DisplayManager displayManager, ResourceBundle language, GameData gameData) {
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
    // Top Left Corner
    if (gameBoardTileCount > 0) {
      gameBoard.add(makeCornerProperty(gameBoardTileCount), 0, 0);
      gameBoardTileCount--;
    }

    // Top Row
    for (int i = 0; i < SIDE_LENGTH; i++) {
      if (gameBoardTileCount > 0) {
        gameBoard.add(makeTopBottomProperty(gameBoardTileCount), i + 1, 0);
        gameBoardTileCount--;
      }
      else {
        break;
      }
    }

    // Top Right Corner
    if (gameBoardTileCount > 0) {
      gameBoard.add(makeCornerProperty(gameBoardTileCount), BOARD_SIZE-1, 0);
      gameBoardTileCount--;
    }

    // Right Column
    for (int i = 0; i < SIDE_LENGTH; i++) {
      if (gameBoardTileCount > 0) {
        gameBoard.add(makeLeftRightProperty(gameBoardTileCount), BOARD_SIZE-1, i + 1);
        gameBoardTileCount--;
      }
      else {
        break;
      }
    }

    // Bottom Right Corner
    if (gameBoardTileCount > 0) {
      gameBoard.add(makeCornerProperty(gameBoardTileCount), BOARD_SIZE-1, BOARD_SIZE-1);
      gameBoardTileCount--;
    }

    // Bottom Row
    for (int i = 0; i < SIDE_LENGTH; i++) {
      if (gameBoardTileCount > 0) {
        gameBoard.add(makeTopBottomProperty(gameBoardTileCount), SIDE_LENGTH-i, BOARD_SIZE-1);
        gameBoardTileCount--;
      }
      else {
        break;
      }
    }

    // Bottom Left Corner
    if (gameBoardTileCount > 0) {
      gameBoard.add(makeCornerProperty(gameBoardTileCount), 0, BOARD_SIZE-1);
      gameBoardTileCount--;
    }

    // Left Column
    for (int i = 0; i < SIDE_LENGTH; i++) {
      if (gameBoardTileCount > 0) {
        gameBoard.add(makeLeftRightProperty(gameBoardTileCount), 0, SIDE_LENGTH-i);
        gameBoardTileCount--;
      }
      else {
        break;
      }
    }
    boardComponent.getChildren().add(gameBoard);
  }

  private StackPane makeTopBottomProperty(int gameBoardTileCount) {
    Rectangle tile = getRectangle();
    StackPane stackPane = new StackPane();
    Label name = new Label(gameData.getBoard().getTileAtIndex(gameData.getBoard().getTiles().size() - gameBoardTileCount).getName());
    stackPane.getChildren().addAll(tile, name);

    return stackPane;
  }

  private StackPane makeLeftRightProperty(int gameBoardTileCount) {
    Rectangle tile = getRectangle();
    StackPane stackPane = new StackPane();
    Label name = new Label(gameData.getBoard().getTileAtIndex(gameData.getBoard().getTiles().size() - gameBoardTileCount).getName());
    stackPane.getChildren().addAll(tile, name);

    return stackPane;
  }

  private StackPane makeCornerProperty(int gameBoardTileCount) {
    Rectangle tile = getRectangle();
    StackPane stackPane = new StackPane();
    Label name = new Label(gameData.getBoard().getTileAtIndex(gameData.getBoard().getTiles().size() - gameBoardTileCount).getName());
    name.setWrapText(true);
    name.setMaxWidth(RECT_HEIGHT);
    stackPane.getChildren().addAll(tile, name);

    return stackPane;
  }

  private Rectangle getRectangle() {
    Rectangle tile = new Rectangle(RECT_HEIGHT, RECT_WIDTH);
    tile.setFill(Color.WHITE);
    tile.setStroke(Color.BLACK);
    return tile;
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

  public void updateLocation() {
    GridPane board = (GridPane) boardComponent.getChildren().get(0);
    int playerPos = gameData.getCurrentPlayer().getLocation();
    int currPlayer = gameData.getPlayers().indexOf(gameData.getCurrentPlayer());
    StackPane stackPane = (StackPane) board.getChildren().get(playerPos);
    stackPane.getChildren().add(allCirclePieces.get(currPlayer));
  }


  public VBox getComponent() {
    return boardComponent;
  }

  public List<Circle> getAllCirclePieces() {
    return allCirclePieces;
  }



}
