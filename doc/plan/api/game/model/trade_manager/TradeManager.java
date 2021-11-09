package model.trade_manager;

import java.util.*;
import model.data.collectables.Collectable;
import model.data.game_data.Player;

/**
 * This class manages the trade interaction between two players. It has methods that allow both players to offer collectable items and money, and methods to either accept or refuse a deal.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public abstract class TradeManager {

    /**
     * Default constructor
     */
    public TradeManager() {
    }

    /**
     * 
     */
    private List<Collectable> playerOneOffer;

    /**
     * 
     */
    private List<Collectable> playerTwoOffer;

    /**
     * 
     */
    private Player playerOne;

    /**
     * 
     */
    private Player playerTwo;


    /**
     * @param p1 
     * @param p2
     */
    public abstract void initTrade(Player p1, Player p2);

    /**
     * @param c
     */
    public abstract void toggleTrade(Collectable c);

    /**
     * @param player 
     * @param amount
     */
    public abstract void offerAmount(Player player, int amount);

    /**
     * 
     */
    public abstract void acceptTrade();

    /**
     * 
     */
    public abstract void refuseTrade();

}