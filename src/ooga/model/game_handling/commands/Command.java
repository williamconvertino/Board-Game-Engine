package ooga.model.game_handling.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;
import ooga.model.data.player.Player;

@Deprecated
public interface Command {

  public void execute(Player p) throws IllegalAccessException, InvocationTargetException;

}
