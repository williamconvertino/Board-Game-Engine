package ooga.util.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.exceptions.AttributeNotFoundException;
import ooga.exceptions.InvalidFileFormatException;
import ooga.model.data.cards.Card;
import ooga.model.data.player.Player;
import org.junit.jupiter.api.Test;

public class PlayerParserTest {

  private List<Player> playerList;

  @Test
  void testParsePlayer()
      throws AttributeNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InvalidFileFormatException, IOException {

    playerList = new ArrayList<>();
    playerList = PlayerParser.getPlayersFromFile("data/monopoly_original/players/players.info");

    assertEquals("Will",playerList.get(0).getName());
    assertEquals("Henry",playerList.get(1).getName());
    assertEquals("Aaric",playerList.get(2).getName());
    assertEquals("Casey",playerList.get(3).getName());
    assertEquals("Jordan",playerList.get(4).getName());



  }
}
