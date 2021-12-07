package ooga.display.popup;


import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ooga.display.ui_tools.UIBuilder;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;

/**
 * PopUp for each property
 *
 * @author Aaric Han
 */
public class PropertyInfoPopUp {

  private final Popup myPopup;
  private final TileModel myPropertyTileModel;
  private final UIBuilder myUIBuilder;
  private final ResourceBundle myResource;

  /**
   * Constructor for property info pop-up
   */
  public PropertyInfoPopUp(TileModel propertyTileModel, UIBuilder uiBuilder,
      ResourceBundle resource) {
    myPopup = new Popup();
    myPropertyTileModel = propertyTileModel;
    myUIBuilder = uiBuilder;
    myResource = resource;
    setupPopup();
  }

  private void setupPopup() {
    // Property Popup:
    VBox popUpVBox = new VBox();
    popUpVBox.setPrefSize(400, 600);
    popUpVBox.setId("propertyVBox");

    // index 0
    Label name = new Label(myPropertyTileModel.getName());
    popUpVBox.getChildren().add(name);

    if (myPropertyTileModel instanceof PropertyTileModel) {
      PropertyTileModel propertyTileModel = (PropertyTileModel) myPropertyTileModel;
      // index 1
      Label cost = new Label(String.format("%s %d", myResource.getString("propCostLabel"),
          propertyTileModel.getProperty().getCost()));
      popUpVBox.getChildren().add(cost);

      // index 2
      Label rentCost = new Label(String.format("%s %d", myResource.getString("rentCostLabel"),
          propertyTileModel.getProperty().getRentCost()));
      popUpVBox.getChildren().add(rentCost);

      // index 3
      Label numHouses = new Label(String.format("%s %d", myResource.getString("houseLabel"),
          propertyTileModel.getProperty().getNumHouses()));
      popUpVBox.getChildren().add(numHouses);

      // index 3
      Label numHotels = new Label(String.format("%s %d", myResource.getString("hotelLabel"),
          propertyTileModel.getProperty().getNumHotels()));
      popUpVBox.getChildren().add(numHotels);

      // index 4
      Label houseCost = new Label(String.format("%s %d", myResource.getString("houseCostLabel"),
          propertyTileModel.getProperty().getHouseCost()));
      popUpVBox.getChildren().add(houseCost);

      // index 5
      String ownerNameCheck = propertyTileModel.getProperty().getOwner().getName();
      if (ownerNameCheck.equals("Null")) {
        ownerNameCheck = myResource.getString("noOwnerString");
      }
      Label owner = new Label(
          String.format("%s %s", myResource.getString("ownerLabel"), ownerNameCheck));
      popUpVBox.getChildren().add(owner);

      // index 6
      Label mortgageValue = new Label(
          String.format("%s %d", myResource.getString("mortgageValueLabel"),
              propertyTileModel.getProperty().getMortgageValue()));
      popUpVBox.getChildren().add(mortgageValue);
    }

    // Index 7 or 1
    Button closeButton = myUIBuilder.makeTextButton("Close", e -> hidePopup());
    popUpVBox.getChildren().add(closeButton);

    myPopup.getContent().add(popUpVBox);
  }

  /**
   * Show popup for the property
   *
   * @param stage
   */
  public void showPopup(Stage stage) {
    myPopup.show(stage);
  }

  private void hidePopup() {
    myPopup.hide();
  }
}
