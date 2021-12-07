package ooga.display.ui_tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import ooga.display.Display;

/**
 * UI building tools for the display NOTE: These many components require language support from
 * language resource property packages.
 *
 * @author Henry Huynh
 * @author Aaric Han
 */

public class UIBuilder {

  private static final String LANGUAGE_PACKAGE = Display.class.getPackageName() + ".resources.";
  private ResourceBundle langResource;

  /**
   * UI builder holds helper methods that create UI components
   *
   * @param langResource is the properties file that determines langauge
   */
  public UIBuilder(ResourceBundle langResource) {
    this.langResource = langResource;
  }

  public void setLanguage(String language) {
    this.langResource = ResourceBundle.getBundle(LANGUAGE_PACKAGE + language);
  }

  /**
   * creates a Horizontal configuration of label and node using id
   *
   * @param id   is the label reference
   * @param node is the node to be added with the label
   * @return HBox with label Node pair
   */
  public Node makeHolderBox(String id, Node node) {
    HBox holder = new HBox();
    Label label = new Label(langResource.getString(id));
    holder.getStyleClass().add("holder");
    holder.getChildren().addAll(label, node);
    return holder;
  }


  /**
   * creates a button with proper label and OnAction values
   *
   * @param id       is the name/title of the button
   * @param response is the action event the button should have
   * @return new button with properly initialized
   */
  public Button makeTextButton(String id, EventHandler<ActionEvent> response) {
    Button button = new Button();
    button.setOnAction(response);
    button.setText(langResource.getString(id));
    button.setId(id);
    button.getStyleClass().add("button");
    return button;
  }

  public Button makeImageButton(String id, EventHandler<ActionEvent> response, String imagePath,
      int width, int height) {
    ImageView image = new ImageView(new Image(imagePath));
    image.setId(id);
    Button button = new Button();
    button.setOnAction(response);
    button.setId(id);
    button.setGraphic(image);
    image.setFitHeight(height);
    image.setFitWidth(width);

    return button;
  }

  public Button makeImageHoverButton(String id, EventHandler<ActionEvent> response,
      String imagePath, int width, int height, String description) {
    Button button = makeImageButton(id, response, imagePath, width, height);
    Tooltip tt = new Tooltip();
    tt.setText(description);
    tt.setShowDelay(new Duration(.0001));
    tt.setHideDelay(new Duration(.0001));

    button.setTooltip(tt);
    return button;
  }


  /**
   * creates a slider
   *
   * @param id       sets id of this node
   * @param min      minimum for the slider
   * @param max      maximum for the slider
   * @param start    initial value of the slider
   * @param response number value of slider
   * @return Node with slider to select value
   */
  public Node makeSlider(String id, double min, double max, double start,
      Consumer<Number> response) {
    Slider slider = new Slider();
    slider.setMin(min);
    slider.setMax(max);
    slider.setValue(start);
    slider.valueProperty().addListener((o, oldValue, newValue) -> response.accept(newValue));
    slider.getStyleClass().add("slider");
    slider.setId(id);
    return makeHolderBox(id, slider);
  }

  /**
   * Creates a ComboBox menu with Label
   *
   * @param id       used as reference for label
   * @param choices  l of possible choices for dropdown menu
   * @param response calls appropriate function through user input
   * @return HolderBox with label ComboBox pair
   */
  public Node makeCombo(String id, List<String> choices, Consumer<String> response) {
    ComboBox<String> options = new ComboBox<>();
    options.getStyleClass().add("choice-box");
    Map<String, String> lang = new HashMap<>();
    for (String option : choices) {
      lang.put(langResource.getString(option), option);
    }
    options.setItems(FXCollections.observableArrayList(lang.keySet().stream().toList()));
//        options.setValue(langResource.getString(choices.get(0)));
    options.valueProperty()
        .addListener((o, oldValue, newValue) -> response.accept(lang.get(newValue)));
    options.setId(id);
    return makeHolderBox(id, options);
  }

  /**
   * Groups buttons together horizontally
   *
   * @param buttons to be lined up horizontally
   * @return Node HBox with horizontally aligned buttons
   */
  public Node makeButtonBox(Node... buttons) {
    HBox hBox = new HBox();
    for (Node button : buttons) {
      hBox.getChildren().add(button);
    }
    hBox.getStyleClass().add("button-box");
    return hBox;
  }

  /**
   * Creates label using id
   *
   * @param id name for label
   * @return appropriate label using id
   */
  public Node makeLabel(String id) {
    return new Label(langResource.getString(id));
  }

  /**
   * Creates label using name
   *
   * @param name name for label
   * @return appropriate label using name
   */
  public Node makeLabelNoID(String name) {
    return new Label(name);
  }

  /**
   * Creates label using id
   *
   * @param id name for label
   * @return appropriate label using id
   */
  public Node makeSmallLabel(String id) {
    Label smallLabel = new Label(langResource.getString(id));
    smallLabel.getStyleClass().add("small-label");
    return smallLabel;
  }

  /**
   * Creates label with String
   *
   * @param string for the label
   * @return appropriate label using id
   */
  public Node makeSmallLabelNoID(String string) {
    Label smallLabel = new Label(string);
    smallLabel.getStyleClass().add("small-label");
    return smallLabel;
  }

  /**
   * @param header  the error category
   * @param message the error message
   * @return error alert
   */
  public Alert makeErrorAlert(String header, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText(header);
    alert.setContentText(message);
    return alert;
  }

  /**
   * @param header  the confirmation category
   * @param message the confirmation message
   * @return confirmation alert
   */
  public Alert makeConfirmationAlert(String header, String message) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setHeaderText(header);
    alert.setContentText(message);
    return alert;
  }


  /**
   * @param id reference to find tab Pane
   * @return tab Pane
   */
  public TabPane makeTabPane(String id) {
    TabPane tabPane = new TabPane();
    tabPane.setId(id);
    return tabPane;
  }

  /**
   * @param id reference to find tab and what to name it
   * @return tab
   */
  public Tab makeTab(String id) {
    Tab tab = new Tab(id);
    tab.setId(id);
    return tab;
  }

  /**
   * @param id reference to find tab and what to name it
   * @return textArea
   */
  public Node makeTextArea(String id) {
    return new TextArea(langResource.getString(id));
  }


  /**
   * @param id reference to find tab and what to name it
   * @return textField
   */
  public Node makePrefilledTextField(String id) {
    TextField textField = new TextField(langResource.getString(id));
    textField.setId(id);
    return textField;
  }

  /**
   * @param id reference to find tab and what to name it
   * @return textField
   */
  public Node makeTextField(String id) {
    TextField textField = new TextField();
    textField.setId(id);
    return textField;
  }

}