package ooga;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.display.DisplayManager;


/**
 * Initializes and runs the program.
 *
 * @author William Convertino
 * @author Casey Goldstein
 * @author Aaric Han
 * @author Henry Huynh
 * @author Jordon Castleman
 * @since 0.0.1
 */
public class Main extends Application {

  /**
   * @see Application#start(Stage)
   */
  @Override
  public void start(Stage stage) {
    DisplayManager displayManager = new DisplayManager(stage);
  }
}
