package ooga.display.screens;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
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

  /**
   * Constructor for creating an option menu screen
   * @param stage The stage
   * @param displayManager The display manager
   * @param langResource The language
   */
  public GameCreatorScreen(Stage stage, DisplayManager displayManager, ResourceBundle langResource) {
    PropertyPopUp = new Popup();
    propBox = new VBox();
    propBox.setId("propertyCreatorBox");
    tileCounter = 40;
    myLangResource = langResource;
    myBuilder = new UIBuilder(langResource);
    myStage = stage;
    myDisplayManager = displayManager;
    stage.setHeight(800);
    stage.setWidth(1200);



    startMenu = new VBox();
    startMenu.getChildren().add(myBuilder.makeLabel("GameCreator"));
    startMenu.getChildren().add(myBuilder.makeLabel("TilesLeft"));
    counter = (Label) (myBuilder.makeLabel("TileCounter"));
    startMenu.getChildren().add(counter);
    startMenu.getChildren().add(startCreating());
    makeScene();
  }

  private Node startCreating() {
    VBox result = new VBox();

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

  private void triggerGameCreation() throws IOException {
    System.out.println(gameName.getText());
    File variationFolder = new File("data/variations/" + gameName.getText().replace(" ","_"));
    File variationBoard = new File("data/variations/" + gameName.getText() + "/board");
    File variationCards = new File("data/variations/" + gameName.getText() + "/cards");
    File variationProperties = new File("data/variations/" + gameName.getText() + "/properties");
    File variationPlayers = new File("data/variations/" + gameName.getText() + "/players");
    File variationConfigFile = new File("data/variations/" + gameName.getText() + "/config.properties");
    System.out.println(variationFolder.mkdirs());
    System.out.println(variationBoard.mkdirs());
    System.out.println(variationCards.mkdirs());
    System.out.println(variationProperties.mkdirs());
    System.out.println(variationPlayers.mkdirs());
    System.out.println(variationConfigFile.createNewFile());
    VBox result = new VBox();
    result.getChildren().add(myBuilder.makeLabel("SetRules"));
    List<String> dieOptions = new ArrayList<>(Arrays.asList("TwoRegularDice","OneRegularDie"));
    result.getChildren().add(myBuilder.makeCombo("ChooseYourDice", dieOptions, e ->
        setDie(e)));

    HBox createPropertyButtons = new HBox();

    createPropertyButtons.getChildren().add(myBuilder.makeTextButton("AddRegularProperty",e -> createRegularPropertyPopUp()));
    createPropertyButtons.getChildren().add(myBuilder.makeTextButton("AddRailroadProperty",e -> createRailroadPropertyPopUp()));
    createPropertyButtons.getChildren().add(myBuilder.makeTextButton("AddUtilitiesProperty",e -> createUtilitiesPropertyPopUp()));


    result.getChildren().add(createPropertyButtons);

    result.getChildren().add(myBuilder.makeImageButton("Chance",e -> createCardTile(), "images/questionmark.png",50,50));

    startMenu.getChildren().add(result);


   board = new HBox();

   result.getChildren().add(board);


  }


  private void setDie(String dieType){
    System.out.println("Die Set To: " + dieType);
  }

  private void createRegularPropertyPopUp(){


    propertyName = (TextField) myBuilder.makeTextField("EnterPropertyName");
    propertyCost = (TextField) myBuilder.makeTextField("EnterCost");
    propertyRentCosts = (TextField) myBuilder.makeTextField("EnterRentCosts");
    propertyHouseCost = (TextField) myBuilder.makeTextField("EnterHouseCost");
    propertyNeighbors = (TextField) myBuilder.makeTextField("EnterNeighbors");
    propertyMortgage = (TextField) myBuilder.makeTextField("EnterMortgagePrice");
    propertyColor = (TextField) myBuilder.makeTextField("EnterColor");

    //propBox.setPrefSize(200, 300);
    propBox.getChildren().add(myBuilder.makeLabel("EnterPropertyName"));
    propBox.getChildren().add(propertyName);
    propBox.getChildren().add(myBuilder.makeLabel("EnterCost"));
    propBox.getChildren().add(propertyCost);
    propBox.getChildren().add(myBuilder.makeLabel("EnterRentCosts"));
    propBox.getChildren().add(propertyRentCosts);
    propBox.getChildren().add(myBuilder.makeLabel("EnterHouseCost"));
    propBox.getChildren().add(propertyHouseCost);
    propBox.getChildren().add(myBuilder.makeLabel("EnterNeighbors"));
    propBox.getChildren().add(propertyNeighbors);
    propBox.getChildren().add(myBuilder.makeLabel("EnterMortgagePrice"));
    propBox.getChildren().add(propertyMortgage);
    propBox.getChildren().add(myBuilder.makeLabel("EnterColor"));
    propBox.getChildren().add(propertyColor);
    propBox.getChildren().add(myBuilder.makeTextButton("SaveProperty",e -> {
      try {
        saveRegularProperty();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }));
    propBox.setId("ProfileVBox");
    PropertyPopUp.getContent().add(propBox);
    PropertyPopUp.show(myStage);
  }

  private void createCardTile(){
    board.getChildren().add(createBoardSpace(new Image("images/questionmark.png")));
    tileCounter--;
    counter.setText(""+ tileCounter);
  }

  private void createRailroadPropertyPopUp(){


    propertyName = (TextField) myBuilder.makeTextField("EnterPropertyName");
    propertyCost = (TextField) myBuilder.makeTextField("EnterCost");
    propertyRentCosts = (TextField) myBuilder.makeTextField("EnterRentCosts");
    propertyNeighbors = (TextField) myBuilder.makeTextField("EnterNeighbors");
    propertyMortgage = (TextField) myBuilder.makeTextField("EnterMortgagePrice");
    propertyImage = (TextField) myBuilder.makeTextField("EnterImage");

    //propBox.setPrefSize(200, 300);
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
    propBox.getChildren().add(myBuilder.makeLabel("EnterImage"));
    propBox.getChildren().add(propertyImage);
    propBox.getChildren().add(myBuilder.makeTextButton("SaveProperty",e -> {
      try {
        saveRailroadProperty();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }));
    propBox.setId("ProfileVBox");
    PropertyPopUp.getContent().add(propBox);
    PropertyPopUp.show(myStage);
  }

  private void createUtilitiesPropertyPopUp(){


    propertyName = (TextField) myBuilder.makeTextField("EnterPropertyName");
    propertyCost = (TextField) myBuilder.makeTextField("EnterCost");
    propertyRentCosts = (TextField) myBuilder.makeTextField("EnterRentCosts");
    propertyNeighbors = (TextField) myBuilder.makeTextField("EnterNeighbors");
    propertyMortgage = (TextField) myBuilder.makeTextField("EnterMortgagePrice");
    propertyImage = (TextField) myBuilder.makeTextField("EnterImage");

    //propBox.setPrefSize(200, 300);
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
    propBox.getChildren().add(myBuilder.makeLabel("EnterImage"));
    propBox.getChildren().add(propertyImage);
    propBox.getChildren().add(myBuilder.makeTextButton("SaveProperty",e -> {
      try {
        saveUtilitiesProperty();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }));
    propBox.setId("ProfileVBox");
    PropertyPopUp.getContent().add(propBox);
    PropertyPopUp.show(myStage);
  }



  private void showOptions(){
    System.out.println("hi");
  }

  private void saveRegularProperty() throws IOException {
    writeRegularPropertyFile(propertyName.getText(),propertyCost.getText(),propertyRentCosts.getText(),propertyHouseCost.getText(), propertyNeighbors.getText(), propertyMortgage.getText(),propertyColor.getText());
    PropertyPopUp.hide();
    propBox = new VBox();
    PropertyPopUp = new Popup();
    tileCounter--;
    counter.setText("" + tileCounter);
    board.getChildren().add(createBoardSpace(propertyName.getText(),propertyColor.getText()));
  }

  private void saveRailroadProperty() throws IOException {
    writeRailroadPropertyFile(propertyName.getText(),propertyCost.getText(),propertyRentCosts.getText(), propertyNeighbors.getText(), propertyMortgage.getText(),propertyImage.getText());
    PropertyPopUp.hide();
    propBox = new VBox();
    PropertyPopUp = new Popup();
    tileCounter--;
    counter.setText("" + tileCounter);
    board.getChildren().add(createBoardSpace(propertyName.getText(),"white"));
  }

  private void saveUtilitiesProperty() throws IOException {
    writeRailroadPropertyFile(propertyName.getText(),propertyCost.getText(),propertyRentCosts.getText(), propertyNeighbors.getText(), propertyMortgage.getText(),propertyImage.getText());
    PropertyPopUp.hide();
    propBox = new VBox();
    PropertyPopUp = new Popup();
    tileCounter--;
    counter.setText("" + tileCounter);
    board.getChildren().add(createBoardSpace(propertyName.getText(),"white"));
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

    System.out.println("hi!");
    property.createNewFile();
  }

  private void writeRailroadPropertyFile(String name, String cost, String rentcosts, String neighbors, String mortgage, String image)
      throws IOException {
    File property = new File("data/variations/" + gameName.getText().replace(" ","_") + "/properties/" + name + ".property");

    FileWriter fw = new FileWriter(property);
    fw.write("Name=" + name + "\n");
    fw.write("Type=" + "Railroad" + "\n");
    fw.write("Cost=" + cost + "\n");
    fw.write("RentCost=" + rentcosts + "\n");
    fw.write("Neighbors=" + neighbors + "\n");
    fw.write("Mortgage=" + mortgage + "\n");
    fw.write("Image=" + image);

    fw.close();

    System.out.println("hi!");
    property.createNewFile();
  }

  private void writeUtilitiesPropertyFile(String name, String cost, String rentcosts, String neighbors, String mortgage, String image)
      throws IOException {
    File property = new File("data/variations/" + gameName.getText().replace(" ","_") + "/properties/" + name + ".property");

    FileWriter fw = new FileWriter(property);
    fw.write("Name=" + name + "\n");
    fw.write("Type=" + "Utilities" + "\n");
    fw.write("Cost=" + cost + "\n");
    fw.write("RentCost=" + rentcosts + "\n");
    fw.write("Neighbors=" + neighbors + "\n");
    fw.write("Mortgage=" + mortgage + "\n");
    fw.write("Image=" + image);

    fw.close();

    System.out.println("hi!");
    property.createNewFile();
  }

  private VBox createBoardSpace(String name,String color){
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
    return stackPane;
  }

  private VBox createBoardSpace(Image image){
    VBox stackPane = new VBox();
    stackPane.setId("creatorTile");
    ImageView view = new ImageView(image);
    view.setFitWidth(50);
    view.setFitHeight(50);
    stackPane.setStyle("-fx-background-color:" + "white" + ";");
    stackPane.getChildren().addAll(view);
    stackPane.setMaxWidth(50);
    stackPane.setMaxHeight(50);
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