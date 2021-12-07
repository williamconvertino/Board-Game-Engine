package ooga.util.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ooga.model.data.player.Player;

/**
 * Generates a list of players with names generated from a players.info file.
 *
 * @author William Convertino
 */
public class PlayerParser {

  /**
   * Returns a list of players with names generated from the specified file.
   *
   * @param filePath the path of the players.info file.
   * @return a list of players with the given names.
   * @throws IOException if the file cannot be found.
   */
  public static List<Player> getPlayersFromFile(String filePath) throws IOException {
    BufferedReader myReader = new BufferedReader(new FileReader(new File(filePath)));
    ArrayList<Player> myPlayers = new ArrayList<>();
    String playerName;
    while ((playerName = myReader.readLine()) != null) {
      myPlayers.add(new Player(playerName));
    }
    return myPlayers;
  }

}
