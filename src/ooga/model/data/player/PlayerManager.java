package ooga.model.data.player;

import java.util.List;
import ooga.exceptions.NoRemainingPlayersException;

/**
 * This class keeps track of the players in the game, and determines the order in which they take
 * their turns.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public abstract class PlayerManager {

  /**
   * Constructs a new PlayerManager with the specified players.
   *
   * @param myPlayers a list of the players in the game.
   */
  public PlayerManager(List<Player> myPlayers) {

  }

  /**
   * Returns the next player in the player order.
   *
   * @return the next player in the player order.
   */
  public abstract Player getNextPlayer() throws NoRemainingPlayersException;

  /**
   * Returns a list of all the players in the game.
   *
   * @return a list of all the players in the game.
   */
  public abstract List<Player> getPlayers();

  /**
   * Returns a list of all the active players in the game.
   *
   * @return a list of all the active players in the game.
   */
  public abstract List<Player> getActivePlayers();

  /**
   * Finds if there are any players remaining in the game.
   *
   * @return true if there still active players in the game, false otherwise.
   */
  protected boolean existsRemainingPlayers() {
    return (getActivePlayers().size() > 0);
  }


}
