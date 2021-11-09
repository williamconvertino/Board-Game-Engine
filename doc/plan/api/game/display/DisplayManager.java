package display;

import java.util.*;
import model.data.game_data.Player;

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
    public DisplayManager() {
    }




    /**
     * @param player
     */
    public abstract void setActivePlayer(Player player);

}