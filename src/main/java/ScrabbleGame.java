package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class ScrabbleGame {

    // Attributes

    private final Board board = new Board();

    private final Bag bag = new Bag();
    private final UserInterface userInterface = new UserInterface();

    private final int NUM_PLAYERS = 2;
    private final ArrayList<Player> players = new ArrayList<Player>();

    // Constructor

    public ScrabbleGame() {
        System.out.println("Hello World !");

        initialiseGame();


    }
    // Methods

    private void initialiseGame() {

        // Add each player to the game
        for (int i=1; i <= NUM_PLAYERS; i++) {
            players.add(new Player(this, i));
        }

        // Display the board
        userInterface.renderBoard(board);

        // Display each player's rack
        userInterface.renderPlayerRack(players.get(0));
        userInterface.renderPlayerRack(players.get(1));

    }

    public Bag getBag() {
        return bag;
    }
}