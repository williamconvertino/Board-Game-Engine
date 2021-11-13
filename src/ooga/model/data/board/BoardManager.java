package ooga.model.data.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ooga.exceptions.TileNotFoundException;
import ooga.model.data.player.Player;
import ooga.model.data.tiles.Tile;

public abstract class BoardManager {

  public BoardManager(List<Tile> myTiles) {

  }

  /**
   * Returns a list of all the board's tiles.
   *
   * @return a list of all the board's tiles.
   */
  public abstract List<Tile> getTiles();

  /**
   * Returns the tile at the specified index.
   *
   * @param index the index of the desired tile.
   * @return the tile at the specified index.
   */
  public abstract Tile getTileAtIndex(int index);

  /**
   * Moves a player forward the specified number of spaces.
   *
   * @param p the player to move.
   * @param spaces the number of spaces to move the player.
   */
  public abstract void movePlayerFd(Player p, int spaces);

  /**
   *  Moves a player backward the specified number of spaces.
   *
   * @param p the player to move.
   * @param spaces the number of spaces to move the player.
   */
  public abstract void movePlayerBk(Player p, int spaces);

  /**
   * Moves a player to the specified tile index.
   *
   * @param p the player to move.
   * @param index the index of the desired tile.
   * @param doPassThrough states whether the player should pass through the intermediate tiles or not.
   */
  public abstract void movePlayerToIndex(Player p, int index, boolean doPassThrough);

  /**
   * Moves a player to the next closest tile with the specified name.
   *
   * @param p the player to move.
   * @param tileName the name of the tile to which the player should move.
   * @param doPassThrough states whether the player should pass through the intermediate tiles or not.
   */
  public void movePlayerToTile(Player p, String tileName, boolean doPassThrough) throws TileNotFoundException{
    int index = findNextClosestTileIndex(p, tileName);
    movePlayerToIndex(p, index, doPassThrough);
  }

  /**
   * Finds the index of the next closest tile, with the specified tile name, to the specified player.
   *
   * @param p the player to use as a starting point.
   * @param tileName the name of the tile to find.
   */
  public abstract int findNextClosestTileIndex(Player p, String tileName) throws TileNotFoundException;

  /**
   * Finds the next closest tile, with the specified tile name, to the specified player.
   *
   * @param p the player to use as a starting point.
   * @param tileName the name of the tile to find.
   */
  public Tile findNextClosestTile(Player p, String tileName) throws TileNotFoundException {
    int index = findNextClosestTileIndex(p,tileName);
    return getTileAtIndex(index);
  }

  /**
   *
   */
  protected boolean hasTile(String tileName) {
    List<Tile> sortedTiles = new ArrayList<>();
    Collections.copy(getTiles(), sortedTiles);
    sortedTiles.removeIf(e->!e.getName().equals(tileName));
    return (sortedTiles.size() != 0);
  }

}
