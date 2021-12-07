package ooga.display.screens;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.ui_tools.LanguageUI;
import ooga.display.ui_tools.UIBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class allows the user to create their own monopoly board from scratch, and adjust the game rules.
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
  private static final List<String> LANGUAGES_LIST = List.of("English", "Spanish", "French", "Irish", "Latin");
  private static final int TILE_AMOUNT = 40;
  private static final String IMAGE_RESOURCE = DEFAULT_RESOURCE_PACKAGE + "image_paths";
  private static final String VARIATION_PATH = "data/variations/";
  private static final String CARDS_PATH = "/cards";
  private static final String CHANCE_PATH = "/chance";
  private static final String COMMUNITY_CHEST_PATH = "/community_chest";
  private static final String TILES_PATH = "/tiles";
  private static final String CONFIG = "/config";
  private static final String BOARD_PATH = "/board";
  private static final String PROPERTIES_PATH = "/properties";
  private static final String DEFAULT_VARIATION_PATH = "/original";
  private static final int TILE_SUBSTRING_CUTOFF = 37;
  private static final int CHANCE_SUBSTRING_CUTOFF = 38;
  private static final int COMMUNITY_CHEST_SUBSTRING_CUTOFF = 47;
  private static final int SELECTOR_MENU_WIDTH = 500;
  private static final int IMAGE_SIZE = 40;
  private static final int TILE_SIZE = 50;
  private static final int CREATOR_ROW_MAX = 10;
  private static final int TILE_X_TRANSLATION = -500;
  private static final int TILE_Y_TRANSLATION = 50;
  private static final int DISPLAY_HEIGHT = 600;
  private static final int DISPLAY_WIDTH = 800;
  private static final int SET_GAME_BUTTON_WIDTH=150;
  private static final int SET_GAME_BUTTON_HEIGHT=75;


  private Scene scene;
  private TextField gameName;
  private Popup PropertyPopUp;
  private VBox propBox;
  private int tileCounter;
  private VBox selectorMenu;
  private Stage myStage;
  private DisplayManager myDisplayManager;
  private UIBuilder myBuilder;
  private ResourceBundle myLangResource;
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
  private ResourceBundle myGameImages;

  private int rowJumper;
  private int colJumper;

  private File variationFolder;
  private File variationBoard;
  private File variationCards;
  private File variationProperties;
  private File variationConfigFile;
  private File variationBoardFile;
  private File variationTiles;
  private File variationChanceCards;
  private File variationCommunityChestCards;

  private Button jailButton;
  private Button goToJailButton;

  private HBox createSpecialTileButtons;

  private Path src;
  private Path des;

  private HBox allElements;


  /**
   * Constructor for creating a custom game
   * @param stage The stage
   * @param displayManager The display manager
   * @param langResource The language
   */
  public GameCreatorScreen(Stage stage, DisplayManager displayManager, ResourceBundle langResource) {
    rowJumper = 0;
    colJumper = 0;
    PropertyPopUp = new Popup();
    propBox = new VBox();
    propBox.setId("propertyCreatorBox");
    tileCounter = TILE_AMOUNT-1; //The Go tile is already placed
    myLangResource = langResource;
    myBuilder = new UIBuilder(langResource);
    myStage = stage;
    myDisplayManager = displayManager;
    myGameImages = ResourceBundle.getBundle(IMAGE_RESOURCE);

    allElements = new HBox();

    selectorMenu = new VBox();
    selectorMenu.setMaxWidth(SELECTOR_MENU_WIDTH);
    selectorMenu.setMinWidth(SELECTOR_MENU_WIDTH);
    selectorMenu.getChildren().add(createInitialSelectorMenu());

    allElements.getChildren().add(selectorMenu);
    makeScene();
  }

  //Sets the proper variation name and changes back to Player Screen
  private void setGame(){
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

  //Creates all folders and files for the new variation, and copies all necessary information over
  private void makeDirectories() throws IOException {
    variationFolder = new File(VARIATION_PATH + gameName.getText().replace(" ","_"));
    variationFolder.mkdirs();
    variationBoard = new File(VARIATION_PATH + gameName.getText() + BOARD_PATH);
    variationBoard.mkdirs();
    variationCards = new File(VARIATION_PATH + gameName.getText() + CARDS_PATH);
    variationCards.mkdirs();
    variationProperties = new File(VARIATION_PATH + gameName.getText() + PROPERTIES_PATH);
    variationProperties.mkdirs();
    variationBoardFile = new File(VARIATION_PATH + gameName.getText() + BOARD_PATH + "/" + gameName.getText() +  BOARD_PATH.replace("/","."));
    variationBoardFile.createNewFile();
    variationConfigFile = new File(VARIATION_PATH + gameName.getText() + CONFIG + PROPERTIES_PATH.replace("/","."));
    variationConfigFile.createNewFile();
    variationTiles = new File(VARIATION_PATH + gameName.getText() + BOARD_PATH + TILES_PATH);
    variationTiles.mkdirs();
    variationChanceCards =new File(VARIATION_PATH + gameName.getText() + CARDS_PATH + CHANCE_PATH);
    variationChanceCards.mkdirs();
    variationCommunityChestCards =new File(VARIATION_PATH + gameName.getText() + CARDS_PATH  + COMMUNITY_CHEST_PATH);
    variationCommunityChestCards.mkdirs();
    copyTileFiles();
    copyConfigFile();
    copyCardFiles(CHANCE_PATH,CHANCE_SUBSTRING_CUTOFF);
    copyCardFiles(COMMUNITY_CHEST_PATH,COMMUNITY_CHEST_SUBSTRING_CUTOFF);
  }

  //copies tile files from original variation to new variation
  private void copyTileFiles() throws IOException {
    String tileName;
    File fileFolder = new File(VARIATION_PATH + DEFAULT_VARIATION_PATH + BOARD_PATH + TILES_PATH);
    for(File file: fileFolder.listFiles()){
      tileName= file.getPath().substring(TILE_SUBSTRING_CUTOFF);
      System.out.println(tileName);
      new File(VARIATION_PATH + gameName.getText() + BOARD_PATH + TILES_PATH + tileName).createNewFile();
      src = Paths.get(file.getPath());
      des = Paths.get(VARIATION_PATH + gameName.getText() + BOARD_PATH + TILES_PATH + tileName);
      Files.copy(src, des,StandardCopyOption.REPLACE_EXISTING);
    }
  }

  //copies configuration file from original variation to new variation
  private void copyConfigFile() throws IOException {
    src = Paths.get(VARIATION_PATH + DEFAULT_VARIATION_PATH + CONFIG + PROPERTIES_PATH.replace("/","."));
    des = Paths.get(VARIATION_PATH + gameName.getText() + CONFIG + PROPERTIES_PATH.replace("/","."));
    Files.copy(src, des,StandardCopyOption.REPLACE_EXISTING);
  }

  //copies chance and community chest cards from original variation to new variation
  private void copyCardFiles(String cardPath, int pathCutoff) throws IOException {
    String tileName;
    File fileFolder = new File(VARIATION_PATH + DEFAULT_VARIATION_PATH + CARDS_PATH + cardPath);
    for(File file: fileFolder.listFiles()){
      tileName= file.getPath().substring(pathCutoff);
      new File(VARIATION_PATH + gameName.getText() + CARDS_PATH + cardPath + tileName).createNewFile();
      src = Paths.get(file.getPath());
      des = Paths.get(VARIATION_PATH + gameName.getText() + CARDS_PATH + cardPath + tileName);
      Files.copy(src, des,StandardCopyOption.REPLACE_EXISTING);
    }
  }

  //creates the variation folder hierarchy and displays the rest of the selector menu
  private void createFullSelectorMenu() throws IOException {
    makeDirectories();
    VBox rightside = new VBox();
    rightside.setId("Right");
    allElements.getChildren().add(rightside);

    Button setGameButton = myBuilder.makeTextButton("SetGame", e -> setGame());
    //setGameButton.setTranslateX(130);
    //setGameButton.setTranslateY(505);
    setGameButton.setTranslateX(60);
    setGameButton.setTranslateY(20);
    setGameButton.setPrefWidth(SET_GAME_BUTTON_WIDTH);
    setGameButton.setPrefHeight(SET_GAME_BUTTON_HEIGHT);
    StackPane instructions = new StackPane();
    instructions.setMinWidth(260);
    instructions.setMaxWidth(260);
    instructions.setMaxHeight(460);
    instructions.setMinHeight(460);
    instructions.setId("EditorInstructions");
    rightside.getChildren().add(instructions);
    rightside.getChildren().add(setGameButton);
    Text instructionText = new Text("Welcome to the Custom Game Creator! \n \n Please start by typing in your game name and setting your rules. \n \n Then, using our creator buttons, create your monopoly board! \n \n (Note: You are only able to use one Jail and one Go To Jail tile) \n \n When you have placed 40 tiles, please hit 'Set Game'. \n \n \n Enjoy!");
    instructions.getChildren().add(instructionText);
    instructionText.setWrappingWidth(220);

    selectorMenu.getChildren().add(myBuilder.makeLabel("SetRules"));

    //create Die Selector
    List<String> dieOptions = new ArrayList<>(Arrays.asList("TwoRegularDice","OneRegularDie"));
    selectorMenu.getChildren().add(myBuilder.makeCombo("ChooseYourDice", dieOptions, e -> setDie(e)));

    HBox createPropertyButtons = new HBox();

    createPropertyButtons.getChildren().add(myBuilder.makeTextButton("AddRegularProperty",e -> createPropertyPopUp("Regular")));
    createPropertyButtons.getChildren().add(myBuilder.makeTextButton("AddRailroadProperty",e -> createPropertyPopUp("Railroad")));
    createPropertyButtons.getChildren().add(myBuilder.makeTextButton("AddUtilitiesProperty",e -> createPropertyPopUp("Utilities")));
    selectorMenu.getChildren().add(createPropertyButtons);

    createSpecialTileButtons = new HBox();
    createTileButtons();
    selectorMenu.getChildren().add(createSpecialTileButtons);

    selectorMenu.getChildren().add(myBuilder.makeLabel("BoardTiles"));

    HBox tileCountBox = new HBox();
    counter = (Label) myBuilder.makeLabel("TileCounter");
    tileCountBox.getChildren().addAll(myBuilder.makeLabel("TilesLeft"),counter);
    selectorMenu.getChildren().add(tileCountBox);


    board = new HBox();
    selectorMenu.getChildren().add(board);
    board.getChildren().add(createBoardSpace("Go",new Image(myGameImages.getString("Go"))));
  }

  //creates the special tile creator buttons
  private void createTileButtons() throws IOException{
      jailButton = myBuilder.makeImageButton("Jail",e -> tryCreateSingleCardTile("Jail", jailButton), myGameImages.getString("Jail"),IMAGE_SIZE,IMAGE_SIZE);
      createSpecialTileButtons.getChildren().add(jailButton);
      goToJailButton = myBuilder.makeImageButton("GoToJail",e -> tryCreateSingleCardTile("GoToJail", goToJailButton), myGameImages.getString("GoToJail"),IMAGE_SIZE,IMAGE_SIZE);
      createSpecialTileButtons.getChildren().add(goToJailButton);
      createSpecialTileButtons.getChildren().add(myBuilder.makeImageButton("Chance",e -> tryCreateCardTile("Chance"), myGameImages.getString("Chance"),IMAGE_SIZE,IMAGE_SIZE));
      createSpecialTileButtons.getChildren().add(myBuilder.makeImageButton("CommunityChest",e -> tryCreateCardTile("CommunityChest"), myGameImages.getString("CommunityChest"),IMAGE_SIZE,IMAGE_SIZE));
      createSpecialTileButtons.getChildren().add(myBuilder.makeImageButton("FreeParking",e -> tryCreateCardTile("FreeParking"), myGameImages.getString("FreeParking"),IMAGE_SIZE,IMAGE_SIZE));
      createSpecialTileButtons.getChildren().add(myBuilder.makeImageButton("LuxuryTax",e -> tryCreateCardTile("LuxuryTax"), myGameImages.getString("LuxuryTax"),IMAGE_SIZE,IMAGE_SIZE));
      createSpecialTileButtons.getChildren().add(myBuilder.makeImageButton("IncomeTax",e -> tryCreateCardTile("IncomeTax"), myGameImages.getString("IncomeTax"),IMAGE_SIZE,IMAGE_SIZE));
  }

  //calls CreateSingleCardTime and catches IOExceptions
  private void tryCreateSingleCardTile(String name, Button button){
    try{
      createSingleCardTile(name,button);
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }

  //calls CreateCardTime and catches IOExceptions
  private void tryCreateCardTile(String name){
    try{
      createCardTile(name);
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

  //Adds a special tile to the board, and decrements tile count
  private void createCardTile(String name) throws IOException {
    board.getChildren().add(createSpecialTile(name, new Image(myGameImages.getString(name))));
    tileCounter--;
    counter.setText(""+ tileCounter);
  }

  //Adds a special, one-use-only tile to the board, and decrements tile count
  private void createSingleCardTile(String name, Button jail) throws IOException {
    board.getChildren().add(createSpecialTile(name, new Image(myGameImages.getString(name))));
    createSpecialTileButtons.getChildren().remove(jail);
    tileCounter--;
    counter.setText(""+ tileCounter);
  }

  //Sets the die to whatever the user chooses
  private void setDie(String dieType){
    //TODO: Enter this
  }

  //Creates the property pop up screen for the user to input
  private void createPropertyPopUp(String type){
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

    switch (myType){
      case "Regular":
        propertyHouseCost = (TextField) myBuilder.makeTextField("EnterHouseCost");
        propertyColor = (TextField) myBuilder.makeTextField("EnterColor");
        propBox.getChildren().add(myBuilder.makeLabel("EnterHouseCost"));
        propBox.getChildren().add(propertyHouseCost);
        propBox.getChildren().add(myBuilder.makeLabel("EnterColor"));
        propBox.getChildren().add(propertyColor);
        break;

      case "Railroad": case "Utilities":
        propertyImage = (TextField) myBuilder.makeTextField("EnterImage");
        propBox.getChildren().add(myBuilder.makeLabel("EnterImage"));
        propBox.getChildren().add(propertyImage);
        break;
    }
    propBox.getChildren().add(myBuilder.makeTextButton("SaveProperty",e -> { try { saveProperty(myType);} catch (IOException ex) {ex.printStackTrace();}}));
    propBox.setId("propertyVBox");
    PropertyPopUp.getContent().add(propBox);
    PropertyPopUp.show(myStage);
  }

  //Saves the inputted property information, adds it to the board, and writes it to the variation folder.
  private void saveProperty(String type) throws IOException {
    String myType = type;
    switch (myType){
      case "Regular":
        writeRegularPropertyFile(propertyName.getText(),propertyCost.getText(),propertyRentCosts.getText(),propertyHouseCost.getText(), propertyNeighbors.getText(), propertyMortgage.getText(),propertyColor.getText());
        board.getChildren().add(createBoardSpace(propertyName.getText(),propertyColor.getText()));
        break;

      case "Utilities": case "Railroad":
        writeSpecialPropertyFile(propertyName.getText(),type,propertyCost.getText(),propertyRentCosts.getText(), propertyNeighbors.getText(), propertyMortgage.getText(),propertyImage.getText());
        board.getChildren().add(createBoardSpace(propertyName.getText(),new Image(myGameImages.getString(type))));
        break;
    }

    PropertyPopUp.hide();
    propBox = new VBox();
    PropertyPopUp = new Popup();
    tileCounter--;
    counter.setText("" + tileCounter);
  }

  //Writes a single tile name to the .board file in the variation folder
  private void writeLineToBoard(String tileName) throws IOException {
    FileWriter fw = new FileWriter(variationBoardFile,true);
    fw.write(tileName+ "\n");
    fw.close();
  }

  //Writes a regular property file to the properties folder
  private void writeRegularPropertyFile(String name, String cost, String rentcosts, String housecost, String neighbors, String mortgage, String color)
      throws IOException {
    File property = new File(VARIATION_PATH + gameName.getText().replace(" ","_") + PROPERTIES_PATH + "/" + name.toLowerCase(Locale.ROOT).replace(" ","_") + PROPERTIES_PATH.replace("/","."));

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
  private void writeSpecialPropertyFile(String name, String type, String cost, String rentcosts, String neighbors, String mortgage, String image)
      throws IOException {
    File property = new File(VARIATION_PATH + gameName.getText().replace(" ","_") + PROPERTIES_PATH + "/" + name.toLowerCase(Locale.ROOT).replace(" ","_") + PROPERTIES_PATH.replace("/","."));
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
  private VBox createBoardSpace(String name,String color) throws IOException {
    writeLineToBoard(name);
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
    if(tileCounter % CREATOR_ROW_MAX == 0){
      colJumper += TILE_X_TRANSLATION;
      rowJumper += TILE_Y_TRANSLATION;
    }
    tileBox.setTranslateY(rowJumper);
    tileBox.setTranslateX(colJumper);
    return tileBox;
  }

  //creates a board space with given name and image
  private VBox createBoardSpace(String name, Image image) throws IOException {
    writeLineToBoard(name);
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
    if(tileCounter % CREATOR_ROW_MAX == 0){
      colJumper += TILE_X_TRANSLATION;
      rowJumper += TILE_Y_TRANSLATION;
    }
    stackPane.setTranslateY(rowJumper);
    stackPane.setTranslateX(colJumper);
    return stackPane;
  }

  //creates a special tile for the board with given name and image
  private VBox createSpecialTile(String name, Image image) throws IOException {
    return createBoardSpace(myLangResource.getString(name),image);
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