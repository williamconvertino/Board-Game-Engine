package ooga.model.game_handling.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import ooga.exceptions.InvalidFileFormatException;
import ooga.exceptions.TileNotFoundException;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.die.Die;
import ooga.model.game_handling.FunctionExecutor;

/**
 * A utility that can execute action commands formatted as strings.
 *
 * @author William Convertino
 * @since 1.0.1
 */
public class ActionSequenceHelperCommands {


  private final FunctionExecutor functionExecutor;
  private final GameData gameData;

  public ActionSequenceHelperCommands(FunctionExecutor functions, GameData gameData) {
    this.functionExecutor = functions;
    this.gameData = gameData;
  }


  public Integer multiply(Integer a1, Integer a2) {
    return a1 * a2;
  }

  public List<Player> getAllPlayers() {
    return new ArrayList<>(gameData.getPlayers());
  }

  public Player getCurrentPlayer() {
    return gameData.getCurrentPlayer();
  }

  public List<Player> getAllPlayersButCurrent() {
    List<Player> allPlayers = new ArrayList<>(gameData.getPlayers());
    allPlayers.remove(gameData.getCurrentPlayer());
    return (allPlayers);
  }

  public Integer getDieRoll()
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Die myDie = gameData.getDie().getClass().getConstructor().newInstance();
    return myDie.roll();
  }

  public Integer rollDieTimesValue(Integer value)
      throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    return getDieRoll() * value;
  }

  public Integer numberOfPlayersTimesValue(Integer value) {
    return gameData.getPlayers().size() * value;
  }

  public void loseMoneyForNumHouses(Player player, Integer amountPerHouse) {
    functionExecutor.loseMoney(player, player.getNumHouses() * amountPerHouse);
  }

  public void loseMoneyForNumHotels(Player player, Integer amountPerHotel) {
    functionExecutor.loseMoney(player, player.getNumHotels() * amountPerHotel);
  }

  public void advanceToPropertyAndPayX(Player player, String propertyName, Integer amount)
      throws TileNotFoundException {
    functionExecutor.movePlayerToTile(player, propertyName);
    TileModel currentTile = gameData.getBoard().getTileAtIndex(player.getLocation());
    if (currentTile instanceof PropertyTileModel) {
      Property currentProperty = ((PropertyTileModel) currentTile).getProperty();
      if (currentProperty.getOwner() != player
          && currentProperty.getOwner() != Property.NULL_OWNER) {
        for (int i = 1; i < amount; i++) {
          functionExecutor.loseMoney(player, currentProperty.getRentCost());
          functionExecutor.addMoney(currentProperty.getOwner(), currentProperty.getRentCost());
        }
      }
    }
  }

  public void advanceToTypeAndPayX(Player player, String type, Integer amount)
      throws InvalidFileFormatException, TileNotFoundException {
    TileModel myTile = gameData.getBoard().getClosestTileOfType(player, type);
    advanceToPropertyAndPayX(player, myTile.getName(), amount);
  }

  public void advanceToType(Player player, String type)
      throws TileNotFoundException, InvalidFileFormatException {
    TileModel myTile = gameData.getBoard().getClosestTileOfType(player, type);
    functionExecutor.advancePlayerToTile(player, myTile.getName());
  }


}
