package cs410.uno;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class PlayerTest {


    //Ensures that a player is correctly initialized with the provided name,
    // an empty hand, and the ability to retrieve the player's name.
    @Test
    void testPlayerInitialization() {
        Player player = new Player("TestPlayer");
        assertNotNull(player);
        assertEquals("TestPlayer", player.getName());
        assertNotNull(player.getHand());
        assertTrue(player.getHand().isEmpty());
    }

    //Tests the setHand method by setting a new hand for a player
    // and verifying that the player's hand is updated accordingly.
    @Test
    void testSetHand() {
        Player player = new Player("TestPlayer");
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(Card.CardColor.RED, Card.CardType.NORMAL, "1"));
        hand.add(new Card(Card.CardColor.BLUE, Card.CardType.NORMAL, "5"));
        player.setHand(hand);
        assertEquals(hand, player.getHand());
    }

    //Tests the getName method by creating a player and verifying
    // that the returned name matches the expected name.
    @Test
    void testGetName() {
        Player player = new Player("TestPlayer");
        assertEquals("TestPlayer", player.getName());
    }

    //Tests the getHand method by setting a new hand for a player
    // and verifying that the returned hand matches the expected hand.
    @Test
    void testGetHand() {
        Player player = new Player("TestPlayer");
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(Card.CardColor.GREEN, Card.CardType.NORMAL, "8"));
        hand.add(new Card(Card.CardColor.YELLOW, Card.CardType.NORMAL, "2"));
        player.setHand(hand);
        assertEquals(hand, player.getHand());
    }
}