package ooga.model.data.player;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class PlayerManagerTest {

  List<Player> myPlayers;
  Player p1 = new Player("p1");
  Player p2 = new Player("p2");
  Player p3 = new Player("p3");

  @BeforeEach
  void reset() {
    myPlayers = new ArrayList<>();
    myPlayers.add(p1);
    myPlayers.add(p2);
    myPlayers.add(p3);
  }

  @Test
  abstract void getNextPlayerTest();

  @Test
  abstract void getPlayersTest();

  @Test
  abstract void getActivePlayerTest();

  @Test
  abstract void existsRemainingPlayersTest();


}
