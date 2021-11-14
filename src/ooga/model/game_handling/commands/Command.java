package ooga.model.game_handling.commands;

import java.lang.reflect.InvocationTargetException;
import ooga.model.data.player.Player;

public interface Command {

  public void execute(Player p) throws IllegalAccessException, InvocationTargetException;

}
