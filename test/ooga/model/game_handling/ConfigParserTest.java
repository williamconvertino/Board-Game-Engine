package ooga.model.game_handling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import ooga.exceptions.AttributeNotFoundException;
import ooga.model.data.properties.Property;
import ooga.model.game_handling.parsers.PropertyParser;
import org.junit.jupiter.api.Test;

public class ConfigParserTest extends GameHandlingTest{
  @Test
  void testParseProperties()
      throws AttributeNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    PropertyParser myParser = new PropertyParser();
    ArrayList<Property> propertyList = myParser.parseProperties("monopoly_original/board/properties");
    for (Property prop: propertyList){
      System.out.println(prop.getColor());
    }

  }


  @Test
  void testParseProperty()
      throws AttributeNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    PropertyParser myParser = new PropertyParser();
    Property testProperty = myParser.parsePropertyFile(new File("data/monopoly_original/board/properties/mediterranean_avenue.property"));
    assertEquals(testProperty.getName(),"Mediterranean Avenue");

  }
}
