package game;

import java.util.LinkedList;

/**
 * <p></p>
 * <p></p>
 */
public class Bag {

    // *** Constants ***

    // *** Attributes ***
    /**
     *
     */
    private final TileFactory tileFactory = new TileFactory();
    private final LinkedList<Tile> tiles = new LinkedList<>();

    // *** Constructor ***
    /**
     *
     */
    public Bag() {
        // Have the tile factory create a new set of tiles
        LinkedList<Tile> tilesToAdd = tileFactory.getNewTiles();

        // Add these new tiles to the bag
        addTiles(tilesToAdd);
    }

    // *** Methods ***
    /**
     *
     * @param tilesToAdd
     */
    public void addTiles(LinkedList<Tile> tilesToAdd) {
        tiles.addAll(tilesToAdd);
    }

    /**
     *
     * @return
     */
    public Tile removeTile(){
        return (tiles.remove());
    }

    /**
     *
     * @return
     */
    public boolean isNotEmpty(){
        return (!tiles.isEmpty());
    }
}
