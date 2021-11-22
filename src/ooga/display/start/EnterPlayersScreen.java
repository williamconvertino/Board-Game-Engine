package ooga.display.start;

import java.sql.Array;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.ui_tools.LanguageUI;
import ooga.display.ui_tools.UIBuilder;
import ooga.model.data.gamedata.GameData;

import java.awt.*;
import java.util.List;
import java.util.ResourceBundle;



public class EnterPlayersScreen extends Display {
    private BorderPane playerMenu;
    private Stage myStage;
    private DisplayManager myDisplayManager;
    private UIBuilder myBuilder;
    private ResourceBundle myLangResource;
    private LanguageUI myLanguageUI;
    private VBox myTextAreaVBox;
    private static final String DEFAULT_RESOURCE_PACKAGE = Display.class.getPackageName() + ".resources.";
    private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
    private static final String DEFAULT_STYLE = STYLE_PACKAGE + "mainmenu.css";
    private static final String ENTER_NAME = "EnterPlayerName";
    private static final String PLAYER_NAME = "PlayerNameLabel";
    private GameData myGameData;
    private Scene scene;

    public EnterPlayersScreen (Stage stage, DisplayManager displayManager, ResourceBundle langResource, GameData gameData) {
        myGameData = gameData;
        myLangResource = langResource;
        myBuilder = new UIBuilder(langResource);
        myStage = stage;
        myDisplayManager = displayManager;
        playerMenu = new BorderPane();
        playerMenu.setTop(myBuilder.makeLabel("EnterPlayerNames"));
        playerMenu.setLeft(makeTextAreas());
        playerMenu.setRight(makeRight());
        makeScene();
    }

    private Node makeRight() {
        VBox result = new VBox();
        result.getChildren().add(myBuilder.makeButton("Continue", e -> myDisplayManager.startGame()));
        result.getChildren().add(myBuilder.makeButton("GotoHome", e -> myDisplayManager.goStartMenu()));
        return result;
    }

    private Node makeTextAreas() {
        myTextAreaVBox = new VBox();
        for (int i = 0; i < myGameData.getPlayers().size(); i++) {
            myTextAreaVBox.getChildren().add(myBuilder.makeLabel(PLAYER_NAME));
            myTextAreaVBox.getChildren().add(myBuilder.makeTextField(ENTER_NAME));
        }
        return myTextAreaVBox;
    }

    public List<Node> getTextAreaInfo() {
        List<Node> textAreaList = new ArrayList<>();
        textAreaList.add(myTextAreaVBox.getChildren().get(1));
        textAreaList.add(myTextAreaVBox.getChildren().get(3));
        textAreaList.add(myTextAreaVBox.getChildren().get(5));
        textAreaList.add(myTextAreaVBox.getChildren().get(7));
        return textAreaList;
    }

    private void makeScene() {
        scene = new Scene(playerMenu, 800, 600);
        scene.getStylesheets().add(DEFAULT_STYLE);
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