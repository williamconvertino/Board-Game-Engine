package ooga.model.data.cards;


import ooga.OriginalTest;
import ooga.model.data.deck.Deck;
import ooga.model.data.deck.DeckManager;
import ooga.model.data.player.Player;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardTest extends OriginalTest {

  DeckManager decks;
  Deck communityChest;
  Deck chance;

  @BeforeEach
  public void initialize() {
    super.initialize();
    try {
      decks = gameData.getDecks();
      chance = decks.getDeck("Chance");
      communityChest = decks.getDeck("Community Chest");
      assertNotNull(communityChest);
      assertNotNull(chance);
    } catch (Exception e) {
      assertTrue(false);
    }
  }

  @Test
  void testCards() {
    try {
      Player currentPlayer = gameData.getCurrentPlayer();

      for (Player p: gameData.getPlayers()) {
        p.setLocation(0);
      }

      //Advance to BW
      int destination = board.getTileIndex("Boardwalk");
      assertNotEquals(currentPlayer.getLocation(), destination);
      chance.getCard("Advance To Boardwalk").execute(currentPlayer);
      assertEquals(currentPlayer.getLocation(), destination);

      //Advance to GO
      destination = board.getTileIndex("Go");
      assertNotEquals(currentPlayer.getLocation(), destination);
      chance.getCard("Advance To Go").execute(currentPlayer);
      assertEquals(currentPlayer.getLocation(), destination);

      //Advance to StC
      destination = board.getTileIndex("St. Charles Place");
      assertNotEquals(currentPlayer.getLocation(), destination);
      chance.getCard("Advance To St. Charles Place").execute(currentPlayer);
      assertEquals(currentPlayer.getLocation(), destination);

      //Advance to RR
      currentPlayer.setLocation(0);
      currentPlayer.setBalance(400);

      destination = board.getTileIndex("Reading Railroad");
      assertNotEquals(currentPlayer.getLocation(), destination);
      chance.getCard("Advance To The Nearest Railroad").execute(currentPlayer);
      assertEquals(currentPlayer.getLocation(), destination);

      turnManager.buyProperty(board.getTile("Reading Railroad"));

      turnManager.endTurn();

      currentPlayer = gameData.getCurrentPlayer();
      currentPlayer.setLocation(0);
      currentPlayer.setBalance(400);

      destination = board.getTileIndex("Reading Railroad");
      assertNotEquals(currentPlayer.getLocation(), destination);
      chance.getCard("Advance To The Nearest Railroad").execute(currentPlayer);
      assertEquals(currentPlayer.getLocation(), destination);

      assertEquals(currentPlayer.getBalance(), 350);

      currentPlayer.setLocation(9);
      chance.getCard("Go Back Three Spaces").execute(currentPlayer);
      assertEquals(currentPlayer.getLocation(), 6);

      currentPlayer.setBalance(9999);
      currentPlayer.setLocation(0);
      turnManager.buyProperty(board.findNextClosestTile(currentPlayer, "Electric Company"));
      turnManager.endTurn();
      currentPlayer = gameData.getCurrentPlayer();
      currentPlayer.setLocation(0);
      currentPlayer.setBalance(1000);
      chance.getCard("Advance To The Nearest Utility").execute(currentPlayer);
      assertNotEquals(currentPlayer.getLocation(), 1000);
      assertNotEquals(currentPlayer.getBalance(), 1000);

      currentPlayer.setLocation(0);
      destination = board.getTileIndex("Jail");
      assertFalse(currentPlayer.isInJail());
      chance.getCard("Go To Jail").execute(currentPlayer);
      assertEquals(currentPlayer.getLocation(), destination);
      assertTrue(currentPlayer.isInJail());

      Property boardwalk = ((PropertyTileModel)board.getTile("Boardwalk")).getProperty();
      Property park = ((PropertyTileModel)board.getTile("Park Place")).getProperty();

      currentPlayer.giveProperty(boardwalk);
      currentPlayer.giveProperty(park);

      currentPlayer.setBalance(1000000);
      turnManager.setSelectedTile(board.getTile("Boardwalk"));
      turnManager.buyHouse();
      turnManager.buyHouse();
      turnManager.buyHouse();
      turnManager.buyHouse();
      turnManager.buyHouse();

      turnManager.setSelectedTile(board.getTile("Park Place"));
      turnManager.buyHouse();
      turnManager.buyHouse();
      turnManager.buyHouse();

      assertEquals(boardwalk.getNumHouses(), 0);
      assertEquals(boardwalk.getNumHotels(), 1);
      assertEquals(park.getNumHouses(), 3);
      assertEquals(park.getNumHotels(), 0);

      currentPlayer.setBalance(1000);

      chance.getCard("Make General Repairs").execute(currentPlayer);
      assertEquals(currentPlayer.getBalance(), (1000-175));

      Property rr = ((PropertyTileModel)board.getTile("Reading Railroad")).getProperty();
      rr.setOwner(Property.NULL_OWNER);
      currentPlayer.setBalance(0);
      currentPlayer.setLocation(10);
      assertEquals(currentPlayer.getBalance(), 0);
      chance.getCard("Take A Trip To Reading Railroad").execute(currentPlayer);
      assertEquals(currentPlayer.getLocation(), board.getTileIndex("Reading Railroad"));
      assertEquals(currentPlayer.getBalance(), 200);

    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }

  }



}
