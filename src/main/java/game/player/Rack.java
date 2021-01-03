package main.java.game.player;

import main.java.game.Bag;
import main.java.game.Tile;

import java.util.ArrayList;

public class Rack {

    // *** Attributes ***
    private final int MAX_RACK_SIZE = 7;

    private ArrayList<Tile> tiles = new ArrayList<Tile>();

    // todo rename / make clearer

    // *** Constructor ***
    public Rack() {
    }

    // *** Methods ***

    // todo rack should store reference of bag and "draw from bag"
    /**
     * Fill the player's rack from the bag based on the number of tiles missing
     */
    public void fill(Bag bag) {
        Tile tileToAdd;

        // While there are missing tiles from the rack, add one from the bag
        while (numTilesMissing() > 0) {

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
        ArrayList<Character> rackFormatted = new ArrayList<Character>();

        // Add each tile to the string
        for (Tile tile : tiles) {
            rackFormatted.add(tile.getCharacter());
        }
        // Return the character ArrayList
        return rackFormatted;
    }

    public Rack createBackup() {
        Rack rackBackup = new Rack();
        rackBackup.setAllTiles(this.tiles);

        return rackBackup;
    }

    public void restoreBackup(Rack rackBackup) {
        setAllTiles(rackBackup.tiles);
    }

    public void setAllTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }




}
