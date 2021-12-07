package ooga.display.game_board.bottom;

import java.util.ResourceBundle;
import javafx.scene.layout.VBox;
import ooga.display.DisplayManager;
import ooga.display.ui_tools.UIBuilder;

/**
 * This is the bottom display element of the game display
 *
 * @author Henry Huynh
 * @author Aaric Han
 */
public class Bottom {

  private final VBox bottomComponent;
  private final UIBuilder myUIBuilder;
  private final ResourceBundle myLangResource;
  private final DisplayManager myDisplayManager;

  /**
   * The constructor for the left display element
   */
  public Bottom(ResourceBundle language, DisplayManager displayManager) {
    myDisplayManager = displayManager;
    bottomComponent = new VBox();
    myLangResource = language;
    myUIBuilder = new UIBuilder(myLangResource);
    makeBottomComponent();
  }

  private void makeBottomComponent() {
    bottomComponent.getChildren()
        .add(myUIBuilder.makeTextButton("LossScreen", e -> myDisplayManager.goLossScreen()));
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
