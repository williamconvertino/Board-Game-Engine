package ooga.display.game_board;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.game_board.top.Top;

/**
 * This class displays all elements of the Game BoardManager
 *
 * @author Aaric Han
 */
public class GameBoardDisplay extends Display {

  private BorderPane theGameBoard;
  private Top theTop;

  private Stage myStage;
  private DisplayManager myDisplayManager;
  private Scene scene;

  /**
   * This constructor makes theGameBoard borderpane with all
   * elements top, left, right, bottom, and center
   */
  public GameBoardDisplay(Stage stage, DisplayManager displayManager) {
    myStage = stage;
    myDisplayManager = displayManager;
    theTop = new Top(this, myDisplayManager);
    theGameBoard = new BorderPane();
    theGameBoard.setCenter(new HBox());
    theGameBoard.setBottom(new HBox());
    theGameBoard.setRight(new HBox());
    theGameBoard.setLeft(new HBox());
    theGameBoard.setTop(theTop.getTopComponent());
    makeScene();
  }

  private void makeScene() {
    scene = new Scene(theGameBoard, 800, 600);
    myStage.setScene(scene);
    myStage.show();
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
