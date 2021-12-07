package ooga.util.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.exceptions.AttributeNotFoundException;
import ooga.exceptions.InvalidFileFormatException;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;
import org.junit.jupiter.api.Test;

public class BoardParserTest extends ParserTest {

  Map<String, PropertyTileModel> propperTileMap;
  Map<String,TileModel> nonPropperTileMap;

  @Test
  void testParseBoard()
      throws AttributeNotFoundException, IOException, InvalidFileFormatException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {

    propperTileMap = new HashMap<>();
    nonPropperTileMap = new HashMap<>();
    propperTileMap = tileParser.parsePropertyTiles(propertyList);
    Map<String, TileModel> nonPropTileMap = new HashMap();
    nonPropperTileMap = tileParser.parseNonPropertyTiles("variations/original/board/tiles");


    Map<String,TileModel> tileModelMap = new HashMap<>() {{
      putAll(propperTileMap);
      putAll(nonPropperTileMap);
    }};


    List<TileModel> tiles = boardParser.parseBoard("data/variations/original/board/original.board",tileModelMap);
    assertEquals(40,tiles.size());
    assertEquals("Go",tiles.get(0).getName());
    assertEquals("Boardwalk",tiles.get(39).getName());

  }

}
