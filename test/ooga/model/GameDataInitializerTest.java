package ooga.model;

import ooga.model.game_initialization.GameDataInitializer;
import ooga.exceptions.ImproperlyFormattedFile;
import ooga.model.data.gamedata.GameData;
import ooga.util.parsers.ParserTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GameDataInitializerTest extends ParserTest {

  @Test
  void TestInitializeGameData() throws ImproperlyFormattedFile {
    GameData data = (new GameDataInitializer()).generateGameData("original",null);
    assertEquals(data.getBoard().getTiles().size(),40);
  }
}
