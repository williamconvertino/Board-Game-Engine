package ooga.display.game_board.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import ooga.display.DisplayManager;
import ooga.display.communication.EventManager;
import ooga.display.communication.TMEvent;
import ooga.display.popup.PropertyInfoPopUp;
import ooga.display.ui_tools.UIBuilder;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.tilemodels.TileModel;

import static ooga.display.communication.EventManager.EVENT_NAMES.*;

/**
 * Makes a game board
 *
 * @author Aaric Han
 * @author Henry Huynh
 */
public class Board {
  private static final int RECT_WIDTH = 70;
  private static final int RECT_HEIGHT = 80;
  private static final double PREF_WIDTH_BOARD = 1100;
  private static final double PREF_HEIGHT_BOARD = 1100;
  private static final int RADIUS = 10;
  private DisplayManager myDisplayManager;
  private ResourceBundle myLanguage;
  private UIBuilder myBuilder;
  private final int BOARD_SIZE = 11;
  private final int SIDE_LENGTH = 9;
  private final int CENTER_IMAGE_HEIGHT = 500;
  private final int CENTER_IMAGE_WIDTH = 500;

  private ArrayList<Circle> allCirclePieces = new ArrayList<Circle>();

  private VBox boardComponent;
  private GameData gameData;
  private Map<EventManager.EVENT_NAMES, TMEvent> eventMap;
  private ArrayList<PropertyInfoPopUp> allPropInfoPopups = new ArrayList<>(2*BOARD_SIZE + 2*SIDE_LENGTH);

  /**
   * Constructor for the game board
   */
  public Board(DisplayManager displayManager, ResourceBundle language, GameData gameData,
               Map<EventManager.EVENT_NAMES, TMEvent> eventMap) {
    this.eventMap = eventMap;
    myLanguage = language;
    myBuilder = new UIBuilder(myLanguage);
    myDisplayManager = displayManager;
    boardComponent = new VBox();
    this.gameData = gameData;
    createBoard();
    createCenterImage();
    startPieces();
  }

  private void createBoard() {
    StackPane gameBoard = new StackPane();
    GridPane boardEdge = new GridPane();
    boardEdge.setPrefSize(PREF_WIDTH_BOARD, PREF_HEIGHT_BOARD);
    int gameBoardTileCount = gameData.getBoard().getTiles().size();
    int makeTileIndex = gameData.getBoard().getTiles().size() - gameBoardTileCount;

    // Top Left Corner
    if (gameBoardTileCount > 0) {
      boardEdge.add(makePropertyPane(makeTileIndex, RECT_HEIGHT, RECT_HEIGHT), 0, 0);
      makeTileIndex++;
    }

    // Top Row
    for (int i = 0; i < SIDE_LENGTH; i++) {
      if (gameBoardTileCount > 0) {
        boardEdge.add(makePropertyPane(makeTileIndex, RECT_WIDTH, RECT_HEIGHT), i + 1, 0);
        makeTileIndex++;
      }
      else {
        break;
      }
    }

    // Top Right Corner
    if (gameBoardTileCount > 0) {
      boardEdge.add(makePropertyPane(makeTileIndex, RECT_HEIGHT, RECT_HEIGHT), BOARD_SIZE-1, 0);
      makeTileIndex++;
    }

    // Right Column
    for (int i = 0; i < SIDE_LENGTH; i++) {
      if (gameBoardTileCount > 0) {
        boardEdge.add(makePropertyPane(makeTileIndex, RECT_HEIGHT, RECT_WIDTH), BOARD_SIZE-1, i + 1);
        makeTileIndex++;
      }
      else {
        break;
      }
    }

    // Bottom Right Corner
    if (gameBoardTileCount > 0) {
      boardEdge.add(makePropertyPane(makeTileIndex, RECT_HEIGHT, RECT_HEIGHT), BOARD_SIZE-1, BOARD_SIZE-1);
      makeTileIndex++;
    }

    // Bottom Row
    for (int i = 0; i < SIDE_LENGTH; i++) {
      if (gameBoardTileCount > 0) {
        boardEdge.add(makePropertyPane(makeTileIndex, RECT_WIDTH, RECT_HEIGHT), SIDE_LENGTH-i, BOARD_SIZE-1);
        makeTileIndex++;
      }
      else {
        break;
      }
    }

    // Bottom Left Corner
    if (gameBoardTileCount > 0) {
      boardEdge.add(makePropertyPane(makeTileIndex, RECT_HEIGHT, RECT_HEIGHT), 0, BOARD_SIZE-1);
      makeTileIndex++;
    }

    // Left Column
    for (int i = 0; i < SIDE_LENGTH; i++) {
      if (gameBoardTileCount > 0) {
        boardEdge.add(makePropertyPane(makeTileIndex, RECT_HEIGHT, RECT_WIDTH), 0, SIDE_LENGTH-i);
        makeTileIndex++;
      }
      else {
        break;
      }
    }

    // Center Image

    gameBoard.getChildren().add(boardEdge);
    gameBoard.getChildren().add(createCenterImage());
    boardComponent.getChildren().add(gameBoard);
  }

  private BorderPane createCenterImage() {
    BorderPane centerImg = new BorderPane();
    ImageView imageView = new ImageView("resources/center_images/original.png");
    imageView.setFitHeight(CENTER_IMAGE_HEIGHT);
    imageView.setFitWidth(CENTER_IMAGE_WIDTH);
    imageView.setPreserveRatio(true);
    centerImg.setCenter(imageView);
    return centerImg;
  }

  private StackPane makePropertyPane(int gameBoardTileCount, int width, int height) {
    Rectangle tile = getRectangle(width, height, gameBoardTileCount);
    StackPane stackPane = new StackPane();
    stackPane.setId(String.format("Tile%d", gameBoardTileCount));
    Label name = new Label(gameData.getBoard().getTileAtIndex(gameBoardTileCount).getName());
    name.setWrapText(true);
    name.setMaxWidth(Math.min(height, width));
    name.setId("propdisptilename");
    stackPane.getChildren().addAll(tile, name);
    allPropInfoPopups.add(new PropertyInfoPopUp(gameData.getBoard().getTileAtIndex(gameBoardTileCount), myBuilder, myLanguage));
    stackPane.setOnMouseClicked(e -> allPropInfoPopups.get(gameBoardTileCount).showPopup(myDisplayManager.getMyStage()));
    return stackPane;
  }

  private Rectangle getRectangle(double width, double height, int gameBoardTileCount) {
    Rectangle tile = new Rectangle(width, height);
    tile.setFill(Color.WHITE);
    tile.setStroke(Color.BLACK);
    tile.setOnMouseClicked(e->showDetails(gameData.getBoard().getTileAtIndex(gameData.getBoard().getTiles().size() - gameBoardTileCount)));
    return tile;
  }

  private void showDetails(TileModel tile) {
    eventMap.get(SELECT_TILE).execute(tile);
  }


  private void startPieces() {
    StackPane entireBoard = (StackPane) boardComponent.getChildren().get(0);
    GridPane edgeBoard = (GridPane) entireBoard.getChildren().get(0);
    StackPane stackPane = (StackPane) edgeBoard.getChildren().get(0);
    Map<Integer, Pos> positionMap = new HashMap<>();
    positionMap.put(0, Pos.TOP_LEFT);
    positionMap.put(1, Pos.TOP_RIGHT);
    positionMap.put(2, Pos.BOTTOM_LEFT);
    positionMap.put(3, Pos.BOTTOM_RIGHT);
    for(int i = 0; i < gameData.getPlayers().size(); i++) {
      allCirclePieces.add(new Circle(RADIUS, gameData.getPlayers().get(i).getColor()));
      stackPane.getChildren().add(allCirclePieces.get(i));
      StackPane.setAlignment(allCirclePieces.get(i), positionMap.get(i));
    }
  }

  /**
   * Update the circle piece of the player
   */
  public void updateLocation() {
    StackPane entireBoard = (StackPane) boardComponent.getChildren().get(0);
    GridPane edgeBoard = (GridPane) entireBoard.getChildren().get(0);
    int playerPos = gameData.getCurrentPlayer().getLocation();
    int currPlayer = gameData.getPlayers().indexOf(gameData.getCurrentPlayer());
    StackPane stackPane = (StackPane) edgeBoard.getChildren().get(playerPos);
    stackPane.getChildren().add(allCirclePieces.get(currPlayer));
  }

  /**
   * Update popup for properties
   */
  public void updatePropertyPopups() {
    for (int i = 0; i < gameData.getBoard().getTiles().size(); i++) {
      allPropInfoPopups.set(i, new PropertyInfoPopUp(gameData.getBoard().getTileAtIndex(i), myBuilder, myLanguage));
    }
  }

  /**
   * Return this component
   * @return boardComponent
   */
  public VBox getComponent() {
    return boardComponent;
  }

  /**
   * Get the list of circle pieces
   * @return allCirclePieces
   */
  public ArrayList<Circle> getAllCirclePieces() {
    return allCirclePieces;
  }
}
