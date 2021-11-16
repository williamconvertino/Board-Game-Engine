package ooga.display.game_board.bottom;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import ooga.display.DisplayManager;
import ooga.display.game_board.GameBoardDisplay;
import ooga.display.ui_tools.UIBuilder;

import java.util.ResourceBundle;

/**
 * This is the bottom display element of the game display
 *
 * @author Henry Huynh
 */
public class Bottom {

    private GameBoardDisplay myGameBoardDisplay;
    private DisplayManager myDisplayManager;
    private VBox bottomComponent;
    private UIBuilder myUIBuilder;
    private ResourceBundle myLangResource;
    /**
     * The constructor for the left display element
     */
    public Bottom(GameBoardDisplay gameBoardDisplay, DisplayManager displayManager, ResourceBundle language) {
        myGameBoardDisplay = gameBoardDisplay;
        myDisplayManager = displayManager;
        bottomComponent = new VBox();
        myLangResource = language;
        myUIBuilder = new UIBuilder(myLangResource);
        makeBottomComponent();
    }


    /**
     * Make the UI for the bottomComponent
     */
    private void makeBottomComponent() {
        bottomComponent.getChildren().add(myUIBuilder.makeLabel("Test"));
    }

    /**
     * Returns the bottom component VBox
     * @return
     */
    public VBox getComponent() {
        return bottomComponent;
    }

}
