package ooga.model.game_handling.turn_manager;

import ooga.display.communication.DisplayComm;
import ooga.display.communication.DisplayStateSignaler.State;
import ooga.exceptions.InsufficientBalanceException;
import ooga.exceptions.MaxRollsReachedException;
import ooga.exceptions.PropertyOwnedException;
import ooga.model.data.gamedata.GameData;
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
        this.selectedTile = null;
        gameData.resetTurnData();
        gameData.setNextPlayer();
        displayComm.signalDisplay(State.START_TURN);
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
        if (gameData.getNumRolls() > 3) {
            functionExecutor.goToJail(gameData.getCurrentPlayer());
            endTurn();
            return;
        }

        //If the roll is a double, increase the maximum number of rolls by one.
        if (gameData.getDie().lastRollDouble()) {
            maxRolls++;
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
        displayComm.signalTile(tile);
        if (!(tile instanceof PropertyTileModel)) {
            return;
        }

        Property selectedProperty = ((PropertyTileModel)tile).getProperty();

        if (selectedProperty.getOwner() == Property.NULL_OWNER) { //TODO: check that the player is on the tile.
            displayComm.signalDisplay(State.BUY_PROPERTY);
            return;
        }

        if (gameData.getCurrentPlayer().getProperties().contains(selectedProperty)) {
            if (selectedProperty.isMortgaged()) {
                displayComm.signalDisplay(State.UNMORTGAGE_PROPERTY);
            } else if (selectedProperty.isMonopoly()) {
                displayComm.signalDisplay(State.BUY_HOUSE);
            } else {
                displayComm.signalDisplay(State.MORTGAGE_PROPERTY);
            }
        }

    }


    public void buyProperty(Property property) {
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


}