package ooga.model.data.tilemodels;

import static ooga.display.communication.DisplayStateSignaler.State.BUY_PROPERTY;
import static ooga.display.communication.DisplayStateSignaler.State.PAY_MONEY_TO_PLAYER;

import ooga.display.communication.DisplayComm;
import ooga.model.data.player.Player;
import ooga.model.data.properties.Property;
import ooga.model.game_handling.commands.ActionSequence;

/**
 * This class represents a tile corresponding to a property card. When a player lands on it, they
 * execute the landOnProperty sequence, which either makes them pay rent or allows them to purchase
 * the property.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class PropertyTileModel extends TileModel {

  //The property that this tile represents.
  private Property myProperty;

  //The action sequence to execute when this tile has been landed on.
  private ActionSequence landOnPropertySequence;

  //The display communication module of this model.
  private DisplayComm displayComm;

  /**
   * Constructs a new tile with the specified name.
   *
   * @param myName the name of the tile.
   */
  public PropertyTileModel(String myName) {
    super(myName, "Property");
  }

  /**
   * Constructs a new tile with the specified name.
   *
   * @param myName the name of the tile.
   * @param type   the type of the tile.
   */
  public PropertyTileModel(String myName, String type) {
    super(myName, type);
  }

  /**
   * Constructs a new tile with the specified name and property.
   *
   * @param name                   the name of the tile.
   * @param type                   the type of the tile.
   * @param property               the property associated with this tile.
   * @param landOnPropertySequence the action sequence to execute when this tile has been landed
   *                               on.
   */
  public PropertyTileModel(String name, String type, Property property,
      ActionSequence landOnPropertySequence, DisplayComm displayComm) {
    this(name, type);
    this.myProperty = property;
    this.landOnPropertySequence = landOnPropertySequence;
    this.displayComm = displayComm;
  }

  /**
   * Does nothing when a player passes through the tile.
   *
   * @param player the player who is passing through the tile.
   */
  @Override
  public void executePassThrough(Player player) {
    //Do Nothing.
  }

  /**
   * Selects the current property.
   *
   * @param player the player who landed on the tile.
   */
  @Override
  public void executeLandOn(Player player) {
    if (myProperty.getOwner() == player || myProperty.isMortgaged()) {
      return;
    }
    if (myProperty.getOwner() == Property.NULL_OWNER) {
      displayComm.signalState(BUY_PROPERTY);
      return;
    }
    player.addMoney(-1 * myProperty.getRentCost());
    myProperty.getOwner().addMoney(myProperty.getRentCost());
    displayComm.signalState(PAY_MONEY_TO_PLAYER);
  }

  public Property getProperty() {
    return myProperty;
  }


}