package ooga.util.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.exceptions.AttributeNotFoundException;
import ooga.exceptions.InvalidFileFormatException;
import ooga.model.data.cards.Card;
import ooga.model.data.properties.Property;
import ooga.model.game_handling.GameHandlingTest;
import ooga.util.parsers.PropertyParser;
import org.junit.jupiter.api.Test;

public class CardParserTest extends ParserTest {
  @Test
  void testParseChanceCards()
      throws AttributeNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InvalidFileFormatException {

    Map<String,Card>cardMap = new HashMap();
    ArrayList<Card> cardList = cardParser.parseCards("monopoly_original/cards/chance");
    for (Card card: cardList){
      cardMap.putIfAbsent(card.getName(),card);
    }
    assertEquals(3,cardList.size());
    assertEquals(true,cardMap.containsKey("AdvanceToGo"));
    assertEquals(false,cardMap.containsKey("GoToJail"));

  }
}
