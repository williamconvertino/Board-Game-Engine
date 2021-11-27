package ooga;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ooga.exceptions.AttributeNotFoundException;
import ooga.exceptions.ImproperlyFormattedFile;
import ooga.model.data.cards.Card;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.OriginalPlayerManager;
import ooga.model.data.player.Player;
import ooga.model.data.player.PlayerManager;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.EmptyTileModel;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.die.Die;
import ooga.model.die.OriginalDice;
import ooga.model.game_handling.board_manager.BoardManager;
import ooga.model.game_handling.board_manager.OriginalBoardManager;
import ooga.model.game_handling.commands.ActionSequenceParser;
import ooga.model.game_handling.parsers.BoardParser;
import ooga.model.game_handling.parsers.CardParser;
import ooga.model.game_handling.parsers.PlayerParser;
import ooga.model.game_handling.parsers.PropertyParser;

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
  public static final String PROPERTIES = "/board/properties";
  public static final String TILES = "/board/tiles";
  public static final String CONFIG = "/config";

  public static final String PLAYER_MANAGER = "PlayerManager";
  public static final String BOARD_MANAGER = "BoardManager";
  public static final String DIE = "Die";


  public static PropertyParser propertyParser;

  public static Object playerManager;
  private ArrayList<Player> playerList;
  private OriginalDice dice;
  private GameData gameData;
  private ArrayList<Property> propertyList;
//
//  //NOTE: THIS WILL BE GENERALIZED OUTSIDE OF ORIGINAL VERSION ONCE EVERYTHING IS RUNNING
//  public GameDataInitializer(){
//
//    myPropertyParser = new PropertyParser();
//    playerList = new ArrayList<>();
//    playerList.add(new Player ("Henry"));
//    playerList.add(new Player ("Jordan"));
//    playerList.add(new Player ("Will"));
//    playerList.add(new Player ("Aaric"));
//    playerManager = new OriginalPlayerManager(playerList);
//    dice = new OriginalDice();
//    propertyList = new ArrayList<>();
//
//  }
//
//  public void initialize(){
//    try{
//      propertyList = myPropertyParser.parseProperties(PROPERTIES_FOLDER_PATH);
//      for (Property property: propertyList){
//        System.out.println(property);
//      }
//    }
//    catch (AttributeNotFoundException e) {
//      Alert errorAlert = new Alert(AlertType.ERROR);
//      errorAlert.setHeaderText("Missing Information");
//      errorAlert.setContentText("Please check files for missing information");
//      errorAlert.showAndWait();
//    }
//
//  }


  public static GameData generateGameData(String variationFilePath)
      throws ImproperlyFormattedFile, AttributeNotFoundException, IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

    ResourceBundle modelConfig = ResourceBundle.getBundle(variationFilePath.substring(5,variationFilePath.length()) + CONFIG);//).replaceAll("/", ".") );

    String currentFile = null;

    try {
      currentFile = PLAYER_NAMES;
      List<Player> myPlayers = PlayerParser.getPlayersFromFile(variationFilePath + PLAYER_NAMES);

      currentFile = CONFIG;
      System.out.println(modelConfig.getString(PLAYER_MANAGER));
      playerManager = Class.forName(modelConfig.getString(PLAYER_MANAGER)).getConstructor(List.class).newInstance(myPlayers);

      propertyParser = new PropertyParser();

      //TODO: Integrate property parser
      currentFile = PROPERTIES;
      System.out.println(PROPERTIES);
      System.out.println(variationFilePath + currentFile);
      List<Property> myProperties = propertyParser.parseProperties(variationFilePath + currentFile);

      for (Property prop: myProperties){
        System.out.println(prop.getName());
      }

      BoardParser myBoardParser = new BoardParser();
      //List<TileModel> myTiles = myBoardParser.parseBoard(variationFilePath + TILES);

      //FOR TESTING TODO: Remove and replace with parsing.
      //myTiles.add(new EmptyTileModel("t1"));
      //myTiles.add(new EmptyTileModel("t2"));
      //myTiles.add(new EmptyTileModel("t3"));

      //BoardManager myBoardManager = (BoardManager) Class.forName(modelConfig.getString(BOARD_MANAGER)).getConstructor(List.class).newInstance(myTiles);
      //Die myDie = (Die) Class.forName(modelConfig.getString(DIE)).getConstructor().newInstance();

      //GameData myGameData = new GameData(myPlayerManager, myBoardManager, myDie);
      return null;

    } catch (Exception e) {
      e.printStackTrace();
      throw new ImproperlyFormattedFile(variationFilePath + currentFile);
    }
  }


}
