package ooga.model.game_handling.board_manager;

import java.util.List;
import ooga.exceptions.TileNotFoundException;
import ooga.model.data.player.Player;
import ooga.model.data.tilemodels.TileModel;
import ooga.util.ImmutTool;

/**
 * An implementation of the BoardManager class in which the tiles are arranged in a circular
 * pattern. When a player reaches the last tile, they then move back to the first tile.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class OriginalBoardManager extends BoardManager {

  //A list of the board_manager's tiles.
  private final List<TileModel> myTiles;

  /**
   * Constructs a new OriginalBoardManager with the specified tiles.
   *
   * @param tiles
   */
  public OriginalBoardManager(List<TileModel> tiles) {
    super(tiles);
    myTiles = tiles;
  }

  /**
   * @see BoardManager#getTiles()
   */
  @Override
  public List<TileModel> getTiles() {
    return ImmutTool.getImmutableList(myTiles);
  }

  /**
   * @see BoardManager#getTileAtIndex(int)
   */
  @Override
  public TileModel getTileAtIndex(int index) {
    return myTiles.get(index);
  }

  /**
   * Moves the player forward the specified number of spaces. This triggers pass-through events.
   *
   * @param p      the player to move.
   * @param spaces the number of spaces to move the player.
   */
  @Override
  public void movePlayerFd(Player p, int spaces) {

    int index = p.getLocation();

    //Execute the pass through functions of each tile the player passes.
    for (int t = 0; t < spaces - 1; t++) {
      index = (index + 1) % myTiles.size();
      p.setLocation(index);
      myTiles.get(index).executePassThrough(p);
    }

    //Execute the land function of the tile the player lands on.
    index = (index + 1) % myTiles.size();
    p.setLocation(index);
    myTiles.get(index).executeLandOn(p);

  }

  /**
   * Moves the player forward the specified number of spaces. This does NOT trigger pass-through
   * events.
   *
   * @param p      the player to move.
   * @param spaces the number of spaces to move the player.
   */
  @Override
  public void movePlayerBk(Player p, int spaces) {

    int index = p.getLocation();

    //Execute the pass through functions of each tile the player passes.
    for (int t = 0; t < spaces - 1; t++) {
      index = index - 1;
      if (index < 0) {
        index = myTiles.size() - 1;
      }
      p.setLocation(index);
    }

    //Execute the land function of the tile the player lands on.
    index = index - 1;
    if (index < 0) {
      index = myTiles.size() - 1;
    }
    p.setLocation(index);
    myTiles.get(index).executeLandOn(p);
  }

  /**
   * @see BoardManager#movePlayerToIndex(Player, int, boolean)
   */
  @Override
  public void movePlayerToIndex(Player p, int index, boolean doPassThrough) {

    if (doPassThrough) {
      //If doPassThrough is enabled, find the number of spaces to move and execute the movePlayerFd command.
      int spaces = index - p.getLocation();
      if (spaces < 0) {
        spaces = myTiles.size() + spaces;
      }
      movePlayerFd(p, spaces);
    } else {
      //Otherwise, move the player to the specified index and execute that tile's landOn command.
      p.setLocation(index);
      myTiles.get(index).executeLandOn(p);
    }
  }

  /**
   * @see BoardManager#findNextClosestTileIndex(Player, String)
   */
  @Override
  public int findNextClosestTileIndex(Player p, String tileName) throws TileNotFoundException {

    int index = p.getLocation();

    //Find the next closest tile with the specified name.
    while (!myTiles.get(index).getName().equals(tileName)) {
      index = (index + 1) % myTiles.size();

      //If the whole board_manager has been checked, throw an error.
      if (index == p.getLocation()) {
        if (myTiles.get(index).getName().equals(tileName)) {
          return index;
        } else {
          throw new TileNotFoundException(tileName);
        }
      }
    }
    return index;
  }


}
