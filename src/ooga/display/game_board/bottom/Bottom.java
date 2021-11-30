package ooga.display.game_board.bottom;

import javafx.scene.layout.VBox;
import ooga.display.ui_tools.UIBuilder;

import java.util.ResourceBundle;

/**
 * This is the bottom display element of the game display
 *
 * @author Henry Huynh
 * @author Aaric Han
 */
public class Bottom {

  private VBox bottomComponent;
  private UIBuilder myUIBuilder;
  private ResourceBundle myLangResource;

  /**
   * The constructor for the left display element
   */
  public Bottom(ResourceBundle language) {
    bottomComponent = new VBox();
    myLangResource = language;
    myUIBuilder = new UIBuilder(myLangResource);
    makeBottomComponent();
  }

  private void makeBottomComponent() {
    bottomComponent.getChildren().add(myUIBuilder.makeLabel("Test"));
  }

  /**
   * Returns the bottom component VBox
   *
   * @return
   */
  public VBox getComponent() {
    return bottomComponent;
  }

}
