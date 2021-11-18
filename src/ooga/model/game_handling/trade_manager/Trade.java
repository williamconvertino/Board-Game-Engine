package ooga.model.game_handling.trade_manager;

import ooga.exceptions.TradeException;
import ooga.model.data.player.Player;
import ooga.model.data.properties.Property;

import java.util.List;

public class Trade {

    private Player offerer;
    private Player receiver;
    private int proposedOfferedBalance;
    private List<Property> proposedOfferedProperties;
    private int proposedReceivedBalance;
    private List<Property> proposedReceivedProperties;

    public Trade(Player offering, Player receiving, int offeringAmount, int receivingAmount, List<Property> offeringProperties, List<Property> receivingProperties) {
        offerer = offering;
        receiver = receiving;
        proposedOfferedBalance = offeringAmount;
        proposedReceivedBalance = receivingAmount;
        proposedOfferedProperties = offeringProperties;
        proposedReceivedProperties = receivingProperties;
    }

    public boolean isValidated() throws TradeException {
        return (offerer.isActive() && receiver.isActive() && validateBalance(offerer, proposedOfferedBalance) && validateBalance(receiver, proposedReceivedBalance) && validateProperties(offerer, proposedOfferedProperties) && validateProperties(receiver, proposedReceivedProperties));
    }

    public boolean validateBalance(Player player, int amount) throws TradeException {
        boolean ret = true;
        if (player.getBalance() < amount) {
            ret = false;
            throw new TradeException();
        }
        return ret;
    }

    public boolean validateProperties(Player player, List<Property> properties) throws TradeException {
        boolean ret = true;
        for (Property property: properties) {
            if (!player.getProperties().contains(property)) {
                ret = false;
                throw new TradeException();
            }
        }
        return ret;
    }

    public Player getOfferer() {
        return offerer;
    }

    public Player getReceiver() {
        return receiver;
    }

    public int getProposedOfferedBalance() {
        return proposedOfferedBalance;
    }

    public int getProposedReceivedBalance() {
        return proposedReceivedBalance;
    }

    public List<Property> getProposedOfferedProperties() {
        return proposedOfferedProperties;
    }

    public List<Property> getProposedReceivedProperties() {
        return proposedReceivedProperties;
    }
}
