package ooga.model.game_handling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import ooga.model.data.properties.Property;
import org.junit.jupiter.api.Test;

public class ConfigParserTest extends GameHandlingTest{
  @Test
  void testParseProperties() throws IOException {
    ConfigParser myParser = new ConfigParser();
    ArrayList<Property> propertyList = myParser.parseProperties("monopoly_original/board/properties");
    for (Property prop: propertyList){
      System.out.println(prop.getName());
    }

  }


  @Test
  void testParseProperty() throws IOException {
    ConfigParser myParser = new ConfigParser();
    Property testProperty = myParser.parsePropertyFile(new File("data/monopoly_original/board/properties/mediterranean_avenue.property"));
    assertEquals(testProperty.getName(),"Mediterranean Avenue");

  }
}
