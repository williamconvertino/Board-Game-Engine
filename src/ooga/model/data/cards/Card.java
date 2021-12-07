package ooga.model.data.cards;


import ooga.display.communication.DisplayComm;
import ooga.model.data.player.Player;
import ooga.model.game_handling.commands.ActionSequence;

/**
 * A class to represent the game's commands cards.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class Card {

  //The name of the card.
  private final String myName;

  //A description of the card.
  private final String myDescription;

  //An action sequence to execute.
  private final ActionSequence myActionSequence;

  //The display communication module of this class.
  private final DisplayComm displayComm;

  public Card(String myName, String myDescription, ActionSequence actionSequence,
      DisplayComm displayComm) {
    this.myName = myName;
    this.myDescription = myDescription;
    this.myActionSequence = actionSequence;
    this.displayComm = displayComm;
  }

  /**
   * Executes the card's command list with the active player as the subject.
   *
   * @param player the player who is executing the card.
   */
  public void execute(Player player) {
    myActionSequence.execute(player);
    displayComm.displayCard(this);
  }

  /**
   * Returns the name of the card.
   *
   * @return the name of the card.
   */
  public String getName() {
    return myName;
  }

  /**
   * Returns the description of the card.
   *
   * @return the description of the card.
   */
  public String getDescription() {
    return myDescription;
  }

}