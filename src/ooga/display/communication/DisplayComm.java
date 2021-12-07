package ooga.display.communication;

import javafx.scene.control.Alert.AlertType;
import ooga.display.DisplayManager;
import ooga.display.communication.DisplayStateSignaler.State;
import ooga.exceptions.PlayerWarning;
import ooga.model.data.cards.Card;
import ooga.model.data.player.Player;
import ooga.model.data.tilemodels.ActionTileModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The display communication which shows exceptions
 *
 * @author William Convertino
 */
public class DisplayComm {

  private static final Logger LOG = LogManager.getLogger(DisplayComm.class);

  private final DisplayManager displayManager;
  private final DisplayStateSignaler signaler;


  /**
   * The constructor to make a exception handler in DisplayComm
   */
  public DisplayComm(DisplayManager dm) {
    this.displayManager = dm;
    this.signaler = new DisplayStateSignaler(dm);
  }

  /**
   * Handles showing the exception passed in
   *
   * @param e
   */
  public void showException(Exception e) {
    if (e instanceof PlayerWarning) {
      displayManager.showAlert(((PlayerWarning) e).getDescription(), AlertType.ERROR);
    } else {
      LOG.error(String.format("%s", e.toString()));
    }
  }

  /**
   * Signals the display that a new state has been reached.
   *
   * @param state the state that has been reached.
   */
  public void signalState(State state) {
    signaler.signalDisplay(state);
  }

  /**
   * Displays the specified card.
   *
   * @param card the card to display.
   */
  public void displayCard(Card card) {
    displayManager.showAlert(
        String.format("You have drawn the following card:\n\n %s", card.getDescription()),
        AlertType.INFORMATION);
  }

  /**
   * Displays the specified tile.
   *
   * @param tile the tile to display.
   */
  public void displayActionTile(ActionTileModel tile) {
    displayManager.showAlert(String.format("[%s] - %s", tile.getName(), tile.getDescription()),
        AlertType.INFORMATION);
  }

  /**
   * Shows an alert that a player has lost.
   *
   * @param player the player who has lost.
   */
  public void displayPlayerLose(Player player) {
    displayManager.showAlert(String.format("Player: %s has lost the game", player.getName()),
        AlertType.INFORMATION);
  }

}
