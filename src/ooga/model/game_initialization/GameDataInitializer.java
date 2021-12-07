package ooga.model.game_initialization;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import ooga.display.communication.DisplayComm;
import ooga.exceptions.ImproperlyFormattedFile;
import ooga.model.data.deck.Deck;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;
import ooga.model.data.player.PlayerManager;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.die.Die;
import ooga.model.game_handling.FunctionExecutor;
import ooga.model.game_handling.board_manager.BoardManager;
import ooga.model.game_handling.commands.ActionSequenceExecutor;
import ooga.util.parsers.BoardParser;
import ooga.util.parsers.CardParser;
import ooga.util.parsers.PropertyParser;
import ooga.util.parsers.TileParser;

/**
 * This class parses all configuration files and preps program for game.
 *
 * @author Casey Goldstein
 * @author William Convertino
 * @author Jordan Castleman
 * @since 0.0.2
 */
public class GameDataInitializer {

  //Resource bundles for various data values.
  private final ResourceBundle configValueBundle;
  private final ResourceBundle directoryBundle;

  //Parsers with which the data is generated.
  private PropertyParser propertyParser;
  private CardParser cardParser;
  private TileParser tileParser;
  private ActionSequenceExecutor actionSequenceParser;


  /**
   * Constructs a new GameDataInitializer, and initializes it to use ConfigValues.properties and
   * DirectoryNames.properties
   */
  public GameDataInitializer() {
    configValueBundle = ResourceBundle.getBundle(
        GameDataInitializer.class.getPackageName() + ".ConfigValues");
    directoryBundle = ResourceBundle.getBundle(
        GameDataInitializer.class.getPackageName() + ".DirectoryNames");
  }

  /**
   * Generates and returns a fresh instance of gamedata for the specified variation name.
   *
   * @param variationName the variation type for which the gamedata should be generated.
   * @param displayComm   the displayComm that the data should use to communicate with the display.
   * @return the generated gamedata.
   * @throws ImproperlyFormattedFile if the variation provided contains an improperly formatted
   *                                 file.
   */
  public GameData generateGameData(String variationName, DisplayComm displayComm)
      throws ImproperlyFormattedFile {

    String variationFilePath = directoryBundle.getString("variationPath") + variationName;

    //unpack config properties file
    ResourceBundle modelConfig = ResourceBundle.getBundle(
        variationFilePath + directoryBundle.getString("configPath"));//).replaceAll("/", ".") );

    String currentFile = null;

    try {
      //create the game die
      Die myDie = (Die) Class.forName(modelConfig.getString(configValueBundle.getString("die")))
          .getConstructor().newInstance();

      //create GameData and FunctionExecutor objects
      GameData gameData = new GameData();
      FunctionExecutor functionExecutor = new FunctionExecutor(gameData, myDie, displayComm);

      //create parsers
      propertyParser = new PropertyParser();
      actionSequenceParser = new ActionSequenceExecutor(functionExecutor, gameData, displayComm);
      cardParser = new CardParser(actionSequenceParser, displayComm);
      tileParser = new TileParser(actionSequenceParser, gameData, displayComm);
      BoardParser myBoardParser = new BoardParser();

      //parse all player names into list and create player manager
      currentFile = directoryBundle.getString("playerNames");
      List<Player> myPlayers = new ArrayList<>();
      for (int i = 0; i < 4; i++) {
        myPlayers.add(new Player(""));
      }

      PlayerManager playerManager = (PlayerManager) Class.forName(
              modelConfig.getString(configValueBundle.getString("playerManager")))
          .getConstructor(List.class).newInstance(myPlayers);

      //parse card files into decks
      currentFile = directoryBundle.getString("chanceCardFiles");
      Deck chanceDeck = new Deck("Chance", cardParser.parseCards(variationFilePath + currentFile));
      currentFile = directoryBundle.getString("communityChestCards");
      Deck communityChestDeck = new Deck("Community Chest",
          cardParser.parseCards(variationFilePath + currentFile));

      //combine decks into list, and give to gameData
      List<Deck> deckList = new ArrayList<>();
      deckList.add(chanceDeck);
      deckList.add(communityChestDeck);
      gameData.setDeckManager(deckList);

      //parse monopoly properties into a list
      currentFile = directoryBundle.getString("propertyFiles");
      List<Property> myPropertyList = propertyParser.parseProperties(
          variationFilePath + currentFile);

      //parse all board elements (properties, action tiles, card tiles) into a map
      currentFile = directoryBundle.getString("tileFiles");
      Map<String, PropertyTileModel> propertyTileList = tileParser.parsePropertyTiles(
          myPropertyList);
      Map<String, TileModel> nonPropertyTileList = tileParser.parseNonPropertyTiles(
          variationFilePath + currentFile);
      Map<String, TileModel> tileModelMap = new HashMap<>() {{
        putAll(propertyTileList);
        putAll(nonPropertyTileList);
      }};

      //parse board file into an ordered list of TileModels, then construct board manager.
      currentFile = directoryBundle.getString("boardPath");
      List<TileModel> myTiles = myBoardParser.parseBoard(
          directoryBundle.getString("dataPath") + variationFilePath + currentFile + variationName
              + configValueBundle.getString("boardExtension"), tileModelMap);
      BoardManager myBoardManager = (BoardManager) Class.forName(modelConfig.getString(
              configValueBundle.getString("boardManager"))).getConstructor(List.class)
          .newInstance(myTiles);

      //create the game die
      myDie = (Die) Class.forName(modelConfig.getString(configValueBundle.getString("die")))
          .getConstructor().newInstance();

      //set necessary information in gameData
      gameData.setGameData(playerManager, myBoardManager, myDie);

      return gameData;

    } catch (Exception e) {
      e.printStackTrace();
      e.getCause();
      throw new ImproperlyFormattedFile(variationFilePath + currentFile);
    }
  }
}
