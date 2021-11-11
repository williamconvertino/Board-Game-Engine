package ooga.model.data.tiles;

import ooga.model.data.collectables.Deck;
import ooga.model.data.game_data.Player;
import ooga.model.game_handling.turn_manager.GameFunctionManager;

/**
 * This class represents a tile that corresponds to a specific deck of cards. When a player lands on this tile, they draw a card from the deck and execute that card.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
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
    private Deck deck;

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