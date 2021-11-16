package ooga.display.game_board.board;

import java.awt.Canvas;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ooga.display.DisplayManager;
import ooga.display.game_board.GameBoardDisplay;
import ooga.display.ui_tools.UIBuilder;

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

  private VBox boardComponent;

  /**
   * Constructor for the game board
   */
  public Board(GameBoardDisplay gameBoardDisplay, DisplayManager displayManager, ResourceBundle language) {
    myLanguage = language;
    myBuilder = new UIBuilder(myLanguage);
    myGameBoardDisplay = gameBoardDisplay;
    myDisplayManager = displayManager;
    boardComponent = new VBox();
    createBoard();
  }


  public void createBoard() {

    GridPane gameBoard = new GridPane();
    gameBoard.setPrefSize(500, 500);

    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {

        Rectangle tile = new Rectangle(50, 50);
        tile.setFill(Color.WHITE);
        tile.setStroke(Color.BLACK);

        Text text = new Text();
        text.setFont(Font.font(40));
        gameBoard.add(new StackPane(tile, text), j, i);

        //GridPane.setRowIndex(tile, i);
        //GridPane.setColumnIndex(tile, j);
        //gameBoard.getChildren().addAll(tile, text);
        tile.setOnMouseClicked(event -> drawMove(text));
      }
    }
    boardComponent.getChildren().add(gameBoard);
  }

  public void drawMove(Text text) {
    text.setText("O");
    text.setFill(Color.BLACK);
  }

  public VBox getComponent() {
    return boardComponent;
  }



}
