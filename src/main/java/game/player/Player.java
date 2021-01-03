package main.java.game.player;

import main.java.game.*;

public abstract class Player {

    // Attributes
    protected final ScrabbleGame scrabbleGame;

    protected final int number;

    protected final int STARTING_SCORE = 0;

    protected int score = STARTING_SCORE;

    protected final Rack rack;
    // todo tiles placed

    protected Turn turnCurrent;

    // Constructor


    public Player(ScrabbleGame scrabbleGame, int number) {
        this.scrabbleGame = scrabbleGame;
        this.number = number;
        this.rack = new Rack();

        // fill the rack
        this.rack.fill(scrabbleGame.getBag());
    }

    // Methods

    /**
     *
     * @return
     */
    public void takeTurn() {
        // todo separate human and npc turn
        // Start a new turn for the player
        turnCurrent = new Turn(scrabbleGame.getBoard(),this);

        // Update the score accordingly
        updateScore();

        // Replace placed tiles from the bag
        rack.fill(scrabbleGame.getBag());
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
        // For each word in the words formed on the player's current turn
        for (Word word : turnCurrent.getWordsFormed()) {

            // todo move
            System.out.println("Formed " + word.toString() + " for " + word.getPoints() + " points.");

            // Update the player's score based on the word's points
            score += word.getPoints();
        }
    }
}
