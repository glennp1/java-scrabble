package main.java.game.player;

import main.java.game.*;

import java.util.LinkedList;

public abstract class Player {

    // Attributes
    protected final ScrabbleGame scrabbleGame;

    protected final int number;

    protected final int STARTING_SCORE = 0;

    protected int score = STARTING_SCORE;

    protected final Rack rack;
    // todo tiles placed

    // A set of moves confirmed by the player
    protected LinkedList<Move> movesConfirmed = new LinkedList<>();

    // The words formed by the player
    // protected LinkedList<LinkedList<Square>> wordsFormed =

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
        new Turn(scrabbleGame.getBoard(),this);
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

    public void setScore(int score) {
        this.score = score;
    }
}
