package ooga.display.game_board.bottom;

import javafx.scene.layout.VBox;
import ooga.display.DisplayManager;
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
  private DisplayManager myDisplayManager;

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
    bottomComponent.getChildren().add(myUIBuilder.makeTextButton("LossScreen", e -> myDisplayManager.goLossScreen()));
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
