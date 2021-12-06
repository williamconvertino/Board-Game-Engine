package ooga.util.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import ooga.exceptions.AttributeNotFoundException;
import ooga.exceptions.InvalidFileFormatException;
import ooga.model.data.cards.Card;
import org.junit.jupiter.api.Test;

public class CardParserTest extends ParserTest {
  @Test
  void testParseChanceCards()
      throws AttributeNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InvalidFileFormatException {

    Map<String,Card>cardMap = new HashMap();
    ArrayList<Card> cardList = cardParser.parseCards("variations/original/cards/chance");
    for (Card card: cardList){
      cardMap.putIfAbsent(card.getName(),card);
    }
    assertEquals(true,cardMap.containsKey("Advance To Go"));
    assertEquals(true,cardMap.containsKey("Go To Jail"));

  }
}
