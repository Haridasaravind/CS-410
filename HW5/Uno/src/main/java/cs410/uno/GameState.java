package cs410.uno;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Represents the state of the Uno game.
 */
public class GameState {
    // Class details
    // Maintains a list of players participating in the Uno game.
    private List<Player> players;

    /**
     * Constructs a new game state with the provided draw pile.
     *
     * drawPile The draw pile for the game.
     * Draw and Discard Piles (drawPile, discardPile):
     * Represents the draw pile and discard pile, respectively, both initialized with provided cards.
     */
    List<Card> drawPile;
    List<Card> discardPile;
    private int currentPlayerIndex;

    GameState(List<Card> drawPile) {
        players = new ArrayList<>();
        drawPile = drawPile;
        discardPile = new ArrayList<>();
        currentPlayerIndex = 0;
    }

    // Initiates a new Uno game by creating a deck, shuffling it, and distributing initial cards to players.
    public static GameState startGame(int countPlayers,
                                      int countInitialCardsPerPlayer,
                                      int countDigitCardsPerColor,
                                      int countSpecialCardsPerColor,
                                      int countWildCards)
    {
        // return new GameState();  FIXME
        // Creates a new deck (Deck class) with specified card counts.
        // Shuffles the deck.

        Deck deck = new Deck(10, 4, 1);
        deck.shuffle();
        // Retrieves the cards from the deck as the draw pile.
        List<Card> drawPile = deck.getCards();
        //Initializes a new GameState with the draw pile.
        GameState gameState = new GameState(drawPile);
        // Adds players to the game, assigns initial hands, and draws the first card for the discard pile.
        // Initialize the players and deal initial hands
        for(int i = 0; i < countPlayers; i++)
        {
            Player player = new Player("Player " + (i + 1));
            gameState.players.add(player);
            gameState.currentPlayerIndex = i;
//            List<Card> hand = new ArrayList<>();
            // Deal initial cards to the player's hand
            for (int j = 0; j < countInitialCardsPerPlayer; j++) {
                // Create and add cards to the hand
                // You will need to implement a method to draw cards from the draw pile
                // and remove them from the draw pile
                Card drawnCard = gameState.drawCardFromDrawPile();
//                hand.add(drawnCard);
            }
//            player.setHand(hand);
        }

        // Draw the first card from the draw pile to start the discard pile
        gameState.discardPile.add(gameState.drawCardFromDrawPile());

        return gameState; // might remove in future
    }
    //Draws a card from the draw pile, adds it to the current player's hand, and returns the drawn card.
    public Card drawCardFromDrawPile() {
        if (drawPile.isEmpty()) {
            refreshDrawPile();
        }

        Card drawnCard = drawPile.remove(0);
        getCurrentPlayer().getHand().add(drawnCard);

        return drawnCard;
    }
    // Refreshes the draw pile when it's empty by shuffling the discard pile, preserving the top card.
    void refreshDrawPile() {
        if (discardPile.isEmpty()) {
            return ;
        }
        Card topCard = discardPile.remove(0);
        drawPile.addAll(discardPile);
        Collections.shuffle(drawPile);
        discardPile.clear();
        discardPile.add(topCard);
    }
    // Checks if any player's hand is empty, indicating the end of the game.
    boolean isGameOver() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }
    //  Executes one turn of the game, allowing the current player to play a card or draw one.
    public void runOneTurn() {
        Player currentPlayer = getCurrentPlayer();
        List<Card> playableCards = getPlayableCards(currentPlayer.getHand(), getTopCard());

        if (!playableCards.isEmpty()) {
            // If there are playable cards, choose one and play it
            Card chosenCard = chooseCardToPlay(playableCards);
            playCard(currentPlayer, chosenCard);
        } else {
            // If there are no playable cards, draw a card and check if it's playable
            Card drawnCard = drawCardFromDrawPile();
            if (isCardPlayable(drawnCard, getTopCard())) {
                playCard(currentPlayer, drawnCard);
            }
        }

        Player nextPlayer = getNextPlayer();
        currentPlayerIndex = players.indexOf(nextPlayer);
    }
    // Retrieves a list of playable cards from the given hand based on the top card of the discard pile.
    List<Card> getPlayableCards(List<Card> hand, Card topCard) {
        List<Card> playableCards = new ArrayList<>();
        for (Card card : hand) {
            if (isCardPlayable(card, topCard)) {
                playableCards.add(card);
            }
        }
        return playableCards;
    }
    // Retrieves the top card from the discard pile or a placeholder card if the discard pile is empty.
    Card getTopCard() {
        if (!discardPile.isEmpty()) {
            return discardPile.get(discardPile.size() - 1);
        } else {
            // If the discard pile is empty, return a placeholder
            return new Card(Card.CardColor.WILD, Card.CardType.WILD, "Wild");
        }
    }
    // Checks if a card is playable based on color or type matching with the top card.
    boolean isCardPlayable(Card card, Card topCard) {
        return card.getColor() == topCard.getColor() || card.getType() == topCard.getType();
    }
    private Card chooseCardToPlay(List<Card> playableCards) {
        return playableCards.get(0);
    }
    // Chooses a card to play from the list of playable cards (currently chooses the first card).
    void playCard(Player player, Card card) {
        // Remove the card from the player's hand and add it to the discard pile
        List<Card> playerHand = new ArrayList<>(player.getHand());
        playerHand.remove(card);
        player.setHand(playerHand);
        discardPile.add(card);
    }
    // Removes the played card from the player's hand and adds it to the discard pile.
    public void applySpecialCardEffects(Card card) {
        if (card.getType() == Card.CardType.SKIP) {
            // Implement logic for SKIP card effect
            System.out.println("Skip card played. Skipping the next player's turn.");
            Player skippedPlayer = getNextPlayer();
            setCurrentPlayer(getNextPlayer()); // Skip the next player's turn
        } else if (card.getType() == Card.CardType.REVERSE) {
            // Implement logic for REVERSE card effect
            System.out.println("Reverse card played. Reversing the order of play.");
            Collections.reverse(players);
        } else if (card.getType() == Card.CardType.DRAW_TWO) {
            // Implement logic for DRAW_TWO card effect
            System.out.println("Draw Two card played. Next player draws two cards.");
            Player nextPlayer = getNextPlayer();// Draw the second card for the next player
            setCurrentPlayer(nextPlayer);
            drawCardFromDrawPile();
            drawCardFromDrawPile();
        }
    }
    // Applies special effects of skip, reverse, or draw two based on the played special card.

    public void setCurrentPlayer(Player player) {
        currentPlayerIndex = players.indexOf(player);
    }
    // Manage the current player, retrieve the current player,
    // get the next player, get the winner, and retrieve the list of players.
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
    // Returns the current player in the game.
    // Retrieves the player from the list of players based on the current player index.
    public Player getNextPlayer() {
        int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return players.get(nextPlayerIndex);
    }
    //  Calculates the index of the next player in a circular manner and retrieves that player from the list.
    //  Returns the player who will take the next turn.
    public Player getWinner() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return player;
            }
        }
        return null;
    }
    // Determines and returns the player who has won the game.
    // Iterates through the list of players and checks
    // if any player's hand is empty. If found, that player is declared the winner; otherwise, returns null.

    public List<Player> getPlayers() {
        return players;
    }
    // Provides access to the list containing all players currently participating in the Uno game.
}
