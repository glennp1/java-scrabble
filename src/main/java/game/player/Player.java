package game.player;

import game.*;

/**
 * <p>Represents a player in the game,
 * whom can take a turn when prompted </p>
 * <p>In future this class may be made into a superclass
 * with two subclasses, one to represent a human player
 * and the other to represent a npc player</p>
 */
public class Player {

    // *** Constants ***
    /**
     * The starting score of all players
     */
    private static final int STARTING_SCORE = 0;

    // *** Attributes ***
    /**
     * The game the player belongs to
     */
    private final ScrabbleGame scrabbleGame;

    /**
     * The player's number, player one = 1 etc...
     */
    private final int number;

    /**
     * The player's score, increased when words are completed
     */
    private int score = STARTING_SCORE;

    /**
     * The players rack, containing all of their tiles
     */
    private final Rack rack;

    /**
     * The player's current turn
     */
    private Turn turnCurrent;

    // *** Constructor ***
    /**
     * Creates a new instance of the player class, then initialises the
     * parameters and fills the players rack so they are ready for play
     *
     * @param scrabbleGame the game the player belongs to
     * @param number the players number, starts at 1
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
     * Prompts the player to take their turn, taking into account if it
     * is the first turn of the game, then returning if the turn is
     * successful, that is, they did not pass
     *
     * @return true if the turn is successful, false if they passed
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
     * Getter for the player's number, used by display
     *
     * @return the player's number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Getter for the player's rack, used by display and turn backups
     *
     * @return the player's rack containing all of their tiles
     */
    public Rack getRack() {
        return rack;
    }

    /**
     * Getter for the player's score, used by display
     *
     * @return the player's score
     */
    public int getScore() {
        return score;
    }
}
