package ooga.display.screens;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ooga.display.DisplayManager;
import ooga.display.communication.DisplayComm;
import ooga.display.communication.EventManager;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.OriginalPlayerManager;
import ooga.model.data.player.Player;
import ooga.model.data.player.PlayerManager;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.EmptyTileModel;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.die.Die;
import ooga.model.die.OriginalDice;
import ooga.model.game_handling.FunctionExecutor;
import ooga.model.game_handling.board_manager.BoardManager;
import ooga.model.game_handling.board_manager.OriginalBoardManager;
import ooga.model.game_handling.TurnManager;
import org.junit.jupiter.api.Test;
import ooga.util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartMenuTest extends DukeApplicationTest {
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
  public DisplayManager dm;

  @Override
  public void start(Stage stage) {
    p1 = new Player("p1");
    p2 = new Player("p2");
    p3 = new Player("p3");
    p4 = new Player("p4");

    TileModel t0;
    TileModel t1;
    TileModel t2;
    TileModel t3;
    TileModel t4;
    TileModel t5;
    TileModel t6;
    TileModel t7;
    TileModel t8;
    TileModel t9;
    TileModel t10;
    TileModel t11;
    TileModel t12;
    TileModel t13;
    TileModel t14;
    TileModel t15;
    TileModel t16;
    TileModel t17;
    TileModel t18;
    TileModel t19;
    TileModel t20;
    TileModel t21;
    TileModel t22;
    TileModel t23;
    TileModel t24;
    TileModel t25;
    TileModel t26;
    TileModel t27;
    TileModel t28;
    TileModel t29;
    TileModel t30;
    TileModel t31;
    TileModel t32;
    TileModel t33;
    TileModel t34;
    TileModel t35;
    TileModel t36;
    TileModel t37;
    TileModel t38;
    TileModel t39;


    Property prop1;

    ArrayList<Player> playerList = new ArrayList<>();
    playerList.add(p1);
    playerList.add(p2);
    playerList.add(p3);
    playerList.add(p4);

    myPlayers = new OriginalPlayerManager(playerList);

    t0 = new EmptyTileModel("Go");
    t1 = new EmptyTileModel("t1");
    t2 = new EmptyTileModel("t2");
    t3 = new EmptyTileModel("t3");
    t4 = new EmptyTileModel("t4");
    t5 = new EmptyTileModel("t5");
    t6 = new EmptyTileModel("t6");
    t7 = new EmptyTileModel("t7");
    t8 = new EmptyTileModel("t8");
    t9 = new EmptyTileModel("t9");
    t10 = new EmptyTileModel("Jail");
    t11 = new EmptyTileModel("t11");
    t12 = new EmptyTileModel("t12");
    t13 = new EmptyTileModel("t13");
    t14 = new EmptyTileModel("t14");
    t15 = new EmptyTileModel("t15");
    t16 = new EmptyTileModel("t16");
    t17 = new EmptyTileModel("t17");
    t18 = new EmptyTileModel("t18");
    t19 = new EmptyTileModel("t19");
    t20 = new EmptyTileModel("Free Parking");
    t21 = new EmptyTileModel("t21");
    t22 = new EmptyTileModel("t22");
    t23 = new EmptyTileModel("t23");
    t24 = new EmptyTileModel("t24");
    t25 = new EmptyTileModel("t25");
    t26 = new EmptyTileModel("t26");
    t27 = new EmptyTileModel("t27");
    t28 = new EmptyTileModel("t28");
    t29 = new EmptyTileModel("t29");
    t30 = new EmptyTileModel("Go to Jail");
    t31 = new EmptyTileModel("t31");
    t32 = new EmptyTileModel("t32");
    t33 = new EmptyTileModel("t33");
    t34 = new EmptyTileModel("t34");
    t35 = new EmptyTileModel("t35");
    t36 = new EmptyTileModel("t36");
    t37 = new EmptyTileModel("t37");
    t38 = new EmptyTileModel("t38");
    t39 = new EmptyTileModel("t39");


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
    tileList.add(t15);
    tileList.add(t16);
    tileList.add(t17);
    tileList.add(t18);
    tileList.add(t19);
    tileList.add(t20);
    tileList.add(t21);
    tileList.add(t22);
    tileList.add(t23);
    tileList.add(t24);
    tileList.add(t25);
    tileList.add(t26);
    tileList.add(t27);
    tileList.add(t28);
    tileList.add(t29);
    tileList.add(t30);
    tileList.add(t31);
    tileList.add(t32);
    tileList.add(t33);
    tileList.add(t34);
    tileList.add(t35);
    tileList.add(t36);
    tileList.add(t37);
    tileList.add(t38);
    tileList.add(t39);

    dm = new DisplayManager(stage);
    myBoard = new OriginalBoardManager(tileList);
    myDie = new OriginalDice();
    myGameData = new GameData(myPlayers, myBoard, myDie);
    myDisplayComm = new DisplayComm(dm);
    myFunctionExecutor = new FunctionExecutor(myGameData, myDie, myDisplayComm);
    myTurnManager = new TurnManager(myGameData, myFunctionExecutor, myDisplayComm);
    EventManager eh = new EventManager(myTurnManager);

  }

  /**
   * This test tests to see if at the screens menu (display Index 0)
   * is active
   */
  @Test
  public void onStartScreen() {
    assertEquals(0, dm.getCurrDisplayIndex());
  }

  /**
   * This test tests to see if at the screens menu (display Index 0)
   * whether or not clicking the options button makes the index
   * of List of Displays in DisplayManager go to 1 (OptionsMenu)
   */
  @Test
  public void clickOptions() {
    Button options = lookup("#Options").query();
    clickOn(options);
    assertEquals(1, dm.getCurrDisplayIndex());
  }

  /**
   * This test clicks options then clicks home
   */
  @Test
  public void clickHomeFromOptions() {
    Button options = lookup("#Options").query();
    clickOn(options);
    Button GoHome = lookup("#GotoHome").query();
    clickOn(GoHome);
    assertEquals(0, dm.getCurrDisplayIndex());
  }

  /**
   * This test tests to see if at the screens menu (display index 0)
   * whether or not clicking on the screens button makes the index
   * of List of Displays in DisplayManager go to 2 (PlayerName)
   */
  @Test
  public void clickStartEnterPlayerNames() {
    Button options = lookup("#Start").query();
    clickOn(options);
    assertEquals(2, dm.getCurrDisplayIndex());
  }

  /**
   * This test clicks screens then clicks home
   */
  @Test
  public void clickHomeFromPlayerNames() {
    Button options = lookup("#Start").query();
    clickOn(options);
    Button GoHome = lookup("#GotoHome").query();
    clickOn(GoHome);
    assertEquals(0, dm.getCurrDisplayIndex());
  }

  /**
   * This test clicks screens then clicks continue
   */
  @Test
  public void clickContinueFromPlayerNames() {
    Button options = lookup("#Start").query();
    clickOn(options);
    Button GoHome = lookup("#Continue").query();
    clickOn(GoHome);
    assertEquals(4, dm.getCurrDisplayIndex());
  }
}
