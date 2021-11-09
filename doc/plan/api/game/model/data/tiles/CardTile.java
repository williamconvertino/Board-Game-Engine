package model.data.tiles;

import java.util.*;

/**
 * 
 */
public abstract class CardTile extends Tile {

    /**
     * Default constructor
     */
    public CardTile() {
    }

    /**
     * 
     */
    public Deck deck;

    /**
     * @param player 
     * @param functionManager
     */
    public abstract void executePassThrough(Player player, GameFunctionManager functionManager);

    /**
     * @param player 
     * @param functionManager
     */
    public abstract void executeLandOn(Player player, GameFunctionManager functionManager);

}