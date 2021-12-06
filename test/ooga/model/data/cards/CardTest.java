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
      assertNotEquals(currentPlayer.getLocation(), destination);

      //functionExecutor.advancePlayerToTile(currentPlayer, "Boardwalk");
      //assertEquals(currentPlayer.getLocation(), destination);

      chance.getCard("Advance To Boardwalk").execute(currentPlayer);
      assertEquals(currentPlayer.getLocation(), destination);


    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }

  }



}
