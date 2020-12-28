package main.java;

import java.util.Random;

public class Tile {

    // Attributes
    private final char START_OF_ALPHABET = 'a';
    private final int ALPHABET_LENGTH = 26;

    private char character;


    // Constructor

    public Tile() {
        generateRandomCharacter();
    }

    // Methods
    private void generateRandomCharacter() {
        Random random = new Random();
        character = (char) (START_OF_ALPHABET + random.nextInt(ALPHABET_LENGTH));
    }

    public char getCharacter() {
        return character;
    }
}
