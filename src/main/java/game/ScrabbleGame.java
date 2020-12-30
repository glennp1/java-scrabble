package main.java.game;

import java.util.ArrayList;

public class ScrabbleGame {

    // Attributes

    private final Board board = new Board();

    private final Bag bag = new Bag();

    private static final int NUM_PLAYERS = 2;

    private final ArrayList<Player> players = new ArrayList<Player>();

    private static final int FIRST_PLAYER_INDEX = 0;
    private Player currentPlayer = null;

    private boolean finalRound = false; // Stores if it is the final round of the game
    private Player finalRoundPlayer = null; // The player that caused the final round

    // todo maybe constants should be static ??? yes

    // *** Constructor ***

    public ScrabbleGame() {

        initialisePlayers();

        playGame();

    }
    // Methods

    private void initialisePlayers() {

        // Add each player to the game
        for (int i = 1; i <= NUM_PLAYERS; i++) {
            players.add(new Player(this, i));
        }

        // Set the current player
        currentPlayer = players.get(FIRST_PLAYER_INDEX);
    }

    private void playGame() {

        // While the game is not over
        while (!gameOver()) {

            // Prompt the current player to take their turn
            currentPlayer.takeTurn();

            // Update the current player
            updateCurrentPlayer();
        }

        // Decide the winner of the game and display the result
        decideWinner();

    }

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

    private boolean gameOver(){
        return (finalRound && currentPlayer == finalRoundPlayer);
    }

    private void decideWinner() {
        Player winner = null;
        int highestScore = -1;
        boolean draw = false;

        // Find the highers scoring player or players
        for (Player player : players) {
            int score = player.getScore();
            if (score > highestScore) {
                winner = player;
                draw = false;
            }
            else if (score == highestScore) {
                draw = true;
            }
        }

        // Display the outcome of the game
        DisplayFacade displayFacade = DisplayFacade.getInstance();
        if (draw) {
            displayFacade.renderDraw();
        }
        else {
            displayFacade.renderWinner(winner);
        }
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }
}