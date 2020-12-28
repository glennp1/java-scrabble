package main.java;

import java.util.ArrayList;

public class Player {

    // Attributes
    private final ScrabbleGame scrabbleGame;

    private final int number;

    private final Rack rack = new Rack();
    // todo tiles placed

    // Constructor


    public Player(ScrabbleGame scrabbleGame, int number) {
        this.scrabbleGame = scrabbleGame;
        this.number = number;
        fillRack();
    }

    // Methods

    public void takeTurn() {
        // Place tiles

        // Pass

        // End Turn

        // Replace placed tiles
    }

    /**
     * Fill the player's rack from the bag based on the number of tiles missing
     */
    private void fillRack() {
        Tile tileToAdd;

        // While there are missing tiles from the rack, add one from the bag
        while (rack.numTilesMissing() > 0) {

            // Remove a tile from the bag and store it
            tileToAdd = scrabbleGame.getBag().removeTile();

            // Add this tile to the player's rack
            rack.addTile(tileToAdd);
       }
    }

    public int getNumber() {
        return number;
    }

    public Rack getRack() {
        return rack;
    }

}
