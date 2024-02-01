package cs410.uno;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class DeckTest {

    @Test
    public void testInitialization() {
        Deck deck = new Deck(2, 1, 4);
        List<Card> cards = deck.getCards();

        // Check if the deck is not null
        assertNotNull(deck);

        // Check if the deck is not empty
        assertFalse(cards.isEmpty());

        // Check if the total number of cards is correct
        assertEquals(4 * 10 + 1 * (3 * 4) + 4 * 1, cards.size());
    }

    @Test
    public void testShuffling() {
        Deck deckBeforeShuffle = new Deck(2, 1, 4);
        Deck deckAfterShuffle = new Deck(2, 1, 4);

        // Shuffle the deck
        deckAfterShuffle.shuffle();

        // Check if the order of cards has changed after shuffling
        assertNotEquals(deckBeforeShuffle.getCards(), deckAfterShuffle.getCards());
    }
}
