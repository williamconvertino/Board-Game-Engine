package ooga.model.game_handling;

import java.util.ArrayList;
import ooga.model.data.player.OriginalPlayerManager;
import ooga.model.data.player.Player;
import ooga.model.data.player.PlayerManager;
import ooga.model.data.properties.Property;
import ooga.model.data.tiles.EmptyTile;
import ooga.model.data.tiles.Tile;
import ooga.model.game_handling.board_manager.BoardManager;
import ooga.model.game_handling.board_manager.OriginalBoardManager;
import ooga.model.game_handling.turn_manager.TurnManager;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class GameHandlingTest {


  Player p1;
  Player p2;
  Player p3;
  Player p4;

  PlayerManager myPlayers;
  BoardManager myBoard;

  TurnManager myTurnManager;

  @BeforeEach
  void initGamestate() {
    p1 = new Player("p1");
    p2 = new Player("p2");
    p3 = new Player("p3");
    p4 = new Player("p4");

    Tile t1 ;
    Tile t2 ;
    Tile t3 ;

    Property prop1;

    ArrayList<Player> playerlist = new ArrayList<>();
    playerlist.add(p1);
    playerlist.add(p2);
    playerlist.add(p3);
    playerlist.add(p4);

    myPlayers = new OriginalPlayerManager(playerlist);

    t1 = new EmptyTile("t1");
    t2 = new EmptyTile("t2");
    t3 = new EmptyTile("t3");

    ArrayList<Tile> tileList = new ArrayList<Tile>();

    tileList.add(t1);
    tileList.add(t2);
    tileList.add(t3);

    myBoard = new OriginalBoardManager(tileList);

  }


}
