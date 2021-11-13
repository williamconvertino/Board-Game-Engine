package ooga.model.data.tiles;

import java.lang.reflect.Method;
import java.util.List;
import javax.swing.Action;
import ooga.model.data.player.Player;

/**
 *
 */
public class ActionTile extends Tile {

  private List<Method> myPassThroughActions;

  private List<Method> myLandOnActions;

  /**
   * Constructs a new tile with the specified name.
   *
   * @param myName the name of the tile.
   */
  public ActionTile(String myName) {
    super(myName);
  }

  /**
   * Constructs a new ActionTile with the specified name, pass-through actions, and land-on actions.
   *
   * @param myName the name of the tile.
   * @param myPassThroughActions a list of actions to execute when the tile is passed through.
   * @param myLandOnActions a list of actions to execute when the tile is landed on.
   */
  public ActionTile(String myName, List<Method> myPassThroughActions, List<Method> myLandOnActions) {
    this(myName);
    this.myPassThroughActions = myPassThroughActions;
    this.myLandOnActions = myLandOnActions;
  }

  @Override
  public void executePassThrough(Player player) {

  }

  @Override
  public void executeLandOn(Player player) {

  }
}
