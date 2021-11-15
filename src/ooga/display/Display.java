package ooga.display;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ResourceBundle;

/**
 * The high level display class that handles most of the front-end components
 *
 * @author William Convertino
 * @author Henry Huynh
 * @since 0.0.1
 */
public class Display {

  private static final String RESOURCE_DIRECTORY = Display.class.getPackageName() + ".resources.";
  private ResourceBundle languageResource;
  /**
   * Default constructor
   */
  public Display() {
    languageResource = ResourceBundle.getBundle(RESOURCE_DIRECTORY + "English");
  }


  public Scene getScene() {
    return null;
  }
}