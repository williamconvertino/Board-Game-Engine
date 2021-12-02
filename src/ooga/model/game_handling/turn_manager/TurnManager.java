package ooga.model.game_handling.turn_manager;

import static ooga.display.communication.DisplayStateSignaler.State.GO_TO_JAIL;
import static ooga.display.communication.DisplayStateSignaler.State.PLAYER_WIN;
import static ooga.display.communication.DisplayStateSignaler.State.READY_TO_ROLL;

import java.util.ArrayList;
import java.util.List;
import ooga.display.communication.DisplayComm;
import ooga.display.communication.DisplayStateSignaler.State;
import ooga.exceptions.InsufficientBalanceException;
import ooga.exceptions.MaxHousesReachedException;
import ooga.exceptions.MaxRollsReachedException;
import ooga.exceptions.MortgageException;
import ooga.exceptions.NoHousesToSellException;
import ooga.exceptions.PropertyNotMonopolyException;
import ooga.exceptions.PropertyNotOwnedException;
import ooga.exceptions.PropertyOwnedException;
import ooga.exceptions.TileNotAPropertyException;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.game_handling.FunctionExecutor;

/**
 * This class manages the actions that a player can do on their turn.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public class TurnManager {

    /**The maximum number of rolls allowed before a player goes to jail.**/
    public static final int GLOBAL_MAX_ROLLS = 3;

    //The current game data.
    private GameData gameData;

    //The tile that the player has selected.
    private TileModel selectedTile;

    //The maximum number of rolls that a player can roll this turn.
    private int maxRolls;

    //The class that executes the functions.
    private FunctionExecutor functionExecutor;

    //The display communication class.
    private DisplayComm displayComm;

    //WIP
    private boolean commandActive;

    /**
     * Default constructor
     */
    public TurnManager(GameData gameData, FunctionExecutor functionExecutor, DisplayComm displayComm) {
        this.gameData = gameData;
        this.functionExecutor = functionExecutor;
        this.displayComm = displayComm;
        this.maxRolls = 1;
        this.commandActive = true;
    }

    /**
     * Starts the next turn.
     */
    public void endTurn() {
        if (gameData.getCurrentPlayer().getBalance() < 0) {
            gameData.getCurrentPlayer().setActiveStatus(false);
        }
        this.selectedTile = null;
        gameData.resetTurnData();
        gameData.setNextPlayer();
        List<Player> activePlayers = new ArrayList<>(gameData.getPlayers());
        activePlayers.removeIf(e->!e.isActive());
        if (activePlayers.size() == 1) {
            displayComm.signalState(PLAYER_WIN);
        } else {
            displayComm.signalState(READY_TO_ROLL);
        }
    }

    /**
     * Makes the player roll the dice, and move accordingly. If they roll doubles, they are allowed to roll an additional time.
     * If they roll 3 times, they are sent to jail.
     */
    public void roll() {

        //Check to see if the player has already rolled the max # of times, if so throw an error.
        if (gameData.getNumRolls() >= maxRolls) {
            displayComm.showException(new MaxRollsReachedException());
            return;
        }

        //Roll the die.
        int myRoll = gameData.getDie().roll();
        gameData.addRoll();

        //If the roll is the third of the turn, send the player to jail.
        if (gameData.getNumRolls() > GLOBAL_MAX_ROLLS) {
            functionExecutor.goToJail(gameData.getCurrentPlayer());
            displayComm.signalState(GO_TO_JAIL);
            endTurn();
            return;
        }

        //If the roll is a double, increase the maximum number of rolls by one.
        if (gameData.getDie().lastRollDouble()) {
            maxRolls++;
            displayComm.signalState(READY_TO_ROLL);
        }
        functionExecutor.movePlayerFd(gameData.getCurrentPlayer(), myRoll);
    }

    /**
     * Sets the currently selected tile to the specified tile, and signals
     * the display to show the property buttons.
     *
     * @param tile the tile to select.
     */
    public void setSelectedTile(TileModel tile) {
        this.selectedTile = tile;
    }

    /**
     * Makes the current player buy the property on the specified property.
     *
     * @param tile the tile whose property is purchased.
     */
    public void buyProperty(TileModel tile) {
        if (!(tile instanceof PropertyTileModel)) {
            displayComm.showException(new TileNotAPropertyException());
            return;
        }
        Property property = ((PropertyTileModel)tile).getProperty();
        if (property.getOwner() != Property.NULL_OWNER) {
            displayComm.showException(new PropertyOwnedException(property.getOwner().getName()));
            return;
        }
        if (property.getCost() > gameData.getCurrentPlayer().getBalance()) {
            displayComm.showException(new InsufficientBalanceException());
            return;
        }
        gameData.getCurrentPlayer().addMoney(-1 * property.getCost());
        gameData.getCurrentPlayer().giveProperty(property);
    }

    /**
     * Makes the current player buy a house on the selected property.
     *
     * @param property
     */
    public void buyHouse(Property property) {
        if (!gameData.getCurrentPlayer().getProperties().contains(property)) {
            displayComm.showException(new PropertyNotOwnedException());
            return;
        }
        if (!property.isMonopoly()) {
            displayComm.showException(new PropertyNotMonopolyException());
            return;
        }
        if (property.getHouseCost() > gameData.getCurrentPlayer().getBalance()) {
            displayComm.showException(new InsufficientBalanceException());
            return;
        }
        try {
          property.buyHouse();
          gameData.getCurrentPlayer().addMoney(-1 * property.getHouseCost());
        } catch (Exception e) {
            displayComm.showException(e);
        }
    }

    /**
     * Makes the current player sell a house on the selected property.
     *
     * @param property the property on which the houses should be sold.
     */
    public void sellHouse(Property property) {
        if (!gameData.getCurrentPlayer().getProperties().contains(property)) {
            displayComm.showException(new PropertyNotOwnedException());
            return;
        }
        try {
            property.sellHouse();
            gameData.getCurrentPlayer().addMoney(property.getHouseCost() / 2);
        } catch (Exception e) {
            displayComm.showException(e);
        }
    }
}