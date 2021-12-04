package ooga.display.screens.player_profile;

import java.io.File;
import java.sql.SQLOutput;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.popup.ExceptionPopUp;
import ooga.display.ui_tools.UIBuilder;

/**
 * This class is the sign up screen
 */
public class SignupProfile implements Profile {
  private static final String DEFAULT_RESOURCE_PACKAGE =
      Display.class.getPackageName() + ".resources.";
  private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static final String DEFAULT_STYLE = STYLE_PACKAGE + "mainmenu.css";

  private Stage myStage;
  private Popup myPopup;
  private UIBuilder myUIBuilder;
  private ResourceBundle myResource;
  private TextField myName;
  private TextField myUsername;
  private TextField myPassword;
  private String myAvatar;

  private final String AVATAR_DIR_PATH = "data/profiles/avatar-img/";
  private final File AVATAR_DIR = new File(AVATAR_DIR_PATH);

  public SignupProfile(Stage stage, UIBuilder uiBuilder, ResourceBundle resourceBundle) {
    myStage = stage;
    myUIBuilder = uiBuilder;
    myResource = resourceBundle;
    makeScene();
  }

  @Override
  public Popup getPopup() {
    return myPopup;
  }

  @Override
  public void buttonPressed() {
    // TODO: Send information from textfields and file chooser to Jordan's backend
    String name = myName.getText();
    String username = myUsername.getText();
    String password = myPassword.getText();
    String avatar = myAvatar;

    String errorLabel = "";
    String errorContent = "";

    ExceptionPopUp error;

    if (username.equals(myResource.getString("UsernameTextFieldID")) || username.isBlank()) {
      errorLabel = "Invalid Fields";
      errorContent = String.format("%s\n%s", errorContent, myResource.getString("PleaseEnterNewUsername"));
    }
    if (password.equals(myResource.getString("PasswordTextFieldID")) || password.isBlank()) {
      errorLabel = "Invalid Fields";
      errorContent = String.format("%s\n%s", errorContent, myResource.getString("PleaseEnterNewPassword"));
    }
    if (name.equals(myResource.getString("NameTextFieldID")) || name.isBlank()) {
      errorLabel = "Invalid Fields";
      errorContent = String.format("%s\n%s", errorContent, myResource.getString("PleaseEnterNewName"));
    }
    if (avatar == null) {
      errorLabel = "Invalid Fields";
      errorContent = String.format("%s\n%s", errorContent, myResource.getString("PleaseSelectAvatar"));
    }
    if (!errorLabel.isBlank()) error = new ExceptionPopUp(errorLabel, errorContent, myResource);
    else {
      // TODO: Send info to Jordan's methods

      // Then:
      closePopup();
    }
  }

  private void makeScene() {
    VBox playerMenu = new VBox();
    playerMenu.setPrefSize(400, 400);
    // Sign Up Label
    playerMenu.getChildren().add(myUIBuilder.makeLabel("Signup"));
    // Username textfield
    playerMenu.getChildren().add(myUIBuilder.makeSmallLabel("EnterUsernameLabel"));
    myUsername = (TextField) myUIBuilder.makeTextField("UsernameTextFieldID");
    playerMenu.getChildren().add(myUsername);
    // Password textfield
    playerMenu.getChildren().add(myUIBuilder.makeSmallLabel("EnterPasswordLabel"));
    myPassword = (TextField) myUIBuilder.makeTextField("PasswordTextFieldID");
    playerMenu.getChildren().add(myPassword);
    // Profile Name textfield
    playerMenu.getChildren().add(myUIBuilder.makeSmallLabel("EnterNameLabel"));
    myName = (TextField) myUIBuilder.makeTextField("NameTextFieldID");
    playerMenu.getChildren().add(myName);
    // Profile Avatar Image
    playerMenu.getChildren().add(myUIBuilder.makeSmallLabel("ChooseAvatarLabel"));
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(AVATAR_DIR);
    playerMenu.getChildren().add(myUIBuilder.makeButton("ChooseAvatarButton", e-> {
      File avatarfile = fileChooser.showOpenDialog(myStage);
      if (!(avatarfile == null)) {
        myAvatar = avatarfile.getName();
      }
    }));

    // Signup Button
    Button signupButton = myUIBuilder.makeButton("Signup", e -> buttonPressed());
    // Close Button
    Button closeButton = myUIBuilder.makeButton("Close", e -> closePopup());
    playerMenu.getChildren().add(myUIBuilder.makeButtonBox(signupButton, closeButton));

    playerMenu.setId("ProfileVBox");
    myPopup = new Popup();
    myPopup.getContent().add(playerMenu);
  }

  private void closePopup() {
    myPopup.hide();
  }
}
