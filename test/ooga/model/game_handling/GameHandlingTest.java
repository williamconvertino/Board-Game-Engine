package ooga.model.game_handling;


import java.util.ArrayList;
import ooga.display.DisplayManager;
import ooga.display.communication.DisplayComm;
import ooga.display.communication.DisplayStateSignaler;
import ooga.display.communication.ExceptionHandler;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.OriginalPlayerManager;
import ooga.model.data.player.Player;
import ooga.model.data.player.PlayerManager;
import ooga.model.data.properties.Property;
import ooga.model.data.tiles.EmptyTile;
import ooga.model.data.tiles.Tile;
import ooga.model.die.Die;
import ooga.model.die.OriginalDice;
import ooga.model.game_handling.board_manager.BoardManager;
import ooga.model.game_handling.board_manager.OriginalBoardManager;
import ooga.model.game_handling.turn_manager.TurnManager;
import org.junit.jupiter.api.BeforeEach;


public class GameHandlingTest {


  public Player p1;
  public Player p2;
  public Player p3;
  public Player p4;

  public PlayerManager myPlayers;
  public BoardManager myBoard;

  public TurnManager myTurnManager;

  public GameData myGameData;

  public FunctionExecutor myFunctionExecutor;

  public DisplayComm myDisplayComm;

  public Die myDie;

  @BeforeEach
  void initGamestate() {
    p1 = new Player("p1");
    p2 = new Player("p2");
    p3 = new Player("p3");
    p4 = new Player("p4");

    Tile t0;
    Tile t1;
    Tile t2;
    Tile t3;
    Tile t4;
    Tile t5;
    Tile t6;
    Tile t7;
    Tile t8;
    Tile t9;
    Tile t10;
    Tile t11;
    Tile t12;
    Tile t13;
    Tile t14;

    Property prop1;

    ArrayList<Player> playerlist = new ArrayList<>();
    playerlist.add(p1);
    playerlist.add(p2);
    playerlist.add(p3);
    playerlist.add(p4);

    myPlayers = new OriginalPlayerManager(playerlist);

    t0 = new EmptyTile("t0");
    t1 = new EmptyTile("t1");
    t2 = new EmptyTile("t2");
    t3 = new EmptyTile("t3");
    t4 = new EmptyTile("t4");
    t5 = new EmptyTile("t5");
    t6 = new EmptyTile("t6");
    t7 = new EmptyTile("t7");
    t8 = new EmptyTile("t8");
    t9 = new EmptyTile("t9");
    t10 = new EmptyTile("t10");
    t11 = new EmptyTile("t11");
    t12 = new EmptyTile("t12");
    t13 = new EmptyTile("t13");
    t14 = new EmptyTile("t14");

    ArrayList<Tile> tileList = new ArrayList<Tile>();

    tileList.add(t0);
    tileList.add(t1);
    tileList.add(t2);
    tileList.add(t3);
    tileList.add(t4);
    tileList.add(t5);
    tileList.add(t6);
    tileList.add(t7);
    tileList.add(t8);
    tileList.add(t9);
    tileList.add(t10);
    tileList.add(t11);
    tileList.add(t12);
    tileList.add(t13);
    tileList.add(t14);

    myBoard = new OriginalBoardManager(tileList);
    myDie = new OriginalDice();
    myGameData = new GameData(myPlayers, myBoard, myDie);
    myDisplayComm = new DisplayComm();
    myFunctionExecutor = new FunctionExecutor(myGameData, myDie, myDisplayComm);
    myTurnManager = new TurnManager(myGameData, myFunctionExecutor, myDisplayComm);

  }

}