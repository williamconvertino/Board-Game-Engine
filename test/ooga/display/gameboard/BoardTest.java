package ooga.display.gameboard;

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
import ooga.model.game_handling.turn_manager.TurnManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ooga.util.DukeApplicationTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BoardTest extends DukeApplicationTest {
    public DisplayManager dm;

    @Override
    public void start(Stage stage) {
        dm = new DisplayManager(stage);
    }

    /**
     * This test clicks roll dice
     */
    @Test
    public void clickRollDice() {
        Button start = lookup("Start").query();
        clickOn(start);
        Button continueButton = lookup("Continue").query();
        clickOn(continueButton);
        Button rollDice = lookup("#RollDice").query();
        int prevLocation = dm.getGameData().getCurrentPlayer().getLocation();
        int playerIndex = dm.getGameData().getPlayers().indexOf(dm.getGameData().getCurrentPlayer());
        clickOn(rollDice);
        int newLocation = dm.getGameData().getPlayers().get(playerIndex).getLocation();
        Assertions.assertNotEquals(prevLocation, newLocation);
    }

    /**
     * This test tests end turn button
     */
    @Test
    void clickEndTurn() {
        Button start = lookup("Start").query();
        clickOn(start);
        Button continueButton = lookup("Continue").query();
        clickOn(continueButton);
        Button rollDice = lookup("#RollDice").query();
        clickOn(rollDice);
        int prevPlayerIndex = dm.getGameData().getPlayers().indexOf(dm.getGameData().getCurrentPlayer());
        Button endTurn = lookup("#EndTurn").query();
        clickOn(endTurn);
        int currentPlayerIndex = dm.getGameData().getPlayers().indexOf(dm.getGameData().getCurrentPlayer());
        Assertions.assertNotEquals(prevPlayerIndex, currentPlayerIndex);
    }
}
