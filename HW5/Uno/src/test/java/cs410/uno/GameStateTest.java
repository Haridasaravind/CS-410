package cs410.uno;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    private Deck getShuffleDeck() {
        Deck deck = new Deck(10, 3, 8);
        deck.shuffle();
        return deck;
    }

    @Test
    void testStartGame() {
        int countPlayers = 3;
        int countInitialCardsPerPlayer = 7;
        int countDigitCardsPerColor = 2;
        int countSpecialCardsPerColor = 1;
        int countWildCards = 4;

        GameState gameState = GameState.startGame(
                countPlayers,
                countInitialCardsPerPlayer,
                countDigitCardsPerColor,
                countSpecialCardsPerColor,
                countWildCards
        );

        assertNotNull(gameState);
        assertEquals(countPlayers, gameState.getPlayers().size());
        for (Player player : gameState.getPlayers()) {
            if (player != gameState.getCurrentPlayer())
                assertEquals(countInitialCardsPerPlayer, player.getHand().size());
            else
                assertEquals(countInitialCardsPerPlayer + 1, player.getHand().size());
        }
        assertNotNull(gameState.getTopCard());
        assertFalse(gameState.isGameOver());
    }

    @Test
    void testRunOneTurn() {
        Deck deck = getShuffleDeck();
        // Create a simple game state with one player and a single card in the discard pile
        GameState gameState = new GameState(deck.getCards());
        Player player = new Player("TestPlayer");
        player.setHand(List.of(new Card(Card.CardColor.RED, Card.CardType.NORMAL, "1")));
        gameState.getPlayers().add(player);
        gameState.discardPile.add(new Card(Card.CardColor.RED, Card.CardType.NORMAL, "2"));

        // Run a turn
        gameState.runOneTurn();

        // The player should play the only playable card in their hand
        assertTrue(gameState.getTopCard().getColor() == Card.CardColor.RED || gameState.getTopCard().getType() == Card.CardType.NORMAL);
        assertEquals(0, player.getHand().size());
        assertTrue(gameState.isGameOver());
    }

    @Test
    void testRefreshDrawPile() {
        // Create a game state with an empty draw pile, a discard pile with one card
        GameState gameState = new GameState(new ArrayList<>());
        gameState.discardPile.add(new Card(Card.CardColor.GREEN, Card.CardType.SKIP, "Skip"));
        gameState.discardPile.add(new Card(Card.CardColor.GREEN, Card.CardType.SKIP, "Skip"));

        // Refresh the draw pile
        gameState.refreshDrawPile();

        // The draw pile should contain one card (the card from the discard pile)
        assertEquals(1, gameState.drawPile.size());
        assertEquals(Card.CardColor.GREEN, gameState.drawPile.get(0).getColor());
        assertEquals(Card.CardType.SKIP, gameState.drawPile.get(0).getType());
    }
}
