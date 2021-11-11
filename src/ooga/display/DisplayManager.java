package ooga.display;

import ooga.display.communication.DisplayStateSignaler;
import ooga.display.communication.EventManager;
import ooga.model.data.player.Player;

/**
 * This class manages the display elements of the program.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public abstract class DisplayManager {

    /**
     * Default constructor
     */
    public DisplayManager(EventManager events) {
    }


    public abstract void signalState(DisplayStateSignaler.States s);

}