package ooga.model.game_handling.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ooga.exceptions.InvalidFileFormatException;
import ooga.model.data.player.Player;
import ooga.model.game_handling.FunctionExecutor;
import ooga.model.game_handling.GameHandlingTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActionSequenceExecutorTest extends GameHandlingTest {

  ActionSequenceExecutor ASE;

  @BeforeEach
  void initialize() {
    ASE = new ActionSequenceExecutor(myFunctionExecutor,myGameData, myDisplayComm);
  }

  @Test
  void testParseCommand() {
    String command = "advancePlayerTo p0 \"St. Charles Place\" please \"Or else!!\"";
    String[] desired = {"advancePlayerTo", "p0", "St. Charles Place", "please", "Or else!!"};
    assertArrayEquals(ASE.parseCommand(command), desired);
  }

  @Test
  void testSingleArgumentCommands() {

    try {
      Player currentPlayer = myGameData.getCurrentPlayer();
      List<Player> playerList = new ArrayList<>(myGameData.getPlayers());
      assertEquals(playerList.stream().filter(e->e.getBalance() ==1520).count(), 0);
      assertEquals(playerList.size() ,4);
      assertEquals(currentPlayer.getBalance(), 1500);
      ASE.executeCommand("addMoney p0 20");
      assertEquals(currentPlayer.getBalance(), 1520);
      assertEquals(playerList.stream().filter(e->e.getBalance() ==1520).count(), 1);
      ASE.executeCommand("addMoneyToMultiple px0 20");
      assertEquals(playerList.stream().filter(e->e.getBalance() ==1520).count(), 4);

      ASE.executeCommand("addMoneyToMultiple pa 50");
      assertEquals(playerList.stream().filter(e->e.getBalance() ==1570).count(), 4);


      ASE.executeCommand("addMoney p0 rollDie");
      assertNotEquals(currentPlayer.getBalance(),1570);

    } catch (InvalidFileFormatException e) {
      assertTrue(false);
    }

  }

  @Test
  void testMultistep() {

    try {
      Player currentPlayer = myGameData.getCurrentPlayer();
      List<Player> playerList = new ArrayList<>(myGameData.getPlayers());
      assertEquals(playerList.stream().filter(e->e.getBalance() ==1520).count(), 0);
      assertEquals(playerList.size() ,4);
      assertEquals(currentPlayer.getBalance(), 1500);
      ASE.executeCommand("addMoney p0 20");
      assertEquals(currentPlayer.getBalance(), 1520);


//      ASE.executeCommand("addMoney p0 x 10 10");
//      assertEquals(1620, currentPlayer.getBalance());

      ASE.executeCommand("addMoney p0 rollDieX 100");
      assertTrue(currentPlayer.getBalance() >= 1620);

      currentPlayer.setBalance(600);

      currentPlayer.giveProperty(prop1);
      myTurnManager.setSelectedTile(t5);

      assertEquals(currentPlayer.getBalance(), 600);
      ASE.executeCommand("loseMoneyForNumHouses p0 100");

      myTurnManager.buyHouse();

      assertEquals(currentPlayer.getBalance(), 590);
      ASE.executeCommand("loseMoneyForNumHouses p0 100");

      assertEquals(currentPlayer.getBalance(), 490);
      myTurnManager.buyHouse();

      assertEquals(currentPlayer.getBalance(), 480);

      ASE.executeCommand("loseMoneyForNumHotels p0 10");

      assertEquals(currentPlayer.getBalance(), 470);



    } catch (Exception e) {
      assertTrue(false);
    }

  }

  @Test
  void testTileMovement() {
    try {
      Player currentPlayer = myGameData.getCurrentPlayer();
      List<Player> playerList = new ArrayList<>(myGameData.getPlayers());
      assertEquals(playerList.stream().filter(e->e.getBalance() ==1520).count(), 0);
      assertEquals(playerList.size() ,4);
      assertEquals(currentPlayer.getBalance(), 1500);

      assertNotEquals(currentPlayer.getLocation(), 5);
      ASE.executeCommand("advanceToTile p0 prop1");

      assertEquals(currentPlayer.getLocation(), 5);

      int  location = currentPlayer.getLocation();

      ASE.executeCommand("moveFd p0 4");
      assertEquals(currentPlayer.getLocation(), location + 4);


      ASE.executeCommand("moveBk p0 3");
      assertEquals(currentPlayer.getLocation(), location + 1);

    } catch (Exception e) {
      assertTrue(false);
    }

  }

  @Test
  public void moveByType() {

    try {
      Player currentPlayer = myGameData.getCurrentPlayer();
      List<Player> playerList = new ArrayList<>(myGameData.getPlayers());

      assertNotEquals(currentPlayer.getLocation(), 5);
      ASE.executeCommand("advanceToType p0 Property");
      assertEquals(currentPlayer.getLocation(), 5);

      ASE.executeCommand("advanceToType p0 Empty");
      assertEquals(currentPlayer.getLocation(), 6);
      ASE.executeCommand("advanceToType p0 Property");
      assertEquals(currentPlayer.getLocation(), 5);

      currentPlayer.giveProperty(prop1);
      myTurnManager.endTurn();

      Player newCurrentPlayer = myGameData.getCurrentPlayer();
      if (currentPlayer == newCurrentPlayer) {
        return;
      }


      assertEquals(newCurrentPlayer.getBalance(), 1500);

      ASE.executeCommand("advanceToTypeAndPayX p0 Property 2");
      assertEquals(newCurrentPlayer.getLocation(), 5);

      assertEquals(newCurrentPlayer.getBalance(), 1480);

    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }

  }


}
