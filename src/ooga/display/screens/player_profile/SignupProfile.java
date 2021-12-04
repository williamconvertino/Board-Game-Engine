package ooga.display.screens.player_profile;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.ui_tools.UIBuilder;
import org.w3c.dom.Text;

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
  private TextField myName;
  private TextField myUsername;
  private TextField myPassword;

  public SignupProfile(Stage stage, UIBuilder uiBuilder) {
    myStage = stage;
    myUIBuilder = uiBuilder;
    makeScene();
  }

  @Override
  public Popup getPopup() {
    return myPopup;
  }

  @Override
  public void buttonPressed() {

  }

  private void makeScene() {
    VBox playerMenu = new VBox();
    playerMenu.setPrefSize(400, 400);
    // Sign Up Label
    playerMenu.getChildren().add(myUIBuilder.makeLabel("Signup"));
    // Username textfield
    myUsername = (TextField) myUIBuilder.makeTextField("UsernameTextFieldID");
    playerMenu.getChildren().add(myUsername);
    // Password textfield
    myPassword = (TextField) myUIBuilder.makeTextField("PasswordTextFieldID");
    playerMenu.getChildren().add(myPassword);
    // Profile Name textfield
    myName = (TextField) myUIBuilder.makeTextField("NameTextFieldID");
    playerMenu.getChildren().add(myName);
    // Signup Button
    playerMenu.getChildren().add(myUIBuilder.makeButton("Signup", e -> buttonPressed()));
    // Close Button
    playerMenu.getChildren().add(myUIBuilder.makeButton("Close", e -> closePopup()));
    playerMenu.setId("ProfileVBox");
    myPopup = new Popup();
    myPopup.getContent().add(playerMenu);
  }

  private void closePopup() {
    myPopup.hide();
  }
}
