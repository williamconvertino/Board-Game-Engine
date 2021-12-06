package ooga.display.screens;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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

  private BorderPane startMenu;
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


  private Node propertyName;
  private Node propertyCost;
  private Node propertyRentCosts;
  private Node propertyHouseCost;
  private Node propertyNeighbors;
  private Node propertyMortgage;
  private Node propertyColor;

  /**
   * Constructor for creating an option menu screen
   * @param stage The stage
   * @param displayManager The display manager
   * @param langResource The language
   */
  public GameCreatorScreen(Stage stage, DisplayManager displayManager, ResourceBundle langResource) {
    myLangResource = langResource;
    myBuilder = new UIBuilder(langResource);
    myStage = stage;
    myDisplayManager = displayManager;



    startMenu = new BorderPane();
    startMenu.setTop(myBuilder.makeLabel("GameCreator"));
    startMenu.setLeft(create());
    makeScene();
  }

  private Node create(){
    VBox result = new VBox();
    result.getChildren().add(myBuilder.makePrefilledTextField("EnterGameName"));
    result.getChildren().add(myBuilder.makeLabel("SetRules"));
    List<String> dieOptions = new ArrayList<>(Arrays.asList("TwoRegularDice","OneRegularDie"));
    result.getChildren().add(myBuilder.makeCombo("ChooseYourDice", dieOptions, e ->
        setDie(e)));
    result.getChildren().add(myBuilder.makeTextButton("MakeProperty",e -> createButtonPopUp()));

    return result;
  }
  private void setDie(String dieType){
    System.out.println("Die Set To: " + dieType);
  }

  private void createButtonPopUp(){
    Popup PropertyPopUp = new Popup();
    VBox propBox = new VBox();
    propBox.setId("propertyCreatorBox");

    propertyName = myBuilder.makeTextField("EnterPropertyName");
    propertyCost = myBuilder.makeTextField("EnterCost");
    propertyRentCosts = myBuilder.makeTextField("EnterRentCosts");
    propertyHouseCost = myBuilder.makeTextField("EnterHouseCost");
    propertyNeighbors = myBuilder.makeTextField("EnterNeighbors");
    propertyMortgage = myBuilder.makeTextField("EnterMortgagePrice");
    propertyColor = myBuilder.makeTextField("EnterColor");

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
    propBox.getChildren().add(myBuilder.makeTextButton("SaveProperty",e -> saveProperty()));
    propBox.setId("ProfileVBox");
    PropertyPopUp.getContent().add(propBox);
    PropertyPopUp.show(myStage);
  }

  /**
   * Make the panel with buttons to screens or go to settings
   */
  private Node optionsPanel() {
    VBox result = new VBox();
    List<String> placeHolder = new ArrayList<>();
    List<String> themes = new ArrayList<>(Arrays.asList("Original", "Mono", "Duke"));
    result.getChildren().add(myBuilder.makeCombo("NumberofPlayers", placeHolder, e ->
        myDisplayManager.changePlayerCount()));
    result.getChildren()
        .add(myBuilder.makeCombo("Theme", themes, e -> myDisplayManager.changeTheme(e)));
    myLanguageUI = new LanguageUI(myDisplayManager, myLangResource, LANGUAGES_LIST);
    result.getChildren().add(myLanguageUI);
    result.getChildren().add(myBuilder.makeTextButton("GotoHome", e -> myDisplayManager.goStartMenu()));
    return result;
  }

  private void saveProperty(){

    System.out.println("hi!");
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