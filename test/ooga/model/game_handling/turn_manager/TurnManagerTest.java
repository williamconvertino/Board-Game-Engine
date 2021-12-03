package ooga.model.game_handling.turn_manager;

import java.util.ArrayList;
import java.util.List;
import ooga.exceptions.TileNotFoundException;
import ooga.model.data.player.Player;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.game_handling.GameHandlingTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TurnManagerTest extends GameHandlingTest {



  @Test
  void testStartTurn() {

    myTurnManager.endTurn();
    assertNotEquals(null, myGameData.getCurrentPlayer());

    assertEquals(0, myGameData.getNumRolls());
    myTurnManager.roll();
    assertNotEquals(0, myGameData.getNumRolls());
    myTurnManager.endTurn();
    assertEquals(0, myGameData.getNumRolls());

  }

  @Test
  void testRoll() {
    myTurnManager.endTurn();
    Player currentPlayer = myGameData.getCurrentPlayer();
    int location1 = currentPlayer.getLocation();

    assertEquals(0, myGameData.getNumRolls());
    myTurnManager.roll();
    assertEquals(1, myGameData.getNumRolls());

    int location2 = currentPlayer.getLocation();
    assertNotEquals(location1, location2);

    if (myDie.lastRollDouble()) {
      myTurnManager.roll();
      assertNotEquals(location2, currentPlayer.getLocation());
    } else {
      myTurnManager.roll();
      assertEquals(location2, currentPlayer.getLocation());
    }

  }

  @Test
  void testSelectAndBuy() throws TileNotFoundException {
    Player p = myGameData.getCurrentPlayer();
    myFunctionExecutor.movePlayerToIndex(p, 5);
    int location = p.getLocation();
    TileModel myTile = myBoard.getTileAtIndex(location);
    Property myProp = ((PropertyTileModel)myTile).getProperty();
    assertEquals(1500, p.getBalance());
    assertFalse(p.getProperties().contains(myProp));
    myTurnManager.setSelectedTile(myTile);
    myTurnManager.buyProperty(myTile);
    assertEquals(1400, p.getBalance());
    assertTrue(p.getProperties().contains(myProp));
    myTurnManager.endTurn();
    p = myGameData.getCurrentPlayer();
    myTile = myBoard.getTileAtIndex(location);
    myProp = ((PropertyTileModel)myTile).getProperty();
    myFunctionExecutor.movePlayerToIndex(p, 5);
    location = p.getLocation();


    myTurnManager.setSelectedTile(myTile);
    myTurnManager.buyProperty(myTile);
    assertFalse(p.getProperties().contains(myProp));
  }

  @Test
  void buyHouseTest() {

    Player p = myGameData.getCurrentPlayer();
    TileModel tile = myBoard.getTileAtIndex(5);
    Property myProp = ((PropertyTileModel)tile).getProperty();

    myTurnManager.setSelectedTile(tile);
    assertFalse(p.getProperties().contains(myProp));
    assertEquals(0,myProp.getNumHouses());
    assertEquals(1500, p.getBalance());
    myTurnManager.buyHouse(myProp);
    assertFalse(p.getProperties().contains(myProp));
    assertEquals(0,myProp.getNumHouses());
    assertEquals(1500, p.getBalance());

    myTurnManager.buyProperty(tile);
    myTurnManager.buyHouse(myProp);

    assertTrue(p.getProperties().contains(myProp));
    assertEquals(1,myProp.getNumHouses());
    assertEquals(1390, p.getBalance());
  }

  @Test
  void sellHouseTest() {
    Player p = myGameData.getCurrentPlayer();
    TileModel tile = myBoard.getTileAtIndex(5);
    Property myProp = ((PropertyTileModel)tile).getProperty();
    myTurnManager.setSelectedTile(tile);
    myTurnManager.buyProperty(tile);
    myTurnManager.buyHouse(myProp);
    assertEquals(1, myProp.getNumHouses());
    myTurnManager.sellHouse(myProp);
    assertEquals(0, myProp.getNumHouses());

  }

  @Test
  void testPlayerLose() {
    List<Player> remaingPlayers = new ArrayList<>(myGameData.getPlayers());
    remaingPlayers.removeIf(e->!e.isActive());
    assertTrue(remaingPlayers.size() == 4);
    myGameData.getCurrentPlayer().addMoney(-1000000);
    myTurnManager.endTurn();
    remaingPlayers.removeIf(e->!e.isActive());
    assertTrue(remaingPlayers.size() == 3);

  }

}
