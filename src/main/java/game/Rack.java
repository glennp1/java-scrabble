package main.java.game;

import java.util.ArrayList;

public class Rack {

    // Attributes
    private final int MAX_RACK_SIZE = 7;

    private final ArrayList<Tile> tiles = new ArrayList<Tile>();

    // Constructor
    public Rack() {
    }

    /**
     * Returns the first tile that contains a specific character, otherwise return null
     *
     * @param characterToGet the specific character to search for
     * @return the tile that contains the character
     */
    public Tile getTileByChar(char characterToGet) {
        // Look for a tile in the ArrayList tiles that matches the character
        for (Tile tile : tiles) {
            if (tile.getCharacter() == characterToGet) {

                // Return the tile
                return tile;
            }
        }

        // Otherwise indicate no tile was found
        return null;
    }

    /**
     * Removes the specified array list of tiles from the rack
     *
     * @param tilesToRemove the tiles indicated for removal
     */
    public void removeManyTiles(ArrayList<Tile> tilesToRemove){
        tiles.removeAll(tilesToRemove);
    }

    /**
     * Adds the specified tile to the rack
     *
     * @param tileToAdd the specified tile to be added to the rack
     */
    public void addTile(Tile tileToAdd) {
        tiles.add(tileToAdd);
    }

    /**
     * Returns the number of tiles missing from the rack
     *
     * @return an integer representing the number of tiles missing
     */
    public int numTilesMissing() {
        return (MAX_RACK_SIZE - tiles.size());
    }

    /** Formats the tiles in the rack as an array list of characters
     *
     * @return the tiles formatted as an array list of characters
     */
    public ArrayList<Character> formatForDisplay() {

        // Create a character Arraylist
        ArrayList<Character> rackAsCharArray = new ArrayList<Character>();

        // Add each tile to the string
        for (Tile tile : tiles) {
            rackAsCharArray.add(tile.getCharacter());
        }
        // Return the character ArrayList
        return rackAsCharArray;
    }
}
