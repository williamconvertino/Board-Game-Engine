package ooga.model.data.properties;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import ooga.model.data.player.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {

  @Test
  void testConstructorAndSetGet() {

    Property p = new Property("name", "Regular",1, new int[]{2,4,6}, 3, new LinkedList<>(), 5,"red");

    assertEquals("name", p.getName());
    assertEquals(1, p.getCost());
    assertEquals(5, p.getMortgageValue());
    assertEquals(3, p.getHouseCost());
    assertEquals(Property.NULL_OWNER, p.getOwner());
    assertEquals(0, p.getNumHouses());
  }

  @Test
  void testHouses() {

    Property p = new Property("name", "Regular",1, new int[]{2,4,6}, 3, new LinkedList<>(), 5,"red");

    assertEquals(0, p.getNumHouses());
    try {
      p.mortgageProperty();
    } catch (Exception e) {
    }
    try {
      p.buyHouse();
    } catch (Exception e) {
      assertTrue(true);
    }
    try {
      p.unmortgageProperty();
    } catch (Exception e) {

    }
    try {
      p.buyHouse();
    } catch (Exception e) {
      assertTrue(false);
    }
    assertEquals(1, p.getNumHouses());

    try {
      p.buyHouse();
      p.buyHouse();
      p.buyHouse();
      p.buyHouse();
      assertTrue(false);
    } catch (Exception e) {
      assertTrue(true);
    }
    assertEquals(0, p.getNumHouses());
    assertEquals(1, p.getNumHotels());
  }

  @Test
  void testRent() {

    Property p = new Property("name", "Regular",1, new int[]{2,4,6}, 3, new LinkedList<>(), 5,"red");

    assertEquals(2, p.getRentCost());
    try {
      p.buyHouse();
    } catch (Exception e) {
    }

    assertEquals(4, p.getRentCost());

    try {
      p.buyHouse();
    } catch (Exception e) {
    }

    assertEquals(6, p.getRentCost());

    try {
      p.mortgageProperty();
    }
    catch (Exception e) {
    }
    assertEquals(0, p.getRentCost());
  }

  @Test
  void testMonopoly() {

    List<String> set1 = new ArrayList<>();
    List<String> set2 = new ArrayList<>();

    set1.add("p2");
    set1.add("p1");

    Property p1 = new Property("p1", "Regular",1, new int[]{2,4,6}, 3, set1, 5,"red");
    Property p2 = new Property("p2", "Regular",1, new int[]{2,4,6}, 3, set2, 5,"red");

    assertFalse(p1.isMonopoly());
    assertFalse(p2.isMonopoly());

    Player player = new Player("player");

    player.giveProperty(p1);
    player.giveProperty(p2);

    assertTrue(p1.isMonopoly());
    assertTrue(p2.isMonopoly());

    assertEquals(4, p1.getRentCost());
    assertEquals(4, p2.getRentCost());

  }

}
