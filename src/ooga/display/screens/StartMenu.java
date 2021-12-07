package ooga.display.screens;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.popup.ExceptionPopUp;
import ooga.display.screens.player_profile.LoginProfile;
import ooga.display.screens.player_profile.SignupProfile;
import ooga.display.screens.player_profile.UpdateProfile;
import ooga.display.ui_tools.UIBuilder;

public class StartMenu extends Display {

  private final BorderPane startMenu;
  private final Stage myStage;
  private final DisplayManager myDisplayManager;
  private final UIBuilder myBuilder;
  private final ResourceBundle myResource;
  private Scene scene;
  private UpdateProfile updateProfile;

  private static final String DEFAULT_RESOURCE_PACKAGE =
      Display.class.getPackageName() + ".resources.";
  private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static final String DEFAULT_STYLE = STYLE_PACKAGE + "original.css";
  private static final String DUKE_STYLE = STYLE_PACKAGE + "duke.css";
  private static final String MONO_STYLE = STYLE_PACKAGE + "mono.css";

  private final String PROFILES_DIR = "data/profiles/playerProfiles.csv";
  private final String AVATAR_DIR_PATH = "data/profiles/avatar-img/";
  private final String AVATAR_PATH = "profiles/avatar-img/";
  private final File AVATAR_DIR = new File(AVATAR_DIR_PATH);


  /**
   * Constructor for creating a start menu screen
   *
   * @param stage
   * @param displayManager
   * @param langResource
   */
  public StartMenu(Stage stage, DisplayManager displayManager, ResourceBundle langResource) {
    myResource = langResource;
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
    result.getChildren()
        .add(myBuilder.makeTextButton("Start", e -> myDisplayManager.goPlayerScreen()));
    result.getChildren()
        .add(myBuilder.makeTextButton("Options", e -> myDisplayManager.goOptions()));
    SignupProfile signup = new SignupProfile(myStage, myBuilder, myResource, myDisplayManager,
        myDisplayManager.getProfileManager());
    result.getChildren().add(myBuilder.makeTextButton("Signup", e -> {
      signup.getPopup().show(myStage);
    }));
    LoginProfile login = new LoginProfile(myStage, myBuilder, myResource, myDisplayManager,
        myDisplayManager.getProfileManager());
    result.getChildren()
        .add(myBuilder.makeTextButton("Login", e -> login.getPopup().show(myStage)));
    updateProfile = new UpdateProfile(myStage, myBuilder, myResource, myDisplayManager,
        myDisplayManager.getProfileManager());
    result.getChildren().add(myBuilder.makeTextButton("Update", e -> {
      notLoggedInException(updateProfile);
    }));
    return result;
  }

  private void notLoggedInException(UpdateProfile update) {
    ExceptionPopUp notLoggedIn;
    if (!myDisplayManager.checkLoggedIn()) {
      notLoggedIn = new ExceptionPopUp("Not logged in", "Please login first.", myResource);
    } else {
      update.getPopup().show(myStage);
    }
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

  /**
   * Set updated profile info
   */
  public void setUpdateProfile(String username, String image, String DisplayName) {
    updateProfile.updateUsername(username);
    ImageView avatar = new ImageView();
    avatar.setImage(new Image(AVATAR_PATH + image));
    avatar.setFitWidth(40);
    avatar.setFitHeight(40);
    startMenu.setRight(new VBox(new Label(DisplayName), avatar));

  }
}
