package main.java;

import java.util.LinkedList;

public class Bag {

    // Attributes
    private final int MAX_BAG_SIZE = 100;
    private final LinkedList<Tile> tilesInBag = new LinkedList<Tile>();


    // Constructor


    public Bag() {
        initialiseTiles();
    }

    // Methods
    private void initialiseTiles(){
        for (int i = 0; i < MAX_BAG_SIZE; i++) {
            tilesInBag.add(new Tile());
        }
    }

    public Tile removeTile(){
        return (tilesInBag.remove());
    }

    public boolean isNotEmpty(){
        return (!tilesInBag.isEmpty());
    }
}
