package ooga.model.game_handling.board_manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ooga.exceptions.InvalidFileFormatException;
import ooga.exceptions.TileNotFoundException;
import ooga.model.data.player.Player;
import ooga.model.data.tilemodels.TileModel;

/**
 * A class
 *
 * @author William Convertino
 */
public abstract class BoardManager {

  /**
   * Constructs a new BoardManager with the specified tiles.
   *
   * @param myTiles the tiles in this board.
   */
  public BoardManager(List<TileModel> myTiles) {

  }

  /**
   * Returns a list of all the board_manager's tiles.
   *
   * @return a list of all the board_manager's tiles.
   */
  public abstract List<TileModel> getTiles();

  /**
   * Returns the tile at the specified index.
   *
   * @param index the index of the desired tile.
   * @return the tile at the specified index.
   */
  public abstract TileModel getTileAtIndex(int index);

  /**
   * Moves a player forward the specified number of spaces.
   *
   * @param p      the player to move.
   * @param spaces the number of spaces to move the player.
   */
  public abstract void movePlayerFd(Player p, int spaces);

  /**
   * Moves a player backward the specified number of spaces.
   *
   * @param p      the player to move.
   * @param spaces the number of spaces to move the player.
   */
  public abstract void movePlayerBk(Player p, int spaces);

  /**
   * Moves a player to the specified tile index.
   *
   * @param p             the player to move.
   * @param index         the index of the desired tile.
   * @param doPassThrough states whether the player should pass through the intermediate tiles or
   *                      not.
   */
  public abstract void movePlayerToIndex(Player p, int index, boolean doPassThrough);

  /**
   * Moves a player to the next closest tile with the specified name.
   *
   * @param p             the player to move.
   * @param tileName      the name of the tile to which the player should move.
   * @param doPassThrough states whether the player should pass through the intermediate tiles or
   *                      not.
   */
  public void movePlayerToTile(Player p, String tileName, boolean doPassThrough)
      throws TileNotFoundException {
    int index = findNextClosestTileIndex(p, tileName);
    movePlayerToIndex(p, index, doPassThrough);
  }

  /**
   * Finds the index of the next closest tile, with the specified tile name, to the specified
   * player.
   *
   * @param p        the player to use as a starting point.
   * @param tileName the name of the tile to find.
   */
  public abstract int findNextClosestTileIndex(Player p, String tileName)
      throws TileNotFoundException;

  /**
   * Finds the next closest tile, with the specified tile name, to the specified player.
   *
   * @param p        the player to use as a starting point.
   * @param tileName the name of the tile to find.
   */
  public TileModel findNextClosestTile(Player p, String tileName) throws TileNotFoundException {
    int index = findNextClosestTileIndex(p, tileName);
    return getTileAtIndex(index);
  }

  /**
   * Returns the tile with the specified name that is closest to the specified location.
   *
   * @param location the location to use as reference.
   * @param tileName the name of the desired tile.
   * @return the tile with the specified name that is closest to the specified location.
   * @throws TileNotFoundException if the tile cannot be found.
   */
  public TileModel findNextClosestTile(int location, String tileName) throws TileNotFoundException {
    Player simPlayer = new Player("");
    simPlayer.setLocation(location);
    return findNextClosestTile(simPlayer, tileName);
  }

  /**
   * Returns the first tile found with the given name.
   *
   * @param tileName the name of the desired tile.
   * @return the first tile found with the given name.
   * @throws TileNotFoundException if the tile cannot be found.
   */
  public TileModel getTile(String tileName) throws TileNotFoundException {
    return findNextClosestTile(0, tileName);
  }

  /**
   * Returns the index of the first tile found with the given name.
   *
   * @param tileName the name of the desired tile.
   * @return the index of the first tile found with the given name.
   * @throws TileNotFoundException if the tile cannot be found.
   */
  public int getTileIndex(String tileName) throws TileNotFoundException {
    Player playerSim = new Player("");
    playerSim.setLocation(0);
    return findNextClosestTileIndex(playerSim, tileName);
  }

  /**
   * Returns a list of all the tiles with the specified type.
   *
   * @param type the type of tile to return.
   * @return a list of all the tiles with the specified type.
   */
  public List<TileModel> getAllTilesOfType(String type) {
    List<TileModel> tiles = new ArrayList<>(getTiles());
    tiles.removeIf(e -> !e.getMyType().equals(type));
    return tiles;
  }

  /**
   * Returns the closest tile to the given player of the given type.
   *
   * @param player the player to use for reference.
   * @param type   the type of tile to search for.
   * @return the closest tile to the given player of the given type.
   * @throws InvalidFileFormatException if the tile type cannot be found.
   */
  public TileModel getClosestTileOfType(Player player, String type)
      throws InvalidFileFormatException {
    List<TileModel> possibleTiles = getAllTilesOfType(type);
    TileModel desiredTile = possibleTiles.stream().reduce((a, b) ->
        {
          try {
            return
                getDistance(player.getLocation(), findNextClosestTileIndex(player, a.getName()))
                    < getDistance(player.getLocation(), findNextClosestTileIndex(player, b.getName()))
                    ? a : b;

          } catch (TileNotFoundException e) {
            return null;
          }
        }
    ).get();

    if (desiredTile == null) {
      throw new InvalidFileFormatException();
    }
    return desiredTile;
  }

  //Returns the one-way distance between two locations.
  private int getDistance(int location1, int location2) {
    if (location2 >= location1) {
      return location2 - location1;
    } else {
      return location2 + location1;
    }
  }

  /**
   * States whether or not the board has a tile with the specified name.
   *
   * @param tileName the name of the desired tile.
   * @return true if the board contains the tile, false if not.
   */
  protected boolean hasTile(String tileName) {
    List<TileModel> sortedTiles = new ArrayList<>();
    Collections.copy(getTiles(), sortedTiles);
    sortedTiles.removeIf(e -> !e.getName().equals(tileName));
    return (sortedTiles.size() != 0);
  }


}
