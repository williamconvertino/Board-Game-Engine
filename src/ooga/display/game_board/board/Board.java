package ooga.display.game_board.board;

import java.awt.Canvas;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
  private GameBoardDisplay myGameBoardDisplay;
  private DisplayManager myDisplayManager;
  private Canvas centerComponent;
  private ResourceBundle myLanguage;
  private UIBuilder myBuilder;
  private final int BOARD_SIZE = 11;
  private final int BOARD_LENGTH = BOARD_SIZE*2 + (BOARD_SIZE - 2)*2;

  //FIXME: change to list
  private ArrayList<Circle> allCirclePieces = new ArrayList<>();
  private ArrayList<Integer> allPlayerLocation = new ArrayList<>();

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


  public void createBoard() {

    GridPane gameBoard = new GridPane();
    gameBoard.setPrefSize(500, 500);

    int gameBoardTileCount = gameData.getBoard().getTiles().size();

    for (int i = 0; i < BOARD_SIZE; i++) {
      if (gameBoardTileCount <= 0) {
        break;
      }
      Rectangle tile = new Rectangle(50, 50);
      tile.setFill(Color.WHITE);
      tile.setStroke(Color.BLACK);

      gameBoard.add(new StackPane(tile), i, 0);
      gameBoardTileCount--;
    }
    for (int j = 1; j < BOARD_SIZE-1; j++) {
      if (gameBoardTileCount <= 0) {
        break;
      }
      Rectangle tile = new Rectangle(50, 50);
      tile.setFill(Color.WHITE);
      tile.setStroke(Color.BLACK);
      gameBoard.add(new StackPane(tile), BOARD_SIZE-1, j);
      gameBoardTileCount--;
    }
    for (int k = BOARD_SIZE-1; k >= 0; k--) {
      if (gameBoardTileCount <= 0) {
        break;
      }
      Rectangle tile = new Rectangle(50, 50);
      tile.setFill(Color.WHITE);
      tile.setStroke(Color.BLACK);

      gameBoard.add(new StackPane(tile), k, BOARD_SIZE-1);
      gameBoardTileCount--;
    }
    for (int m = BOARD_SIZE-2; m > 0; m--) {
      if (gameBoardTileCount <= 0) {
        break;
      }
      Rectangle tile = new Rectangle(50, 50);
      tile.setFill(Color.WHITE);
      tile.setStroke(Color.BLACK);

      gameBoard.add(new StackPane(tile), 0, m);
      gameBoardTileCount--;
    }
    boardComponent.getChildren().add(gameBoard);

  }

  public void drawMove(Circle circle) {
    circle.setFill(Color.BLACK);
  }

  private void startPieces() {
    GridPane board = (GridPane) boardComponent.getChildren().get(0);
    StackPane stackPane = (StackPane) board.getChildren().get(0);
    for(int i = 0; i < gameData.getPlayers().size(); i++) {
      if(i == 0) {
        allCirclePieces.add(new Circle(20, Color.BLACK));
        stackPane.getChildren().add(allCirclePieces.get(i));
      }
      else if(i == 1) {
        allCirclePieces.add(new Circle(20, Color.RED));
        stackPane.getChildren().add(allCirclePieces.get(i));
      }
      else if(i == 2) {
        allCirclePieces.add(new Circle(20, Color.GREEN));
        stackPane.getChildren().add(allCirclePieces.get(i));
      }
      else if(i == 3) {
        allCirclePieces.add(new Circle(20, Color.BLUE));
        stackPane.getChildren().add(allCirclePieces.get(i));
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

  public ArrayList<Circle> getAllCirclePieces() {
    return allCirclePieces;
  }



}
