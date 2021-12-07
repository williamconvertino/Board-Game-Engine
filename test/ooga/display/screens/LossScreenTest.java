package ooga.display.screens;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import ooga.display.DisplayManager;
import ooga.util.DukeApplicationTest;
import org.junit.jupiter.api.Test;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.display.DisplayManager;
import org.junit.jupiter.api.Test;
import ooga.util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LossScreenTest extends DukeApplicationTest {
    public DisplayManager dm;

    @Override
    public void start(Stage stage) {
        dm = new DisplayManager(stage);
    }

    /**
     * Create a basic test for loss screen is accessible
     */
    @Test
    public void goToLossScreen() {
        Button start = lookup("#Start").query();
        clickOn(start);
        Button continueButton = lookup("#Continue").query();
        clickOn(continueButton);
        Button lostButton = lookup("#LossScreen").query();
        clickOn(lostButton);
        assertEquals(5, dm.getCurrDisplayIndex());
    }

    /**
     * Check that we can go back to the game
     */
    @Test
    public void goToGame() {
        Button start = lookup("#Start").query();
        clickOn(start);
        Button continueButton = lookup("#Continue").query();
        clickOn(continueButton);
        Button lostButton = lookup("#LossScreen").query();
        clickOn(lostButton);
        Button gameButton = lookup("#GoBackToGame").query();
        clickOn(gameButton);
        assertEquals(4, dm.getCurrDisplayIndex());
    }

}
