package ooga.display;

import javafx.scene.Scene;

import java.util.ResourceBundle;

/**
 * The high level display class that handles most of the front-end components
 *
 * @author William Convertino
 * @author Henry Huynh
 * @author Aaric Han
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

  /**
   * Default getScene method
   * @return null (a scene when overridden)
   */
  public Scene getScene() {
    return null;
  }
}