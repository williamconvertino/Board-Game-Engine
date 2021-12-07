package ooga.model.game_handling.trade_manager;

import java.util.List;
import ooga.exceptions.TradeException;
import ooga.model.data.player.Player;
import ooga.model.data.properties.Property;

/**
 * This class manages the trade interaction between two players. It has methods that allow both
 * players to offer collectable items and money, and methods to either accept or refuse a deal.
 *
 * @author Jordan Castleman
 * @since 0.0.1
 */
public class TradeManager {

  //The current trade
  public Trade trade;

  public TradeManager() {
    trade = null;
  }

  public Trade getTrade() {
    return trade;
  }

  /**
   * Method to propose a trade from one player to another.  Trades are comprised of money and or
   * properties.
   *
   * @param offering-            the player offering the trade.
   * @param receiving-           the player the trade is being offered to.
   * @param offeringAmount-      the amount of money the offerer is proposing to give the receiver.
   * @param receivingAmount-     the amount of money the offerer is proposing to get from the
   *                             receiver.
   * @param offeringProperties-  the properties the offerer is proposing to give to the receiver.
   * @param receivingProperties- the properties the offerer is proposing to get from the receiver.
   */
  public void proposeTrade(Player offering, Player receiving, int offeringAmount,
      int receivingAmount, List<Property> offeringProperties, List<Property> receivingProperties) {
    Trade proposedTrade = new Trade(offering, receiving, offeringAmount, receivingAmount,
        offeringProperties, receivingProperties);
    if (proposedTrade.isValidated()) {
      trade = proposedTrade;
    } else {
      trade = null;
    }
  }

  /**
   * Method to accept a trade. Resets the current trade to null after the trade has been executed.
   *
   * @throws TradeException- thrown if no trade has been proposed.
   */
  public void acceptTrade() throws TradeException {
    if (trade == null) {
      throw new TradeException();
    } else {
      transferFunds();
      transferProperties();
      trade = null;
    }
  }

  /**
   * Method to refuse a trade that resets the current trade to null.
   */
  public void refuseTrade() throws TradeException {
    if (trade == null) {
      throw new TradeException();
    } else {
      trade = null;
    }
  }

  /**
   * Method to transfer money between the offerer and receiver as specified in the trade.
   */
  private void transferFunds() {
    //send money to receiver
    trade.getOfferer().addMoney(trade.getProposedOfferedBalance() * -1);
    trade.getReceiver().addMoney(trade.getProposedOfferedBalance());
    //send money to offerer
    trade.getReceiver().addMoney(trade.getProposedReceivedBalance() * -1);
    trade.getOfferer().addMoney(trade.getProposedReceivedBalance());
  }

  /**
   * Method to transfer properties between the offerer and receiver as specified in the trade.
   */
  private void transferProperties() {
    List<Property> toReceiver = trade.getProposedOfferedProperties();
    for (Property property : toReceiver) {
      trade.getOfferer().removeProperty(property);
      trade.getReceiver().giveProperty(property);
    }
    List<Property> toOfferer = trade.getProposedReceivedProperties();
    for (Property property : toOfferer) {
      trade.getReceiver().removeProperty(property);
      trade.getOfferer().giveProperty(property);
    }
  }


}