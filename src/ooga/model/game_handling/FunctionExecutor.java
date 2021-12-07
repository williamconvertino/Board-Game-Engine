package ooga.model.game_handling;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import ooga.display.communication.DisplayComm;
import ooga.exceptions.TileNotFoundException;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;
import ooga.model.die.Die;

/**
 * This class executes a variety of functions that change the game data. This is where the majority
 * of the logic updates will happen.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class FunctionExecutor {

  //The game data to change.
  private final GameData gamedata;

  //A method with which this class can end the current player's turn.
  private Method endTurn;

  //The die to use when rolling.
  private final Die myDie;

  //The display communication class.
  private final DisplayComm displayComm;

  /**
   * Creates a new FunctionExecutor and initializes it with the specified gamedata, die, and display
   * communication module.
   *
   * @param gameData    the gamedata to act on.
   * @param die         the die to use.
   * @param displayComm the display communication module for the current game.
   */
  public FunctionExecutor(GameData gameData, Die die, DisplayComm displayComm) {
    this.gamedata = gameData;
    this.myDie = die;
    this.displayComm = displayComm;
  }

  /**
   * Moves the player to the specified location.
   *
   * @param player   the player to move.
   * @param location the location at which the player should be moved.
   */
  public void movePlayerToIndex(Player player, Integer location) {
    gamedata.getBoard().movePlayerToIndex(player, location, false);
  }

  /**
   * Moves the specified player to the next tile with the given name.
   *
   * @param player   the player to move.
   * @param tileName the name of the tile to which the player should be moved.
   * @throws TileNotFoundException if the tile with the given name cannot be found.
   */
  public void movePlayerToTile(Player player, String tileName) throws TileNotFoundException {
    try {
      gamedata.getBoard().movePlayerToTile(player, tileName, false);
    } catch (Exception e) {
      displayComm.showException(e);
      throw e;
    }
  }

  /**
   * Advances the player to the nearest tile with the specified name. This executes any pass-through
   * commands of tiles that it advances through.
   *
   * @param player   the player to move.
   * @param tileName the name of the tile to which the player should be moved.
   * @throws TileNotFoundException if the tile with the given name cannot be found.
   */
  public void advancePlayerToTile(Player player, String tileName) throws TileNotFoundException {
    try {
      gamedata.getBoard().movePlayerToTile(player, tileName, true);
    } catch (Exception e) {
      displayComm.showException(e);
      throw e;
    }
  }

  /**
   * Advances the specified player to the nearest tile with a name matching any of the specified
   * names. This executes any pass-through commands of tiles that it advances through.
   *
   * @param player    the player to advance.
   * @param tileNames an array of the names of tiles that are acceptable.
   */
  public void advancePlayerToTile(Player player, String[] tileNames)
      throws TileNotFoundException {

    String closestTileName = Arrays.stream(tileNames).reduce(
            (a, b) ->
            {
              try {
                return gamedata.getBoard().findNextClosestTileIndex(player, a)
                    > gamedata.getBoard().findNextClosestTileIndex(player, b) ? a : b;
              } catch (TileNotFoundException e) {

              }
              return null;
            }
        )
        .get();

    if (closestTileName == null) {
      throw new TileNotFoundException("");
    }
    advancePlayerToTile(player, closestTileName);

  }

  /**
   * Moves a player forward by the specified number of spaces.
   *
   * @param player the player to move.
   * @param spaces the number of spaces to move.
   */
  public void movePlayerFd(Player player, Integer spaces) {
    gamedata.getBoard().movePlayerFd(player, spaces);
  }

  /**
   * Moves a player bakward by the specified number of spaces.
   *
   * @param player the player to move.
   * @param spaces the number of spaces to move.
   */
  public void movePlayerBk(Player player, Integer spaces) {
    gamedata.getBoard().movePlayerBk(player, spaces);
  }

  /**
   * Rolls the die and returns the result.
   *
   * @return the result of the roll.
   */
  public int rollDie() {
    return (myDie.roll());
  }

  /**
   * Gives the specified player a specified amount of money.
   *
   * @param player the player who is to receive the money.
   * @param amount the amount of money that player should receive.
   */
  public void addMoney(Player player, Integer amount) {
    player.addMoney(amount);
  }

  /**
   * Adds the specified amount of money to each player in the specified list.
   *
   * @param players a list of the players to whom the money should be added.
   * @param amount  the amount of money to add.
   */
  public void addMoney(List<Player> players, Integer amount) {
    for (Player p : players) {
      p.addMoney(amount);
    }
  }

  /**
   * Takes away a specified amount of money from the specified player and returns any debt that
   * player takes on.
   *
   * @param player the player who is to lose the money.
   * @param amount the amount of money that player should lose.
   */
  public void loseMoney(Player player, Integer amount) {
    player.addMoney(-1 * amount);
  }

  /**
   * Takes away a specified amount of money from the specified player and returns any debt that
   * player takes on.
   *
   * @param players the players who are to lose the money.
   * @param amount  the amount of money that each player should lose.
   */
  public void loseMoney(List<Player> players, Integer amount) {
    for (Player p : players) {
      p.addMoney(-1 * amount);
    }
  }

  /**
   * Sends the specified player to jail.
   *
   * @param player the player to send to jail.
   */
  public void goToJail(Player player) {
    player.setJailStatus(true);
    try {
      gamedata.getBoard().movePlayerToTile(player, "Jail", false);
    } catch (TileNotFoundException e) {
      displayComm.showException(e);
    }
  }

  /**
   * Executes the card with the specified name.
   *
   * @param player   the player who receives the card.
   * @param cardName the name of the card that should be executed.
   */
  @Deprecated
  public void executeCard(Player player, String cardName) {

  }

}