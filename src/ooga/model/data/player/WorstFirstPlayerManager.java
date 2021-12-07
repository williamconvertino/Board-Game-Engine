package ooga.model.data.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ooga.exceptions.NoRemainingPlayersException;

/**
 * An implementation of the PlayerManager class that makes the player with the least amount of money
 * roll each turn.
 *
 * @author William Convertino
 * @since 1.0.1
 */
public class WorstFirstPlayerManager extends OriginalPlayerManager {

  /**
   * @param myPlayers
   * @see PlayerManager#PlayerManager(List)
   */
  public WorstFirstPlayerManager(List<Player> myPlayers) {
    super(myPlayers);
  }

  /**
   * @see WorstFirstPlayerManager#getNextPlayer()
   */
  @Override
  public Player getNextPlayer() throws NoRemainingPlayersException {
    List<Player> possiblePlayers = new ArrayList<>(getActivePlayers());

    int minValue = possiblePlayers.stream()
        .min((a, b) -> a.getBalance() < b.getBalance() ? a.getBalance() : b.getBalance()).get()
        .getBalance();
    possiblePlayers.removeIf(e -> e.getBalance() != minValue);
    if (possiblePlayers.size() == 0) {
      throw new NoRemainingPlayersException();
    }
    return possiblePlayers.get(new Random().nextInt(possiblePlayers.size()));
  }

}
