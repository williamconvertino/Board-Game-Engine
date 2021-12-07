package ooga.model.game_handling.commands;

import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ooga.display.communication.DisplayComm;
import ooga.exceptions.InvalidFileFormatException;
import ooga.exceptions.TileNotFoundException;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.die.Die;
import ooga.model.game_handling.FunctionExecutor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * A utility that can execute action commands formatted as strings.
 *
 * @author William Convertino
 * @since 0.0.2
 */

public class ActionSequenceExecutor {

  //The directory containing the ActionSequence resources.
  private static final String RESOURCE_DIRECTORY = "src/ooga/model/game_handling/commands/resources/";
  private static final String COMMAND_DATA_FILENAME = "commands.json";
  private static final String ARGUMENT_DATA_FILENAME = "arguments.json";

  //The FunctionExecutor that houses the commands to be run.
  private final FunctionExecutor functionExecutor;

  //The current game's gamedata.
  private final GameData gameData;

  private final ActionSequenceHelperCommands actionSequenceHelper;

  //The JSON parser of this class.
  private final JSONParser myJSONParser;

  //A JSONObject containing all the commands and their associated data.
  private JSONObject myCommands;

  //A JSONObject containing all the arguments for the commands and their associated data.
  private JSONObject myArgs;

  /**
   * Constructs a new ActionSequenceParser.
   *
   * @param functions the functions with which this class can run.
   * @param gameData  a reference to the game's data.
   */
  public ActionSequenceExecutor(FunctionExecutor functions, GameData gameData,
      DisplayComm displayComm) {
    this.functionExecutor = functions;
    this.gameData = gameData;
    this.actionSequenceHelper = new ActionSequenceHelperCommands(functions, gameData);
    this.myJSONParser = new JSONParser();
    try {
      myCommands = (JSONObject) myJSONParser.parse(
          new FileReader(String.format("%s%s", RESOURCE_DIRECTORY, COMMAND_DATA_FILENAME)));
      myArgs = (JSONObject) myJSONParser.parse(
          new FileReader(String.format("%s%s", RESOURCE_DIRECTORY, ARGUMENT_DATA_FILENAME)));
    } catch (Exception e) {
      displayComm.showException(e);
    }
  }

  /**
   * Executes an action command given as a string.
   *
   * @param command the command to execute.
   */
  public void executeCommand(String command) throws InvalidFileFormatException {

    if (command == null || command.strip().length() == 0) {
      return;
    }

    try {

      String[] commandElements = parseCommand(command);

      //Find the JSON entry corresponding with the desired command.
      JSONObject fExecutorCommand = (JSONObject) myCommands.get(commandElements[0]);

      Class sourceClass = Class.forName((String) fExecutorCommand.getOrDefault("sourceclass",
          "ooga.model.game_handling.FunctionExecutor"));

      Method fExecutorMethod = generateMethodFromJSONObject(fExecutorCommand, sourceClass);

      //
      Object[] fExecutorArgs = generateArgumentArrayFromJSONObject(fExecutorCommand, sourceClass,
          Arrays.copyOfRange(commandElements, 1, commandElements.length));

      fExecutorMethod.invoke(getInstanceOfClass(sourceClass), fExecutorArgs);

    } catch (Exception e) {
      throw new InvalidFileFormatException();
    }

  }

  //TODO Add comments
  private Method generateMethodFromJSONObject(JSONObject jObject, Class sourceClass)
      throws ClassNotFoundException, NoSuchMethodException {

    //Generate an array of the classes that the method takes in as an argument.
    Class[] argumentClasses = new Class[((Long) jObject.getOrDefault("numarg",
        Long.valueOf(0))).intValue()];
    for (int i = 0; i < argumentClasses.length; i++) {
      argumentClasses[i] = Class.forName(
          (String) jObject.get(String.format("%s%s", "argtype", i + 1)));
    }

    //Find the name of the method.
    String methodName = (String) jObject.get("method");

    //Find and return the method with the found name and argument type list.
    return sourceClass.getMethod(methodName, argumentClasses);
  }

  //TODO Add comment
  private Object[] generateArgumentArrayFromJSONObject(JSONObject jObject, Class sourceClass,
      String[] commandElements)
      throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InvalidFileFormatException {

    List<Object> arguments = new ArrayList<>();

    //For each of the arguments needed:
    for (int i = 0; i < ((Long) jObject.getOrDefault("numarg", Long.valueOf(0))).intValue(); i++) {

      //If the element is in the arguments JSON file:
      if (myArgs.containsKey(commandElements[i])) {

        JSONObject argumentData = (JSONObject) myArgs.get(commandElements[i]);

        //Find the relevant argument generation function, and generate the argument.
        Class generatorClass = Class.forName((String) argumentData.getOrDefault("sourceclass",
            "ooga.model.game_handling.commands.ActionSequenceHelperCommands"));
        Method argumentGeneratorMethod = generateMethodFromJSONObject(argumentData, generatorClass);
        Object[] argumentGeneratorArguments = generateArgumentArrayFromJSONObject(argumentData,
            generatorClass,
            Arrays.copyOfRange(commandElements, 1, commandElements.length));

        //Add this argument to the argument list.
        arguments.add(
            argumentGeneratorMethod.invoke(getInstanceOfClass(argumentGeneratorMethod.getClass()),
                argumentGeneratorArguments));

      } else {

        Object rawValue = getRawValue(commandElements[i]);
        if (rawValue == null) {
          throw new InvalidFileFormatException();
        }
        arguments.add(rawValue);

      }

    }

    return arguments.toArray();

  }


  public Object getInstanceOfClass(Class c) {
    if (c.equals(gameData.getClass())) {
      return gameData;
    } else if (c.equals(functionExecutor.getClass())) {
      return functionExecutor;
    } else if (c.equals(ActionSequenceHelperCommands.class)) {
      return actionSequenceHelper;
    } else {
      return actionSequenceHelper;
    }

  }

  public String[] parseCommand(String command) {
    List<String> elementList = new ArrayList<>();
    String[] splitArray = command.split(" ");
    for (int i = 0; i < splitArray.length; i++) {

      if (splitArray[i].startsWith("\"")) {
        String element = splitArray[i].substring(1);
        while (!splitArray[i].endsWith("\"")) {
          i++;
          element += " " + splitArray[i];
        }
        elementList.add(element.substring(0, element.length() - 1));
      } else {
        elementList.add(splitArray[i]);
      }

    }
    String[] ret = new String[elementList.size()];
    for (int i = 0; i < elementList.size(); i++) {
      ret[i] = elementList.get(i);
    }
    return ret;
  }

  public Object getRawValue(String value) {
    try {
      return Integer.parseInt(value);
    } catch (Exception e) {
      return value;
    }
  }

  public Integer multiply(Integer a1, Integer a2) {
    return a1 * a2;
  }

  public List<Player> getAllPlayers() {
    return new ArrayList<>(gameData.getPlayers());
  }

  public Player getCurrentPlayer() {
    return gameData.getCurrentPlayer();
  }

  public List<Player> getAllPlayersButCurrent() {
    List<Player> allPlayers = new ArrayList<>(gameData.getPlayers());
    allPlayers.remove(gameData.getCurrentPlayer());
    return (allPlayers);
  }

  public Integer getDieRoll()
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Die myDie = gameData.getDie().getClass().getConstructor().newInstance();
    return myDie.roll();
  }

  public Integer rollDieTimesValue(Integer value)
      throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    return getDieRoll() * value;
  }

  public Integer numberOfPlayersTimesValue(Integer value) {
    return gameData.getPlayers().size() * value;
  }

  public void loseMoneyForNumHouses(Player player, Integer amountPerHouse) {
    functionExecutor.loseMoney(player, player.getNumHouses() * amountPerHouse);
  }

  public void loseMoneyForNumHotels(Player player, Integer amountPerHotel) {
    functionExecutor.loseMoney(player, player.getNumHotels() * amountPerHotel);
  }

  public void advanceToPropertyAndPayX(Player player, String propertyName, Integer amount)
      throws TileNotFoundException {
    functionExecutor.movePlayerToTile(player, propertyName);
    TileModel currentTile = gameData.getBoard().getTileAtIndex(player.getLocation());
    if (currentTile instanceof PropertyTileModel) {
      Property currentProperty = ((PropertyTileModel) currentTile).getProperty();
      if (currentProperty.getOwner() != player
          && currentProperty.getOwner() != Property.NULL_OWNER) {
        for (int i = 1; i < amount; i++) {
          functionExecutor.loseMoney(player, currentProperty.getRentCost());
          functionExecutor.addMoney(currentProperty.getOwner(), currentProperty.getRentCost());
        }
      }
    }
  }

  public void advanceToTypeAndPayX(Player player, String type, Integer amount)
      throws InvalidFileFormatException, TileNotFoundException {
    TileModel myTile = gameData.getBoard().getClosestTileOfType(player, type);
    advanceToPropertyAndPayX(player, myTile.getName(), amount);
  }

  public void advanceToType(Player player, String type)
      throws TileNotFoundException, InvalidFileFormatException {
    TileModel myTile = gameData.getBoard().getClosestTileOfType(player, type);
    functionExecutor.advancePlayerToTile(player, myTile.getName());
  }


}
