package ooga.util.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import ooga.exceptions.AttributeNotFoundException;
import ooga.model.data.properties.Property;
import ooga.model.game_handling.GameHandlingTest;
import org.junit.jupiter.api.Test;

public class PropertyParserTest extends GameHandlingTest {
  @Test
  void testParseProperties()
      throws AttributeNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    PropertyParser myParser = new PropertyParser();
    Map<String,Property> propList = new HashMap();
    ArrayList<Property> propertyList = myParser.parseProperties(
        "variations/original/properties");
    for (Property prop: propertyList){
      propList.putIfAbsent(prop.getName(),prop);
    }
    assertEquals(28,propList.size());
    assertEquals(true,propList.containsKey("Boardwalk"));
    assertEquals(false,propList.containsKey("Walkboard"));

  }

  @Test
  void testParsePropertyName()
      throws AttributeNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    PropertyParser myParser = new PropertyParser();
    Property testProperty = myParser.parsePropertyFile(new File("data/variations/original/properties/mediterranean_avenue.property"));
    assertEquals(testProperty.getName(),"Mediterranean Avenue");
  }

  @Test
  void testBadParsePropertyName()
      {
    boolean thrown = false;

    try{
    PropertyParser myParser = new PropertyParser();
    Property testProperty = myParser.parsePropertyFile(new File("data/testdata/faulty_property.property"));

    assertEquals(testProperty.getName(),"Mediterranean Avenue");
    }
    catch (Exception e){
      thrown = true;
    }

    assertEquals(true,thrown);
  }

  @Test
  void testBadParsePropertyFile()
  {
    boolean thrown = false;

    try{
      PropertyParser myParser = new PropertyParser();
      Property testProperty = myParser.parsePropertyFile(new File("data/testdata/faulty_file.property"));

      assertEquals(testProperty.getName(),"Mediterranean Avenue");
    }
    catch (Exception e){
      thrown = true;
    }

    assertEquals(true,thrown);
  }

  @Test
  void testSpacesBetweenCommasInDataFiles()
      throws AttributeNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    PropertyParser myParser = new PropertyParser();
    Property testProperty = myParser.parsePropertyFile(new File("data/variations/original/properties/connecticut_avenue.property"));
    assertEquals("Oriental Avenue",testProperty.getNeighbors().get(1));
    //should return "Oriental Avenue", NOT " Oriental Avenue" (space at beginning)
  }

}
