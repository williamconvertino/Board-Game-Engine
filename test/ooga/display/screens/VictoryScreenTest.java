//package ooga.display.screens;
//
//import javafx.application.Platform;
//import javafx.scene.control.Button;
//import javafx.stage.Stage;
//import ooga.display.DisplayManager;
//import ooga.util.DukeApplicationTest;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class VictoryScreenTest extends DukeApplicationTest {
//    public DisplayManager dm;
//
//    @Override
//    public void start(Stage stage) {
//        dm = new DisplayManager(stage);
//    }
//
//    /**
//     * Create a basic test for victory screen is accessible
//     */
//    @Test
//    public void goToWinScreen() {
//        Button start = lookup("#Start").query();
//        clickOn(start);
//        Button continueButton = lookup("#Continue").query();
//        clickOn(continueButton);
//        Platform.runLater(new Runnable(){
//            @Override
//            public void run() {
//                dm.goVictoryScreen();
//                assertEquals(5, dm.getCurrDisplayIndex());
//            }
//        });
//
//
//    }
//
//    /**
//     * Check that we can go back to the home
//     */
//    @Test
//    public void goToHome() {
//        Button start = lookup("#Start").query();
//        clickOn(start);
//        Button continueButton = lookup("#Continue").query();
//        clickOn(continueButton);
//        Button lostButton = lookup("#LossScreen").query();
//        clickOn(lostButton);
//        Button gameButton = lookup("#GoBackToGame").query();
//        clickOn(gameButton);
//        assertEquals(4, dm.getCurrDisplayIndex());
//    }
//}
