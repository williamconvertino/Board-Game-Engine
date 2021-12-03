package ooga.model.game_handling.commands;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import ooga.display.Display;
import ooga.exceptions.InvalidFileFormatException;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;
import ooga.model.data.properties.Property;
import ooga.model.game_handling.FunctionExecutor;

/**
 * A utility that can convert string lines into executable commands.
 *
 * @author William Convertino
 *
 * @since 0.0.2
 */

public class ActionSequenceParser {

  //The directory containing the ActionSequence resources.
  private static final String RESOURCE_DIRECTORY = ActionSequenceParser.class.getPackageName() + ".resources.".replace(".","/");

  //The FunctionExecutor that houses the commands to be run.
  private FunctionExecutor functions;

  //The current game's gamedata.
  private GameData gameData;

  //The ResourceBundle containing the command methods and their keywords.
  private ResourceBundle commandDoc;

  //The ResourceBundle containing the argument generation methods and their keywords.
  private ResourceBundle argumentsDoc;

  //The ResourceBundle containing the multi-step argument generation methods and their keywords.
  private ResourceBundle multistepArgumentsDoc;

  /**
   * Constructs a new ActionSequenceParser.
   *
   * @param functions the functions with which this class can run.
   * @param gameData a reference to the game's data.
   */
  public ActionSequenceParser (FunctionExecutor functions, GameData gameData) {
    System.out.println(RESOURCE_DIRECTORY);
    this.functions = functions;
    this.gameData = gameData;
    this.commandDoc = ResourceBundle.getBundle(RESOURCE_DIRECTORY + "commands");
    this.argumentsDoc = ResourceBundle.getBundle(RESOURCE_DIRECTORY + "arguments");
    this.multistepArgumentsDoc = ResourceBundle.getBundle(RESOURCE_DIRECTORY + "multistep_arguments");
  }


  //The following lines of code were my first attempt at making the ActionSequenceParser. This implementation would have allowed
  //sequences to be self-contained when executing the commands. As it is, I couldn't get this method to work, and figured my time was
  //better spent elsewhere.

  //I left this code here as a reminder of what could have been :(

//
//  /**
//   * Creates a command from the specified text and adds it to the given ActionSequence.
//   *
//   * @param line the line to parse into a command.
//   * @param sequence the sequence to which the command is added.
//   * @throws InvalidFileFormatException if the line is unable to be parsed.
//   */
//  public void createSequence(String line, ActionSequence sequence) throws InvalidFileFormatException {
//
//    //If the line is null or empty, return.
//    if (line == null || line.length() == 0) {
//      return;
//    }
//
//    //Otherwise, split the line into its elements.
//    String[] elements = line.split(" ");
//
//    try {
//
//      //Convert the first element into the function the command should execute.
//      String methodName = commandDoc.getString(elements[0]);
//      Method func = functions.getClass().getMethod(methodName);
//
//      //Then create a list of suppliers to feed the function its arguments.
//      //The actual creation of these suppliers is below.
//      List<Supplier> argGenerator = new ArrayList<>();
//
//      for (int i = 1; i < elements.length; i++) {
//
//        String element = elements[i];
//
//        if (multistepArgumentsDoc.containsKey(element)) {
//          //If the argument is a multi-step argument, then generate its supplier
//          //using the associated method, and pass the next element in the line
//          //as that method's argument.
//          Method argumentGenerator = getClass().getMethod(multistepArgumentsDoc.getString(element));
//          i+=1;
//
//          Object arg = rawValue(elements[i]);
//
//          //Add the supplier to the list.
//          argGenerator.add((Supplier) argumentGenerator.invoke(this, arg));
//
//        } else if (argumentsDoc.containsKey(element)) {
//
//          //If the argument is not multi-step, but is associated with a generator
//          // method, then call that method and add the returned Supplier to the generator list.
//          argGenerator.add((Supplier)getClass().getMethod(argumentsDoc.getString(element)).invoke(this));
//        } else {
//
//          //If the argument cannot be found, then create a supplier that returns its
//          //raw value.
//          Object rawValue = rawValue(element);
//          argGenerator.add(new Supplier() {
//            @Override
//            public Object get() {
//              return rawValue;
//            }
//          });
//        }
//      }
//
//      //Create the command itself.
//      Command myCommand = (Player p) -> {
//
//        //Generate an array of arguments from the argument generator list.
//        //(This will only execute when the command is called, so the values will
//        //be updated to reflect the current game state.)
//        Object[] args = new Object[argGenerator.size()];
//
//        for (int s = 0; s <  argGenerator.size(); s++) {
//          args[s] = argGenerator.get(s).get();
//        }
//
//        //Execute the game function using the generated list of arguments.
//        func.invoke(functions, args);
//      };
//
//      //If everything above executed without exception, then add the command
//      //to the sequence.
//      sequence.add(myCommand);
//
//    } catch (Exception e) {
//
//      //If the generation of the ActionSequence led to an error, display it above.
//      throw new InvalidFileFormatException();
//    }
//  }
//
//  //private void
//
//  private Object rawValue(String s) {
//    try {
//      int val = Integer.parseInt(s);
//      return val;
//    } catch (Exception e) {
//      return s;
//    }
//  }
//
//
//  private Supplier getNumHouses(Player p) {
//    return new Supplier() {
//      @Override
//      public Object get() {
//        int num = 0;
//        for (Property p: gameData.getCurrentPlayer().getProperties()) {
//          num += p.getNumHouses();
//        }
//        return num;
//      }
//    };
//  }

}
