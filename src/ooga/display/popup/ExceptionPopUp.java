package ooga.display.popup;

import ooga.display.ui_tools.UIBuilder;

import java.util.ResourceBundle;

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
   * This is for the situation where you want just a generic error.
   * @param langResource
   */
  public ExceptionPopUp(ResourceBundle langResource) {
    UIBuilder uiBuilder = new UIBuilder(langResource);
    uiBuilder.makeErrorAlert(DEFAULT_HEADER, DEFAULT_MESSAGE).showAndWait();
  }

  /**
   * Constructor with header title and a message for the specific error
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
