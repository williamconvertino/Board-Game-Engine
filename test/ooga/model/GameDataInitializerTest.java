package ooga.model;

import ooga.GameDataInitializer;
import ooga.exceptions.ImproperlyFormattedFile;
import ooga.model.data.gamedata.GameData;
import ooga.util.parsers.ParserTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GameDataInitializerTest extends ParserTest {

  @Test
  void TestInitializeGameData() throws ImproperlyFormattedFile {
    GameData data = GameDataInitializer.generateGameData("monopoly_original");
    assertEquals(data.getBoard().getTiles().size(),40);
  }
}
