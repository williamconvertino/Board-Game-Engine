package ooga.display;

import java.util.concurrent.TimeUnit;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ooga.display.DisplayManager;
import ooga.util.DukeApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DisplayTest extends DukeApplicationTest {

  public DisplayManager dm;

  @Override
  public void start(Stage stage) {
    dm = new DisplayManager(stage);
  }

  @Test
  public void testDisplay() {
      Button options = lookup("#Start").query();
      clickOn(options);
      options = lookup("#Continue").query();
      clickOn(options);
      options = lookup("#BoardGotoHome").query();
      clickOn(options);
      options = lookup("#Start").query();
      clickOn(options);
      options = lookup("#Continue").query();
      clickOn(options);
      //there should only be 5 displays in DisplayManager's AllDisplays.
      assertEquals(5,dm.getAllDisplays().size());
  }

}
