package ooga.display.screens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.ui_tools.LanguageUI;
import ooga.display.ui_tools.UIBuilder;

/**
 * @author Henry Huynh The Options menu.
 */
public class OptionsMenu extends Display {

  private final BorderPane optionsMenu;
  private final Stage myStage;
  private final DisplayManager myDisplayManager;
  private final UIBuilder myBuilder;
  private final ResourceBundle myLangResource;
  private LanguageUI myLanguageUI;
  private static final String DEFAULT_RESOURCE_PACKAGE =
      Display.class.getPackageName() + ".resources.";
  private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static final String DEFAULT_STYLE = STYLE_PACKAGE + "original.css";
  private static final String DUKE_STYLE = STYLE_PACKAGE + "duke.css";
  private static final String MONO_STYLE = STYLE_PACKAGE + "mono.css";
  private static final List<String> LANGUAGES_LIST = List.of("English", "Spanish", "French",
      "Irish", "Latin");
  private Scene scene;

  /**
   * Constructor for creating an option menu screen
   *
   * @param stage          The stage
   * @param displayManager The display manager
   * @param langResource   The language
   */
  public OptionsMenu(Stage stage, DisplayManager displayManager, ResourceBundle langResource) {
    myLangResource = langResource;
    myBuilder = new UIBuilder(langResource);
    myStage = stage;
    myDisplayManager = displayManager;
    optionsMenu = new BorderPane();
    optionsMenu.setTop(myBuilder.makeLabel("OPTIONS"));
    optionsMenu.setLeft(optionsPanel());
    makeScene();
  }

  /**
   * Make the panel with options
   */
  private Node optionsPanel() {
    VBox result = new VBox();
    List<String> themes = new ArrayList<>(Arrays.asList("Original", "Mono", "Duke"));
    result.getChildren()
        .add(myBuilder.makeCombo("Theme", themes, e -> myDisplayManager.changeTheme(e)));
    myLanguageUI = new LanguageUI(myDisplayManager, myLangResource, LANGUAGES_LIST);
    result.getChildren().add(myLanguageUI);
    result.getChildren()
        .add(myBuilder.makeTextButton("GotoHome", e -> myDisplayManager.goStartMenu()));
    return result;
  }

  private void makeScene() {
    scene = new Scene(optionsMenu, 800, 600);
    scene.getStylesheets().add(DEFAULT_STYLE);
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
