package ooga.display.start;

import javafx.scene.Node;
import javafx.scene.Scene;
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

public class OptionsMenu extends Display {
    private BorderPane startMenu;
    private Stage myStage;
    private DisplayManager myDisplayManager;
    private UIBuilder myBuilder;
    private ResourceBundle myLangResource;
    private LanguageUI myLanguageUI;
    private static final String DEFAULT_RESOURCE_PACKAGE = Display.class.getPackageName() + ".resources.";
    private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
    private static final String DEFAULT_STYLE = STYLE_PACKAGE + "styles.css";
    private Scene scene;

    public OptionsMenu (Stage stage, DisplayManager displayManager, ResourceBundle langResource) {
        myLangResource = langResource;
        myBuilder = new UIBuilder(langResource);
        myStage = stage;
        myDisplayManager = displayManager;
        startMenu = new BorderPane();
        startMenu.setTop(myBuilder.makeLabel("OPTIONS"));
        startMenu.setLeft(optionsPanel());
        makeScene();
    }

    /**
     * Make the panel with buttons to start or go to settings
     */
    private Node optionsPanel() {
        VBox result = new VBox();
        List<String> placeHolder = new ArrayList<>();
        List<String> placeHolder2 = new ArrayList<>();
        List<String> placeHolder3 = new ArrayList<>();
        result.getChildren().add(myBuilder.makeCombo("NumberofPlayers", placeHolder, e ->
                myDisplayManager.changePlayerCount()));
        result.getChildren().add(myBuilder.makeCombo("Theme", placeHolder2, e -> myDisplayManager.changeTheme(e)));
        myLanguageUI = new LanguageUI(myDisplayManager, myLangResource, placeHolder3); // Needs to be able to change the
        // language everywhere, not just optionsMenu
        result.getChildren().add(myLanguageUI);
        result.getChildren().add(myBuilder.makeButton("GotoHome", e -> myDisplayManager.goStartMenu()));
        return result;
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