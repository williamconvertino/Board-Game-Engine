package ooga.model.data.tiles;

import ooga.model.data.player.Player;

public class EmptyTile extends Tile {

  /**
   * Constructs a new tile with the specified name.
   *
   * @param myName the name of the tile.
   */
  public EmptyTile(String myName) {
    super(myName);
  }

  /**
   * Constructs a new EmptyTile with the name "Empty."
   */
  public EmptyTile() {
    super("Empty");
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
