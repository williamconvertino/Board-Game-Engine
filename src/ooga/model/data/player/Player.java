package ooga.model.data.player;

import java.util.List;
import ooga.model.data.collectables.Collectable;
import ooga.model.data.collectables.properties.Property;

/**
 * 
 */
public class Player {

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
    private List<Collectable> myCollectables;

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
    public void addMoney(int amount) {

    }

    /**
     * @return
     */
    public int getMoney() {
        return 0;
    }

    /**
     * @return
     */
    public List<Collectable> getCollectables() {
        return null;
    }

    /**
     * @return
     */
    public List<Property> getProperties() {
        return null;
    }

    /**
     * @return
     */
    public int getLocation() {
        return 0;
    }

    /**
     * @param location
     */
    public void setLocation(int location) {

    }

    /**
     * @param property
     */
    public void giveProperty(Property property) {

    }

    /**
     * @return
     */
    public boolean isInJail() {
        return false;
    }

    /**
     * @param status
     */
    public void setStatus(boolean status) {

    }

    public void addCollectable(Collectable c) {
        myCollectables.add(c);
    }

}