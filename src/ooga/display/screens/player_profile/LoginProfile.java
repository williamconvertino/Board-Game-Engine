package ooga.display.screens.player_profile;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.popup.ExceptionPopUp;
import ooga.display.ui_tools.UIBuilder;

public class LoginProfile implements Profile {
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

  public LoginProfile(Stage stage, UIBuilder uiBuilder, ResourceBundle resourceBundle) {
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
    String username = myUsername.getText();
    String password = myPassword.getText();

    String errorLabel = "";
    String errorContent = "";

    ExceptionPopUp error;

    if (username.equals(myResource.getString("UsernameTextFieldID")) || username.isBlank() || username.contains(" ")) {
      errorLabel = "Invalid Fields";
      errorContent = String.format("%s\n%s", errorContent, myResource.getString("PleaseEnterNewUsername"));
    }
    if (password.equals(myResource.getString("PasswordTextFieldID")) || password.isBlank() || password.contains(" ")) {
      errorLabel = "Invalid Fields";
      errorContent = String.format("%s\n%s", errorContent, myResource.getString("PleaseEnterNewPassword"));
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
    playerMenu.getChildren().add(myUIBuilder.makeLabel("Login"));
    // Username textfield
    playerMenu.getChildren().add(myUIBuilder.makeSmallLabel("EnterUsernameLabel"));
    myUsername = (TextField) myUIBuilder.makeTextField("UsernameTextFieldID");
    playerMenu.getChildren().add(myUsername);
    // Password textfield
    playerMenu.getChildren().add(myUIBuilder.makeSmallLabel("EnterPasswordLabel"));
    myPassword = (TextField) myUIBuilder.makeTextField("PasswordTextFieldID");
    playerMenu.getChildren().add(myPassword);
    // Signup Button
    Button loginButton = myUIBuilder.makeButton("Login", e -> buttonPressed());
    // Close Button
    Button closeButton = myUIBuilder.makeButton("Close", e -> closePopup());
    playerMenu.getChildren().add(myUIBuilder.makeButtonBox(loginButton, closeButton));

    playerMenu.setId("ProfileVBox");
    myPopup = new Popup();
    myPopup.getContent().add(playerMenu);
  }

  private void closePopup() {
    myPopup.hide();
  }
}
