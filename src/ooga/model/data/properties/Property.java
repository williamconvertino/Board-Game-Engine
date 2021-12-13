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
 * @author William Convertino, Jordan Castleman, Aaric Han
 * @since 0.0.1
 */
public class Property {

  /**
   * An empty player to represent an unowned property.
   **/
  public static final Player NULL_OWNER = new Player("Null");

  //The name of this property.
  private final String name;

  //The type of this property;
  private final String type;

  //The cost of this property.
  private final int cost;

  //An array of the rest cost with the specified number of houses.
  //The index of the array corresponds to the number of houses built,
  //with 0 representing the base rent.
  private final int[] rentCost;

  //The number of houses currently built on the property.
  private int numHouses;

  //The maximum number of houses that can be built on this property, if applicable.
  private int maxHouses;

  //The cost of buying one house on this property, if applicable.
  private int houseCost;

  //The owner of this property.
  private Player owner;

  //image of property, if applicable.
  private String image;

  //A list of the names of the properties in this properties set.
  private final List<String> setMemberNames;

  //The amount of money received for mortgaging this property.
  private final int mortgageValue;

  //Whether this property is mortgaged or not.
  private boolean isMortgaged;

  //Color of property.
  private String color;

  // monopoly? 0 = not monopoly, 1 = monopoly
  private int monopoly;

  /**
   * Constructs a new regular property with the specified data.
   *
   * @param name           the name of the property.
   * @param cost           the cost of the property.
   * @param rentCost       an array of the rent cost of this property for each house built.
   * @param houseCost      the cost of buying one house.
   * @param setMemberNames the names of the other members in this property's set.
   * @param mortgageValue  the value of mortgaging this property.
   */
  public Property(String name, String type, int cost, int[] rentCost,
      int houseCost, List<String> setMemberNames, int mortgageValue, String color) {
    this.name = name;
    this.type = type;
    this.cost = cost;
    this.rentCost = rentCost;
    this.maxHouses = rentCost.length - 1;
    this.houseCost = houseCost;
    this.setMemberNames = setMemberNames;
    this.mortgageValue = mortgageValue;
    this.owner = NULL_OWNER;
    this.numHouses = 0;
    this.isMortgaged = false;
    this.color = color;
    this.monopoly = 0;
  }

  /**
   * Constructs a new special property with the specified data. For Railroads and Utilities.
   *
   * @param name           the name of the property.
   * @param cost           the cost of the property.
   * @param rentCost       an array of the rent cost of this property for each house built.
   * @param image          the image associated with the property
   * @param setMemberNames the names of the other members in this property's set.
   * @param mortgageValue  the value of mortgaging this property.
   */
  public Property(String name, String type, int cost, int[] rentCost,
      List<String> setMemberNames, int mortgageValue, String image) {
    this.name = name;
    this.type = type;
    this.cost = cost;
    this.rentCost = rentCost;
    this.setMemberNames = setMemberNames;
    this.mortgageValue = mortgageValue;
    this.owner = NULL_OWNER;
    this.isMortgaged = false;
    this.image = image;
  }


  /**
   * Returns the properties' name.
   *
   * @return the properties' name.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the properties' type.
   *
   * @return the properties' type.
   */
  public String getType() {
    return type;
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
    if (numHouses < maxHouses) {
      return numHouses;
    } else {
      return 0;
    }
  }

  /**
   * Return the number of hotels on the property.
   *
   * @return the number of hotels on the property.
   */
  public int getNumHotels() {
    if (numHouses == maxHouses) {
      return 1;
    } else {
      return 0;
    }
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
   * Returns the color of the property
   *
   * @return property color
   */
  public String getColor() {
    return color;
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
   *
   * @throws MortgageException
   */
  public void mortgageProperty() throws MortgageException {
    if (!isMortgaged) {
      isMortgaged = true;
    } else {
      throw new MortgageException();
    }
  }

  /**
   * Unmortgages this property.
   *
   * @throws MortgageException
   */
  public void unmortgageProperty() throws MortgageException {
    if (isMortgaged) {
      isMortgaged = false;
    } else {
      throw new MortgageException();
    }
  }


  /**
   * States whether or not this property is a part of a monopoly.
   *
   * @return true if this property is part of a monopoly, and false otherwise.
   */
  public boolean isMonopoly() {
    if (monopoly == 1) {
      return true;
    }
    if (owner == NULL_OWNER) {
      return false;
    }
    List<String> requiredProperties = new ArrayList<>(setMemberNames);
    for (Property prop : owner.getProperties()) {
      requiredProperties.removeIf(e -> e.equals(prop.getName()));
    }
    if (requiredProperties.isEmpty()) {
      monopoly = 1;
      return true;
    } else {
      monopoly = 0;
      return false;
    }
  }

  /**
   * Sets the monopoly value
   */
  public void setMonopoly() {
    monopoly = 1;
  }

  /**
   * Returns the rent cost for this property.
   *
   * @return the rent cost for this property.
   */
  public int getRentCost() {
    if (isMortgaged) {
      return 0;
    } else if (numHouses == 0 && isMonopoly()) {
      return (2 * rentCost[0]);
    }
    return rentCost[numHouses];
  }

  /**
   * Buys a new house on this property.
   *
   * @throws MaxHousesReachedException if the maximum number of houses has already been reached.
   * @throws MortgageException         if the house is mortgaged and therefore cannot build houses.
   */
  public void buyHouse() throws MaxHousesReachedException, MortgageException {
    if (isMortgaged) {
      throw new MortgageException();
    }
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
   * @throws MortgageException       if the house is mortgaged and therefore cannot sell houses.
   */
  public void sellHouse() throws NoHousesToSellException, MortgageException {
    if (isMortgaged) {
      throw new MortgageException();
    }
    if (numHouses != 0) {
      numHouses--;
    } else {
      throw new NoHousesToSellException();
    }
  }

  /**
   * returns list of all property neighbors
   *
   * @return
   */
  public List<String> getNeighbors(){
    return setMemberNames;
  }

  public boolean isMortgaged() {
    return isMortgaged;
  }

  @Override
  public String toString() {
    return this.getName();
  }
}