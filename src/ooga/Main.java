package ooga;


import javafx.application.Application;
import javafx.stage.Stage;
import ooga.display.DisplayManager;


/**
 * Initializes and runs the game.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        DisplayManager displayManager = new DisplayManager(stage);
    }
}
