package ooga;

import java.util.List;
import javafx.scene.control.Alert.AlertType;
import ooga.display.communication.DisplayComm;
import ooga.display.communication.DisplayStateSignaler;
import ooga.display.communication.DisplayStateSignaler.State;
import ooga.display.communication.EventManager;
import ooga.exceptions.CardNotFoundException;
import ooga.exceptions.DeckNotFoundException;
import ooga.exceptions.PlayerWarning;
import ooga.exceptions.TileNotFoundException;
import ooga.model.data.cards.Card;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;
import ooga.model.data.player.PlayerManager;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.ActionTileModel;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.game_handling.FunctionExecutor;
import ooga.model.game_handling.TurnManager;
import ooga.model.game_handling.board_manager.BoardManager;
import ooga.model.game_initialization.GameDataInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class OriginalTest {

  public GameData gameData;

  public DisplayComm myDisplayComm;

  public FunctionExecutor functionExecutor;

  public EventManager eventManager;

  public TurnManager turnManager;

  public BoardManager board;


  public Player p1;
  public Player p2;
  public Player p3;
  public Player p4;


  @BeforeEach
  public void initialize() {

    myDisplayComm = new DisplayComm(null) {
      @Override
      public void showException(Exception e) {
        //Do nothing
      }
      @Override
      public void signalState(State state) {
        //Do nothing
      }

      @Override
      public void displayCard(Card card) {
        //Do nothing
      }
      @Override
      public void displayActionTile(ActionTileModel tile) {
        //Do nothing
      }
      @Override
      public void displayPlayerLose(Player player) {
        //Do nothing
      }
    };
    try {
      String variationName = GameManager.DEFAULT_VARIATION_NAME;
      gameData = (new GameDataInitializer()).generateGameData(variationName, myDisplayComm);
      functionExecutor = new FunctionExecutor(gameData, gameData.getDie(), myDisplayComm);
      turnManager = new TurnManager(gameData, functionExecutor, myDisplayComm);
      eventManager = new EventManager(turnManager);

      List<Player> playerList = gameData.getPlayers();
      p1 = playerList.get(0);
      p2 = playerList.get(1);
      p3 = playerList.get(2);
      p4 = playerList.get(3);

      board = gameData.getBoard();
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }
  }

  @Test
  void testInitialization () {


    //System.out.println(gameData.getBoard().getTiles());
  }


  @Test
  void testExceptions() {
    try {
      gameData.getDecks().getRandomDeck().getCard("bruh");
      assertTrue(false);
    } catch (CardNotFoundException c) {
      assertTrue(true);
    }
    try {
      gameData.getDecks().getDeck("bruh2");
      assertTrue(false);
    } catch ( DeckNotFoundException c) {
      assertTrue(true);
    }
    Player currentPlayer = gameData.getCurrentPlayer();
    try {
      currentPlayer.giveProperty( ((PropertyTileModel) (board.getTile("Boardwalk"))).getProperty() );
      turnManager.setSelectedTile(board.getTile("Boardwalk"));
      turnManager.buyHouse();
      turnManager.buyHouse(((PropertyTileModel) (board.getTile("Boardwalk"))).getProperty());
      turnManager.sellHouse();
      turnManager.sellHouse(((PropertyTileModel) (board.getTile("Boardwalk"))).getProperty());
      currentPlayer.giveProperty( ((PropertyTileModel) (board.getTile("Park Place"))).getProperty() );
      turnManager.sellHouse();
      turnManager.setSelectedTile(board.getTile("Go"));
      turnManager.buyHouse();
      turnManager.sellHouse();

      for (Player p: gameData.getPlayers()) {
        p.setActiveStatus(false);
      }
      turnManager.endTurn();

      assertTrue(true);
    } catch (TileNotFoundException c) {
      assertTrue(false);
    }



  }

}
