package ooga.display.game_board.left;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import ooga.display.DisplayManager;
import ooga.display.communication.EventManager;
import ooga.display.game_board.GameBoardDisplay;
import ooga.display.ui_tools.UIBuilder;
import ooga.model.data.gamedata.GameData;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * This is the left display element of the game display
 *
 * @author Henry Huynh
 */
public class Left {


    private static final String BALANCE = "Balance";
    private static final String PROPERTIES = "Properties";
    private static final String CARDS = "Cards";
    private static final String NONE = "None";
    private GameBoardDisplay myGameBoardDisplay;
    private DisplayManager myDisplayManager;
    private VBox leftComponent;
    private UIBuilder myUIBuilder;
    private ResourceBundle myLangResource;
    private GameData myGameData;
    private Map<EventManager.EVENT_NAMES, EventHandler> myEventMap;
    private static final String PLAYER = "Player";
    private static final String LOCATION = "Location";

    /**
     * The constructor for the left display element
     */
    public Left(GameBoardDisplay gameBoardDisplay, DisplayManager displayManager, ResourceBundle language, Map<EventManager.EVENT_NAMES, EventHandler> eventMap,  GameData gameData) {
        myGameData = gameData;
        myEventMap = eventMap;
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

//        Tab tab2 = myUIBuilder.makeTab("Player2Info");
//        tab2.setContent(myUIBuilder.makeLabel("ShowPlayer2Items"));
//        Tab tab3 = myUIBuilder.makeTab("Player3Info");
//        tab3.setContent(myUIBuilder.makeLabel("ShowPlayer3Items"));
//        Tab tab4 = myUIBuilder.makeTab("Player4Info");
//        tab4.setContent(myUIBuilder.makeLabel("ShowPlayer4Items"));
//        tab2.setClosable(false);
//        tab3.setClosable(false);
//        tab4.setClosable(false);
        TabPane tabPane = myUIBuilder.makeTabPane("tabPane");
        tabPane.getTabs().addAll(makePlayerTab(0), makePlayerTab(1), makePlayerTab(2),
                makePlayerTab(3));
        leftComponent.getChildren().add(tabPane);

    }

    /**
     * @param playerIndex 0 - indexed player number
     * @return Tab for the specified player
     */
    private Tab makePlayerTab(int playerIndex) {

        Tab tab1 = myUIBuilder.makeTab(myGameData.getPlayers().get(playerIndex).getName());
        VBox result = new VBox();
        result.getChildren().addAll(
                myUIBuilder.makeLabel(LOCATION),
                myUIBuilder.makeLabel(PLAYER + (playerIndex + 1)),
                new Label(String.valueOf(myGameData.getPlayers().get(playerIndex).getLocation())),
                myUIBuilder.makeLabel(BALANCE),
                new Label(String.valueOf(myGameData.getPlayers().get(playerIndex).getBalance())),
                myUIBuilder.makeLabel(PROPERTIES),
                makePlayerTabProperties(playerIndex),
                myUIBuilder.makeLabel(CARDS),
                makePlayerTabCards(playerIndex)
        );
        tab1.setContent(result);
        tab1.setClosable(false);
        return tab1;
    }

    public Label makePlayerTabProperties(int playerIndex) {
        if (myGameData.getPlayers().get(playerIndex).getProperties().isEmpty()) return (Label) myUIBuilder.makeLabel(NONE);
        return (Label) myUIBuilder.makeLabel(String.valueOf(myGameData.getPlayers().get(playerIndex).getProperties()));
    }

    public Label makePlayerTabCards(int playerIndex) {
        if (myGameData.getPlayers().get(playerIndex).getCards().isEmpty()) return (Label) myUIBuilder.makeLabel(NONE);
        return (Label) myUIBuilder.makeLabel(String.valueOf(myGameData.getPlayers().get(playerIndex).getCards()));
    }
    /**
     * Returns the left component VBox
     * @return
     */
    public VBox getComponent() {
        return leftComponent;
    }

}
