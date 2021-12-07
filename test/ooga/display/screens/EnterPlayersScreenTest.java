package ooga.display.screens;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.display.DisplayManager;
import org.junit.jupiter.api.Test;
import ooga.util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnterPlayersScreenTest extends DukeApplicationTest {
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

  /**
   * This test clicks screens then enters names
   */
  @Test
  public void modifyPlayerNames() {
    Button options = lookup("#Start").query();
    clickOn(options);
    TextField player1Name = lookup("#EnterPlayerName1").query();
    writeInputTo(player1Name, "p1");
    Button Continue = lookup("#Continue").query();
    clickOn(Continue);
    assertEquals("p1", dm.getGameData().getPlayers().get(0).getName());
  }

  /**
   * This test clicks screens then enters names
   */
  @Test
  public void modifyPlayerColor() {
    Button options = lookup("#Start").query();
    clickOn(options);
    ComboBox player1Color = lookup("#SelectColor1").query();
    select(player1Color, "YELLOW");
    Button Continue = lookup("#Continue").query();
    clickOn(Continue);
    assertEquals(Color.YELLOW, dm.getGameData().getPlayers().get(0).getColor());
  }

  /**
   * This test clicks on default monopoly game
   */
  @Test
  public void selectDefault(){
    Button options = lookup("#Start").query();
    clickOn(options);
    options = lookup("#variationButton").query();
    clickOn(options);
  }

  @Test
  public void selectFolder(){
    Button options = lookup("#Start").query();
    clickOn(options);
     options = lookup("#VariationLoader").query();
    clickOn(options);
  }
}
