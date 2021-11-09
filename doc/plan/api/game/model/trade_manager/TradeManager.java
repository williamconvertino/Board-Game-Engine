package model.trade_manager;

import java.util.*;

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
    public List<Collectable> playerOneOffer;

    /**
     * 
     */
    public List<Collectable> playerTwoOffer;

    /**
     * 
     */
    public Player playerOne;

    /**
     * 
     */
    public Player playerTwo;


    /**
     * @param p1 
     * @param p2
     */
    public abstract void initTrade(Player p1, Player p2);

    /**
     * @param Collectable
     */
    public abstract void toggleTrade(void Collectable);

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