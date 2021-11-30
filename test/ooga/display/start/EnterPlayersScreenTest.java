package ooga.display.start;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
import ooga.model.game_handling.turn_manager.TurnManager;
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
   * This test tests to see if at the start menu (display index 0)
   * whether or not clicking on the start button makes the index
   * of List of Displays in DisplayManager go to 2 (PlayerName)
   */
  @Test
  public void clickStartEnterPlayerNames() {
    Button options = lookup("#Start").query();
    clickOn(options);
    assertEquals(2, dm.getCurrDisplayIndex());
  }

  /**
   * This test clicks start then clicks home
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
   * This test clicks start then clicks continue
   */
  @Test
  public void clickContinueFromPlayerNames() {
    Button options = lookup("#Start").query();
    clickOn(options);
    Button GoHome = lookup("#Continue").query();
    clickOn(GoHome);
    assertEquals(3, dm.getCurrDisplayIndex());
  }

  /**
   * This test clicks start then enters names
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
   * This test clicks start then enters names
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
}
