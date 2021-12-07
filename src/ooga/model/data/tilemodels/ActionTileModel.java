package ooga.model.data.tilemodels;

import ooga.display.communication.DisplayComm;
import ooga.model.data.player.Player;
import ooga.model.game_handling.commands.ActionSequence;

/**
 * An implementation of the Tile class that executes an ActionSequence when passed through, and an
 * ActionSequence when landed on.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class ActionTileModel extends TileModel {

  //The action sequence to execute when the player passes through this tile.
  private ActionSequence myPassThroughActions;

  //The action sequence to execute when the player lands on this tile.
  private ActionSequence myLandOnActions;

  //The display communication module of this class.
  private DisplayComm myDisplayComm;

  //The description of this tile.
  private String myDescription;

  /**
   * @see TileModel#TileModel(String, String)
   */
  public ActionTileModel(String name, String type) {
    super(name, type);
  }

  /**
   * Constructs a new ActionTile with the specified name, pass-through commands, and land-on
   * commands.
   *
   * @param name               the name of the tile.
   * @param passThroughActions a list of commands to execute when the tile is passed through.
   * @param landOnActions      a list of commands to execute when the tile is landed on.
   */
  public ActionTileModel(String name, String description, ActionSequence passThroughActions,
      ActionSequence landOnActions, DisplayComm displayComm) {
    this(name, "Action");
    this.myPassThroughActions = passThroughActions;
    this.myLandOnActions = landOnActions;
    this.myDisplayComm = displayComm;
    this.myDescription = description;
  }

  /**
   * @see TileModel#executePassThrough(Player)
   */
  @Override
  public void executePassThrough(Player player) {
    if (myPassThroughActions != null) {
      myPassThroughActions.execute(player);
    }
  }

  /**
   * @see TileModel#executeLandOn(Player)
   */
  @Override
  public void executeLandOn(Player player) {
    if (myLandOnActions == null || myLandOnActions.isEmpty()) {
      return;
    }
    myDisplayComm.displayActionTile(this);
    myLandOnActions.execute(player);
  }

  /**
   * Returns the description of the tile.
   *
   * @return this tile's description.
   */
  public String getDescription() {
    return myDescription;
  }
}
