package ooga.model.game_handling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import ooga.exceptions.AttributeNotFoundException;
import ooga.model.data.properties.Property;
import ooga.model.game_handling.parsers.BoardParser;
import ooga.model.game_handling.parsers.PropertyParser;
import org.junit.jupiter.api.Test;

public class BoardParserTest extends ParserTest{
  @Test
  void testParseBoard() throws AttributeNotFoundException, IOException {
    BoardParser myBoardParser = new BoardParser(propertyList);
    myBoardParser.parseBoard("data/monopoly_original/board/propertyOnly.board");

  }

}
