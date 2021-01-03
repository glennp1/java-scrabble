package main.java.game;

import java.util.Random;

public class Tile {

    // Attributes
    private final char character;
    private final int points;



    // Constructor
    public Tile(char character, int points) {
        this.character = character;
        this.points = points;

    }

    public char getCharacter() {
        return character;
    }

    public int getPoints() {
        return points;
    }
}
