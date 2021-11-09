package model.data.collectables.properties;

import java.util.*;
import model.data.collectables.Collectable;
import model.data.game_data.Player;

/**
 * This class keeps track of the data associated with any given property.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public abstract class Property extends Collectable {

    /**
     * Default constructor
     */
    public Property() {
    }

    /**
     * 
     */
    private int baseRent;

    /**
     * 
     */
    private int houseCost;

    /**
     * 
     */
    private int numHouses;

    /**
     * 
     */
    private Player owner;

    /**
     * 
     */
    private List<Property> propertySet;

    /**
     * 
     */
    private int morgageValue;

    /**
     * 
     */
    private boolean isMorgaged;

    /**
     * 
     */
    public abstract void buyHouse();

    /**
     * 
     */
    public abstract void sellHouse();

    /**
     * @return
     */
    public abstract int getRent();

    /**
     * @param player
     */
    public abstract void setOwner(Player player);

    /**
     *
     */
    public abstract Player getOwner();

    /**
     * @return
     */
    public abstract boolean isMonopoly();

}