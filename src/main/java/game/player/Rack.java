package game.player;

import game.Bag;
import game.Tile;

import java.util.ArrayList;

/**
 * <p></p>
 * <p></p>
 */
public class Rack {

    // *** Constants ***
    /**
     *
     */
    private static final int MAX_RACK_SIZE = 7;

    // *** Attributes ***
    /**
     *
     */
    private final ArrayList<Tile> tiles = new ArrayList<>();

    // *** Constructor ***
    /**
     *
     */
    public Rack() {
    }

    // *** Methods ***
    /**
     * Fill the player's rack from the bag based on the number of tiles missing
     */
    public void fill(Bag bag) {
        Tile tileToAdd;

        // While there are missing tiles from the rack, and the bag is not empty
        while (numTilesMissing() > 0 && bag.isNotEmpty()) {

            // Remove a tile from the bag and store it
            tileToAdd = bag.removeTile();

            // Add this tile to the player's rack
            addTile(tileToAdd);
        }
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
     * Removes the specified tile from the rack
     *
     * @param tileToRemove the tiles indicated for removal
     */
    public void removeTile(Tile tileToRemove){
        tiles.remove(tileToRemove);
    }



    /** Formats the tiles in the rack as an array list of characters
     *
     * @return the tiles formatted as an array list of characters
     */
    public ArrayList<Character> formatForDisplay() {

        // Create a character Arraylist
        ArrayList<Character> rackFormatted = new ArrayList<>();

        // Add each tile to the string
        for (Tile tile : tiles) {
            rackFormatted.add(tile.getCharacter());
        }
        // Return the character ArrayList
        return rackFormatted;
    }

    /**
     *
     * @return
     */
    public Rack createBackup() {
        Rack rackBackup = new Rack();

        // Add all the tiles to rack backup individually,
        // rather than copying the list itself
        rackBackup.tiles.addAll(this.tiles);

        return rackBackup;
    }

    /**
     *
     * @param rackBackup
     */
    public void restoreBackup(Rack rackBackup) {
        this.tiles.clear();
        this.tiles.addAll(rackBackup.tiles);
    }
}
