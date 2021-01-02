package main.java.game;

import main.java.game.player.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Square {

    // Attributes
    private Tile tile;
    private int points;
    private int row;
    private int col;

    // Constructor

    public Square(Tile tile, int points, int row, int col) {
        this.tile = tile;
        this.points = points;
        this.row = row;
        this.col = col;
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

    public boolean isEmpty() {
        return tile == null;
    }

    /**
     * Returns if a square is in line with the squares within a series of moves
     * That is, they share either a row or a column with one another
     *
     * @param moves A linked list of moves
     * @return true if squares are in line, false otherwise
     */
    public boolean isInLine(LinkedList<Move> moves) {
        // To store if the row or column match
        boolean sameRow = true;
        boolean sameCol = true;

        // Iterate through each of the squares, recording if the row or column
        // deviates from the row or column of this square
        for (Move move : moves) {
            if (this.row != move.getSquareSelected().getRow()) {
                sameRow = false;
            }
            if (this.col != move.getSquareSelected().getCol()) {
                sameCol = false;
            }
        }

        // Return if either true
        return (sameRow || sameCol);
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public int getPoints() {
        return points;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
