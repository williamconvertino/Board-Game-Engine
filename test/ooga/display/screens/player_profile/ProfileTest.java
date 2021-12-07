package ooga.display.screens.player_profile;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.display.DisplayManager;
import ooga.util.DukeApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProfileTest extends DukeApplicationTest {
  public DisplayManager dm;

  @Override
  public void start(Stage stage) {
    dm = new DisplayManager(stage);
  }

  /**
   * This test tests signup button
   */
  @Test
  public void testSignupButton() {
    Button signUpButton = lookup("#Signup").query();
    clickOn(signUpButton);
    Button closeButton = lookup("#Close").query();
    clickOn(closeButton);
  }

  /**
   * This test tests signup process
   */
  @Test
  public void testSignupProcess() {
    Button signUpButton = lookup("#Signup").query();
    clickOn(signUpButton);
    int random = (int) (Math.random() * 99999);
    TextField usernameTextField = lookup("#UsernameTextFieldID").query();
    writeInputTo(usernameTextField, "fxtestuser" + random);
    TextField passwordTextField = lookup("#PasswordTextFieldID").query();
    writeInputTo(passwordTextField, "fxtestuser" + random);
    TextField nameTextField = lookup("#NameTextFieldID").query();
    writeInputTo(nameTextField, "fxtestuser" + random);
    Button signupButton1 = lookup("#SignupNow").queryButton();
    clickOn(signupButton1);
  }

  /**
   * This test tests login button
   */
  @Test
  public void testLoginButton() {
    Button loginButton = lookup("#Login").query();
    clickOn(loginButton);
    Button closeButton = lookup("#Close").query();
    clickOn(closeButton);
  }

  /**
   * This test tests login and update profile functionality
   */
  @Test
  public void testLoginAndUpdateButton() {
    // Log in first
    Button loginButton = lookup("#Login").query();
    clickOn(loginButton);
    TextField usernameTextField = lookup("#UsernameTextFieldID").query();
    writeInputTo(usernameTextField, "fxtestuser");
    TextField passwordTextField = lookup("#PasswordTextFieldID").query();
    writeInputTo(passwordTextField, "fxtestuser");
    loginButton = lookup("#LoginNow").query();
    clickOn(loginButton);

    Button updateProfButton = lookup("#Update").query();
    clickOn(updateProfButton);
    passwordTextField = lookup("#PasswordTextFieldID").query();
    writeInputTo(passwordTextField, "fxtestuser");
    TextField nameTextField = lookup("#NameTextFieldID").query();
    writeInputTo(nameTextField, "FX Test User");
    Button updateButton = lookup("#UpdateNow").query();
    clickOn(updateButton);
  }

  /**
   * This test tests bad update
   */
  @Test
  public void testLoginAndBadUpdate() {
    // Log in first
    Button loginButton = lookup("#Login").query();
    clickOn(loginButton);
    TextField usernameTextField = lookup("#UsernameTextFieldID").query();
    writeInputTo(usernameTextField, "fxtestuser");
    TextField passwordTextField = lookup("#PasswordTextFieldID").query();
    writeInputTo(passwordTextField, "fxtestuser");
    loginButton = lookup("#LoginNow").query();
    clickOn(loginButton);

    Button updateProfButton = lookup("#Update").query();
    clickOn(updateProfButton);
    passwordTextField = lookup("#PasswordTextFieldID").query();
    writeInputTo(passwordTextField, "");
    TextField nameTextField = lookup("#NameTextFieldID").query();
    writeInputTo(nameTextField, "");
    Button updateButton = lookup("#UpdateNow").query();
    clickOn(updateButton);
  }



}
