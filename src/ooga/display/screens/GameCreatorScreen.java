package ooga.display.screens;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.ui_tools.LanguageUI;
import ooga.display.ui_tools.UIBuilder;

/**
 * This class allows the user to create their own monopoly board from scratch, and adjust the game
 * rules.
 *
 * @author Casey Goldstein
 */
public class GameCreatorScreen extends Display {

  private static final String DEFAULT_RESOURCE_PACKAGE =
      Display.class.getPackageName() + ".resources.";
  private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static final String DEFAULT_STYLE = STYLE_PACKAGE + "original.css";
  private static final String DUKE_STYLE = STYLE_PACKAGE + "duke.css";
  private static final String MONO_STYLE = STYLE_PACKAGE + "mono.css";
  private static final List<String> LANGUAGES_LIST = List.of("English", "Spanish", "French",
      "Irish", "Latin");
  private static final int TILE_AMOUNT = 40;
  private static final String IMAGE_RESOURCE = DEFAULT_RESOURCE_PACKAGE + "image_paths";
  private static final String VARIATION_PATH = "data/variations/";
  private static final String PROPERTIES_PATH = "/properties";
  private static final int SELECTOR_MENU_WIDTH = 600;
  private static final int IMAGE_SIZE = 40;
  private static final int TILE_SIZE = 50;
  private static final int CREATOR_ROW_MAX = 10;
  private static final int TILE_X_TRANSLATION = -500;
  private static final int TILE_Y_TRANSLATION = 50;
  private static final int DISPLAY_HEIGHT = 600;
  private static final int DISPLAY_WIDTH = 900;
  private static final int SET_GAME_BUTTON_WIDTH = 150;
  private static final int SET_GAME_BUTTON_HEIGHT = 75;
  private static final int SET_GAME_BUTTON_X = 60;
  private static final int SET_GAME_BUTTON_Y = 20;
  private static final int INSTRUCTIONS_WIDTH = 260;
  private static final int INSTRUCTIONS_HEIGHT = 460;
  private static final int INSTRUCTIONS_TEXT_WRAP_WIDTH = 220;

  private Scene scene;
  private TextField gameName;
  private Popup PropertyPopUp;
  private VBox propBox;
  private int tileCounter;
  private final VBox selectorMenu;
  private final Stage myStage;
  private final DisplayManager myDisplayManager;
  private final UIBuilder myBuilder;
  private final ResourceBundle myLangResource;
  private LanguageUI myLanguageUI;

  private TextField propertyName;
  private TextField propertyCost;
  private TextField propertyRentCosts;
  private TextField propertyHouseCost;
  private TextField propertyNeighbors;
  private TextField propertyMortgage;
  private TextField propertyColor;
  private TextField propertyImage;
  private Label counter;
  private HBox board;
  private final ResourceBundle myGameImages;

  private int rowJumper;
  private int colJumper;

  private Button jailButton;
  private Button goToJailButton;

  private HBox createSpecialTileButtons;

  private final HBox allElements;

  private final FolderFactory folderFactory;

  private String dieType;
  private String boardManagerType;
  private String playerManagerType;

  /**
   * Constructor for creating a custom game
   *
   * @param stage          The stage
   * @param displayManager The display manager
   * @param langResource   The language
   */
  public GameCreatorScreen(Stage stage, DisplayManager displayManager,
      ResourceBundle langResource) {
    rowJumper = 0;
    colJumper = 0;
    dieType = "OriginalDice";
    boardManagerType = "OriginalBoardManager";
    playerManagerType = "OriginalPlayerManager";
    PropertyPopUp = new Popup();
    propBox = new VBox();
    propBox.setId("propertyCreatorBox");
    tileCounter = TILE_AMOUNT - 1; //The Go tile is already placed
    myLangResource = langResource;
    myBuilder = new UIBuilder(langResource);
    myStage = stage;
    myDisplayManager = displayManager;
    myGameImages = ResourceBundle.getBundle(IMAGE_RESOURCE);
    allElements = new HBox();
    folderFactory = new FolderFactory();
    selectorMenu = new VBox();
    selectorMenu.setMaxWidth(SELECTOR_MENU_WIDTH);
    selectorMenu.setMinWidth(SELECTOR_MENU_WIDTH);
    selectorMenu.getChildren().add(createInitialSelectorMenu());
    allElements.getChildren().add(selectorMenu);
    makeScene();
  }

  //Sets the proper variation name and changes back to Player Screen
  private void setGame() throws IOException {
    if (tileCounter != 0) {
      myBuilder.makeErrorAlert("Board Incomplete", "Please complete the board!").showAndWait();
      return;
    }
    folderFactory.writeConfigFile(boardManagerType, playerManagerType, dieType);
    myDisplayManager.setVariationName(gameName.getText());
    myDisplayManager.goPlayerScreen();
  }

  //Creates the first elements shown on the selector menu
  private Node createInitialSelectorMenu() {
    VBox result = new VBox();
    result.setMaxWidth(SELECTOR_MENU_WIDTH);
    gameName = (TextField) myBuilder.makePrefilledTextField("EnterGameName");
    result.getChildren().add(myBuilder.makeLabel("GameCreator"));
    result.getChildren().add(gameName);
    result.getChildren().add(myBuilder.makeTextButton(("CreateNewGame"), e -> {
      try {
        createFullSelectorMenu();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }));
    return result;
  }

  private VBox createRightElements() {
    VBox rightElements = new VBox();
    rightElements.setId("RightElements");
    rightElements.setId("RightElements");
    Button setGameButton = myBuilder.makeTextButton("SetGame", e -> {
      try {
        setGame();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });
    setGameButton.setTranslateX(SET_GAME_BUTTON_X);
    setGameButton.setTranslateY(SET_GAME_BUTTON_Y);
    setGameButton.setPrefWidth(SET_GAME_BUTTON_WIDTH);
    setGameButton.setPrefHeight(SET_GAME_BUTTON_HEIGHT);
    StackPane instructions = new StackPane();
    instructions.setMinWidth(INSTRUCTIONS_WIDTH);
    instructions.setMaxWidth(INSTRUCTIONS_WIDTH);
    instructions.setMaxHeight(INSTRUCTIONS_HEIGHT);
    instructions.setMinHeight(INSTRUCTIONS_HEIGHT);
    instructions.setId("EditorInstructions");
    rightElements.getChildren().add(instructions);
    rightElements.getChildren().add(setGameButton);
    Text instructionText = new Text(myLangResource.getString("EditorInstructions"));
    instructions.getChildren().add(instructionText);
    instructionText.setWrappingWidth(INSTRUCTIONS_TEXT_WRAP_WIDTH);
    return rightElements;
  }

  //creates the variation folder hierarchy and displays the rest of the selector menu
  private void createFullSelectorMenu() throws IOException {
    folderFactory.makeDirectories(gameName.getText());
    allElements.getChildren().add(createRightElements());
    selectorMenu.getChildren().add(myBuilder.makeLabel("SetRules"));

    HBox ruleBox = new HBox();
    //create Die Selector
    List<String> dieOptions = new ArrayList<>(
        Arrays.asList("OriginalDice", "DoublesDie", "BowserDie", "D20Die", "TenCoins"));
    ruleBox.getChildren().add(myBuilder.makeCombo("ChooseDice", dieOptions, e -> setDie(e)));

    List<String> playerOptions = new ArrayList<>(
        Arrays.asList("OriginalPlayerManager", "RandomPlayerManager", "WinMorePlayerManager",
            "WorstFirstPlayerManager"));
    ruleBox.getChildren()
        .add(myBuilder.makeCombo("ChoosePlayStyle", playerOptions, e -> setPlayerManager(e)));

    List<String> boardOptions = new ArrayList<>(
        Arrays.asList("OriginalBoardManager", "BackwardsBoardManager"));
    ruleBox.getChildren()
        .add(myBuilder.makeCombo("ChooseBoard", boardOptions, e -> setBoardManager(e)));
    selectorMenu.getChildren().add(ruleBox);

    HBox createPropertyButtons = new HBox();
    createPropertyButtons.getChildren()
        .add(myBuilder.makeTextButton("AddRegularProperty", e -> createPropertyPopUp("Regular")));
    createPropertyButtons.getChildren()
        .add(myBuilder.makeTextButton("AddRailroadProperty", e -> createPropertyPopUp("Railroad")));
    createPropertyButtons.getChildren().add(
        myBuilder.makeTextButton("AddUtilitiesProperty", e -> createPropertyPopUp("Utilities")));
    selectorMenu.getChildren().add(createPropertyButtons);

    createSpecialTileButtons = new HBox();
    createTileButtons();
    selectorMenu.getChildren().add(createSpecialTileButtons);

    selectorMenu.getChildren().add(myBuilder.makeLabel("BoardTiles"));

    HBox tileCountBox = new HBox();
    counter = (Label) myBuilder.makeLabel("TileCounter");
    tileCountBox.getChildren().addAll(myBuilder.makeLabel("TilesLeft"), counter);
    selectorMenu.getChildren().add(tileCountBox);

    board = new HBox();
    selectorMenu.getChildren().add(board);
    board.getChildren().add(createBoardSpace("Go", new Image(myGameImages.getString("Go"))));
  }

  //Sets the die to whatever the user chooses
  private void setDie(String die) {
    dieType = die;
  }

  private void setPlayerManager(String playerType) {
    playerManagerType = playerType;
  }

  private void setBoardManager(String boardType) {
    boardManagerType = boardType;
  }

  //creates the special tile creator buttons
  private void createTileButtons() throws IOException {
    jailButton = myBuilder.makeImageButton("Jail", e -> tryCreateSingleCardTile("Jail", jailButton),
        myGameImages.getString("Jail"), IMAGE_SIZE, IMAGE_SIZE);
    createSpecialTileButtons.getChildren().add(jailButton);
    goToJailButton = myBuilder.makeImageButton("GoToJail",
        e -> tryCreateSingleCardTile("GoToJail", goToJailButton),
        myGameImages.getString("GoToJail"), IMAGE_SIZE, IMAGE_SIZE);
    createSpecialTileButtons.getChildren().add(goToJailButton);
    createSpecialTileButtons.getChildren().add(
        myBuilder.makeImageButton("Chance", e -> tryCreateCardTile("Chance"),
            myGameImages.getString("Chance"), IMAGE_SIZE, IMAGE_SIZE));
    createSpecialTileButtons.getChildren().add(
        myBuilder.makeImageButton("CommunityChest", e -> tryCreateCardTile("CommunityChest"),
            myGameImages.getString("CommunityChest"), IMAGE_SIZE, IMAGE_SIZE));
    createSpecialTileButtons.getChildren().add(
        myBuilder.makeImageButton("FreeParking", e -> tryCreateCardTile("FreeParking"),
            myGameImages.getString("FreeParking"), IMAGE_SIZE, IMAGE_SIZE));
    createSpecialTileButtons.getChildren().add(
        myBuilder.makeImageButton("LuxuryTax", e -> tryCreateCardTile("LuxuryTax"),
            myGameImages.getString("LuxuryTax"), IMAGE_SIZE, IMAGE_SIZE));
    createSpecialTileButtons.getChildren().add(
        myBuilder.makeImageButton("IncomeTax", e -> tryCreateCardTile("IncomeTax"),
            myGameImages.getString("IncomeTax"), IMAGE_SIZE, IMAGE_SIZE));
  }

  //calls CreateSingleCardTime and catches IOExceptions
  private void tryCreateSingleCardTile(String name, Button button) {
    try {
      createSingleCardTile(name, button);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //calls CreateCardTime and catches IOExceptions
  private void tryCreateCardTile(String name) {
    try {
      createCardTile(name);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //Adds a special tile to the board, and decrements tile count
  private void createCardTile(String name) throws IOException {
    if (tileCounter <= 0) {
      myBuilder.makeErrorAlert("Board Full", "You Have Placed All Tiles. Please press 'Set Game'")
          .showAndWait();
      return;
    }
    board.getChildren().add(createSpecialTile(name, new Image(myGameImages.getString(name))));
    tileCounter--;
    counter.setText("" + tileCounter);
  }

  //Adds a special, one-use-only tile to the board, and decrements tile count
  private void createSingleCardTile(String name, Button jail) throws IOException {
    if (tileCounter <= 0) {
      myBuilder.makeErrorAlert("Board Full", "You Have Placed All Tiles. Please press 'Set Game'")
          .showAndWait();
      return;
    }
    board.getChildren().add(createSpecialTile(name, new Image(myGameImages.getString(name))));
    createSpecialTileButtons.getChildren().remove(jail);
    tileCounter--;
    counter.setText("" + tileCounter);
  }


  //Creates the property pop up screen for the user to input
  private void createPropertyPopUp(String type) {
    String myType = type;
    propertyName = (TextField) myBuilder.makeTextField("EnterPropertyName");
    propertyCost = (TextField) myBuilder.makeTextField("EnterCost");
    propertyRentCosts = (TextField) myBuilder.makeTextField("EnterRentCosts");
    propertyNeighbors = (TextField) myBuilder.makeTextField("EnterNeighbors");
    propertyMortgage = (TextField) myBuilder.makeTextField("EnterMortgagePrice");

    propBox.getChildren().add(myBuilder.makeLabel("EnterPropertyName"));
    propBox.getChildren().add(propertyName);
    propBox.getChildren().add(myBuilder.makeLabel("EnterCost"));
    propBox.getChildren().add(propertyCost);
    propBox.getChildren().add(myBuilder.makeLabel("EnterRentCosts"));
    propBox.getChildren().add(propertyRentCosts);
    propBox.getChildren().add(myBuilder.makeLabel("EnterNeighbors"));
    propBox.getChildren().add(propertyNeighbors);
    propBox.getChildren().add(myBuilder.makeLabel("EnterMortgagePrice"));
    propBox.getChildren().add(propertyMortgage);

    switch (myType) {
      case "Regular":
        propertyHouseCost = (TextField) myBuilder.makeTextField("EnterHouseCost");
        propertyColor = (TextField) myBuilder.makeTextField("EnterColor");
        propBox.getChildren().add(myBuilder.makeLabel("EnterHouseCost"));
        propBox.getChildren().add(propertyHouseCost);
        propBox.getChildren().add(myBuilder.makeLabel("EnterColor"));
        propBox.getChildren().add(propertyColor);
        break;

      case "Railroad":
      case "Utilities":
        propertyImage = (TextField) myBuilder.makeTextField("EnterImage");
        propBox.getChildren().add(myBuilder.makeLabel("EnterImage"));
        propBox.getChildren().add(propertyImage);
        break;
    }
    propBox.getChildren().add(myBuilder.makeTextButton("SaveProperty", e -> {
      try {
        saveProperty(myType);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }));
    propBox.setId("propertyVBox");
    PropertyPopUp.getContent().add(propBox);
    PropertyPopUp.show(myStage);
  }

  //Saves the inputted property information, adds it to the board, and writes it to the variation folder.
  private void saveProperty(String type) throws IOException {
    if (tileCounter <= 0) {
      myBuilder.makeErrorAlert("Board Full", "You Have Placed All Tiles. Please press 'Set Game'")
          .showAndWait();
      return;
    }
    String myType = type;
    switch (myType) {
      case "Regular":
        writeRegularPropertyFile(propertyName.getText(), propertyCost.getText(),
            propertyRentCosts.getText(), propertyHouseCost.getText(), propertyNeighbors.getText(),
            propertyMortgage.getText(), propertyColor.getText());
        board.getChildren().add(createBoardSpace(propertyName.getText(), propertyColor.getText()));
        break;

      case "Utilities":
      case "Railroad":
        writeSpecialPropertyFile(propertyName.getText(), type, propertyCost.getText(),
            propertyRentCosts.getText(), propertyNeighbors.getText(), propertyMortgage.getText(),
            propertyImage.getText());
        board.getChildren()
            .add(createBoardSpace(propertyName.getText(), new Image(myGameImages.getString(type))));
        break;
    }

    PropertyPopUp.hide();
    propBox = new VBox();
    PropertyPopUp = new Popup();
    tileCounter--;
    counter.setText("" + tileCounter);
  }


  //Writes a regular property file to the properties folder
  private void writeRegularPropertyFile(String name, String cost, String rentcosts,
      String housecost, String neighbors, String mortgage, String color)
      throws IOException {
    File property = new File(
        VARIATION_PATH + gameName.getText().replace(" ", "_") + PROPERTIES_PATH + "/"
            + name.toLowerCase(Locale.ROOT).replace(" ", "_") + PROPERTIES_PATH.replace("/", "."));

    FileWriter fw = new FileWriter(property);
    fw.write("Name=" + name + "\n");
    fw.write("Type=" + "Regular" + "\n");
    fw.write("Cost=" + cost + "\n");
    fw.write("RentCost=" + rentcosts + "\n");
    fw.write("MaxHouses=" + 4 + "\n");
    fw.write("HouseCost=" + housecost + "\n");
    fw.write("Neighbors=" + neighbors + "\n");
    fw.write("Mortgage=" + mortgage + "\n");
    fw.write("Color=" + color);
    fw.close();
    property.createNewFile();
  }

  //Writes a special property file to the properties folder
  private void writeSpecialPropertyFile(String name, String type, String cost, String rentcosts,
      String neighbors, String mortgage, String image)
      throws IOException {
    File property = new File(
        VARIATION_PATH + gameName.getText().replace(" ", "_") + PROPERTIES_PATH + "/"
            + name.toLowerCase(Locale.ROOT).replace(" ", "_") + PROPERTIES_PATH.replace("/", "."));
    FileWriter fw = new FileWriter(property);
    fw.write("Name=" + name + "\n");
    fw.write("Type=" + type + "\n");
    fw.write("Cost=" + cost + "\n");
    fw.write("RentCost=" + rentcosts + "\n");
    fw.write("Neighbors=" + neighbors + "\n");
    fw.write("Mortgage=" + mortgage + "\n");
    fw.write("Image=" + image);
    fw.close();
    property.createNewFile();
  }

  //Creates a board space with given name and color.
  private VBox createBoardSpace(String name, String color) throws IOException {
    folderFactory.writeLineToBoard(name);
    VBox tileBox = new VBox();
    tileBox.setId("creatorTile");
    Label tilename = new Label(name);
    tilename.setId("tileText");
    tilename.setWrapText(true);
    tilename.setMinWidth(TILE_SIZE);
    tilename.setMinHeight(TILE_SIZE);
    tileBox.setStyle("-fx-background-color:" + color + ";");
    tileBox.getChildren().addAll(tilename);
    tileBox.setMaxWidth(TILE_SIZE);
    tileBox.setMaxHeight(TILE_SIZE);
    tileBox.setMinWidth(TILE_SIZE);
    tileBox.setMinHeight(TILE_SIZE);
    if (tileCounter % CREATOR_ROW_MAX == 0) {
      colJumper += TILE_X_TRANSLATION;
      rowJumper += TILE_Y_TRANSLATION;
    }
    tileBox.setTranslateY(rowJumper);
    tileBox.setTranslateX(colJumper);
    return tileBox;
  }

  //creates a board space with given name and image
  private VBox createBoardSpace(String name, Image image) throws IOException {
    folderFactory.writeLineToBoard(name);
    VBox stackPane = new VBox();
    stackPane.setId("creatorTile");
    ImageView view = new ImageView(image);
    view.setFitWidth(IMAGE_SIZE);
    view.setFitHeight(IMAGE_SIZE);
    stackPane.setStyle("-fx-background-color:" + "white" + ";");
    stackPane.getChildren().addAll(view);
    stackPane.setMaxWidth(TILE_SIZE);
    stackPane.setMaxHeight(TILE_SIZE);
    stackPane.setMinWidth(TILE_SIZE);
    stackPane.setMinHeight(TILE_SIZE);
    if (tileCounter % CREATOR_ROW_MAX == 0) {
      colJumper += TILE_X_TRANSLATION;
      rowJumper += TILE_Y_TRANSLATION;
    }
    stackPane.setTranslateY(rowJumper);
    stackPane.setTranslateX(colJumper);
    return stackPane;
  }

  //creates a special tile for the board with given name and image
  private VBox createSpecialTile(String name, Image image) throws IOException {
    return createBoardSpace(myLangResource.getString(name), image);
  }

  //creates scene
  private void makeScene() {
    scene = new Scene(allElements, DISPLAY_WIDTH, DISPLAY_HEIGHT);
    scene.getStylesheets().add(DEFAULT_STYLE);
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