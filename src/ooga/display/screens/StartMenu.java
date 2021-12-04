package ooga.display.screens;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.screens.player_profile.SignupProfile;
import ooga.display.ui_tools.UIBuilder;
import java.util.ResourceBundle;

public class StartMenu extends Display {

  private BorderPane startMenu;
  private Stage myStage;
  private DisplayManager myDisplayManager;
  private UIBuilder myBuilder;
  private Scene scene;
  private static final String DEFAULT_RESOURCE_PACKAGE =
      Display.class.getPackageName() + ".resources.";
  private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static final String DEFAULT_STYLE = STYLE_PACKAGE + "mainmenu.css";

  public StartMenu(Stage stage, DisplayManager displayManager, ResourceBundle langResource) {
    myBuilder = new UIBuilder(langResource);
    myStage = stage;
    myDisplayManager = displayManager;
    startMenu = new BorderPane();
    startMenu.setTop(myBuilder.makeLabel("MONOPOLY"));
    startMenu.setLeft(navigationPanel());
    makeScene();
  }

  /**
   * Make the panel with buttons to screens or go to settings
   */
  private Node navigationPanel() {
    VBox result = new VBox();
    result.getChildren().add(myBuilder.makeButton("Start", e -> myDisplayManager.goPlayerScreen()));
    result.getChildren().add(myBuilder.makeButton("Options", e -> myDisplayManager.goOptions()));
    SignupProfile signup = new SignupProfile(myStage, myBuilder);
    result.getChildren().add(myBuilder.makeButton("Signup", e -> signup.getPopup().show(myStage)));
    return result;
  }

  private void makeScene() {
    scene = new Scene(startMenu, 800, 600);
    scene.getStylesheets().add(DEFAULT_STYLE);
    myStage.setScene(scene);
    myStage.show();
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
