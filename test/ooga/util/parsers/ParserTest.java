package ooga.util.parsers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import ooga.exceptions.AttributeNotFoundException;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.properties.Property;
import ooga.model.game_handling.FunctionExecutor;
import ooga.model.game_handling.commands.ActionSequence;
import ooga.model.game_handling.commands.ActionSequenceParser;
import ooga.util.parsers.PropertyParser;
import org.junit.jupiter.api.BeforeEach;

public class ParserTest {

  public ArrayList<Property> propertyList;
  public PropertyParser propertyParser;
  public CardParser cardParser;
  public TileParser tileParser;
  public BoardParser boardParser;
  public ActionSequenceParser actionSequenceParser;


  @BeforeEach
  void initGamestate()
      throws AttributeNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    propertyParser = new PropertyParser();
    propertyList = propertyParser.parseProperties("monopoly_original/properties");
    GameData gameData = new GameData();
    FunctionExecutor functionExecutor = new FunctionExecutor();
    //create parsers
    actionSequenceParser = new ActionSequenceParser(functionExecutor,gameData);
    cardParser = new CardParser(actionSequenceParser);
    tileParser = new TileParser(actionSequenceParser,gameData);
    boardParser = new BoardParser();
  }
}
