package ooga.display.tileviews;

import javafx.scene.shape.Rectangle;
import ooga.model.data.tilemodels.PropertyTileModel;

public class PropertyTileView extends TileView{

  private static final int PROPERTY_TILE_WIDTH = 10;
  private static final int PROPERTY_TILE_HEIGHT= 20;

  private PropertyTileModel myPropertyTileModel;

  public PropertyTileView(PropertyTileModel propTileModel){
    myPropertyTileModel = propTileModel;
    myTileBox = new Rectangle(PROPERTY_TILE_WIDTH,PROPERTY_TILE_HEIGHT);
  }

}
