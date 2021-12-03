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
import ooga.model.game_handling.commands.ActionSequenceParser;
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
 *
 * @since 0.0.1
 */
public class GameDataInitializer {

  /**A bundle of the configuration values.**/
  public static ResourceBundle CONFIG_VALUES = ResourceBundle.getBundle(GameDataInitializer.class.getPackageName() + ".ConfigValues");

  /**A bundle of the directory names**/
  public static ResourceBundle DIRECTORY_NAMES = ResourceBundle.getBundle(GameDataInitializer.class.getPackageName() + ".DirectoryNames");

  public static PropertyParser propertyParser;
  public static CardParser cardParser;
  public static TileParser tileParser;
  public static ActionSequenceParser actionSequenceParser;

  public static Object playerManager;
  public static Deck chanceDeck;
  public static Deck communityChestDeck;

  public static GameData generateGameData(String variationName, DisplayComm displayComm)
      throws ImproperlyFormattedFile {

    String variationFilePath = DIRECTORY_NAMES.getString("variationPath") + variationName;

    //create GameData and FunctionExecutor objects
    GameData gameData = new GameData();
    FunctionExecutor functionExecutor = new FunctionExecutor();

    //unpack config properties file
    ResourceBundle modelConfig = ResourceBundle.getBundle(variationFilePath + DIRECTORY_NAMES.getString("configPath"));//).replaceAll("/", ".") );

    String currentFile = null;

    try {


      //create parsers
      propertyParser = new PropertyParser();
      actionSequenceParser = new ActionSequenceParser(functionExecutor,gameData);
      cardParser = new CardParser(actionSequenceParser);
      tileParser = new TileParser(actionSequenceParser,gameData, displayComm);
      BoardParser myBoardParser = new BoardParser();

      //parse all player names into list and create player manager
      currentFile = DIRECTORY_NAMES.getString("playerNames");
      List<Player> myPlayers = new ArrayList<>();
      for (int i = 0; i < 4; i++) {
        myPlayers.add(new Player(""));
      }
      playerManager = Class.forName(modelConfig.getString(CONFIG_VALUES.getString("playerManager"))).getConstructor(List.class).newInstance(myPlayers);

      //parse card files into decks
      currentFile = DIRECTORY_NAMES.getString("chanceCardFiles");
      chanceDeck = new Deck("Chance",cardParser.parseCards(variationFilePath + currentFile));
      currentFile = DIRECTORY_NAMES.getString("communityChestCards");;
      communityChestDeck = new Deck ("Community Chest",cardParser.parseCards(variationFilePath + currentFile));

      //combine decks into list, and give to gameData
      List<Deck> deckList = new ArrayList<>();
      deckList.add(chanceDeck);
      deckList.add(communityChestDeck);
      gameData.setDeckManager(deckList);

      //parse monopoly properties into a list
      currentFile = DIRECTORY_NAMES.getString("propertyFiles");
      List<Property> myPropertyList = propertyParser.parseProperties(variationFilePath + currentFile);

      //parse all board elements (properties, action tiles, card tiles) into a map
      currentFile = DIRECTORY_NAMES.getString("tileFiles");
      Map<String,PropertyTileModel> propertyTileList = tileParser.parsePropertyTiles(myPropertyList);
      Map<String,TileModel> nonPropertyTileList = tileParser.parseNonPropertyTiles(variationFilePath + currentFile);
      Map<String,TileModel> tileModelMap = new HashMap<>() {{
        putAll(propertyTileList);
        putAll(nonPropertyTileList);
      }};

      //parse board file into an ordered list of TileModels, then construct board manager.
      currentFile = DIRECTORY_NAMES.getString("boardPath");
      List<TileModel> myTiles = myBoardParser.parseBoard(
          DIRECTORY_NAMES.getString("dataPath") + variationFilePath + currentFile + variationFilePath + CONFIG_VALUES.getString("boardExtension"),tileModelMap);
      BoardManager myBoardManager = (BoardManager) Class.forName(modelConfig.getString(CONFIG_VALUES.getString("boardManager"))).getConstructor(List.class).newInstance(myTiles);

      //create the game die
      Die myDie = (Die) Class.forName(modelConfig.getString(CONFIG_VALUES.getString("die"))).getConstructor().newInstance();

      //set necessary information in gameData
      gameData.setGameData((PlayerManager)playerManager, myBoardManager, myDie);

      return gameData;

    } catch (Exception e) {
      e.printStackTrace();
      e.getCause();
      throw new ImproperlyFormattedFile(variationFilePath + currentFile);
    }
  }
}
