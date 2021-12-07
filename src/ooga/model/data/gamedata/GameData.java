package ooga.model.data.gamedata;

import java.util.List;
import ooga.exceptions.NoRemainingPlayersException;
import ooga.model.data.deck.Deck;
import ooga.model.data.deck.DeckManager;
import ooga.model.data.player.Player;
import ooga.model.data.player.PlayerManager;
import ooga.model.die.Die;
import ooga.model.game_handling.board_manager.BoardManager;

/**
 * A class to store the data of the current game. This includes players and all their associated
 * info, as well as the board_manager and its state.
 *
 * @author William Convertino
 * @author Casey Goldstein
 * @since 0.0.1
 */
public class GameData {

  //The current game board_manager.
  private BoardManager myBoard;

  //A class to keep track of the players in the game.
  private PlayerManager myPlayers;

  //A class to keep track of the active decks in the game.
  private DeckManager myDecks;

  //The player whose turn it currently is.
  private Player currentPlayer;

  //The number of rolls this turn.
  private int numRolls;

  //The previous roll of this turn.
  private int previousRoll;

  //The die being used to generate player rolls.
  private Die myDie;

  //The maximum number of rolls that a player can roll this turn.
  private int maxRolls;

  /**
   * Constructs a new GameData.
   */
  public GameData() {
    this.maxRolls = 1;
  }

  /**
   * Constructs a new GameData with the specified board, players, and die. Used for testing.
   *
   * @param players the players in the game.
   * @param board   the board on which the game is played.
   * @param die     the die with which the game is played.
   */
  public GameData(PlayerManager players, BoardManager board, Die die) {
    this.myPlayers = players;
    this.myBoard = board;
    this.myDie = die;
    setNextPlayer();
    resetTurnData();
  }

  /**
   * Sets the game data general information.
   *
   * @param players
   * @param board
   * @param die
   */
  public void setGameData(PlayerManager players, BoardManager board, Die die) {
    this.myPlayers = players;
    this.myBoard = board;
    this.myDie = die;
    setNextPlayer();
    resetTurnData();
  }

  /**
   * Returns the current board.
   *
   * @return the current board.
   */
  public BoardManager getBoard() {
    return myBoard;
  }

  /**
   * Switches the current player to the next player in the order.
   *
   * @throws NoRemainingPlayersException if no players remain in the game.
   */
  public void setNextPlayer() {
    try {
      this.currentPlayer = myPlayers.getNextPlayer();
    } catch (NoRemainingPlayersException e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates the deck manager with given list of decks.
   *
   * @param decks
   */
  public void setDeckManager(List<Deck> decks) {
    myDecks = new DeckManager();
    for (Deck deck : decks) {
      myDecks.addDeck(deck);
    }
  }

  /**
   * Resets the data pertaining to the current turn.
   */
  public void resetTurnData() {
    this.numRolls = 0;
    this.previousRoll = -1;
    this.maxRolls = 1;
  }

  /**
   * Returns the current player.
   *
   * @return the current player.
   */
  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  /**
   * Returns the number of rolls this turn.
   *
   * @return the number of rolls this turn.
   */
  public int getNumRolls() {
    return numRolls;
  }

  public int getMaxRolls() {
    return maxRolls;
  }

  public void incrementMaxRolls() {
    maxRolls++;
  }

  /**
   * Adds to the total number of rolls.
   */
  public void addRoll() {
    this.numRolls++;
  }

  /**
   * Returns the previous roll this turn.
   *
   * @return the previous roll this turn.
   */
  public int getPreviousRoll() {
    return previousRoll;
  }

  /**
   * Returns the current die state.
   *
   * @return the current die state.
   */
  public Die getDie() {
    return myDie;
  }

  /**
   * Returns the game's active decks.
   *
   * @return the game's active decks.
   */
  public DeckManager getDecks() {
    return myDecks;
  }

  /**
   * Returns a list of all the players in the game.
   *
   * @return a list of all the players in the game.
   */
  public List<Player> getPlayers() {
    return myPlayers.getPlayers();
  }

}