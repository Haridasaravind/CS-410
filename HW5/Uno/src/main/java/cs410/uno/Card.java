package cs410.uno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a card in the Uno game.
 */
public class Card {
    /**
     * Enumerates possible colors of Uno cards.
     */
    public enum CardColor {
        RED, YELLOW, GREEN, BLUE, WILD
    }
    /**
     * Enumerates possible types of Uno cards.
     */
    public enum CardType {
        NORMAL, SKIP, REVERSE, DRAW_TWO, WILD
    }
    /**
     * Constructs a new Uno card with the specified color, type, and value.
     *
     * @param color The color of the card.
     * @param type  The type of the card.
     * @param value The value of the card.
     */
    private CardColor color;
    private CardType type;
    private String value;

    public Card(CardColor color, CardType type, String value) {
        this.color = color;
        this.type = type;
        this.value = value;
    }
    /**
     * Gets the color of the card.
     *
     * @return The color of the card.
     */
    public CardColor getColor()
    {
        return color;
    }
    /**
     * Card card = new Card(Card.CardColor.RED, Card.CardType.NORMAL, "1");
     * Card.CardColor color = card.getColor();
     * color now contains Card.CardColor.RED
     *
     * @param color The new color of the card.
     */
    public void setColor(CardColor color)
    {
        this.color = color;
    }

    /**
     * Gets the type of the card.
     *
     * @return The type of the card.
     */
    public CardType getType() {
        return type;
    }

    /**
     * Sets the type of the card.
     *
     * @param type The new type of the card.
     */
    public void setType(CardType type) {
        this.type = type;
    }

    /**
     * Gets the value of the card.
     *
     * @return The value of the card.
     */
    public String getValue() {
        return value;
    }
    /**
     * Sets the value of the card.
     *
     * @param value The new value of the card.
     */
    public void setValue(String value)
    {
        this.value = value;
    }
}



