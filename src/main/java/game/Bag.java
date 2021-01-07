package game;

import java.util.LinkedList;

/**
 * <p>Stores all the tiles that are used to fill the player's racks</p>
 * <p>The creation logic of each of the tiles is instead handled
 * by tile factory</p>
 */
public class Bag {

    // *** Constants ***

    // *** Attributes ***
    /**
     * Stores an instance of the tile factory, responsible for creating
     * all of the tiles
     */
    private final TileFactory tileFactory = new TileFactory();

    /**
     * Stores all of the tiles in the bag
     */
    private final LinkedList<Tile> tiles = new LinkedList<>();

    // *** Constructor ***
    /**
     * Creates a new instance of the bag, and adds all of the tiles
     * created by the tile factory
     */
    public Bag() {
        // Have the tile factory create a new set of tiles
        LinkedList<Tile> tilesToAdd = tileFactory.getNewTiles();

        // Add these new tiles to the bag
        addTiles(tilesToAdd);
    }

    // *** Methods ***
    /**
     * Adds a specified list of tiles to the tiles stored in the bag
     *
     * @param tilesToAdd the tiles to be added to the bag
     */
    public void addTiles(LinkedList<Tile> tilesToAdd) {
        tiles.addAll(tilesToAdd);
    }

    /**
     * Removes a specified tile from the bag and returns it
     *
     * @return the tile that was removed
     */
    public Tile removeTile(){
        return (tiles.remove());
    }

    /**
     * Returns if the bag is currently not empty, when the bag is empty
     * tile can no longer be removed
     *
     * @return true if the bag is not empty, false otherwise
     */
    public boolean isNotEmpty(){
        return (!tiles.isEmpty());
    }
}
