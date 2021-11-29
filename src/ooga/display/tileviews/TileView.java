package ooga.display.tileviews;

import javafx.scene.shape.Rectangle;
import ooga.model.data.tilemodels.PropertyTileModel;

//WILL DEFINITELY REFACTOR CODE INTO THIS ABSTRACT CLASS

public class TileView {


  private static final int PROPERTY_TILE_WIDTH = 10;
  private static final int PROPERTY_TILE_HEIGHT= 20;

  private PropertyTileModel myPropertyTileModel;
  protected Rectangle myTileBox; //change this to whatever JavaFx object you see fit (VBox, Shape, etc)

  public TileView(){

  }

}
