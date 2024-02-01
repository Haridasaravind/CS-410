package cs410.uno;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Uno game controller.
 */
class UnoGame {

    private GameState gameState;
    /**
     * Constructs a new Uno game with the provided game state.
     * Manages the overall flow of the Uno game until it is completed.
     * @param gameState The initial game state.
     */

    public UnoGame(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Plays the Uno game until completion.
     * Continues as long as the game is not over (!gameState.isGameOver()).
     */

    public void playGame() {
        while (!gameState.isGameOver()) {
            Player currentPlayer = gameState.getCurrentPlayer();

            // Check for playable cards in the player's hand
            List<Card> playableCards = gameState.getPlayableCards(currentPlayer.getHand(), gameState.getTopCard());

            if (!playableCards.isEmpty()) {
                // Allow the player to play a card
                Card cardToPlay = currentPlayer.playCard(playableCards);

                // Apply the card effect
                gameState.playCard(currentPlayer, cardToPlay);

                // Check for special card effects and apply them
                gameState.applySpecialCardEffects(cardToPlay);

                // Determine the next player based on the order of play
                Player nextPlayer = gameState.getNextPlayer();

                // (e.g., reversing, skipping)  Update the game state
                gameState.setCurrentPlayer(nextPlayer);
            } else {
                // Player has no playable cards, draw a card from the draw pile
                Card drawnCard = gameState.drawCardFromDrawPile();
                Player nextPlayer = gameState.getNextPlayer();

                // Update the game state
                gameState.setCurrentPlayer(nextPlayer);
            }
        }
        // The loop exits when the game is over.
        // Retrieves the winner from the game state.
        Player winner = gameState.getWinner();
    }
}

