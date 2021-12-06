package ooga.model.data.cards;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.ResourceBundle;
import ooga.OriginalTest;
import ooga.exceptions.AttributeNotFoundException;
import ooga.exceptions.InvalidFileFormatException;
import ooga.model.data.deck.Deck;
import ooga.model.data.deck.DeckManager;
import ooga.model.data.player.Player;
import ooga.model.game_handling.GameHandlingTest;
import ooga.model.game_handling.commands.ActionSequence;
import ooga.model.game_initialization.GameDataInitializer;
import ooga.util.parsers.CardParser;
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
      communityChest = decks.getDeck("Chance");
      chance = decks.getDeck("Community Chest");
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
      int destination = board.getTileIndex("Boardwalk");
      assertNotEquals(currentPlayer.getLocation(), destination);

    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }

  }



}
