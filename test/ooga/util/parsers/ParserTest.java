package ooga.util.parsers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import ooga.exceptions.AttributeNotFoundException;
import ooga.exceptions.InvalidFileFormatException;
import ooga.model.data.deck.Deck;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.properties.Property;
import ooga.model.game_handling.FunctionExecutor;
import ooga.model.game_handling.GameHandlingTest;
import ooga.model.game_handling.commands.ActionSequenceExecutor;
import org.junit.jupiter.api.BeforeEach;

public class ParserTest extends GameHandlingTest {

  public ArrayList<Property> propertyList;
  public PropertyParser propertyParser;
  public CardParser cardParser;
  public TileParser tileParser;
  public BoardParser boardParser;
  public ActionSequenceExecutor actionSequenceParser;
  public GameData gameData;


  @BeforeEach
  void initGamestate()
      throws AttributeNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InvalidFileFormatException {
    propertyParser = new PropertyParser();
    propertyList = new ArrayList<>();
    propertyList = propertyParser.parseProperties("variations/original/properties");
    gameData = new GameData();
    FunctionExecutor functionExecutor = new FunctionExecutor(gameData, gameData.getDie(), myDisplayComm);
    //create parsers
    actionSequenceParser = new ActionSequenceExecutor(functionExecutor,gameData, myDisplayComm);
    cardParser = new CardParser(actionSequenceParser,myDisplayComm);
    tileParser = new TileParser(actionSequenceParser,gameData, null);
    boardParser = new BoardParser();


    Deck chanceDeck = new Deck("Chance",cardParser.parseCards(
        "variations/original/cards/chance"));
    Deck communityChestDeck = new Deck ("Community Chest",cardParser.parseCards(
        "variations/original/cards/community_chest"));

    //combine decks into list, and give to gameData
    List<Deck> deckList = new ArrayList<>();
    deckList.add(chanceDeck);
    deckList.add(communityChestDeck);
    gameData.setDeckManager(deckList);

  }
}
