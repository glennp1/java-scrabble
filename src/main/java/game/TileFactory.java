package main.java.game;

import java.util.LinkedList;
import java.util.Random;

/**
 * Factory helper class used to instantiate each tile.
 * Separated from the Bag class so as to increase cohesion,
 * and to isolate the logic used in the creation of tiles in case this
 * is updated
 */
public class TileFactory {

    // *** Attributes ***
    private final int NUM_START_TILES = 100;

    private final char START_OF_ALPHABET = 'a';
    private final int ALPHABET_LENGTH = 26;

    // For now this is a constant, this may be changed in future iterations
    private final int DEFAULT_TILE_POINTS = 1;


    // *** Constructor ***
    public TileFactory() {

    }

    // *** Methods ***
    /**
     * Returns a linked list of newly created random tiles for the start of the game
     *
     * @return a linked list of tiles
     */
    public LinkedList<Tile> getNewTiles() {

        // Create a new linked list
        LinkedList<Tile> newTiles = new LinkedList<Tile>();

        // Populate it with the specified number of starting tiles
        for (int i = 0; i < NUM_START_TILES; i++) {

            // Each tile is created with a random character and default tile point value
            Tile newTile = new Tile(generateRandomCharacter(), DEFAULT_TILE_POINTS);
            newTiles.add(newTile);
        }

        // Return this linked list
        return newTiles;
    }

    /**
     * Generates a random lowercase character from a-z and returns it
     *
     * @return random a-z character
     */
    private char generateRandomCharacter() {

        // Select a random value from 0 (inclusive) to 26 (exclusive)
        Random random = new Random();
        int randomCharacterValue = random.nextInt(ALPHABET_LENGTH);

        // Use this to create a character ASCII value
        int characterValue = (START_OF_ALPHABET + randomCharacterValue);

        // Typecast the character ASCII value as a character and return it
        return (char) characterValue;
    }


}
