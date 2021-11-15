package ooga.model.data.cards;


import ooga.model.data.player.Player;
import ooga.model.game_handling.commands.ActionSequence;

/**
 * A class to represent the game's commands cards.
 *
 * @author William Convertino
 *
 * @since 0.0.1
 */
public class Card {

    //The name of the card.
    private String myName;

    //A description of the card.
    private String myDescription;

    //An action sequence to execute.
    private ActionSequence myActionSequence;

    public Card(String myName, String myDescription, ActionSequence actionSequence) {
        this.myName = myName;
        this.myDescription = myDescription;
        this.myActionSequence = actionSequence;
    }

    /**
     * Executes the card's command list with the active player as the subject.
     *
     * @param player the player who is executing the card.
     */
    public void execute(Player player) {
        myActionSequence.execute(player);
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