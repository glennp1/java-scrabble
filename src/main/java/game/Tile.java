package main.java.game;

import java.util.Random;

public class Tile {

    // Attributes
    private char character;
    private int points;

    // Constructor
    public Tile(char character, int points) {
        this.character = character;
        this.points = points;

    }

    public char getCharacter() {
        return character;
    }
}
