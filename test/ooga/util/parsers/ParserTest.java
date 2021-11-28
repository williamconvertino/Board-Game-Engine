package ooga.util.parsers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import ooga.exceptions.AttributeNotFoundException;
import ooga.model.data.properties.Property;
import ooga.util.parsers.PropertyParser;
import org.junit.jupiter.api.BeforeEach;

public class ParserTest {

  public ArrayList<Property> propertyList;

  @BeforeEach
  void initGamestate()
      throws AttributeNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    PropertyParser myParser = new PropertyParser();
    propertyList = myParser.parseProperties("monopoly_original/properties");
  }
}
