package ooga.model.data.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OriginalPlayerManagerTest extends PlayerManagerTest {

  OriginalPlayerManager pm;
  @BeforeEach
  void init() {
    reset();
    pm = new OriginalPlayerManager(myPlayers);
  }

  @Override
  @Test
  void getNextPlayerTest() {

    try {
      Set<Player> firstTurns = new HashSet<>();
      firstTurns.add(pm.getNextPlayer());
      firstTurns.add(pm.getNextPlayer());
      firstTurns.add(pm.getNextPlayer());
      assertTrue(firstTurns.contains(p1));
      assertTrue(firstTurns.contains(p2));
      assertTrue(firstTurns.contains(p3));

      Set<Player> secondTurns = new HashSet<>();
      secondTurns.add(pm.getNextPlayer());
      secondTurns.add(pm.getNextPlayer());
      secondTurns.add(pm.getNextPlayer());
      assertTrue(firstTurns.contains(p1));
      assertTrue(firstTurns.contains(p2));
      assertTrue(firstTurns.contains(p3));

    } catch (Exception e) {
      System.out.println("Error");
      assertTrue(false);
    }

  }

  @Override
  @Test
  void getPlayersTest() {
    List<Player> players = pm.getPlayers();
    assertEquals(3, players.size());

    Player p = players.get(0);
    p.setActiveStatus(false);
    players = pm.getPlayers();
    assertTrue(players.contains(p));
    assertEquals(3, players.size());
  }

  @Override
  @Test
  void getActivePlayerTest() {
    List<Player> activePlayers = pm.getActivePlayers();
    assertEquals(3, activePlayers.size());

    Player p = activePlayers.get(0);
    p.setActiveStatus(false);
    activePlayers = pm.getActivePlayers();
    assertFalse(activePlayers.contains(p));
    assertEquals(2, activePlayers.size());

  }

  @Override
  @Test
  void existsRemainingPlayersTest() {
    List<Player> activePlayers = pm.getActivePlayers();
    assertTrue(pm.existsRemainingPlayers());
    for (Player p: activePlayers) {
      p.setActiveStatus(false);
    }
    assertFalse(pm.existsRemainingPlayers());
  }
}
