package main.java.game;

public class Square {

    // Attributes
    private Tile tile;
    private int points;

    // Constructor

    public Square(Tile tile, int points) {
        this.tile = tile;
        this.points = points;
    }




    // Methods


    public Tile getTile() {
        return tile;
    }

    public char getTileCharacter() {
        if (tile != null) {
            return tile.getCharacter();
        } else {
            return '_';
        }
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

}
