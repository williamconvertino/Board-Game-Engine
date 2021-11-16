package ooga.display.game_board;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.game_board.left.Left;
import ooga.display.game_board.right.Right;
import ooga.display.game_board.top.Top;

import java.util.ResourceBundle;

/**
 * This class displays all elements of the Game BoardManager
 *
 * @author Aaric Han
 */
public class GameBoardDisplay extends Display {

  private BorderPane theGameBoard;
  private Top theTop;
  private Right theRight;
  private Left theLeft;
  //private Bottom theBottom;

  private Stage myStage;
  private DisplayManager myDisplayManager;
  private Scene scene;

  private ResourceBundle myLanguage;
  /**
   * This constructor makes theGameBoard borderpane with all
   * elements top, left, right, bottom, and center
   */
  public GameBoardDisplay(Stage stage, DisplayManager displayManager, ResourceBundle language) {
    myLanguage = language;
    myStage = stage;
    myDisplayManager = displayManager;
    theTop = new Top(this, myDisplayManager);
    theRight = new Right(this, myDisplayManager);
    theLeft = new Left(this, myDisplayManager, myLanguage);

    theGameBoard = new BorderPane();
    theGameBoard.setCenter(new HBox());
    theGameBoard.setBottom(new HBox());
    theGameBoard.setRight(theRight.getComponent());
    theGameBoard.setLeft(theLeft.getComponent());
    theGameBoard.setTop(theTop.getTopComponent());
    makeScene();
  }

  private void makeScene() {
    scene = new Scene(theGameBoard, 800, 600);
  }
  /**
   * Return theGameBoard borderpane
   */
  public BorderPane getTheGameBoard() {
    return theGameBoard;
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
