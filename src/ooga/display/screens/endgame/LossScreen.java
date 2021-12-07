package ooga.display.screens.endgame;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.ui_tools.UIBuilder;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;

/**
 * @author Henry Huynh The loss screen.
 */
public class LossScreen extends Display {


  private final DisplayManager myDisplayManager;
  private final UIBuilder myBuilder;
  private Scene scene;
  private final String myStyle;
  private final BorderPane lossScreen;
  private final GameData myGameData;

  private static final String DEFAULT_RESOURCE_PACKAGE =
      Display.class.getPackageName() + ".resources.";
  private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static final String DEFAULT_STYLE = STYLE_PACKAGE + "original.css";
  private static final String DUKE_STYLE = STYLE_PACKAGE + "duke.css";
  private static final String MONO_STYLE = STYLE_PACKAGE + "mono.css";

  /**
   * @param displayManager The display manager
   * @param langResource   The language resource
   * @param theme          The theme
   * @param gameData       the data of the game
   */
  public LossScreen(DisplayManager displayManager,
      ResourceBundle langResource, String theme, GameData gameData) {
    myGameData = gameData;
    myStyle = theme;
    myBuilder = new UIBuilder(langResource);
    myDisplayManager = displayManager;
    lossScreen = new BorderPane();
    lossScreen.setTop(playersWhoLostLabel());
    lossScreen.setLeft(myBuilder.makeTextButton("GoBackToGame", e -> myDisplayManager.goToGame()));
    makeScene();
  }

  private Label playersWhoLostLabel() {
    StringBuilder sb = new StringBuilder();
    List<String> playersLost = new ArrayList<>();
    sb.append("The following players have lost");
    for (Player p : myGameData.getPlayers()) {
      if (!p.isActive()) {
        playersLost.add(p.getName());
      }
    }
    for (String name : playersLost) {
      sb.append(" ");
      sb.append(name);
    }
    return new Label(sb.toString());
  }

  private void makeScene() {
    scene = new Scene(lossScreen, 800, 600);
    scene.getStylesheets().add(myStyle);
  }

  /**
   * Get the scene
   *
   * @return scene
   */
  @Override
  public Scene getScene() {
    return scene;
  }


}
