package game.player;

import game.*;

/**
 * <p></p>
 * <p></p>
 */
public class Player {

    // *** Constants ***
    /**
     *
     */
    private static final int STARTING_SCORE = 0;

    // *** Attributes ***
    /**
     *
     */
    private final ScrabbleGame scrabbleGame;

    /**
     *
     */
    private final int number;

    /**
     *
     */
    private int score = STARTING_SCORE;

    /**
     *
     */
    private final Rack rack;

    /**
     *
     */
    private Turn turnCurrent;

    // *** Constructor ***
    /**
     *
     * @param scrabbleGame
     * @param number
     */
    public Player(ScrabbleGame scrabbleGame, int number) {
        this.scrabbleGame = scrabbleGame;
        this.number = number;
        this.rack = new Rack();

        // fill the rack
        this.rack.fill(scrabbleGame.getBag());
    }

    // *** Methods ***
    /**
     *
     * @return
     */
    public boolean takeTurn(boolean firstTurn) {
        // Start a new turn for the player
        turnCurrent = new Turn(scrabbleGame.getBoard(),this, firstTurn);

        turnCurrent.signalStart();
        boolean successful = turnCurrent.completeMoves();
        turnCurrent.signalEnd();

        // Update the score accordingly
        updateScore();

        // Replace placed tiles from the bag
        rack.fill(scrabbleGame.getBag());

        return successful;
    }

    /**
     * Updates the score of the player at the end of their turn
     * Calculated by adding the points of each word formed
     */
    private void updateScore() {
        DisplayFacade display = DisplayFacade.getInstance();

        // For each word in the words formed on the player's current turn
        for (Word word : turnCurrent.getWordsFormed()) {

            // Update the player's score based on the word's points
            score += word.getPoints();

            // Display the update
            display.renderWordPoints(this, word);
        }

        // Display the updated score
        display.renderPlayerScore(this);

    }

    /**
     *
     * @return
     */
    public int getNumber() {
        return number;
    }

    /**
     *
     * @return
     */
    public Rack getRack() {
        return rack;
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }
}
