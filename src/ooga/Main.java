package ooga;


import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Initializes and runs the game.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {

        GameManager myGame = new GameManager(stage);

    }
}
