package ooga.model.game_handling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

public class ConfigParserTest extends GameHandlingTest{
  @Test
  void testParseProperty() throws IOException {
    ConfigParser myParser = new ConfigParser();
    myParser.parseProperties("monopoly_original/board/properties");
  }
}
