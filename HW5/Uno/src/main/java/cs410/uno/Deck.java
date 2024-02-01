package cs410.uno;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents the state of the Uno game.
 */
public class Deck {

    private List<Card> cards;
    /**
     * Constructs a new Uno deck based on specified card counts.
     *
     * @param countDigitCardsPerColor    The number of digit cards per color.
     * @param countSpecialCardsPerColor  The number of special cards per color.
     * @param countWildCards             The number of wild cards.
     */
    public Deck(int countDigitCardsPerColor, int countSpecialCardsPerColor, int countWildCards) {
        cards = new ArrayList<>();
        // Create and add Uno cards to the deck based on the specified counts
        // You'll need to populate the deck with normal, special, and wild cards
        populateDeck(countDigitCardsPerColor, countSpecialCardsPerColor, countWildCards);
    }

    private void populateDeck(int countDigitCardsPerColor, int countSpecialCardsPerColor, int countWildCards) {
        // Populate the deck with normal cards
        cards = new ArrayList<>();

        // Populate the deck with normal cards
        for (Card.CardColor color : Card.CardColor.values()) {
            if (color != Card.CardColor.WILD) {
                cards.addAll(generateDigitCards(color, countDigitCardsPerColor));
                cards.addAll(generateSpecialCards(color, countSpecialCardsPerColor));
            }
        }

        // Populate the deck with wild cards
        cards.addAll(generateWildCards(countWildCards));
    }

    private List<Card> generateDigitCards(Card.CardColor color, int count) {
        List<Card> digitCards = new ArrayList<>();
        for (int digit = 0; digit < count; digit++) {
            digitCards.add(new Card(color, Card.CardType.NORMAL, String.valueOf(digit)));
        }
        return digitCards;
    }

    private List<Card> generateSpecialCards(Card.CardColor color, int count) {
        List<Card> specialCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            specialCards.add(new Card(color, Card.CardType.SKIP, "SKIP"));
            specialCards.add(new Card(color, Card.CardType.REVERSE, "REVERSE"));
            specialCards.add(new Card(color, Card.CardType.DRAW_TWO, "DRAW_TWO"));
        }
        return specialCards;
    }

    private List<Card> generateWildCards(int count) {
        List<Card> wildCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            wildCards.add(new Card(Card.CardColor.WILD, Card.CardType.WILD, "WILD"));
        }
        return wildCards;
    }

    public void shuffle() {
        // Implement card shuffling logic
        Collections.shuffle(cards);
    }

    public List<Card> getCards() {
        return cards;
    }
}