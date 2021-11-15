package ooga.display.popup;

import ooga.display.ui_tools.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Henry Huynh
 * Displays the popup for exceptions
 */
public class ExceptionPopUp {
    private static final String DEFAULT_HEADER = "Error";
    private static final String DEFAULT_MESSAGE = "Error has occurred";
    private static final String DEFAULT_LANGUAGE = "English";

    public ExceptionPopUp(ResourceBundle langResource) {
        UIBuilder uiBuilder = new UIBuilder(langResource);
        uiBuilder.makeErrorAlert(DEFAULT_HEADER, DEFAULT_MESSAGE).showAndWait();
    }

    public ExceptionPopUp(String header, String message, ResourceBundle langResource) {
        UIBuilder uiBuilder = new UIBuilder(langResource);
        uiBuilder.makeErrorAlert(header, message).showAndWait();
    }
}
