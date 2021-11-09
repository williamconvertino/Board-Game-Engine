package model.data.game_data;

import java.util.*;
import model.data.collectables.Collectable;
import model.data.collectables.properties.Property;

/**
 * 
 */
public abstract class Player {

    /**
     * Default constructor
     */
    public Player() {
    }

    /**
     * 
     */
    private int money;

    /**
     * 
     */
    private List<Collectable> inventory;

    /**
     * 
     */
    private boolean alive;

    /**
     * 
     */
    private List<Property> properties;

    /**
     * 
     */
    private int location;


    /**
     * @param amount
     */
    public abstract void addMoney(int amount);

    /**
     * @return
     */
    public abstract int getMoney();

    /**
     * @return
     */
    public abstract List<Collectable> getCollectables();

    /**
     * @return
     */
    public abstract List<Property> getProperties();

    /**
     * @return
     */
    public abstract int getLocation();

    /**
     * @param location
     */
    public abstract void setLocation(int location);

    /**
     * @param property
     */
    public abstract void giveProperty(Property property);

    /**
     * @return
     */
    public abstract boolean isInJail();

    /**
     * @param status
     */
    public abstract void setStatus(boolean status);

}