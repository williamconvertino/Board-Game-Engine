package ooga.model.data.tilemodels;

import ooga.model.data.player.Player;

/**
 * This class represents a tile on the board_manager.A player can pass through or land on a tile
 * when moving, which is reflected by the executePassThrough and executeLandOn methods.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public abstract class TileModel {

  private final String myName;
  private final String myType;

  /**
   * Constructs a new tile with the specified name and type.
   *
   * @param name the name of the tile.
   * @param type the type of the tile.
   */
  public TileModel(String name, String type) {
    this.myName = name;
    this.myType = type;
  }

  /**
   * Executes whenever a player passes through the tile.
   *
   * @param player the player who is passing through the tile.
   */
  public abstract void executePassThrough(Player player);

  /**
   * Executes whenever a player lands on a tile.
   *
   * @param player the player who landed on the tile.
   */
  public abstract void executeLandOn(Player player);

  /**
   * Returns the name of the tile.
   *
   * @return the name of the tile.
   */
  public String getName() {
    return myName;
  }

  /**
   * Returns the type of the tile.
   *
   * @return the type of the tile.
   */
  public String getMyType() {
    return myType;
  }

}