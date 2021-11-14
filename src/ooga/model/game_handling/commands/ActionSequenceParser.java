package ooga.model.game_handling.commands;

import java.lang.reflect.Method;
import java.util.ResourceBundle;
import ooga.exceptions.InvalidFileFormatException;
import ooga.model.data.player.Player;
import ooga.model.game_handling.FunctionExecutor;

public class ActionSequenceParser {

  FunctionExecutor functions;
  ResourceBundle commandDoc;

  public ActionSequenceParser (FunctionExecutor functions) {
    this.functions = functions;
    this.commandDoc = ResourceBundle.getBundle("commands");
  }

  public void createSequence(String line, ActionSequence sequence) throws InvalidFileFormatException {
    if (line == null || line.length() == 0) {
      return;
    }

    String[] elements = line.split(" ");

    try {
      String methodName = commandDoc.getString(elements[0]);
      Method func = functions.getClass().getMethod(methodName);

      Command myCommand = (Player p) -> func.invoke(functions);

    } catch (Exception e) {
      throw new InvalidFileFormatException();
    }

  }

}
