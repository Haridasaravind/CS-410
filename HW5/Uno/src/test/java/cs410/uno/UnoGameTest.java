package cs410.uno;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnoGameTest {

    @Test
    void playGame() {
        // Test playing a game
        GameState gameState = GameState.startGame(4, 7, 2, 1, 4);
        UnoGame unoGame = new UnoGame(gameState);

        // Simulate playing the game (might need to customize based on your game logic)
        unoGame.playGame();

        // Ensure the game is over and there is a winner
        assertTrue(gameState.isGameOver());
        assertNotNull(gameState.getWinner());
    }
}