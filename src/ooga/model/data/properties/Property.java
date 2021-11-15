package ooga.model.data.properties;

import java.util.ArrayList;
import java.util.List;
import ooga.exceptions.MaxHousesReachedException;
import ooga.exceptions.MortgageException;
import ooga.exceptions.NoHousesToSellException;
import ooga.model.data.player.Player;

/**
 * This class keeps track of the data associated with any given property.
 * 
 * @author William Convertino, Jordan Castleman
 * 
 * @since 0.0.1
 */
public class Property {

    /**An empty player to represent an unowned property.**/
    public static final Player NULL_OWNER = new Player("Null");

    //The name of this property.
    private String name;

    //The cost of this property.
    private int cost;

    //An array of the rest cost with the specified number of houses.
    //The index of the array corresponds to the number of houses built,
    //with 0 representing the base rent.
    private int[] rentCost;

    //The number of houses currently built on the property.
    private int numHouses;

    //The maximum number of houses that can be built on this property.
    private int maxHouses;

    //The cost of buying one house on this property.
    private int houseCost;

    //The owner of this property.
    private Player owner;

    //A list of the names of the properties in this properties set.
    private List<String> setMemberNames;

    //The amount of money received for mortgaging this property.
    private int mortgageValue;

    //Whether this property is mortgaged or not.
    private boolean isMortgaged;

    /**
     * Constructs a new property with the specified data.
     *
     * @param name the name of the property.
     * @param cost the cost of the property.
     * @param rentCost an array of the rent cost of this property for each house built.
     * @param houseCost the cost of buying one house.
     * @param setMemberNames the names of the other members in this property's set.
     * @param mortgageValue the value of mortgaging this property.
     */
    public Property(String name, int cost, int[] rentCost,
        int houseCost, List<String> setMemberNames, int mortgageValue) {
        this.name = name;
        this.cost = cost;
        this.rentCost = rentCost;
        this.maxHouses = rentCost.length - 1;
        this.houseCost = houseCost;
        this.setMemberNames = setMemberNames;
        this.mortgageValue = mortgageValue;
        this.owner = NULL_OWNER;
        this.numHouses = 0;
        this.isMortgaged = false;
    }


    /**
     * Returns the player's name.
     *
     * @return the player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the cost of the property.
     *
     * @return the cost of the property.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Return the number of houses on the property.
     *
     * @return the number of houses on the property.
     */
    public int getNumHouses() {
        return numHouses;
    }

    /**
     * Returns the cost of buying a house.
     *
     * @return the cost of buying a house.
     */
    public int getHouseCost() {
        return houseCost;
    }

    /**
     * return the properties current owner.
     *
     * @return the properties current owner.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the property to the specified player.
     *
     * @param owner the player to set as the owener of this property.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Returns the mortgage value of this property.
     *
     * @return the amount of money received for mortgaging this property.
     */
    public int getMortgageValue() {
        return mortgageValue;
    }

    public double getUnmortgageValue() {
        return mortgageValue * 1.1;
    }

    /**
     * Mortgages this property.
     * @throws MortgageException
     */
    public void mortgageProperty() throws MortgageException {
        if (!isMortgaged) {
            isMortgaged = true;
        }
        else {
            throw new MortgageException();
        }
    }

    /**
     * Unmortgages this property.
     * @throws MortgageException
     */
    public void unmortgageProperty() throws MortgageException {
        if (isMortgaged) {
            isMortgaged = false;
        }
        else {
            throw new MortgageException();
        }
    }

    /**
     * States whether or not this property is a part of a monopoly.
     *
     * @return true if this property is part of a monopoly, and false otherwise.
     */
    public boolean isMonopoly() {
        if (owner == NULL_OWNER) {
            return false;
        }
        List<String> requiredProperties = new ArrayList<>(setMemberNames);
        for (Property prop: owner.getProperties()) {
            requiredProperties.removeIf(e->e.equals(prop.getName()));
        }
        return requiredProperties.isEmpty();
    }

    /**
     * Returns the rent cost for this property.
     *
     * @return the rent cost for this property.
     */
    public int getRentCost() {
        if (isMortgaged) {
            return 0;
        }
        else if (numHouses == 0 && isMonopoly()) {
            return (2 * rentCost[0]);
        }
        return rentCost[numHouses];
    }

    /**
     * Buys a new house on this property.
     *
     * @throws MaxHousesReachedException if the maximum number of houses has already been reached.
     */
    public void buyHouse() throws MaxHousesReachedException {
        if (numHouses < maxHouses) {
            numHouses++;
        } else {
            throw new MaxHousesReachedException();
        }
    }

    /**
     * Sells a house on this property.
     *
     * @throws NoHousesToSellException if there are no houses on the property.
     */
    public void sellHouse() throws NoHousesToSellException {
        if (numHouses == 0) {
            throw new NoHousesToSellException();
        } else {
            numHouses--;
        }
    }
}