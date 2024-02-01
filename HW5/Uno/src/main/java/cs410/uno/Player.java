package cs410.uno;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a player in the Uno game.
 */
public class Player {
    // Class details...

    /**
     * Constructs a new Uno player with the specified name.
     *
     * @param name The name of the player.
     */

    private String P1;  //
    private List<Card> H1;  //

    /**
     * Constructs a new Uno player with the specified name.
     *
     * @param name The name of the player.
     */

    public Player(String name) {
        this.P1 = name;
        H1 = new ArrayList<>();
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return P1;
    }

    /**
     * Player player = new Player("John");
     * String playerName = player.getName();
     * playerName now contains "John".
     */
    public List<Card> getHand() {
        return H1;
    }
    /**
     * Sets the hand of the player.
     * List<Card> playerHand = player.getHand();
     * @param hand The new hand of the player.
     */
    public void setHand(List<Card> hand) {
        this.H1 = hand;
    }
    /**
     * Plays a card from the list of playable cards.
     *
     * @param playableCards The list of playable cards.
     * @return The card played by the player.
     */
    public Card playCard(List<Card> playableCards) {
        return playableCards.get(0);
    }
}