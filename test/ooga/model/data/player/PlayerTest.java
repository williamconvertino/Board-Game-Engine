package ooga.model.data.player;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import ooga.model.data.cards.Card;
import ooga.model.data.properties.Property;
import ooga.model.game_handling.GameHandlingTest;
import ooga.model.game_handling.commands.ActionSequence;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest extends GameHandlingTest {

  @Test
  void testName() {

    Player p = new Player("p1");
    assertEquals("p1", p.getName());

  }

  @Test
  void testPropertyGiving() {
    Player p = new Player("p1");
    assertTrue(p.getProperties().isEmpty());
    Property prop = new Property("test","Regular",0,new int[] {0},0,new LinkedList<>(),0,"red");
    p.giveProperty(prop);
    assertTrue(p.getProperties().contains(prop));
    p.removeProperty(prop);
    assertFalse(p.getProperties().contains(prop));
  }

  @Test
  void testCardGiving() {
    Player p = new Player("p1");
    assertTrue(p.getCards().isEmpty());
    Card card = new Card("c", "desc", new ActionSequence(myActionSequenceParser, myDisplayComm), myDisplayComm);
    p.giveCard(card);
    assertTrue(p.getCards().contains(card));
    p.removeCard(card);
    assertFalse(p.getCards().contains(card));
  }

  @Test
  void testJail() {
    Player p = new Player("p1");
    assertFalse(p.isInJail());
    p.setJailStatus(true);
    assertTrue(p.isInJail());
  }

  @Test
  void testLocation() {
    Player p = new Player("p1");
    assertEquals(0,p.getLocation());
    p.setLocation(20);
    assertEquals(20, p.getLocation());
  }

}
