package ooga.model.game_handling.trade_manager;

import ooga.model.data.player.Player;
import ooga.model.data.properties.Property;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.LinkedList;
import java.util.List;


public class TradeTest {
    Player player1;
    Player player2;
    Property p1;
    Property p2;
    Property p3;
    Property p4;

    @BeforeEach
    void setUp() {
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
    void testIsValidatedWithValidTrade() {
        int offeringAmount = 500;
        int receivingAmount = 0;
        List<Property> offeringProperties = new LinkedList<Property>();
        List<Property> receivingProperties = new LinkedList<Property>();
        offeringProperties.add(p3);
        receivingProperties.add(p2);
        Trade trade = new Trade(player1, player2, offeringAmount, receivingAmount, offeringProperties, receivingProperties);
        assertTrue(trade.isValidated());
    }

    @Test
    void testIsValidatedWithInvalidMoneyTrade() {
        int offeringAmount = 1501;
        int receivingAmount = 0;
        List<Property> offeringProperties = new LinkedList<Property>();
        List<Property> receivingProperties = new LinkedList<Property>();
        offeringProperties.add(p3);
        receivingProperties.add(p2);
        Trade trade = new Trade(player1, player2, offeringAmount, receivingAmount, offeringProperties, receivingProperties);
        assertFalse(trade.isValidated());
    }

    @Test
    void testIsValidatedWithInvalidPropertyTrade() {
        int offeringAmount = 500;
        int receivingAmount = 0;
        List<Property> offeringProperties = new LinkedList<Property>();
        List<Property> receivingProperties = new LinkedList<Property>();
        Property randomProp = new Property("Random Property", "Regular",1, new int[]{2,4,6}, 3, new LinkedList<>(), 5,"red");
        offeringProperties.add(randomProp);
        receivingProperties.add(p2);
        Trade trade = new Trade(player1, player2, offeringAmount, receivingAmount, offeringProperties, receivingProperties);
        assertFalse(trade.isValidated());
    }

    @Test
    void testIsValidatedWithInvalidPlayerTrade() {
        int offeringAmount = 500;
        int receivingAmount = 0;
        List<Property> offeringProperties = new LinkedList<Property>();
        List<Property> receivingProperties = new LinkedList<Property>();
        offeringProperties.add(p3);
        receivingProperties.add(p2);
        player1.setActiveStatus(false);
        Trade trade = new Trade(player1, player2, offeringAmount, receivingAmount, offeringProperties, receivingProperties);
        assertFalse(trade.isValidated());
    }




}
