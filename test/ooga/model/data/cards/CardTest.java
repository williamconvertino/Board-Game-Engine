package ooga.model.data.cards;

import java.lang.reflect.Method;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

  @Test
  void testConstructor() {
    Card myCard = new Card("name","description", new ArrayList<Method>());

    assertEquals("name", myCard.getName());
    assertEquals("description", myCard.getDescription());
  }

}
