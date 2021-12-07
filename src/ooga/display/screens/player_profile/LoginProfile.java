package ooga.display.screens.player_profile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.popup.ExceptionPopUp;
import ooga.display.ui_tools.UIBuilder;
import ooga.exceptions.PlayerProfileException;
import ooga.util.ProfileManager;


/**
 * The Login Profile object
 *
 * @author Aaric Han
 */
public class LoginProfile implements Profile {

  private static final String DEFAULT_RESOURCE_PACKAGE =
      Display.class.getPackageName() + ".resources.";
  private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static final String DEFAULT_STYLE = STYLE_PACKAGE + "mainmenu.css";

  private final DisplayManager myDisplayManager;
  private final ProfileManager myProfileManager;
  private final Stage myStage;
  private Popup myPopup;
  private final UIBuilder myUIBuilder;
  private final ResourceBundle myResource;
  private TextField myUsername;
  private TextField myPassword;

  private final String AVATAR_DIR_PATH = "data/profiles/avatar-img/";
  private final File AVATAR_DIR = new File(AVATAR_DIR_PATH);
  private final String PROFILES_DIR = "data/profiles/playerProfiles.csv";

  /**
   * Default Constructor to build the Login Profile object
   *
   * @param stage
   * @param uiBuilder
   * @param resourceBundle
   */
  public LoginProfile(Stage stage, UIBuilder uiBuilder, ResourceBundle resourceBundle,
      DisplayManager dm, ProfileManager pm) {
    myProfileManager = pm;
    myDisplayManager = dm;
    myStage = stage;
    myUIBuilder = uiBuilder;
    myResource = resourceBundle;
    makeScene();
  }

  /**
   * Get the popup component
   *
   * @return myPopup
   */
  @Override
  public Popup getPopup() {
    return myPopup;
  }

  /**
   * The actions taken when the login button is pressed
   */
  private void buttonPressed() {
    String username = myUsername.getText();
    String password = myPassword.getText();

    String errorLabel = "";
    String errorContent = "";

    ExceptionPopUp error;

    if (username.equals(myResource.getString("UsernameTextFieldID")) || username.isBlank()
        || username.contains(" ")) {
      errorLabel = "Invalid Fields";
      errorContent = String.format("%s\n%s", errorContent,
          myResource.getString("PleaseEnterNewUsername"));
    }
    if (password.equals(myResource.getString("PasswordTextFieldID")) || password.isBlank()
        || password.contains(" ")) {
      errorLabel = "Invalid Fields";
      errorContent = String.format("%s\n%s", errorContent,
          myResource.getString("PleaseEnterNewPassword"));
    }
    if (!errorLabel.isBlank()) {
      error = new ExceptionPopUp(errorLabel, errorContent, myResource);
    } else {
      String[] playerInfo;
      try {
        playerInfo = myProfileManager.loginPlayerProfile(username, password, PROFILES_DIR);
      } catch (FileNotFoundException e) {
        ExceptionPopUp NoFile = new ExceptionPopUp("Player Data Storage Not Found",
            "Please check data/profiles/playerProfiles.csv", myResource);
        return;
      } catch (PlayerProfileException e) {
        ExceptionPopUp PlayerProfilesNotFound = new ExceptionPopUp("Cannot Find Player",
            "Please Signup", myResource);
        return;
      }
      // Change the login status to logged in (true)
      myDisplayManager.setLoggedIn(true);
      myDisplayManager.updateUser(playerInfo);
      // Then:
      closePopup();
    }
  }

  /**
   * Helper method to make the scene related to the popup
   */
  private void makeScene() {
    VBox playerMenu = new VBox();
    playerMenu.setPrefSize(400, 400);
    // Sign Up Label
    playerMenu.getChildren().add(myUIBuilder.makeLabel("Login"));
    // Username textfield
    playerMenu.getChildren().add(myUIBuilder.makeSmallLabel("EnterUsernameLabel"));
    myUsername = (TextField) myUIBuilder.makePrefilledTextField("UsernameTextFieldID");
    playerMenu.getChildren().add(myUsername);
    // Password textfield
    playerMenu.getChildren().add(myUIBuilder.makeSmallLabel("EnterPasswordLabel"));
    myPassword = (TextField) myUIBuilder.makePrefilledTextField("PasswordTextFieldID");
    playerMenu.getChildren().add(myPassword);
    // Signup Button
    Button loginButton = myUIBuilder.makeTextButton("LoginNow", e -> buttonPressed());
    // Close Button
    Button closeButton = myUIBuilder.makeTextButton("Close", e -> closePopup());
    playerMenu.getChildren().add(myUIBuilder.makeButtonBox(loginButton, closeButton));

    playerMenu.setId("ProfileVBox");
    myPopup = new Popup();
    myPopup.getContent().add(playerMenu);
  }

  /**
   * The action taken when the close button is pressed
   */
  private void closePopup() {
    myPopup.hide();
    myUsername.setText(myResource.getString("UsernameTextFieldID"));
    myPassword.setText(myResource.getString("PasswordTextFieldID"));
  }
}
