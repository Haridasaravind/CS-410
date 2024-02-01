package cs410.uno;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CardTest {
    // Test the corresponding accessor methods.
    @Test
    void getColor() {
        // Test getting the card's color
        Card card = new Card(Card.CardColor.RED, Card.CardType.NORMAL, "1");
        assertEquals(Card.CardColor.RED, card.getColor());
    }

    @Test
    void getType() {
        // Test getting the card's type
        Card card = new Card(Card.CardColor.RED, Card.CardType.NORMAL, "1");
        assertEquals(Card.CardType.NORMAL, card.getType());
    }

    @Test
    void getValue() {
        // Test getting the card's value
        Card card = new Card(Card.CardColor.RED, Card.CardType.NORMAL, "1");
        assertEquals("1", card.getValue());
    }

    //setColor(CardColor color), setType(CardType type), setValue(String value): Test the corresponding mutator methods.
    @Test
    void setColor() {
        // Test setting the card's color
        Card card = new Card(Card.CardColor.RED, Card.CardType.NORMAL, "1");
        card.setColor(Card.CardColor.BLUE);
        assertEquals(Card.CardColor.BLUE, card.getColor());
    }

    @Test
    void setType() {
        // Test setting the card's type
        Card card = new Card(Card.CardColor.RED, Card.CardType.NORMAL, "1");
        card.setType(Card.CardType.SKIP);

        assertEquals(Card.CardType.SKIP, card.getType());
    }

    @Test
    void setValue() {
        // Test setting the card's value
        Card card = new Card(Card.CardColor.RED, Card.CardType.NORMAL, "1");
        card.setValue("2");
        assertEquals("2", card.getValue());
    }
}
