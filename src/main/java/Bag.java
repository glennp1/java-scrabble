package main.java;

import java.util.LinkedList;

public class Bag {

    // Attributes
    private final int BAG_SIZE = 100;

    private LinkedList<Tile> tiles = new LinkedList<Tile>();


    // Constructor


    public Bag() {
        initialiseTiles();
    }

    // Methods
    private void initialiseTiles(){
        for (int i = 0; i < BAG_SIZE; i++) {
            tiles.add(new Tile());
        }
    }

    public Tile removeTile(){
        return (tiles.remove());
    }

    public boolean isEmpty(){
        return (tiles.isEmpty());
    }
}
