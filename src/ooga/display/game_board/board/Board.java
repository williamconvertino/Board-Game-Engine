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
    createBoard();
    startPieces();
    this.gameData = gameData;
  }


  public void createBoard() {

    GridPane gameBoard = new GridPane();
    gameBoard.setPrefSize(500, 500);

    for (int i = 0; i < BOARD_SIZE; i++) {
      Rectangle tile = new Rectangle(50, 50);
      tile.setFill(Color.WHITE);
      tile.setStroke(Color.BLACK);

      Circle piece = new Circle(20, Color.WHITE);
      allCirclePieces.add(piece);
      gameBoard.add(new StackPane(tile, piece), i, 0);
    }
    for (int j = 1; j < BOARD_SIZE-1; j++) {
      Rectangle tile = new Rectangle(50, 50);
      tile.setFill(Color.WHITE);
      tile.setStroke(Color.BLACK);

      Circle piece = new Circle(20, Color.WHITE);
      allCirclePieces.add(piece);
      gameBoard.add(new StackPane(tile, piece), BOARD_SIZE-1, j);
    }
    for (int k = BOARD_SIZE-1; k >= 0; k--) {
      Rectangle tile = new Rectangle(50, 50);
      tile.setFill(Color.WHITE);
      tile.setStroke(Color.BLACK);

      Circle piece = new Circle(20, Color.WHITE);
      allCirclePieces.add(piece);
      gameBoard.add(new StackPane(tile, piece), k, BOARD_SIZE-1);
    }
    for (int m = BOARD_SIZE-2; m > 0; m--) {
      Rectangle tile = new Rectangle(50, 50);
      tile.setFill(Color.WHITE);
      tile.setStroke(Color.BLACK);

      Circle piece = new Circle(20, Color.WHITE);
      allCirclePieces.add(piece);
      gameBoard.add(new StackPane(tile, piece), 0, m);
    }
    boardComponent.getChildren().add(gameBoard);
  }

  public void drawMove(Circle circle) {
    circle.setFill(Color.BLACK);
  }

  private void startPieces() {
    allPlayerLocation.add(0);
    allCirclePieces.get(allPlayerLocation.get(0)).setFill(Color.BLACK);
  }

//  public void movePiece(int playerNum, int rolledNum) {
//    int playerPos = allPlayerLocation.get(playerNum);
//    allCirclePieces.get(playerPos).setFill(Color.WHITE);
//    allPlayerLocation.set(playerNum, (rolledNum+playerPos)%BOARD_LENGTH);
//    playerPos = allPlayerLocation.get(playerNum);
//    allCirclePieces.get(playerPos).setFill(Color.BLACK);
//  }

  public void updateLocation() {
    int playerPos = gameData.getCurrentPlayer().getLocation();
    //allCirclePieces.get(playerPos).setFill(Color.WHITE);
    allCirclePieces.get(playerPos).setFill(Color.BLACK);
  }


  public VBox getComponent() {
    return boardComponent;
  }



}
