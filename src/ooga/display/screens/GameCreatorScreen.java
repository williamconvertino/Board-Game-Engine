package ooga.display.screens;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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

  private TextField propertyName;
  private TextField propertyCost;
  private TextField propertyRentCosts;
  private TextField propertyHouseCost;
  private TextField propertyNeighbors;
  private TextField propertyMortgage;
  private TextField propertyColor;
  private TextField propertyImage;

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

    myLangResource = langResource;
    myBuilder = new UIBuilder(langResource);
    myStage = stage;
    myDisplayManager = displayManager;



    startMenu = new VBox();
    startMenu.getChildren().add(myBuilder.makeLabel("GameCreator"));
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

    createPropertyButtons.getChildren().add(myBuilder.makeTextButton("MakeRegularProperty",e -> createRegularPropertyPopUp()));
    createPropertyButtons.getChildren().add(myBuilder.makeTextButton("MakeRailroadProperty",e -> createRailroadPropertyPopUp()));
    //createPropertyButtons.getChildren().add(myBuilder.makeTextButton("MakeUtilitiesProperty",e -> createButtonPopUp()));

    result.getChildren().add(createPropertyButtons);

    startMenu.getChildren().add(result);

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



  private void showOptions(){
    System.out.println("hi");
  }

  private void saveRegularProperty() throws IOException {
    writeRegularPropertyFile(propertyName.getText(),propertyCost.getText(),propertyRentCosts.getText(),propertyHouseCost.getText(), propertyNeighbors.getText(), propertyMortgage.getText(),propertyColor.getText());
    PropertyPopUp.hide();
    propBox = new VBox();
    PropertyPopUp = new Popup();
  }

  private void saveRailroadProperty() throws IOException {
    writeRailroadPropertyFile(propertyName.getText(),propertyCost.getText(),propertyRentCosts.getText(), propertyNeighbors.getText(), propertyMortgage.getText(),propertyImage.getText());
    PropertyPopUp.hide();
    propBox = new VBox();
    PropertyPopUp = new Popup();
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