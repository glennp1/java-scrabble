package game;

import game.player.Player;

import java.util.ArrayList;

/**
 * <p>Contains all the logic required for the game to run,
 * but does not render it.</p>
 * <p>Encapsulates the board, bag and players in the game and is
 * responsible for prompting each players turn until the game is over
 * and then deciding the games winner.</p>
 */
public class ScrabbleGame {

    // *** Constants ***
    /**
     * The number of players in the game, could be changed in future
     */
    private static final int NUM_PLAYERS = 2;

    /**
     * The index of the first player, in this case we start at 0
     */
    private static final int FIRST_PLAYER_INDEX = 0;

    // *** Attributes ***
    /**
     * The board that the game is played on
     */
    private final Board board = new Board();

    /**
     * The bag that the player draws tiles from to place on their rack
     */
    private final Bag bag = new Bag();

    /**
     * The list of players in the game
     */
    private final ArrayList<Player> players = new ArrayList<Player>();

    /**
     * The current player taking their turn
     */
    private Player currentPlayer = null;

    /**
     * Stores if it is the final round of the game
     */
    private boolean finalRound = false;

    /**
     * Stores the player that caused the final round
     */
    private Player finalRoundPlayer = null;

    /**
     * Stores the winner of the game
     */
    private Player winner = null;

    /**
     * Stores if the game was a draw
     */
    private boolean draw = false;

    // *** Constructor ***
    /**
     * Creates an instance of the game, initialising the dictionary and players
     * in the process. Then the game is played, and an outcome is reached.
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

    /**
     * Initialise all the players in the game. For now this logic is fairly
     * straight forward and so a separate factory class has not been created.
     */
    private void initialisePlayers() {

        // Add each player to the game
        for (int i = 1; i <= NUM_PLAYERS; i++) {
            players.add(new Player(this, i));
        }

        // Set the current player
        currentPlayer = players.get(FIRST_PLAYER_INDEX);
    }

    /**
     * <p>Prompts the game to start.</p>
     * <p>This consists of a series of turns being
     * made by each player until either a player passes, at which point the
     * final round is triggered. Each other player gets one more chance to make
     * a word before the game ends.</p>
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
     * Updates the current player, either incrementing by one or returning
     * to the start of the array list if the end is reached
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
     * Triggers the game to enter its final round, taking note of the player
     * that caused the trigger, as the game will stop on their next turn
     *
     * @param finalRoundPlayer the player that triggered the final round
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
     * Returns if the game is over yet, that is, if it is the final round
     * and it is the turn of the player that triggered the final round
     *
     * @return true if the game is over, false otherwise
     */
    private boolean gameOver(){
        return (finalRound && currentPlayer == finalRoundPlayer);
    }

    /**
     * Decides the winner of the game or if it is a draw
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
     * Prompts the display to show the winner of the game or
     * if it was a draw. The board is shown, followed by the scores
     * scores of the players and then finally the outcome
     */
    private void showGameOutcome() {
        // Display the outcome of the game
        DisplayFacade display = DisplayFacade.getInstance();

        // The board
        display.renderBoard(board);

        // Each player's score
        for (Player player : players) {
            display.renderPlayerScore(player);
        }

        // If it is a draw, render a draw
        if (draw) {
            display.renderDraw();
        }
        // Otherwise render the winner
        else {
            display.renderWinner(winner);
        }
    }

    /**
     * Getter for the board, used to instantiate each turn
     * @return the board that the game is played on
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Getter for the bag, used when filling the players rack with tiles
     * @return the bag that the player draws tiles from to place on their rack
     */
    public Bag getBag() {
        return bag;
    }


}