package ooga.display.game_board.left;

import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import ooga.display.communication.EventManager;
import ooga.display.communication.TMEvent;
import ooga.display.ui_tools.UIBuilder;
import ooga.model.data.gamedata.GameData;

/**
 * This is the left display element of the game display
 *
 * @author Henry Huynh
 * @author Aaric Han
 */
public class Left {


  private static final String BALANCE = "Balance";
  private static final String PROPERTIES = "Properties";
  private static final String CARDS = "Cards";
  private static final String NONE = "None";
  private static final String COLOR = "Color";
  private final VBox leftComponent;
  private final UIBuilder myUIBuilder;
  private final ResourceBundle myLangResource;
  private final GameData myGameData;
  private final Map<EventManager.EVENT_NAMES, TMEvent> myEventMap;
  private static final String LOCATION = "Location";

  /**
   * The constructor for the left display element
   */
  public Left(ResourceBundle language, Map<EventManager.EVENT_NAMES, TMEvent> eventMap,
      GameData gameData) {
    myGameData = gameData;
    myEventMap = eventMap;
    leftComponent = new VBox();
    myLangResource = language;
    myUIBuilder = new UIBuilder(myLangResource);
    makeLeftComponent();
  }


  /**
   * Make the UI for the leftComponent
   */
  private void makeLeftComponent() {
    TabPane tabPane = myUIBuilder.makeTabPane("tabPane");
    tabPane.getTabs().addAll(makePlayerTab(0), makePlayerTab(1), makePlayerTab(2),
        makePlayerTab(3));
    leftComponent.getChildren().add(tabPane);

  }

  /**
   * @param playerIndex 0 - indexed player number
   * @return Tab for the specified player
   */
  private Tab makePlayerTab(int playerIndex) {

    Tab tab1 = myUIBuilder.makeTab(myGameData.getPlayers().get(playerIndex).getName());
    VBox result = new VBox();
    result.getChildren().addAll(
        new Label(myGameData.getPlayers().get(playerIndex).getName()),
        myUIBuilder.makeLabel(COLOR),
        new Circle(20, myGameData.getPlayers().get(playerIndex).getColor()),
        myUIBuilder.makeLabel(LOCATION),
        new Label(String.valueOf(myGameData.getBoard()
            .getTileAtIndex(myGameData.getPlayers().get(playerIndex).getLocation()).getName())),
        myUIBuilder.makeLabel(BALANCE),
        new Label(String.valueOf(myGameData.getPlayers().get(playerIndex).getBalance())),
        myUIBuilder.makeLabel(PROPERTIES),
        makePlayerTabProperties(playerIndex),
        myUIBuilder.makeLabel(CARDS),
        makePlayerTabCards(playerIndex)
    );
    tab1.setContent(result);
    tab1.setClosable(false);
    return tab1;
  }

  /**
   * Update the player tab with new property information
   *
   * @param playerIndex
   * @return label with properties information
   */
  public Label makePlayerTabProperties(int playerIndex) {
    if (myGameData.getPlayers().get(playerIndex).getProperties().isEmpty()) {
      return (Label) myUIBuilder.makeLabel(NONE);
    }
    return (Label) myUIBuilder.makeLabelNoID(
        String.valueOf(myGameData.getPlayers().get(playerIndex).getProperties()));
  }

  /**
   * Update the player tab with new card information
   *
   * @param playerIndex
   * @return label with card information
   */
  public Label makePlayerTabCards(int playerIndex) {
    if (myGameData.getPlayers().get(playerIndex).getCards().isEmpty()) {
      return (Label) myUIBuilder.makeLabel(NONE);
    }
    return (Label) myUIBuilder.makeLabel(
        String.valueOf(myGameData.getPlayers().get(playerIndex).getCards()));
  }

  /**
   * Returns the left component VBox
   *
   * @return leftComponent
   */
  public VBox getComponent() {
    return leftComponent;
  }

}
