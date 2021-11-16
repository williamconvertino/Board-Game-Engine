package ooga.model.game_handling;


import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ooga.exceptions.AttributeNotFoundException;
import ooga.model.data.cards.Card;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.OriginalPlayerManager;
import ooga.model.data.player.Player;
import ooga.model.data.player.PlayerManager;
import ooga.model.data.properties.Property;
import ooga.model.die.OriginalDice;
import ooga.model.game_handling.parsers.CardParser;
import ooga.model.game_handling.parsers.PropertyParser;

/**
 * This class parses all configuration files and preps program for game.
 *
 * @author Casey Goldstein
 * @author William Convertino
 *
 * @since 0.0.1
 */
public class GameInitializer {

  private static final String PROPERTIES_FOLDER_PATH = "monopoly_original/board/properties";

  private CardParser myCardParser;
  private PropertyParser myPropertyParser;
  private OriginalPlayerManager playerManager;
  private ArrayList<Player> playerList;
  private OriginalDice dice;
  private GameData gameData;
  private ArrayList<Property> propertyList;

  //NOTE: THIS WILL BE GENERALIZED OUTSIDE OF ORIGINAL VERSION ONCE EVERYTHING IS RUNNING
  public GameInitializer(){
    myCardParser = new CardParser();
    myPropertyParser = new PropertyParser();
    playerList = new ArrayList<>();
    playerList.add(new Player ("Henry"));
    playerList.add(new Player ("Jordan"));
    playerList.add(new Player ("Will"));
    playerList.add(new Player ("Aaric"));
    playerManager = new OriginalPlayerManager(playerList);
    dice = new OriginalDice();
    propertyList = new ArrayList<>();

  }

  public void initialize(){
    try{
      propertyList = myPropertyParser.parseProperties(PROPERTIES_FOLDER_PATH);
      for (Property property: propertyList){
        System.out.println(property);
      }
    }
    catch (AttributeNotFoundException e) {
      Alert errorAlert = new Alert(AlertType.ERROR);
      errorAlert.setHeaderText("Missing Information");
      errorAlert.setContentText("Please check files for missing information");
      errorAlert.showAndWait();
    }

  }
}
