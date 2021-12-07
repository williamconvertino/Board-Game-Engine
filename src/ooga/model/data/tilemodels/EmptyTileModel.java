package ooga.model.data.tilemodels;

import ooga.model.data.player.Player;

/**
 * An implementation of the Tile class with no functionality. This is intended to be used for board
 * generation when not enough tiles are specified in the board's creation.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class EmptyTileModel extends TileModel {

  /**
   * Constructs a new tile with the specified name.
   *
   * @param myName the name of the tile.
   */
  public EmptyTileModel(String myName) {
    super(myName, "Empty");
  }

  /**
   * Does nothing when a player passes through the tile.
   *
   * @param player the player who is passing through the tile.
   */
  @Override
  public void executePassThrough(Player player) {
    //Do nothing.
  }

  /**
   * Does nothing when a player lands on the tile.
   *
   * @param player the player who landed on the tile.
   */
  @Override
  public void executeLandOn(Player player) {
    //Do nothing.
  }
}
