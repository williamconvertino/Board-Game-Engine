package ooga.model.data.cards;


import java.lang.reflect.Method;
import java.util.List;
import ooga.model.data.player.Player;

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

    //A list of commands to execute. //TODO: Make the command list functional.
    private List<Method> commandList;

    public Card(String myName, String myDescription, List<Method> commandList) {
        this.myName = myName;
        this.myDescription = myDescription;
        this.commandList = commandList;
    }

    /**
     * Executes the card's command list with the active player as the subject.
     *
     * @param player the player who is executing the card.
     */
    public void execute(Player player) {

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