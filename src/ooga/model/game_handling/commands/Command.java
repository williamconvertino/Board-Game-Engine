package ooga.model.game_handling.commands;

import java.lang.reflect.InvocationTargetException;
import ooga.model.data.player.Player;

@Deprecated
public interface Command {

  void execute(Player p) throws IllegalAccessException, InvocationTargetException;

}
