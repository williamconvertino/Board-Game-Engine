package ooga.display.game_board.board;

import static ooga.display.communication.EventManager.EVENT_NAMES.SELECT_TILE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.communication.EventManager;
import ooga.display.communication.EventManager.EVENT_NAMES;
import ooga.display.communication.TMEvent;
import ooga.display.popup.PropertyInfoPopUp;
import ooga.display.ui_tools.UIBuilder;
import ooga.model.data.gamedata.GameData;

/**
 * Makes the gameboard
 *
 * @author Aaric Han
 * @author Henry Huynh
 */
public class GameBoard {
  private static final int RECT_WIDTH = 60;
  private static final int RECT_HEIGHT = 70;
  private static final double PREF_BOARD_SIDE_LEN = 2*RECT_HEIGHT + 9*RECT_WIDTH;
  private static final int RADIUS = 10;

  private DisplayManager myDisplayManager;
  private ResourceBundle myLanguage;
  private UIBuilder myBuilder;
  private final int BOARD_SIZE = 11;
  private final int SIDE_LENGTH = 9;
  private final int CENTER_IMAGE_SIDE_LEN = 300;

  private ArrayList<Circle> allCirclePieces = new ArrayList<Circle>();

  private BorderPane boardComponent;
  private GameData gameData;
  private Map<EventManager.EVENT_NAMES, TMEvent> eventMap;
  private ArrayList<PropertyInfoPopUp> allPropInfoPopups = new ArrayList<>(2*BOARD_SIZE + 2*SIDE_LENGTH);
  private static final String DEFAULT_RESOURCE_PACKAGE =
      Display.class.getPackageName() + ".resources.";

  private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static final String ORIGINAL_STYLE = STYLE_PACKAGE + "original.css";
  private static final String MONO_STYLE = STYLE_PACKAGE + "mono.css";
  private static final String DUKE_STYLE = STYLE_PACKAGE + "duke.css";
  private static final String ORIGINAL_IMG = "center_images/original.png";
  private static final String DUKE_IMG = "center_images/duke.png";
  private static final String MONO_IMG = "center_images/mono.png";

  private static final Map<String, String> IMAGE_MAP = Map.of(
      ORIGINAL_STYLE, ORIGINAL_IMG,
      MONO_STYLE, MONO_IMG,
      DUKE_STYLE, DUKE_IMG
  );

  private String myStyle = ORIGINAL_STYLE;

  private Map<Integer, StackPane> tileStackPaneIndexMap = new HashMap<>();

  private Map<Integer, Pos> positionMap = new HashMap<>();

  public GameBoard(DisplayManager displayManager, ResourceBundle language, GameData gameData,
      Map<EVENT_NAMES, TMEvent> eventMap, String theme) {
    myStyle = theme;
    this.eventMap = eventMap;
    myLanguage = language;
    myBuilder = new UIBuilder(myLanguage);
    myDisplayManager = displayManager;
    boardComponent = new BorderPane();
    this.gameData = gameData;

    initializePositionMap();
    setBoardAttributes();
    createBoard();
    startPieces();
  }

  private void initializePositionMap() {
    positionMap.put(0, Pos.TOP_LEFT);
    positionMap.put(1, Pos.TOP_RIGHT);
    positionMap.put(2, Pos.BOTTOM_LEFT);
    positionMap.put(3, Pos.BOTTOM_RIGHT);
  }

  private void setBoardAttributes() {
    boardComponent.setPrefSize(PREF_BOARD_SIDE_LEN, PREF_BOARD_SIDE_LEN);
    boardComponent.setMaxWidth(PREF_BOARD_SIDE_LEN);
    boardComponent.setMaxHeight(PREF_BOARD_SIDE_LEN);
  }

  private void createBoard() {
    makeCenterImage(myStyle);
    makeTopProperties();
    makeRightProperties();
    makeBottomProperties();
    makeLeftProperties();
  }

  private void startPieces() {
    StackPane stackPane = tileStackPaneIndexMap.get(0);
    for(int i = 0; i < gameData.getPlayers().size(); i++) {
      allCirclePieces.add(new Circle(RADIUS, gameData.getPlayers().get(i).getColor()));
      stackPane.getChildren().add(allCirclePieces.get(i));
      StackPane.setAlignment(allCirclePieces.get(i), positionMap.get(i));
    }
  }

  private void makeCenterImage(String theme) {
    ImageView imageView = new ImageView(IMAGE_MAP.get(theme));
    imageView.setFitHeight(CENTER_IMAGE_SIDE_LEN);
    imageView.setFitWidth(CENTER_IMAGE_SIDE_LEN);
    imageView.setPreserveRatio(true);
    boardComponent.setCenter(imageView);
  }

  private void makeTopProperties() {
    HBox top = new HBox(0);
    top.setAlignment(Pos.CENTER);
    for (int i = 0; i < BOARD_SIZE; i++) {
      if (i == 0 || i == BOARD_SIZE - 1) {
        top.getChildren().add(makeGameBoardTile(RECT_HEIGHT, RECT_HEIGHT, i));
      }
      else {
        top.getChildren().add(makeGameBoardTile(RECT_WIDTH, RECT_HEIGHT, i));
      }
    }
    boardComponent.setTop(top);
  }

  private void makeRightProperties() {
    VBox right = new VBox(0);
    for (int i = BOARD_SIZE; i < BOARD_SIZE + 9; i++) {
      right.getChildren().add(makeGameBoardTile(RECT_HEIGHT, RECT_WIDTH, i));
    }
    boardComponent.setRight(right);
  }

  private void makeBottomProperties() {
    HBox bottom = new HBox(0);
    bottom.setAlignment(Pos.CENTER);
    for (int i = BOARD_SIZE*2+8; i >= BOARD_SIZE + 9; i--) {
      if (i == BOARD_SIZE*2+8 || i == BOARD_SIZE + 9) {
        bottom.getChildren().add(makeGameBoardTile(RECT_HEIGHT, RECT_HEIGHT, i));
      }
      else {
        bottom.getChildren().add(makeGameBoardTile(RECT_WIDTH, RECT_HEIGHT, i));
      }
    }
    boardComponent.setBottom(bottom);
  }

  private void makeLeftProperties() {
    VBox left = new VBox(0);
    for (int i = 39; i > 30; i--) {
      left.getChildren().add(makeGameBoardTile(RECT_HEIGHT, RECT_WIDTH, i));
    }
    boardComponent.setLeft(left);
  }

  private StackPane makeGameBoardTile(int width, int height, int tileIndex) {
    StackPane stackPane = new StackPane();
    stackPane.setId(String.format("Tile%d",tileIndex));
    stackPane.setPrefSize(width, height);
    Rectangle rect = new Rectangle(width, height);
    rect.setFill(Color.WHITE);
    rect.setStroke(Color.BLACK);
    Label propName = new Label(gameData.getBoard().getTileAtIndex(tileIndex).getName());
    propName.setId("propdisptilename");
    propName.setWrapText(true);
    propName.setMaxWidth(Math.min(height, width));
    PropertyInfoPopUp myPropPopup = new PropertyInfoPopUp(gameData.getBoard().getTileAtIndex(tileIndex), myBuilder, myLanguage);
    allPropInfoPopups.add(myPropPopup);
    stackPane.setOnMouseClicked(e -> {
      eventMap.get(SELECT_TILE).execute(gameData.getBoard().getTileAtIndex(tileIndex));
      allPropInfoPopups.get(tileIndex).showPopup(myDisplayManager.getMyStage());
    });
    stackPane.getChildren().addAll(rect, propName);
    tileStackPaneIndexMap.put(tileIndex, stackPane);
    return stackPane;
  }

  public Node getComponent() {
    return boardComponent;
  }

  /**
   * Update the circle piece of the player
   */
  public void updateLocation() {
    int playerPos = gameData.getCurrentPlayer().getLocation();
    int currPlayer = gameData.getPlayers().indexOf(gameData.getCurrentPlayer());
    StackPane stackPane = tileStackPaneIndexMap.get(playerPos);
    stackPane.getChildren().add(allCirclePieces.get(currPlayer));
    StackPane.setAlignment(allCirclePieces.get(currPlayer), positionMap.get(currPlayer));
    updatePropertyPopups();
  }

  /**
   * Update popup for properties
   */
  public void updatePropertyPopups() {
    for (int i = 0; i < gameData.getBoard().getTiles().size(); i++) {
      allPropInfoPopups.set(i, new PropertyInfoPopUp(gameData.getBoard().getTileAtIndex(i), myBuilder, myLanguage));
    }
  }
}
