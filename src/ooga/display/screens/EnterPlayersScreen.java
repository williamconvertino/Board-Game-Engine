package ooga.display.screens;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.ui_tools.LanguageUI;
import ooga.display.ui_tools.UIBuilder;


/**
 * This class makes a player customizer screen that allows players to customize their appearance in
 * game
 *
 * @author Aaric Han
 * @author Henry Huynh
 * @author Casey Goldstein
 */
public class EnterPlayersScreen extends Display {

  private final VBox playerMenu;
  private final Stage myStage;
  private final DisplayManager myDisplayManager;
  private final UIBuilder myBuilder;
  private final ResourceBundle myLangResource;
  private final ResourceBundle myGameImages;
  private LanguageUI myLanguageUI;
  private VBox myTextAreaVBox;
  private VBox myColorSelectionVBox;

  private static final String DEFAULT_RESOURCE_PACKAGE =
      Display.class.getPackageName() + ".resources.";
  private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static final String DEFAULT_STYLE = STYLE_PACKAGE + "original.css";
  private static final String VARIATION_IMAGES =
      DEFAULT_RESOURCE_PACKAGE + "stock_variation_images";
  private static final String DEFAULT_DATA_PACKAGE = "data/";
  private static final String VARIATION_FOLDER_NAME = "variations/";
  private static final String DEFAULT_VARIATION_NAME = "original";
  private final String VARIATION_NAMES_FOLDER = "data/variation_names";
  private static final String DUKE_STYLE = STYLE_PACKAGE + "duke.css";
  private static final String MONO_STYLE = STYLE_PACKAGE + "mono.css";
  private static final String PLAYER_CUSTOMIZER = "PlayerCustomizer";
  private static final String SELECT_COLORLABEL = "SelectColorLabel";
  private static final String SELECT_COLOR = "SelectColor";
  private static final String PLAYER_NAME = "PlayerNameLabel";
  private static final String ENTER_NAME = "EnterPlayerName";
  private static final int DISPLAY_HEIGHT = 600;
  private static final int DISPLAY_WIDTH = 1000;
  private static final int IMAGE_SIZE = 100;
  private Label variationNameLabel;

  private final ArrayList<Color> playerColors = new ArrayList<>();
  private Scene scene;
  private String myStyle = DEFAULT_STYLE;


  /**
   * Default constructor for the Player Customization Screen
   *
   * @param stage
   * @param displayManager
   * @param langResource
   */
  public EnterPlayersScreen(Stage stage, DisplayManager displayManager,
      ResourceBundle langResource, String selectedTheme) {
    myStyle = selectedTheme;
    myLangResource = langResource;//ooga/display/resources/variation_image_paths
    myGameImages = ResourceBundle.getBundle(VARIATION_IMAGES);
    myBuilder = new UIBuilder(langResource);
    myStage = stage;
    myDisplayManager = displayManager;
    playerMenu = new VBox();
    playerMenu.getChildren().add(myBuilder.makeLabel(PLAYER_CUSTOMIZER));
    makePlayerCustomizer();
    makeScene();
  }

  //Creates all elements for player customization
  private void makePlayerCustomizer() {
    HBox playerCustomizer = new HBox();
    playerCustomizer.getChildren()
        .addAll(makeTextAreas(), makeColorSelection(), makeGameSelectorBox(), makeRight());
    playerMenu.getChildren().add(playerCustomizer);
  }

  //creates elements on right side of screen
  private Node makeRight() {
    VBox result = new VBox();
    result.getChildren().add(makeSelectedVariationBox());
    result.getChildren()
        .add(myBuilder.makeTextButton("Continue", e -> myDisplayManager.startGame()));
    result.getChildren()
        .add(myBuilder.makeTextButton("GotoHome", e -> myDisplayManager.goStartMenu()));
    return result;
  }

  //creates dynamic variation selected box label
  private HBox makeSelectedVariationBox() {
    HBox chosenVariationBox = new HBox();
    Label chosenVariation = (Label) myBuilder.makeLabel("SelectedVariation");
    chosenVariation.setTranslateX(0);
    chosenVariation.setTranslateY(0);
    chosenVariationBox.getChildren().add(myBuilder.makeLabel("SelectedVariation"));
    variationNameLabel = (Label) myBuilder.makeLabel("VariationNameLabel");
    variationNameLabel.setText(myDisplayManager.getVariationName());
    chosenVariationBox.getChildren().add(variationNameLabel);
    return chosenVariationBox;
  }

  //creates game selector elements
  private Node makeGameSelectorBox() {
    VBox result = new VBox();
    result.getChildren().add(myBuilder.makeLabel("ChooseGame"));
    result.getChildren().add(myBuilder.makeLabel("SelectEdition"));
    result.getChildren().add(makeVariationButtons());
    result.getChildren().add(myBuilder.makeLabel("Or"));
    result.getChildren().add(myBuilder.makeLabel("LoadVariation"));
    result.getChildren().add(makeLoadButton());
    result.getChildren().add(myBuilder.makeLabel("Or"));
    result.getChildren().add(
        myBuilder.makeTextButton("CreateYourOwn", e -> myDisplayManager.goGameCreatorScreen()));
    return result;
  }

  //creates text areas for player input
  private Node makeTextAreas() {
    myTextAreaVBox = new VBox();
    for (int i = 1; i < 5; i++) {
      myTextAreaVBox.getChildren().add(myBuilder.makeLabel(String.format("%s%d", ENTER_NAME, i)));
      myTextAreaVBox.getChildren()
          .add(myBuilder.makePrefilledTextField(String.format("%s%d", ENTER_NAME, i)));
    }
    return myTextAreaVBox;
  }

  //creates color selection for players
  private Node makeColorSelection() {
    myColorSelectionVBox = new VBox();
    for (int i = 0; i < 4; i++) {
      myColorSelectionVBox.getChildren()
          .add(myBuilder.makeLabel(String.format("%s", SELECT_COLORLABEL)));
      ComboBox colorSelectorBox = new ComboBox();
      ObservableList<String> options =
          FXCollections.observableArrayList(
              "BLACK", "RED", "GREEN", "BLUE", "YELLOW", "VIOLET"
          );
      colorSelectorBox.setItems(options);
      colorSelectorBox.setId(String.format("%s%d", myLangResource.getString(SELECT_COLOR), i + 1));
      colorSelectorBox.getSelectionModel().select(i);
      myColorSelectionVBox.getChildren().add(colorSelectorBox);
    }
    return myColorSelectionVBox;
  }

  /**
   * Gets a list of all textfields in the player customization screen
   *
   * @return List of Player Name Customizer text fields
   * @deprecated use {@link #getPlayerNameTextAreaInfo()} instead.
   */
  @Deprecated
  public List<Node> getTextAreaInfo() {
    List<Node> textAreaList = new ArrayList<>();
    textAreaList.add(myTextAreaVBox.getChildren().get(1));
    textAreaList.add(myTextAreaVBox.getChildren().get(3));
    textAreaList.add(myTextAreaVBox.getChildren().get(5));
    textAreaList.add(myTextAreaVBox.getChildren().get(7));
    return textAreaList;
  }

  /**
   * Gets a list of all textfields in the player customization screen
   *
   * @return List of Player Name Customizer text fields
   */
  public List<Node> getPlayerNameTextAreaInfo() {
    List<Node> textAreaList = new ArrayList<>();
    for (Node nodeCheck : myTextAreaVBox.getChildren()) {
      if (nodeCheck.getId() != null && nodeCheck.getId().contains(ENTER_NAME)) {
        textAreaList.add(nodeCheck);
      }
    }
    return textAreaList;
  }

  /**
   * Gets a list of all Color ComboBox in the player customization screen
   *
   * @return List of String colors
   */
  //FIXME: error with getting player colors
  public List<Node> getPlayerColors() {
    int i = 0;
    List<Node> colorsComboBoxes = new ArrayList<>();
    for (Node nodeCheck : myColorSelectionVBox.getChildren()) {
      if (nodeCheck.getId() != null && nodeCheck.getId().contains(SELECT_COLOR)) {
        colorsComboBoxes.add(nodeCheck);
      }
    }
    return colorsComboBoxes;
  }

  //creates scene with given dimensions and styles
  private void makeScene() {
    scene = new Scene(playerMenu, DISPLAY_WIDTH, DISPLAY_HEIGHT);
    scene.getStylesheets().add(myStyle);
  }

  //creates images for stock variations
  private Node makeVariationButtons() {
    HBox result = new HBox();
    result.setId("variationButtonBox");
    for (String image : myGameImages.keySet()) {
      result.getChildren().add(
          myBuilder.makeImageHoverButton("variationButton", (e -> setVariationName(image)),
              myGameImages.getString(image), IMAGE_SIZE, IMAGE_SIZE,
              myLangResource.getString(image + "_" + "description")));
    }
    return result;
  }

  //Makes dynamic game loader folder button
  private Button makeLoadButton() {
    Button result = new Button();
    ImageView folderImageView = new ImageView(
        new Image("images/closedfolder.png"));
    folderImageView.setFitWidth(100);
    folderImageView.setFitHeight(100);
    result.setGraphic(folderImageView);
    result.setId("VariationLoader");
    result.hoverProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue) {
        folderImageView.setImage(new Image("images/openfolder.png"));
      } else {
        folderImageView.setImage(new Image("images/closedfolder.png"));
      }
    });
    result.setOnAction(event -> {
      String path = loadGame();
      myDisplayManager.setVariationName(path.substring(path.lastIndexOf("/") + 1));
      variationNameLabel.setText(myDisplayManager.getVariationName());
    });
    return result;
  }

  //loads file dialog for game choosing
  private String loadGame() {
    FileChooser GameChooser = new FileChooser();
    GameChooser.setInitialDirectory(new File(VARIATION_NAMES_FOLDER));
    File gameFile = GameChooser.showOpenDialog(getScene().getWindow());
    if (gameFile != null) {
      return gameFile.toString();
    } else {
      return "/" + DEFAULT_VARIATION_NAME;
    }
  }

  //sets the variation name in DisplayManager, and updates the label on screen
  private void setVariationName(String name) {
    myDisplayManager.setVariationName(name);
    variationNameLabel.setText(myDisplayManager.getVariationName());
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