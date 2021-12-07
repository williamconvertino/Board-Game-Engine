package ooga.display.popup;

import java.util.ResourceBundle;
import ooga.display.ui_tools.UIBuilder;

/**
 * Displays the popup for exceptions
 *
 * @author Henry Huynh
 * @author Aaric Han
 */
public class ExceptionPopUp {

  private static final String DEFAULT_HEADER = "Error";
  private static final String DEFAULT_MESSAGE = "Error has occurred";
  private static final String DEFAULT_LANGUAGE = "English";

  /**
   * Default constructor
   *
   * @param langResource
   */
  public ExceptionPopUp(ResourceBundle langResource) {
    UIBuilder uiBuilder = new UIBuilder(langResource);
    uiBuilder.makeErrorAlert(DEFAULT_HEADER, DEFAULT_MESSAGE).showAndWait();
  }

  /**
   * Constructor with header title and a message
   *
   * @param header
   * @param message
   * @param langResource
   */
  public ExceptionPopUp(String header, String message, ResourceBundle langResource) {
    UIBuilder uiBuilder = new UIBuilder(langResource);
    uiBuilder.makeErrorAlert(header, message).showAndWait();
  }
}
