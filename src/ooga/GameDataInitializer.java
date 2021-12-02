package ooga;


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
import ooga.util.parsers.PlayerParser;
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

  //TODO: Replace these with a config file
  public static final String PLAYER_NAMES = "/players/players.info";
  public static final String STARTING_VALUES = "/players/starting_values.info";
  public static final String PROPERTIES = "/properties";
  public static final String TILES = "/board/tiles";
  public static final String CHANCE = "/cards/chance";
  public static final String COMMUNITY_CHEST = "/cards/community_chest";
  public static final String BOARD = "/board/";
  public static final String BOARD_SUFFIX = ".board";
  public static final String CONFIG = "/config";
  public static final String DATA_PATH = "data/";
  public static final String VARIATION_PATH = "variations/";
  public static final String PLAYER_MANAGER = "PlayerManager";
  public static final String BOARD_MANAGER = "BoardManager";
  public static final String DIE = "Die";

  public static PropertyParser propertyParser;
  public static CardParser cardParser;
  public static TileParser tileParser;
  public static ActionSequenceParser actionSequenceParser;

  public static Object playerManager;
  public static Deck chanceDeck;
  public static Deck communityChestDeck;

  public static GameData generateGameData(String variationName, DisplayComm displayComm)
      throws ImproperlyFormattedFile {

    String variationFilePath = VARIATION_PATH + variationName;

    //create GameData and FunctionExecutor objects
    GameData gameData = new GameData();
    FunctionExecutor functionExecutor = new FunctionExecutor();

    //unpack config properties file
    ResourceBundle modelConfig = ResourceBundle.getBundle(variationFilePath + CONFIG);//).replaceAll("/", ".") );

    String currentFile = null;

    try {


      //create parsers
      propertyParser = new PropertyParser();
      actionSequenceParser = new ActionSequenceParser(functionExecutor,gameData);
      cardParser = new CardParser(actionSequenceParser);
      tileParser = new TileParser(actionSequenceParser,gameData, displayComm);
      BoardParser myBoardParser = new BoardParser();

      //parse all player names into list and create player manager
      currentFile = PLAYER_NAMES;
      List<Player> myPlayers = new ArrayList<>();
      for (int i = 0; i < 4; i++) {
        myPlayers.add(new Player(""));
      }
      playerManager = Class.forName(modelConfig.getString(PLAYER_MANAGER)).getConstructor(List.class).newInstance(myPlayers);

      //parse card files into decks
      currentFile = CHANCE;
      chanceDeck = new Deck("Chance",cardParser.parseCards(variationFilePath + currentFile));
      currentFile = COMMUNITY_CHEST;
      communityChestDeck = new Deck ("Community Chest",cardParser.parseCards(variationFilePath + currentFile));

      //combine decks into list, and give to gameData
      List<Deck> deckList = new ArrayList<>();
      deckList.add(chanceDeck);
      deckList.add(communityChestDeck);
      gameData.setDeckManager(deckList);

      //parse monopoly properties into a list
      currentFile = PROPERTIES;
      List<Property> myPropertyList = propertyParser.parseProperties(variationFilePath + currentFile);

      //parse all board elements (properties, action tiles, card tiles) into a map
      currentFile = TILES;
      Map<String,PropertyTileModel> propertyTileList = tileParser.parsePropertyTiles(myPropertyList);
      Map<String,TileModel> nonPropertyTileList = tileParser.parseNonPropertyTiles(variationFilePath + currentFile);
      Map<String,TileModel> tileModelMap = new HashMap<>() {{
        putAll(propertyTileList);
        putAll(nonPropertyTileList);
      }};

      //parse board file into an ordered list of TileModels, then construct board manager.
      currentFile = BOARD;
      List<TileModel> myTiles = myBoardParser.parseBoard(DATA_PATH + variationFilePath + currentFile + variationFilePath + BOARD_SUFFIX,tileModelMap);
      BoardManager myBoardManager = (BoardManager) Class.forName(modelConfig.getString(BOARD_MANAGER)).getConstructor(List.class).newInstance(myTiles);

      //create the game die
      Die myDie = (Die) Class.forName(modelConfig.getString(DIE)).getConstructor().newInstance();

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
