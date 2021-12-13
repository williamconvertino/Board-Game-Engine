package ooga.display.screens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.concurrent.TimeUnit;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
  public void clickStartEnterPlayerNames(){
    try{
      Button options = lookup("#Start").query();
      clickOn(options);
      Button creator = lookup("#CreateYourOwn").query();
      clickOn(creator);
      TextField player1Name = lookup("#EnterGameName").query();
      writeInputTo(player1Name, "monopolytestgame");
      Button start = lookup("#CreateNewGame").query();
      clickOn(start);
      createFreeParking();
      createRegularProperty("Casey Cavern","400","50,200,600,1400,1700,2000","200","Will Walkway","175","green");
      createChance();
      createCommunityChest();
      createRegularProperty("Will Walkway","350","35,175,500,1100,1300,1500","200","Casey Cavern","200","green");
      createIncomeTax();
      createRailroadProperty("Reading Railroad","200","25,50,100,200","Pennsylvania Railroad, Short Line, B&O Railroad","100","null");
      createFreeParking();
      createJail();
      createGoToJail();
      createRegularProperty("Aaric Avenue","600","35,175,500,1100,1300,1500","200","Jordan Junction, Henry Highway","200","blue");
      createCommunityChest();
      createCommunityChest();
      createCommunityChest();
      createRegularProperty("Jordan Junction","550","35,175,500,1100,1300,1500","200","Aaric Avenue, Henry Highway","200","blue");
      createRegularProperty("Henry Highway","650","35,175,500,1100,1300,1500","200","Jordan Junction, Aaric Avenue","200","blue");
      createFreeParking();
      createLuxuryTax();
      createFreeParking();
      createRailroadProperty("Short Line","200","25,50,100,200","Pennsylvania Railroad, B&O Railroad, Reading Railroad","100","null");
      createIncomeTax();
      createLuxuryTax();
      createIncomeTax();
      createLuxuryTax();
      createIncomeTax();
      createLuxuryTax();
      createIncomeTax();
      createLuxuryTax();
      createIncomeTax();
      createUtilitiesProperty("Duke Energy","200","50,100","Solar Farm","100","null");
      createIncomeTax();
      createLuxuryTax();
      createIncomeTax();
      createLuxuryTax();
      createUtilitiesProperty("Solar Farm","200","50,100","Duke Energy","100","null");
      createLuxuryTax();
      createIncomeTax();
      createLuxuryTax();
      createIncomeTax();
      creator = lookup("#SetGame").query();
      clickOn(creator);
      assertEquals(2, dm.getCurrDisplayIndex());

      creator = lookup("#Continue").query();
      clickOn(creator);
      assertNotEquals(2, dm.getCurrDisplayIndex());

    }
    catch(Exception e){
      e.printStackTrace();
    }

  }

  public void createFreeParking(){
    Button creator = lookup("#FreeParking").query();
    clickOn(creator);
  }

  public void createJail(){
    Button creator = lookup("#Jail").query();
    clickOn(creator);
  }

  public void createGoToJail(){
    Button creator = lookup("#GoToJail").query();
    clickOn(creator);
  }

  public void createLuxuryTax(){
    Button creator = lookup("#LuxuryTax").query();
    clickOn(creator);
  }

  public void createIncomeTax(){
    Button creator = lookup("#IncomeTax").query();
    clickOn(creator);
  }

  public void createChance(){
    Button creator = lookup("#Chance").query();
    clickOn(creator);
  }

  public void createCommunityChest(){
    Button creator = lookup("#CommunityChest").query();
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

  public void createRailroadProperty(String name, String cost, String rentcosts, String neighbors,String mortgage, String image){
    Button creator = lookup("#AddRailroadProperty").query();
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
    player1Name = lookup("#EnterImage").query();
    writeInputTo(player1Name, image);
    creator = lookup("#SaveProperty").query();
    clickOn(creator);
  }

  public void createUtilitiesProperty(String name, String cost, String rentcosts, String neighbors,String mortgage, String image){
    Button creator = lookup("#AddUtilitiesProperty").query();
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
    player1Name = lookup("#EnterImage").query();
    writeInputTo(player1Name, image);
    creator = lookup("#SaveProperty").query();
    clickOn(creator);
  }
}
