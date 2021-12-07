package ooga.model.game_handling.board_manager;

import java.util.ArrayList;
import ooga.display.communication.DisplayComm;
import ooga.display.communication.DisplayStateSignaler.State;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.OriginalPlayerManager;
import ooga.model.data.player.Player;
import ooga.model.data.player.PlayerManager;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.EmptyTileModel;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.die.Die;
import ooga.model.die.OriginalDice;
import ooga.model.game_handling.FunctionExecutor;
import ooga.model.game_handling.GameHandlingTest;
import ooga.model.game_handling.TurnManager;
import ooga.model.game_handling.commands.ActionSequence;
import ooga.model.game_handling.commands.ActionSequenceExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BackwardBoardManagerTest {
  public Player p1;
  public Player p2;
  public Player p3;
  public Player p4;

  public PlayerManager myPlayers;
  public BoardManager myBoard;

  public TurnManager myTurnManager;

  public GameData myGameData;

  public FunctionExecutor myFunctionExecutor;

  public ActionSequenceExecutor myActionSequenceParser;

  public DisplayComm myDisplayComm;

  public Die myDie;


  public Property prop1;

  TileModel t0;
  TileModel t1;
  TileModel t2;
  TileModel t3;
  TileModel t4;
  public TileModel t5;
  TileModel t6;
  TileModel t7;
  TileModel t8;
  TileModel t9;
  TileModel t10;
  TileModel t11;
  TileModel t12;
  TileModel t13;
  TileModel t14;

  @BeforeEach
  void initGamestate() {
    p1 = new Player("p1");
    p2 = new Player("p2");
    p3 = new Player("p3");
    p4 = new Player("p4");





    ArrayList<Player> playerlist = new ArrayList<>();
    playerlist.add(p1);
    playerlist.add(p2);
    playerlist.add(p3);
    playerlist.add(p4);

    myPlayers = new OriginalPlayerManager(playerlist);

    myDisplayComm = new DisplayComm(null) {
      @Override
      public void signalState(State s) {

      }
    };

    t0 = new EmptyTileModel("t0");
    t1 = new EmptyTileModel("t1");
    t2 = new EmptyTileModel("t2");
    t3 = new EmptyTileModel("t3");
    t4 = new EmptyTileModel("t4");
    prop1 = new Property("Property 1", "Regular",100, new int[]{5,20,40},10, new ArrayList<>(), 60, "blue" );
    t5 = new PropertyTileModel("prop1","Property", prop1, new ActionSequence(myActionSequenceParser, myDisplayComm), myDisplayComm);
    t6 = new EmptyTileModel("t6");
    t7 = new EmptyTileModel("t7");
    t8 = new EmptyTileModel("t8");
    t9 = new EmptyTileModel("t9");
    t10 = new EmptyTileModel("Jail");
    t11 = new EmptyTileModel("t11");
    t12 = new EmptyTileModel("t12");
    t13 = new EmptyTileModel("t13");
    t14 = new EmptyTileModel("t14");

    ArrayList<TileModel> tileList = new ArrayList<TileModel>();

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

    myBoard = new BackwardsBoardManager(tileList);
    myDie = new OriginalDice();
    myGameData = new GameData(myPlayers, myBoard, myDie);

    myFunctionExecutor = new FunctionExecutor(myGameData, myDie, myDisplayComm);
    myTurnManager = new TurnManager(myGameData, myFunctionExecutor, myDisplayComm);

  }

  @Test
  void test() {
    Player p = myGameData.getCurrentPlayer();
    p.setLocation(10);
    myFunctionExecutor.movePlayerFd(p,3);
    assertEquals(p.getLocation(), 7);

    myFunctionExecutor.movePlayerBk(p,3);
    assertEquals(p.getLocation(), 10);
  }
}
