package ooga.display.game_board.left;

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
 * This is the left display element of the game display
 *
 * @author Henry Huynh
 */
public class Left {

    private GameBoardDisplay myGameBoardDisplay;
    private DisplayManager myDisplayManager;
    private VBox leftComponent;
    private UIBuilder myUIBuilder;
    private ResourceBundle myLangResource;
    /**
     * The constructor for the left display element
     */
    public Left(GameBoardDisplay gameBoardDisplay, DisplayManager displayManager, ResourceBundle language) {
        myGameBoardDisplay = gameBoardDisplay;
        myDisplayManager = displayManager;
        leftComponent = new VBox();
        myLangResource = language;
        myUIBuilder = new UIBuilder(myLangResource);
        makeLeftComponent();
    }


    /**
     * Make the UI for the leftComponent
     */
    private void makeLeftComponent() {
        Tab tab1 = myUIBuilder.makeTab("Player1Info");
        tab1.setContent(myUIBuilder.makeLabel("ShowPlayer1Items"));
        Tab tab2 = myUIBuilder.makeTab("Player2Info");
        tab2.setContent(myUIBuilder.makeLabel("ShowPlayer2Items"));
        Tab tab3 = myUIBuilder.makeTab("Player3Info");
        tab3.setContent(myUIBuilder.makeLabel("ShowPlayer3Items"));
        Tab tab4 = myUIBuilder.makeTab("Player4Info");
        tab3.setContent(myUIBuilder.makeLabel("ShowPlayer4Items"));
        tab1.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        tab4.setClosable(false);
        TabPane tabPane = myUIBuilder.makeTabPane("tabPane");
        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);
        leftComponent.getChildren().add(tabPane);
    }

    /**
     * Returns the left component VBox
     * @return
     */
    public VBox getComponent() {
        return leftComponent;
    }

}
