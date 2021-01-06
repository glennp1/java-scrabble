package game;

import java.util.LinkedList;

public class Bag {

    // *** Constants ***

    // *** Attributes ***
    private final TileFactory tileFactory = new TileFactory();
    private final LinkedList<Tile> tiles = new LinkedList<>();

    // *** Constructor ***
    public Bag() {
        // Have the tile factory create a new set of tiles
        LinkedList<Tile> tilesToAdd = tileFactory.getNewTiles();

        // Add these new tiles to the bag
        addTiles(tilesToAdd);
    }

    // *** Methods ***
    public void addTiles(LinkedList<Tile> tilesToAdd) {
        tiles.addAll(tilesToAdd);
    }

    public Tile removeTile(){
        return (tiles.remove());
    }

    public boolean isNotEmpty(){
        return (!tiles.isEmpty());
    }
}
