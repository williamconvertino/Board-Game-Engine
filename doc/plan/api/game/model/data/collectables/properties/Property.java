package model.data.collectables.properties;

import java.util.*;

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
    public int baseRent;

    /**
     * 
     */
    public int houseCost;

    /**
     * 
     */
    public int numHouses;

    /**
     * 
     */
    public Player owner;

    /**
     * 
     */
    public List<Property> propertySet;

    /**
     * 
     */
    public int morgageValue;

    /**
     * 
     */
    public boolean isMorgaged;

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
     * @return
     */
    public abstract boolean isMonopoly();

}