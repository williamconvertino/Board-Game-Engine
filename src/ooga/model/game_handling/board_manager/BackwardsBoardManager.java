package ooga.model.game_handling.board_manager;

import java.util.List;
import ooga.model.data.player.Player;
import ooga.model.data.tilemodels.TileModel;

/**
 * An implementation of the BoardManager class in which the players go backwards.
 *
 * @author William Convertino
 * @since 1.0.1
 */
public class BackwardsBoardManager extends OriginalBoardManager {

  /**
   * @see OriginalBoardManager#OriginalBoardManager(List)
   */
  public BackwardsBoardManager(List<TileModel> tiles) {
    super(tiles);
  }

  /**
   * @see OriginalBoardManager#movePlayerBk(Player, int)
   */
  @Override
  public void movePlayerBk(Player p, int spaces) {
    super.movePlayerFd(p, spaces);
  }

  /**
   * @see OriginalBoardManager#movePlayerFd(Player, int)
   */
  @Override
  public void movePlayerFd(Player p, int spaces) {
    super.movePlayerBk(p, spaces);
  }
}
