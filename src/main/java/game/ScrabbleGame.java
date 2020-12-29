package main.java.game;

import main.java.display.BoardDisplay;

import java.util.ArrayList;

public class ScrabbleGame {

    // Attributes

    private final Board board = new Board();

    private final Bag bag = new Bag();
    private final DisplayFacade displayFacade = DisplayFacade.getInstance();

    private final int NUM_PLAYERS = 2;
    private final ArrayList<Player> players = new ArrayList<Player>();

    // Constructor

    public ScrabbleGame() {

        initialiseGame();


    }
    // Methods

    private void initialiseGame() {

        // Add each player to the game
        for (int i = 1; i <= NUM_PLAYERS; i++) {
            players.add(new Player(this, i));
        }

        // Display the board
        displayFacade.renderBoard(board);

        displayFacade.renderPlayerRack(players.get(0));
        displayFacade.renderPlayerRack(players.get(1));

        displayFacade.requestMoveInput();

    }

    public Bag getBag() {
        return bag;
    }
}