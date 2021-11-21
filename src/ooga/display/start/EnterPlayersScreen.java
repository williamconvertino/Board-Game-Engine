package ooga.display.start;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.ui_tools.LanguageUI;
import ooga.display.ui_tools.UIBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EnterPlayersScreen extends Display {
    private BorderPane startMenu;
    private Stage myStage;
    private DisplayManager myDisplayManager;
    private UIBuilder myBuilder;
    private ResourceBundle myLangResource;
    private LanguageUI myLanguageUI;
    private static final String DEFAULT_RESOURCE_PACKAGE = Display.class.getPackageName() + ".resources.";
    private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
    private static final String DEFAULT_STYLE = STYLE_PACKAGE + "mainmenu.css";
    private Scene scene;

    public EnterPlayersScreen (Stage stage, DisplayManager displayManager, ResourceBundle langResource) {
        myLangResource = langResource;
        myBuilder = new UIBuilder(langResource);
        myStage = stage;
        myDisplayManager = displayManager;
        startMenu = new BorderPane();
        startMenu.setTop(myBuilder.makeLabel("EnterPlayerNames"));
        startMenu.setLeft(makeTextAreas());
        makeScene();
    }

    private Node makeTextAreas() {
        TextArea area = new TextArea("Enter Player Name");
        return area;
    }


    private void makeScene() {
        scene = new Scene(startMenu, 800, 600);
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