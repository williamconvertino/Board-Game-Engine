package ooga.model.data.tiles;

import ooga.model.data.player.Player;
import ooga.model.game_handling.commands.ActionSequence;

/**
 *  An implementation of the Tile class that executes an ActionSequence
 *  when passed through, and an ActionSequence when landed on.
 *
 * @author William Convertino
 *
 * @since 0.0.1
 */
public class ActionTile extends Tile {

  //The action sequence to execute when the player passes through this tile.
  private ActionSequence myPassThroughActions;

  //The action sequence to execute when the player lands on this tile.
  private ActionSequence myLandOnActions;

  /**
   * Constructs a new tile with the specified name.
   *
   * @param myName the name of the tile.
   */
  public ActionTile(String myName) {
    super(myName);
  }

  /**
   * Constructs a new ActionTile with the specified name, pass-through commands, and land-on commands.
   *
   * @param myName the name of the tile.
   * @param myPassThroughActions a list of commands to execute when the tile is passed through.
   * @param myLandOnActions a list of commands to execute when the tile is landed on.
   */
  public ActionTile(String myName, ActionSequence myPassThroughActions, ActionSequence myLandOnActions) {
    this(myName);
    this.myPassThroughActions = myPassThroughActions;
    this.myLandOnActions = myLandOnActions;
  }

  /**
   * @see Tile#executePassThrough(Player)
   */
  @Override
  public void executePassThrough(Player player) {
    myPassThroughActions.execute(player);
  }

  /**
   * @see Tile#executeLandOn(Player) 
   */
  @Override
  public void executeLandOn(Player player) {
    myLandOnActions.execute(player);
  }
}
