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
    protected LinkedList<Move> movesConfirmed = new LinkedList<Move>();

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

        // Access the instance of the display
        DisplayFacade displayFacade = DisplayFacade.getInstance();

        // Render the turn
        displayFacade.renderPlayerTurn(this);

        // Render the board
        displayFacade.renderBoard(scrabbleGame.getBoard());

        // Render the player's rack
        displayFacade.renderPlayerRack(this);
        // Render the player's score
        displayFacade.renderPlayerScore(this);

        // Confirm moves for the turn
        confirmMoves();

        // Execute the selected moves
        executeMoves();

        // Replace placed tiles from the bag
        rack.fill(scrabbleGame.getBag());

        // End Turn
        displayFacade.renderPlayerEndTurn(this);

        // todo player should handle the input
        //  turn and move object just used to store

        // new Turn(scrabbleGame.getBoard(),this);
    }

    protected abstract void confirmMoves();

    private void executeMoves() {

        if (movesConfirmed == null) {
            return;
        }

        for (Move move : movesConfirmed) {
            move.execute();
            score += move.getPoints();
        }

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
}
