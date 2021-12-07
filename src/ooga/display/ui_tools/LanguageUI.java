package ooga.display.ui_tools;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.layout.VBox;
import ooga.display.DisplayManager;

/**
 * This class creates a language change box. Made it as a class as we need to create one in the
 * options menu & game display
 *
 * @author Henry Huynh
 * @author Aaric Han
 */
public class LanguageUI extends VBox {

  private final String LANGUAGE = "ChangeLanguage";
  private final ResourceBundle myLangResource;
  private final List<String> myLanguages;

  /**
   * Creates initial popup where user chooses language
   *
   * @param displayManager handles new Language input and passes it to view
   * @param langResource   refers to language document for display
   * @param languages      List of available languages
   */
  public LanguageUI(DisplayManager displayManager, ResourceBundle langResource,
      List<String> languages) {
    myLangResource = langResource;
    myLanguages = languages;
    UIBuilder builder = new UIBuilder(langResource);
    this.getChildren().add(
        builder.makeCombo(LANGUAGE, languages, e -> changeLanguage(displayManager, e))
    );
  }

  /**
   * Clears language Dropdown and initializes view with new language
   *
   * @param displayManager handles new Language input and passes it to view
   * @param language       is user chosen language
   */
  private void changeLanguage(DisplayManager displayManager, String language) {
    UIBuilder builder = new UIBuilder(myLangResource);
    this.getChildren().clear();
    this.getChildren().add(
        builder.makeCombo(LANGUAGE, myLanguages, e -> changeLanguage(displayManager, e))
    );
    displayManager.changeLanguage(language);
  }
}
