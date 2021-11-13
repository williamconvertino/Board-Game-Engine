package ooga.display.game_board;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;

/**
 * This class displays all elements of the Game BoardManager
 *
 * @author Aaric Han
 */
public class GameBoardDisplay extends Display {

  private BorderPane theGameBoard;
  private Stage myStage;
  private DisplayManager myDisplayManager;

  /**
   * This constructor makes theGameBoard borderpane with all
   * elements top, left, right, bottom, and center
   */
  public GameBoardDisplay(Stage stage, DisplayManager displayManager) {
    myStage = stage;
    myDisplayManager = displayManager;
    theGameBoard = new BorderPane();
    theGameBoard.setCenter(new HBox());
    theGameBoard.setBottom(new HBox());
    theGameBoard.setRight(new HBox());
    theGameBoard.setLeft(new HBox());
    theGameBoard.setTop(new HBox());
    makeScene();
  }

  private void makeScene() {
    Scene scene = new Scene(theGameBoard, 800, 600);
    myStage.setScene(scene);
    myStage.show();
  }
  /**
   * Return theGameBoard borderpane
   */
  public BorderPane getTheGameBoard() {
    return theGameBoard;
  }
}
