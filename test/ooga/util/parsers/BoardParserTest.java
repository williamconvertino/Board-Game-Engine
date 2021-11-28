package ooga.util.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import ooga.exceptions.AttributeNotFoundException;
import org.junit.jupiter.api.Test;

public class BoardParserTest extends ParserTest {
  @Test
  void testParseBoard() throws AttributeNotFoundException, IOException {
    BoardParser myBoardParser = new BoardParser();
    myBoardParser.parseBoard("data/monopoly_original/board/propertyOnly.board",null);

  }

}
