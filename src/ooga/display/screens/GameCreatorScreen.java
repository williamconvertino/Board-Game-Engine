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
import javafx.scene.layout.VBox;
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

public class GameCreatorScreen extends Display {

  private VBox startMenu;
  private Stage myStage;
  private DisplayManager myDisplayManager;
  private UIBuilder myBuilder;
  private ResourceBundle myLangResource;
  private LanguageUI myLanguageUI;
  private static final String DEFAULT_RESOURCE_PACKAGE =
      Display.class.getPackageName() + ".resources.";
  private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static final String DEFAULT_STYLE = STYLE_PACKAGE + "original.css";
  private static final String DUKE_STYLE = STYLE_PACKAGE + "duke.css";
  private static final String MONO_STYLE = STYLE_PACKAGE + "mono.css";
  private static final List<String> LANGUAGES_LIST = List.of("English", "Spanish", "French", "Irish", "Latin");
  private Scene scene;

  private TextField gameName;
  private Popup PropertyPopUp;
  private VBox propBox;
  private int tileCounter;

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
  private File variationPlayers;
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


  /**
   * Constructor for creating an option menu screen
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
    tileCounter = 39;
    myLangResource = langResource;
    myBuilder = new UIBuilder(langResource);
    myStage = stage;
    myDisplayManager = displayManager;
    myGameImages = ResourceBundle.getBundle("ooga/display/resources/image_paths");


    startMenu = new VBox();
    startMenu.setMaxWidth(400);
    startMenu.getChildren().add(myBuilder.makeLabel("GameCreator"));
    startMenu.getChildren().add(startCreating());
    makeScene();
  }

  /*
  private void copyContent(){
    Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
  }

   */

  private Node startCreating() {
    VBox result = new VBox();
    result.setMaxWidth(400);
    gameName = (TextField) myBuilder.makePrefilledTextField("EnterGameName");

    result.getChildren().add(gameName);
    result.getChildren().add(myBuilder.makeTextButton(("CreateNewGame"), e -> {
      try {
        triggerGameCreation();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }));
    return result;
  }

  private void makeDirectories() throws IOException {
    variationFolder = new File("data/variations/" + gameName.getText().replace(" ","_"));
    variationBoard = new File("data/variations/" + gameName.getText() + "/board");
    variationBoard.mkdirs();
    variationCards = new File("data/variations/" + gameName.getText() + "/cards");
    variationProperties = new File("data/variations/" + gameName.getText() + "/properties");
    variationPlayers = new File("data/variations/" + gameName.getText() + "/players");
    variationBoardFile = new File("data/variations/" + gameName.getText() + "/board/" + gameName.getText() +  ".board");
    variationConfigFile = new File("data/variations/" + gameName.getText() + "/config.properties");
    variationTiles = new File("data/variations/" + gameName.getText() + "/board/tiles");
    variationChanceCards =new File("data/variations/" + gameName.getText() + "/cards/chance");
    variationCommunityChestCards =new File("data/variations/" + gameName.getText() + "/cards/community_chest");
    variationCommunityChestCards.mkdirs();
    variationChanceCards.mkdirs();
    variationTiles.mkdirs();
    variationFolder.mkdirs();
    variationCards.mkdirs();
    variationProperties.mkdirs();
    variationPlayers.mkdirs();
    variationConfigFile.createNewFile();
    variationBoardFile.createNewFile();
    copyTileFiles();
    copyConfigFile();
    copyCardFiles();



  }

  private void copyTileFiles() throws IOException {
    String tileName;
    File fileFolder = new File("data/variations/original/board/tiles");
    for(File file: fileFolder.listFiles()){
      tileName= file.getPath().substring(37);
      new File("data/variations/" + gameName.getText() + "/board/tiles/"+ tileName).createNewFile();
      src = Paths.get(file.getPath());
      des = Paths.get("data/variations/" + gameName.getText() + "/board/tiles/"+ tileName);
      Files.copy(src, des,StandardCopyOption.REPLACE_EXISTING);
    }
  }

  private void copyConfigFile() throws IOException {
    //data/variations/original/config.properties
    src = Paths.get("data/variations/original/config.properties");
    des = Paths.get("data/variations/" + gameName.getText() + "/config.properties");
    Files.copy(src, des,StandardCopyOption.REPLACE_EXISTING);
  }

  private void copyCardFiles() throws IOException {
    String tileName;
    File fileFolder = new File("data/variations/original/cards/chance");
    for(File file: fileFolder.listFiles()){
      tileName= file.getPath().substring(38);
      new File("data/variations/"+ gameName.getText() + "/cards/chance/" + tileName).createNewFile();
      src = Paths.get(file.getPath());
      des = Paths.get("data/variations/"+ gameName.getText() + "/cards/chance/" + tileName);
      Files.copy(src, des,StandardCopyOption.REPLACE_EXISTING);
    }
    fileFolder = new File("data/variations/original/cards/community_chest");
    for(File file: fileFolder.listFiles()){
      tileName= file.getPath().substring(47);
      new File("data/variations/"+ gameName.getText() + "/cards/community_chest/" + tileName).createNewFile();
      src = Paths.get(file.getPath());
      des = Paths.get("data/variations/"+ gameName.getText() + "/cards/community_chest/" + tileName);
      Files.copy(src, des,StandardCopyOption.REPLACE_EXISTING);
    }
  }

  private void triggerGameCreation() throws IOException {
    makeDirectories();
    VBox result = new VBox();
    result.getChildren().add(myBuilder.makeLabel("SetRules"));
    List<String> dieOptions = new ArrayList<>(Arrays.asList("TwoRegularDice","OneRegularDie"));
    result.getChildren().add(myBuilder.makeCombo("ChooseYourDice", dieOptions, e ->
        setDie(e)));

    HBox createPropertyButtons = new HBox();

    createPropertyButtons.getChildren().add(myBuilder.makeTextButton("AddRegularProperty",e -> createPropertyPopUp("Regular")));
    createPropertyButtons.getChildren().add(myBuilder.makeTextButton("AddRailroadProperty",e -> createPropertyPopUp("Railroad")));
    createPropertyButtons.getChildren().add(myBuilder.makeTextButton("AddUtilitiesProperty",e -> createPropertyPopUp("Utilities")));


    result.getChildren().add(createPropertyButtons);

    createSpecialTileButtons = new HBox();

    createTileButtons();





    result.getChildren().add(createSpecialTileButtons);

    result.getChildren().add(myBuilder.makeLabel("BoardTiles"));

    HBox tileCountBox = new HBox();
    counter = (Label) myBuilder.makeLabel("TileCounter");
    tileCountBox.getChildren().addAll(myBuilder.makeLabel("TilesLeft"),counter);

    result.getChildren().add(tileCountBox);

    startMenu.getChildren().add(result);


   board = new HBox();

   result.getChildren().add(board);

    board.getChildren().add(createBoardSpace("Go",new Image(myGameImages.getString("go"))));
  }

  private void createTileButtons() throws IOException{

      jailButton = myBuilder.makeImageButton("Jail",e -> tryCreateSingleCardTile("jail", jailButton), myGameImages.getString("jail"),50,50);
      createSpecialTileButtons.getChildren().add(jailButton);

      goToJailButton = myBuilder.makeImageButton("GoToJail",e -> tryCreateSingleCardTile("go_to_jail", goToJailButton), myGameImages.getString("go_to_jail"),50,50);
      createSpecialTileButtons.getChildren().add(goToJailButton);

      createSpecialTileButtons.getChildren().add(myBuilder.makeImageButton("Chance",e -> tryCreateCardTile("Chance"), myGameImages.getString("chance"),50,50));
      createSpecialTileButtons.getChildren().add(myBuilder.makeImageButton("CommunityChest",e -> tryCreateCardTile("Community Chest"), myGameImages.getString("community_chest"),50,50));
      createSpecialTileButtons.getChildren().add(myBuilder.makeImageButton("FreeParking",e -> tryCreateCardTile("FreeParking"), myGameImages.getString("FreeParking"),50,50));
      createSpecialTileButtons.getChildren().add(myBuilder.makeImageButton("LuxuryTax",e -> tryCreateCardTile("luxury_tax"), myGameImages.getString("luxury_tax"),50,50));
      createSpecialTileButtons.getChildren().add(myBuilder.makeImageButton("IncomeTax",e -> tryCreateCardTile("income_tax"), myGameImages.getString("income_tax"),50,50));



  }

  private void tryCreateSingleCardTile(String name, Button button){
    try{
      createSingleCardTile(name,button);
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }

  private void tryCreateCardTile(String name){
    try{
      createCardTile(name);
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }


  private void createCardTile(String name) throws IOException {
    board.getChildren().add(createSpecialTile(name, new Image(myGameImages.getString(name))));
    tileCounter--;
    counter.setText(""+ tileCounter);
  }

  private void createSingleCardTile(String name, Button jail) throws IOException {
    board.getChildren().add(createSpecialTile(name, new Image(myGameImages.getString(name))));
    createSpecialTileButtons.getChildren().remove(jail);
    tileCounter--;
    counter.setText(""+ tileCounter);
  }


  private void setDie(String dieType){
  }

  private void createPropertyPopUp(String type){
    String myType = type.toLowerCase(Locale.ROOT);
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
      case "regular":
        propertyHouseCost = (TextField) myBuilder.makeTextField("EnterHouseCost");
        propertyColor = (TextField) myBuilder.makeTextField("EnterColor");
        propBox.getChildren().add(myBuilder.makeLabel("EnterHouseCost"));
        propBox.getChildren().add(propertyHouseCost);
        propBox.getChildren().add(myBuilder.makeLabel("EnterColor"));
        propBox.getChildren().add(propertyColor);
        break;


      case "railroad": case "utilities":
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


  private void saveProperty(String type) throws IOException {
    String myType = type;
    switch (myType){
      case "regular":
        writeRegularPropertyFile(propertyName.getText(),propertyCost.getText(),propertyRentCosts.getText(),propertyHouseCost.getText(), propertyNeighbors.getText(), propertyMortgage.getText(),propertyColor.getText());
        board.getChildren().add(createBoardSpace(propertyName.getText(),propertyColor.getText()));
        break;

      case "utilities": case "railroad":
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


  private void writeLineToBoard(String tileName) throws IOException {
    FileWriter fw = new FileWriter(variationBoardFile,true);
    fw.write(tileName+ "\n");
    fw.close();
  }




  private void writeRegularPropertyFile(String name, String cost, String rentcosts, String housecost, String neighbors, String mortgage, String color)
      throws IOException {
    File property = new File("data/variations/" + gameName.getText().replace(" ","_") + "/properties/" + name + ".property");

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

  private void writeSpecialPropertyFile(String name, String type, String cost, String rentcosts, String neighbors, String mortgage, String image)
      throws IOException {
    File property = new File("data/variations/" + gameName.getText().replace(" ","_") + "/properties/" + name + ".property");
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

  private VBox createBoardSpace(String name,String color) throws IOException {
    writeLineToBoard(name);
    VBox stackPane = new VBox();
    stackPane.setId("creatorTile");
    Label tilename = new Label(name);
    tilename.setId("test");
    tilename.setWrapText(true);
    tilename.setMinWidth(50);
    tilename.setMinHeight(50);
    stackPane.setStyle("-fx-background-color:" + color + ";");
    stackPane.getChildren().addAll(tilename);
    stackPane.setMaxWidth(50);
    stackPane.setMaxHeight(50);
    stackPane.setMinWidth(50);
    stackPane.setMinHeight(50);
    if(tileCounter % 10 == 0){
      stackPane.setTranslateX(-500);
      stackPane.setTranslateY(50);
    }
    return stackPane;
  }

  private VBox createSpecialTile(String name, Image image) throws IOException {
    return createBoardSpace(myLangResource.getString(name),image);
  }

  private VBox createBoardSpace(String name, Image image) throws IOException {
    writeLineToBoard(name);
    VBox stackPane = new VBox();
    stackPane.setId("creatorTile");
    ImageView view = new ImageView(image);
    view.setFitWidth(50);
    view.setFitHeight(50);
    stackPane.setStyle("-fx-background-color:" + "white" + ";");
    stackPane.getChildren().addAll(view);
    stackPane.setMaxWidth(50);
    stackPane.setMaxHeight(50);
    stackPane.setMinWidth(50);
    stackPane.setMinHeight(50);
    if(tileCounter % 10 == 0){
      colJumper += -500;
      rowJumper += 50;
    }
    stackPane.setTranslateY(rowJumper);
    stackPane.setTranslateX(colJumper);
    return stackPane;
  }

  private void makeScene() {
    scene = new Scene(startMenu, 800, 600);
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