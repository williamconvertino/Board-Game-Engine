package ooga.model.data.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ooga.exceptions.NoRemainingPlayersException;
import ooga.util.ImmutTool;

/**
 * An implementation of the PlayerManager class that has players take their turns in a fixed loop.
 * In this loop, each player takes one turn and then must wait for every other player to go.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class OriginalPlayerManager extends PlayerManager {

  //Keeps track of all the players in the game.
  private final List<Player> myPlayers;

  //Keeps track of the index of the current active player.
  private int nextPlayerIndex;

  /**
   * @see PlayerManager#PlayerManager(List)
   */
  public OriginalPlayerManager(List<Player> myPlayers) {
    super(myPlayers);
    this.myPlayers = myPlayers;
    Collections.shuffle(myPlayers);
    nextPlayerIndex = 0;
  }

  /**
   * @see PlayerManager#getNextPlayer()
   */
  @Override
  public Player getNextPlayer() throws NoRemainingPlayersException {

    //If there are no active players remaining, throw an exception.
    if (!existsRemainingPlayers()) {
      throw new NoRemainingPlayersException();
    }

    //Search for the next active player in the player list.
    while (!myPlayers.get(nextPlayerIndex).isActive()) {
      nextPlayerIndex = (nextPlayerIndex + 1) % myPlayers.size();
    }

    //Return that player and set the next player.
    Player nextPlayer = myPlayers.get(nextPlayerIndex);
    nextPlayerIndex = (nextPlayerIndex + 1) % myPlayers.size();
    //System.out.println(nextPlayerIndex);

    return nextPlayer;
  }

  /**
   * @see PlayerManager#getPlayers()
   */
  @Override
  public List<Player> getPlayers() {
    return ImmutTool.getImmutableList(myPlayers);
  }


  /**
   * @see PlayerManager#getActivePlayers()
   */
  @Override
  public List<Player> getActivePlayers() {
    List<Player> activePlayers = new ArrayList<>(myPlayers);
    activePlayers.removeIf(e -> !e.isActive());
    return activePlayers;
  }


}
