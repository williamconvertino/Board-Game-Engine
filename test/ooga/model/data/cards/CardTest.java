package ooga.model.data.cards;


import ooga.OriginalTest;
import ooga.model.data.deck.Deck;
import ooga.model.data.deck.DeckManager;
import ooga.model.data.player.Player;
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
  void testChance() {
    try {
      Player currentPlayer = gameData.getCurrentPlayer();

      //Advance to BW
      int destination = board.getTileIndex("Boardwalk");
//      assertNotEquals(currentPlayer.getLocation(), destination);
//      chance.getCard("Advance To Boardwalk").execute(currentPlayer);
//      assertEquals(currentPlayer.getLocation(), destination);
//
//      destination = board.getTileIndex("Go");
//      assertNotEquals(currentPlayer.getLocation(), destination);
//      chance.getCard("Advance To Go").execute(currentPlayer);
//      assertEquals(currentPlayer.getLocation(), destination);
//
//      destination = board.getTileIndex("St. Charles Place");
//      assertNotEquals(currentPlayer.getLocation(), destination);
//      chance.getCard("Advance To St. Charles Place").execute(currentPlayer);
//      assertEquals(currentPlayer.getLocation(), destination);

      currentPlayer.setLocation(0);

      destination = board.getTileIndex("Reading Railroad");
      assertNotEquals(currentPlayer.getLocation(), destination);
      chance.getCard("Advance To The Nearest Railroad").execute(currentPlayer);
      assertEquals(currentPlayer.getLocation(), destination);


    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }

  }



}
