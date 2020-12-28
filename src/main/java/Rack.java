package main.java;

import java.util.ArrayList;

public class Rack {

    // Attributes
    private final int MAX_RACK_SIZE = 7;

    private final ArrayList<Tile> tilesInRack = new ArrayList<Tile>();

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
        for (Tile tile : tilesInRack) {
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
        tilesInRack.removeAll(tilesToRemove);
    }

    /**
     * Adds the specified tile to the rack
     *
     * @param tileToAdd the specified tile to be added to the rack
     */
    public void addTile(Tile tileToAdd) {
        tilesInRack.add(tileToAdd);
    }

    /** Returns the tiles in the rack as a string
     *
     * @return the tiles formatted as a string
     */
    public String toString() {

        // To convert the rack to a string we user a string builder
        StringBuilder rackAsString;
        rackAsString = new StringBuilder();

        // If the rack is not empty
        if (!tilesInRack.isEmpty()) {

            // Add each tile to the string
            rackAsString.append("|");
            for (Tile tile : tilesInRack) {
                rackAsString.append(tile.getCharacter());
                rackAsString.append("|");
            }

        // Otherwise if the rack is empty, indicate this
        } else {
            rackAsString.append("Empty");
        }

        // Return the rack as a string type
        return rackAsString.toString();
    }

    /**
     * Returns the number of tiles missing from the rack
     *
     * @return an integer representing the number of tiles missing
     */
    public int numTilesMissing() {
        return (MAX_RACK_SIZE - tilesInRack.size());
    }
}
