package ooga.model.game_handling.trade_manager;

import java.util.List;
import ooga.model.data.player.Player;
import ooga.model.data.properties.Property;

/**
 * This class represents a trade between two players. It has methods that validate whether this
 * trade is a feasible trade to make.
 *
 * @author Jordan Castleman
 * @since 0.0.1
 */
public class Trade {

  private final Player offerer;
  private final Player receiver;
  private final int proposedOfferedBalance;
  private final List<Property> proposedOfferedProperties;
  private final int proposedReceivedBalance;
  private final List<Property> proposedReceivedProperties;

  public Trade(Player offering, Player receiving, int offeringAmount, int receivingAmount,
      List<Property> offeringProperties, List<Property> receivingProperties) {
    offerer = offering;
    receiver = receiving;
    proposedOfferedBalance = offeringAmount;
    proposedReceivedBalance = receivingAmount;
    proposedOfferedProperties = offeringProperties;
    proposedReceivedProperties = receivingProperties;
  }

  /**
   * Method to validate whether the offerer and receiver are both capable of making this trade.
   *
   * @return- a boolean representing the validity of this trade.
   */
  public boolean isValidated() {
    return (offerer.isActive() && receiver.isActive()
        && validateBalance(offerer, proposedOfferedBalance) && validateBalance(receiver,
        proposedReceivedBalance)
        && validateProperties(offerer, proposedOfferedProperties) && validateProperties(receiver,
        proposedReceivedProperties));
  }

  /**
   * Method to validate whether a player has sufficient funds to complete this trade.
   *
   * @param player- the player involved in this trade being checked
   * @param amount- the amount of money @param player must have to be able to complete the trade
   * @return- a boolean representing whether the player has sufficient funding to complete the
   * trade.
   */
  private boolean validateBalance(Player player, int amount) {
    return (player.getBalance() > amount);
  }

  /**
   * Method to validate whether a player has sufficient properties to complete this trade.
   *
   * @param player-     the player involved in this trade being checked
   * @param properties- the properties @param player must have to be able to complete the trade
   * @return- a boolean representing whether the player has sufficient properties to complete the
   * trade.
   */
  private boolean validateProperties(Player player, List<Property> properties) {
    for (Property property : properties) {
      if (!player.getProperties().contains(property)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Method to get the player who offered this trade.
   *
   * @return- the player who offered this trade.
   */
  public Player getOfferer() {
    return offerer;
  }

  /**
   * Method to get the player on the receiving end of this trade.
   *
   * @return- the player on the receiving end of this trade.
   */
  public Player getReceiver() {
    return receiver;
  }

  /**
   * Method to get how much money the player offering the trade is paying.
   *
   * @return- how much money the player offering the trade is paying.
   */
  public int getProposedOfferedBalance() {
    return proposedOfferedBalance;
  }

  /**
   * Method to get how much money the player receiving the trade is paying.
   *
   * @return- how much money the player receiving the trade is paying.
   */
  public int getProposedReceivedBalance() {
    return proposedReceivedBalance;
  }

  /**
   * Method to get what properties the player offering the trade is giving.
   *
   * @return- what properties the player offering the trade is giving.
   */
  public List<Property> getProposedOfferedProperties() {
    return proposedOfferedProperties;
  }

  /**
   * Method to get what properties the player receiving the trade is giving.
   *
   * @return- what properties the player receiving the trade is giving.
   */
  public List<Property> getProposedReceivedProperties() {
    return proposedReceivedProperties;
  }
}
