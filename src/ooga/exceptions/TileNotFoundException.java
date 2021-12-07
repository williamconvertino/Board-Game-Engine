package ooga.exceptions;

/**
 * An error to throw if a tile name was specified but not found in the board_manager.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class TileNotFoundException extends GameError {

  //The name of the tile that was attempted to be called.
  String tileName;

  /**
   * Constructs a new TileNotFoundException with the specified tile name.
   *
   * @param tileName
   */
  public TileNotFoundException(String tileName) {
    this.tileName = tileName;
  }

}
