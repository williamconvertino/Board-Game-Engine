package ooga.util.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import ooga.exceptions.AttributeNotFoundException;
import ooga.exceptions.InvalidFileFormatException;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;
import org.junit.jupiter.api.Test;

public class TileParserTest extends ParserTest{
  @Test
  void testParsePropertyTiles()
      throws AttributeNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {

    Map<String, PropertyTileModel> propTileMap = new HashMap();
    propTileMap = tileParser.parsePropertyTiles(propertyList);

    assertEquals(28,propTileMap.size());
    assertEquals(true,propTileMap.containsKey("Boardwalk"));
    assertEquals(false,propTileMap.containsKey("Walkboard"));

  }

  @Test
  void testParseNonPropertyTiles()
      throws AttributeNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InvalidFileFormatException {

    Map<String, TileModel> nonPropTileMap = new HashMap();
    nonPropTileMap = tileParser.parseNonPropertyTiles("variations/original/board/tiles");

    assertEquals(8,nonPropTileMap.size());


  }
}
