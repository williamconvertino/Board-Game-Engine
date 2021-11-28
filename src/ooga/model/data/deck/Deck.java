package ooga.model.data.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import ooga.model.data.cards.Card;

/**
 * A data structure that stores a list of executable cards.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public class Deck {

    //The name of the deck.
    private String myName;

    //A list of all the cards that belong to the deck.
    private List<Card> myCardList;

    //A list of all the cards that are available to be drawn.
    private LinkedList<Card> availableCards;

    public Deck(String myName, List<Card> myCards) {
        this.myName = myName;
        this.myCardList = myCards;
        resetDeck();
    }

    public void setDeck(List<Card> cards){
        myCardList = cards;
    }

    /**
     *  Removes a random card from the deck and returns it.
     *
     * @return a random card from the deck.
     */
    public Card drawCard() {
        if (isEmpty()) {
            return null;
        }
        return availableCards.pop();
    }

    //Resets the deck so that it contains each of the cards in myCardList, then shuffles.
    private void resetDeck() {
        List<Card> myCards = new ArrayList<>(myCardList);
        Collections.shuffle(myCards);
        availableCards = new LinkedList<>(myCards);
    }

    /**
     * Returns true if the deck is out of cards, and false otherwise.
     *
     * @return true if the deck is out of cards, and false otherwise.
     */
    public boolean isEmpty(){
        if (availableCards.isEmpty()) {
            resetDeck();
        }
        return availableCards.isEmpty();
    }

    /**
     * Return the name of this deck.
     *
     * @return the name of this deck.
     */
    public String getName() {
        return myName;
    }

    public List<Card> getCards() {return myCardList;}

}