package game.player;

import game.*;

public class Player {

    // *** Attributes ***
    protected final ScrabbleGame scrabbleGame;

    protected final int number;

    protected final int STARTING_SCORE = 0;

    protected int score = STARTING_SCORE;

    protected final Rack rack;

    protected Turn turnCurrent;

    // *** Constructor ***


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
        // todo separate human and npc turn
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

    public int getNumber() {
        return number;
    }

    public Rack getRack() {
        return rack;
    }

    public int getScore() {
        return score;
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
}
