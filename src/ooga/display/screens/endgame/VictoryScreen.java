package ooga.display.screens.endgame;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.ui_tools.LanguageUI;
import ooga.display.ui_tools.UIBuilder;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.OriginalPlayerManager;
import ooga.model.data.player.Player;

import java.io.File;
import java.util.ResourceBundle;

/**
 * @author Henry Huynh
 * The Victory screen.
 */
public class VictoryScreen extends Display {


    private static final String DEFAULT_RESOURCE_PACKAGE =
            Display.class.getPackageName() + ".resources.";
    private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
    private static final String DEFAULT_STYLE = STYLE_PACKAGE + "original.css";
    private static final String DUKE_STYLE = STYLE_PACKAGE + "duke.css";
    private static final String MONO_STYLE = STYLE_PACKAGE + "mono.css";

    private final String PROFILES_DIR = "data/profiles/playerProfiles.csv";
    private final String AVATAR_DIR_PATH = "data/profiles/avatar-img/";
    private final File AVATAR_DIR = new File(AVATAR_DIR_PATH);

    private Stage myStage;
    private DisplayManager myDisplayManager;
    private UIBuilder myBuilder;
    private ResourceBundle myLangResource;
    private ResourceBundle myGameImages;
    private LanguageUI myLanguageUI;
    private VBox myTextAreaVBox;
    private VBox myColorSelectionVBox;
    private Scene scene;
    private String myStyle;
    private BorderPane victoryScreen;
    private GameData myGameData;
    private String winnerName;

    /**
     * Default constructor for the Victory Screen
     * @param stage
     * @param displayManager
     * @param langResource
     */
    public VictoryScreen(Stage stage, DisplayManager displayManager,
                         ResourceBundle langResource, String theme, GameData gameData, OriginalPlayerManager playerManager) {
        myGameData = gameData;
        myStyle = theme;
        myLangResource = langResource;//ooga/display/resources/variation_image_paths
        myBuilder = new UIBuilder(langResource);
        myStage = stage;
        myDisplayManager = displayManager;
        victoryScreen = new BorderPane();
        getWinnerName();
        victoryScreen.setTop(new Label(winnerName + " YOU WON"));
        victoryScreen.setLeft(myBuilder.makeTextButton("GoBackHome", e -> myDisplayManager.goStartMenu()));
        makeScene();
    }

    private void getWinnerName() {
        for (Player p : myGameData.getPlayers()) {
            if (p.isActive()) {
                winnerName = p.getName();
            }
        }
    }
    private void makeScene() {
        scene = new Scene(victoryScreen, 800, 600);
        scene.getStylesheets().add(myStyle);

    }

    /**
     * Get the scene
     *
     * @return scene
     */
    @Override
    public Scene getScene() {
        return scene;
    }

}
