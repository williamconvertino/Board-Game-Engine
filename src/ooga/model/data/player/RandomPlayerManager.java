package ooga.model.data.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ooga.exceptions.NoRemainingPlayersException;

public class RandomPlayerManager extends OriginalPlayerManager {

  /**
   * @param myPlayers
   * @see PlayerManager#PlayerManager(List)
   */
  public RandomPlayerManager(List<Player> myPlayers) {
    super(myPlayers);
  }

  /**
   * @see PlayerManager#getNextPlayer()
   */
  @Override
  public Player getNextPlayer() throws NoRemainingPlayersException {
    List<Player> possiblePlayers = new ArrayList<>(getActivePlayers());
    if (possiblePlayers.size() == 0) {
      throw new NoRemainingPlayersException();
    }
    return possiblePlayers.get(new Random().nextInt(possiblePlayers.size()));
  }
}
