package ooga.display.screens.player_profile;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.popup.ExceptionPopUp;
import ooga.display.ui_tools.UIBuilder;
import ooga.exceptions.PlayerProfileException;
import ooga.util.ProfileManager;

public class UpdateProfile implements Profile {

  private static final String DEFAULT_RESOURCE_PACKAGE =
      Display.class.getPackageName() + ".resources.";
  private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static final String DEFAULT_STYLE = STYLE_PACKAGE + "original.css";
  private static final String DUKE_STYLE = STYLE_PACKAGE + "duke.css";
  private static final String MONO_STYLE = STYLE_PACKAGE + "mono.css";

  private final DisplayManager myDisplayManager;
  private final ProfileManager myProfileManager;
  private final Stage myStage;
  private Popup myPopup;
  private final UIBuilder myUIBuilder;
  private final ResourceBundle myResource;
  private TextField myName;
  private Label myUsername;
  private TextField myPassword;
  private String myAvatar;
  private VBox playerMenu;

  private final String AVATAR_DIR_PATH = "data/profiles/avatar-img/";
  private final File AVATAR_DIR = new File(AVATAR_DIR_PATH);
  private final String PROFILES_DIR = "data/profiles/playerProfiles.csv";
  private final String DEFAULT_AVATAR = "boss.png";

  /**
   * Instantiates a new Signup profile.
   *
   * @param stage          the stage
   * @param uiBuilder      the ui builder
   * @param resourceBundle the resource bundle
   */
  public UpdateProfile(Stage stage, UIBuilder uiBuilder, ResourceBundle resourceBundle,
      DisplayManager dm, ProfileManager pm) {
    myProfileManager = pm;
    myDisplayManager = dm;
    myStage = stage;
    myUIBuilder = uiBuilder;
    myResource = resourceBundle;
    myAvatar = DEFAULT_AVATAR;
    makeScene();
  }

  /**
   * Method to return the popup component
   *
   * @return
   */
  @Override
  public Popup getPopup() {
    return myPopup;
  }

  /**
   * Actions that happen after the update button is pressed
   */

  private void buttonPressed() {
    // TODO: Send information from textfields and file chooser to Jordan's backend
    String name = myName.getText();
    String username = myUsername.getText();
    String password = myPassword.getText();
    String avatar = myAvatar;

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
    if (name.equals(myResource.getString("NameTextFieldID")) || name.isBlank()) {
      errorLabel = "Invalid Fields";
      errorContent = String.format("%s\n%s", errorContent,
          myResource.getString("PleaseEnterNewName"));
    }
    if (avatar == null) {
      errorLabel = "Invalid Fields";
      errorContent = String.format("%s\n%s", errorContent,
          myResource.getString("PleaseSelectAvatar"));
    }
    if (!errorLabel.isBlank()) {
      error = new ExceptionPopUp(errorLabel, errorContent, myResource);
    } else {
      String[] allProfiles = {username, password, avatar, name};
      try {
        myProfileManager.updatePlayerProfile(allProfiles, PROFILES_DIR);
        myDisplayManager.updateUser(allProfiles);
      } catch (IOException e) {
        ExceptionPopUp NoFile = new ExceptionPopUp("Player Data Storage Not Found",
            "Please check data/profiles/playerProfiles.csv", myResource);
        return;
      } catch (PlayerProfileException e) {
        ExceptionPopUp PlayerProfilesNotFound = new ExceptionPopUp("Cannot Find Player",
            "Please Signup", myResource);
        return;
      }
      // Then:
      closePopup();
    }
  }

  /**
   * Makes the components of the popup
   */
  private void makeScene() {
    playerMenu = new VBox();
    playerMenu.setPrefSize(400, 400);
    // Sign Up Label
    playerMenu.getChildren().add(myUIBuilder.makeLabel("Update"));
    // Username textfield
    playerMenu.getChildren().add(myUIBuilder.makeSmallLabel("UsernameLabel"));
    myUsername = new Label("");
    playerMenu.getChildren().add(myUsername);
    // Password textfield
    playerMenu.getChildren().add(myUIBuilder.makeSmallLabel("EnterPasswordLabel"));
    myPassword = (TextField) myUIBuilder.makePrefilledTextField("PasswordTextFieldID");
    playerMenu.getChildren().add(myPassword);
    // Profile Name textfield
    playerMenu.getChildren().add(myUIBuilder.makeSmallLabel("EnterNameLabel"));
    myName = (TextField) myUIBuilder.makePrefilledTextField("NameTextFieldID");
    playerMenu.getChildren().add(myName);
    // Profile Avatar Image
    playerMenu.getChildren().add(myUIBuilder.makeSmallLabel("ChooseAvatarLabel"));
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(AVATAR_DIR);
    HBox avatarBox = new HBox();
    ImageView avatarName = new ImageView();
    Button avatarButton = myUIBuilder.makeTextButton("ChooseAvatarButton", e -> {
      File avatarfile = fileChooser.showOpenDialog(myStage);
      if (avatarfile != null) {
        myAvatar = avatarfile.getName();
        avatarName.setImage(new Image("profiles/avatar-img/" + myAvatar));
        avatarName.setFitHeight(40);
        avatarName.setFitWidth(40);
      }
    });

    avatarBox.getChildren().add(avatarButton);
    avatarBox.getChildren().add(avatarName);
    playerMenu.getChildren().add(avatarBox);

    // Signup Button
    Button signupButton = myUIBuilder.makeTextButton("UpdateNow", e -> buttonPressed());
    // Close Button
    Button closeButton = myUIBuilder.makeTextButton("Close", e -> closePopup());
    playerMenu.getChildren().add(myUIBuilder.makeButtonBox(signupButton, closeButton));

    playerMenu.setId("ProfileVBox");
    myPopup = new Popup();
    myPopup.getContent().add(playerMenu);
  }

  /**
   * Action taken when the close popup button is pressed
   */
  private void closePopup() {
    myPopup.hide();
    myPassword.setText(myResource.getString("PasswordTextFieldID"));
    myName.setText(myResource.getString("NameTextFieldID"));
    myAvatar = "";
  }

  /**
   * Update Username
   */
  public void updateUsername(String username) {
    myUsername = (Label) myUIBuilder.makeSmallLabelNoID(username);
    playerMenu.getChildren().set(2, myUsername);
  }
}
