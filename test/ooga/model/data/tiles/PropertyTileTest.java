package ooga.model.data.tiles;

import ooga.exceptions.TileNotFoundException;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.game_handling.GameHandlingTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyTileTest extends GameHandlingTest {

  @Test
  void testLandOn() throws TileNotFoundException {
    PropertyTileModel myTile = (PropertyTileModel) myBoard.getTileAtIndex(5);

    assertEquals(myTile.getName(), "prop1");

    assertEquals(p2.getBalance(), 1500);
    myBoard.movePlayerToTile(p2, "prop1", false);
    assertEquals(p2.getBalance(), 1500);

    myTile.getProperty().setOwner(p2);

    assertEquals(p1.getBalance(), 1500);
    myBoard.movePlayerToTile(p1, "prop1", false);


    assertEquals(p1.getBalance(), 1490);
    assertEquals(p2.getBalance(), 1510);

  }

}
