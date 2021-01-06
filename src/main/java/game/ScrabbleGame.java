package game;

import game.player.Player;

import java.util.ArrayList;

public class ScrabbleGame {

    // *** Constants ***

    /**
     *
     */
    private static final int FIRST_PLAYER_INDEX = 0;

    // *** Attributes ***

    /**
     *
     */
    private final Board board = new Board();

    /**
     *
     */
    private final Bag bag = new Bag();

    /**
     *
     */
    private static final int NUM_PLAYERS = 2;

    /**
     *
     */
    private final ArrayList<Player> players = new ArrayList<Player>();


    private Player currentPlayer = null;

    private boolean finalRound = false; // Stores if it is the final round of the game
    private Player finalRoundPlayer = null; // The player that caused the final round

    private Player winner = null;
    private boolean draw = false;

    // todo maybe constants should be static ??? yes

    // *** Constructor ***
    /**
     *
     */
    public ScrabbleGame() {

        initialiseDictionary();

        initialisePlayers();

        playGame();

        // Decide the winner of the game and show the result
        decideGameOutcome();
        showGameOutcome();

    }

    // *** Methods ***
    /**
     * Initialises an instance of the dictionary at the start of the game
     * This ensures it does not have to load later
     */
    private void initialiseDictionary() {
        EnglishDictionary.getInstance();
    }

    private void initialisePlayers() {

        // Add each player to the game
        for (int i = 1; i <= NUM_PLAYERS; i++) {
            players.add(new Player(this, i));
        }

        // Set the current player
        currentPlayer = players.get(FIRST_PLAYER_INDEX);
    }

    /**
     *
     */
    private void playGame() {

        boolean firstTurn = true;

        // While the game is not over
        while (!gameOver()) {

            // Prompt the current player to take their turn
            // If the current player was not successful in taking
            // Their turn then this means they passed
            boolean passed = !currentPlayer.takeTurn(firstTurn);

            // If the player passed
            if (passed) {
                // The player has triggered the final round
                triggerFinalRound(currentPlayer);
            }
            // If the player did not pass
            else {
                // It is no longer the first turn
                firstTurn = false;
            }

            // Update the current player
            updateCurrentPlayer();
        }
    }

    /**
     *
     */
    private void updateCurrentPlayer() {

        // Get the index of the current active player
        int currentPlayerIndex = players.indexOf(currentPlayer);

        // Increment this index
        currentPlayerIndex++;

        // If incrementing causes the index go out above the upper bound
        if (currentPlayerIndex == NUM_PLAYERS) {
            // Set it back to the first player
            currentPlayerIndex = FIRST_PLAYER_INDEX;
        }

        // Use this index to set the new current player
        currentPlayer = players.get(currentPlayerIndex);
    }

    /**
     *
     */
    public void triggerFinalRound(Player finalRoundPlayer) {

        // If it is not already the final round
        if (!finalRound) {
            // Record that it is the final round
            finalRound = true;
            // Record the player that triggered the final round, when it arrives at
            // their next turn the game will end
            this.finalRoundPlayer = finalRoundPlayer;
        }
    }

    /**
     *
     * @return
     */
    private boolean gameOver(){
        return (finalRound && currentPlayer == finalRoundPlayer);
    }

    /**
     *
     */
    private void decideGameOutcome() {
        int highestScore = -1;

        // Find the highers scoring player or players
        for (Player player : players) {
            int score = player.getScore();
            if (score > highestScore) {
                winner = player;
                highestScore = score;
                draw = false;
            }
            else if (score == highestScore) {
                draw = true;
            }
        }
    }

    /**
     *
     */
    private void showGameOutcome() {
        // Display the outcome of the game
        DisplayFacade displayFacade = DisplayFacade.getInstance();

        // The board
        displayFacade.renderBoard(board);

        // Each player's score
        for (Player player : players) {
            displayFacade.renderPlayerScore(player);
        }

        // If it is a draw, render a draw
        if (draw) {
            displayFacade.renderDraw();
        }
        // Otherwise render the winner
        else {
            displayFacade.renderWinner(winner);
        }
    }

    /**
     *
     * @return
     */
    public Bag getBag() {
        return bag;
    }

    /**
     *
     * @return
     */
    public Board getBoard() {
        return board;
    }
}