package main.java.game;

public class Player {

    // Attributes
    private final ScrabbleGame scrabbleGame;

    private final int number;

    private final int STARTING_SCORE = 0;

    private int score = STARTING_SCORE;

    private final Rack rack;
    // todo tiles placed

    // Constructor


    public Player(ScrabbleGame scrabbleGame, int number) {
        this.scrabbleGame = scrabbleGame;
        this.number = number;
        this.rack = new Rack(scrabbleGame.getBag());
    }

    // Methods

    public void takeTurn() {

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
}
