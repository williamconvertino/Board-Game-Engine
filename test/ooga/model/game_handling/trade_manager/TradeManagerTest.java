package ooga.model.game_handling.trade_manager;

import ooga.model.data.player.Player;
import ooga.model.data.properties.Property;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.LinkedList;
import java.util.List;

public class TradeManagerTest {

    TradeManager tradeManager;
    Player player1;
    Player player2;
    Property p1;
    Property p2;
    Property p3;
    Property p4;

    @BeforeEach
    void setUp() {
        tradeManager = new TradeManager();
        player1 = new Player("p1");
        player2 = new Player("p2");
        p1 = new Property("Boardwalk", "Regular",1, new int[]{2,4,6}, 3, new LinkedList<>(), 5,"red");
        p2 = new Property("Park Place", "Regular",1, new int[]{2,4,6}, 3, new LinkedList<>(), 5,"red");
        p3 = new Property("New York Ave", "Regular",1, new int[]{2,4,6}, 3, new LinkedList<>(), 5,"red");
        p4 = new Property("St. Charles Place", "Regular",1, new int[]{2,4,6}, 3, new LinkedList<>(), 5,"red");
        player1.giveProperty(p1);
        player1.giveProperty(p3);
        player2.giveProperty(p2);
        player2.giveProperty(p4);
    }

    @Test
    void testProposeTradeValidTrade() {
        int offeringAmount = 500;
        int receivingAmount = 0;
        List<Property> offeringProperties = new LinkedList<Property>();
        List<Property> receivingProperties = new LinkedList<Property>();
        offeringProperties.add(p3);
        receivingProperties.add(p2);
        tradeManager.proposeTrade(player1, player2, offeringAmount, receivingAmount, offeringProperties, receivingProperties);
        Trade proposedTrade = new Trade(player1, player2, offeringAmount, receivingAmount, offeringProperties, receivingProperties);
        Trade actualProposedTrade = tradeManager.getTrade();
        assertEquals(proposedTrade.getOfferer(), actualProposedTrade.getOfferer());
        assertEquals(proposedTrade.getReceiver(), actualProposedTrade.getReceiver());
        assertEquals(proposedTrade.getProposedOfferedBalance(), actualProposedTrade.getProposedOfferedBalance());
        assertEquals(proposedTrade.getProposedReceivedBalance(), actualProposedTrade.getProposedReceivedBalance());
        assertEquals(proposedTrade.getProposedOfferedProperties(), actualProposedTrade.getProposedOfferedProperties());
        assertEquals(proposedTrade.getProposedReceivedProperties(), actualProposedTrade.getProposedReceivedProperties());
    }

    @Test
    void testProposeTradeInvalidTrade() {
        int offeringAmount = 1501;
        int receivingAmount = 0;
        List<Property> offeringProperties = new LinkedList<Property>();
        List<Property> receivingProperties = new LinkedList<Property>();
        offeringProperties.add(p3);
        receivingProperties.add(p2);
        tradeManager.proposeTrade(player1, player2, offeringAmount, receivingAmount, offeringProperties, receivingProperties);
        Trade actualProposedTrade = tradeManager.getTrade();
        assertEquals(actualProposedTrade, null);
    }

    @Test
    void testAcceptTradeWithNullTrade() {
        try {
            tradeManager.acceptTrade();
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    void testAcceptTradeWithValidProposedTrade() {
        int offeringAmount = 500;
        int receivingAmount = 0;
        List<Property> offeringProperties = new LinkedList<Property>();
        List<Property> receivingProperties = new LinkedList<Property>();
        offeringProperties.add(p3);
        receivingProperties.add(p2);
        tradeManager.proposeTrade(player1, player2, offeringAmount, receivingAmount, offeringProperties, receivingProperties);
        try {
            tradeManager.acceptTrade();
        }
        catch (Exception e) {
        }
        //money transferred correctly
        assertEquals(1000, player1.getBalance());
        assertEquals(2000, player2.getBalance());
        //properties transferred correctly
        assertTrue(player1.getProperties().contains(p2));
        assertTrue(player2.getProperties().contains(p3));
        assertFalse(player1.getProperties().contains(p3));
        assertFalse(player2.getProperties().contains(p2));
        //proposed trade reset correctly
        assertEquals(tradeManager.getTrade(), null);
    }

    @Test
    void testRefuseTradeWithProposedTrade() {
        int offeringAmount = 500;
        int receivingAmount = 0;
        List<Property> offeringProperties = new LinkedList<Property>();
        List<Property> receivingProperties = new LinkedList<Property>();
        offeringProperties.add(p3);
        receivingProperties.add(p2);
        tradeManager.proposeTrade(player1, player2, offeringAmount, receivingAmount, offeringProperties, receivingProperties);
        try {
            tradeManager.refuseTrade();
        }
        catch (Exception e) {
        }
        assertEquals(tradeManager.getTrade(), null);
    }

    @Test
    void testRefuseTradeWithNoProposedTrade() {
        try {
            tradeManager.refuseTrade();
        }
        catch (Exception e) {
            assertTrue(true);
        }
        assertEquals(tradeManager.getTrade(), null);
    }
}
