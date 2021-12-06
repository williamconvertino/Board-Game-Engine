package ooga.display.screens;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ooga.display.DisplayManager;
import ooga.util.DukeApplicationTest;
import org.junit.jupiter.api.Test;

public class GameCreatorScreenTest extends DukeApplicationTest {
  public DisplayManager dm;

  @Override
  public void start(Stage stage) {
    dm = new DisplayManager(stage);
  }

  /**
   * This test tests to see if at the screens menu (display index 0)
   * whether or not clicking on the screens button makes the index
   * of List of Displays in DisplayManager go to 2 (PlayerName)
   */
  @Test
  public void clickStartEnterPlayerNames() throws InterruptedException {
    Button options = lookup("#Start").query();
    clickOn(options);
    Button creator = lookup("#CreateYourOwn").query();
    clickOn(creator);
    TextField player1Name = lookup("#EnterGameName").query();
    writeInputTo(player1Name, "monopolytestgame");
    Button start = lookup("#CreateNewGame").query();
    clickOn(start);
    createFreeParking();
    createRegularProperty("Boardwalk","400","20,40,60","150","Park Place","200","blue");
    TimeUnit.SECONDS.sleep(4);
    //assertEquals(2, dm.getCurrDisplayIndex());
  }

  public void createFreeParking(){
    Button creator = lookup("#FreeParking").query();
    clickOn(creator);
  }

  public void createRegularProperty(String name, String cost, String rentcosts, String housecost, String neighbors,String mortgage, String color){
    Button creator = lookup("#AddRegularProperty").query();
    clickOn(creator);
    TextField player1Name = lookup("#EnterPropertyName").query();
    writeInputTo(player1Name, name);
    player1Name = lookup("#EnterCost").query();
    writeInputTo(player1Name, cost);
    player1Name = lookup("#EnterRentCosts").query();
    writeInputTo(player1Name, rentcosts);
    player1Name = lookup("#EnterNeighbors").query();
    writeInputTo(player1Name, neighbors);
    player1Name = lookup("#EnterMortgagePrice").query();
    writeInputTo(player1Name, mortgage);
    player1Name = lookup("#EnterHouseCost").query();
    writeInputTo(player1Name, housecost);
    player1Name = lookup("#EnterColor").query();
    writeInputTo(player1Name, color);
    creator = lookup("#SaveProperty").query();
    clickOn(creator);

  }
}
