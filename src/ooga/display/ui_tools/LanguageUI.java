package ooga.display.ui_tools;

import javafx.scene.layout.VBox;
import ooga.display.DisplayManager;

import java.util.List;
import java.util.ResourceBundle;

public class LanguageUI extends VBox {

    private final String LANGUAGE = "language";

    /**
     * Creates initial popup where user chooses language
     *
     * @param displayManager   handles new Language input and passes it to view
     * @param langResource refers to language document for display
     * @param languages    List of available languages
     */
    public LanguageUI(DisplayManager displayManager, ResourceBundle langResource, List<String> languages) {
        UIBuilder builder = new UIBuilder(langResource);
        this.getChildren().add(
                builder.makeCombo(LANGUAGE, languages, e -> changeLanguage(displayManager, e))
        );
    }

    /**
     * Clears language Dropdown and initializes view with new language
     *
     * @param displayManager handles new Language input and passes it to view
     * @param language   is user chosen language
     */
    private void changeLanguage(DisplayManager displayManager, String language) {

    }
}
