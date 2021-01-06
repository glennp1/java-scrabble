package main.java.game;

/**
 * <p>Represents a single tile in the game.<p/>
 * <p>Can be stored by a number of classes,
 * notably the bag, rack and board <p/>
 */
public class Tile {

    // *** Attributes ***
    /**
     * The character that appears on the face of the tile
     */
    private final char character;

    /**
     * The number of points a tile is worth, for now this is always one
     */
    private final int points;

    // *** Constructor ***
    /**
     * Creates an instance of a Tile, with the specified character and points
     *
     * @param character the character on the face of the tile
     * @param points the number of points a tile is worth
     */
    public Tile(char character, int points) {
        this.character = character;
        this.points = points;
    }

    // *** Methods ***
    /**
     * Getter for the character attribute, note that a blank tile "_" is
     * represented as a null and thus is not stored in the character attribute
     *
     * @return the character on the face of the tile
     */
    public char getCharacter() {
        return character;
    }

    /**
     * Getter for the points attribute, by default this is "1"
     *
     * @return the points on the face of the tile
     */
    public int getPoints() {
        return points;
    }
}
