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
    private Board board;
    // Constructor

    public Square(Tile tile, int points, int row, int col, Board board) {
        this.tile = tile;
        this.points = points;
        this.row = row;
        this.col = col;
        this.board = board;
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

    public boolean hasTileLeft() {
        // Set the column to the left
        int colLeft = col - 1;
        // If the column to the left is outside the bounds return false
        if (colLeft < 0) {
            return false;
        }
        // Otherwise return if the square to the left is empty
        else {
            return !board.getSquareByCoords(row, colLeft).isEmpty();
        }
    }

    public boolean hasTileRight() {
        // Set the column to the right
        int colRight = col + 1;
        // If the column to the right is outside the bounds return false
        if (colRight >= Board.NUM_COLS) {
            return false;
        }
        // Otherwise return if the square to the right is empty
        else {
            return !board.getSquareByCoords(row, colRight).isEmpty();
        }
    }

    public boolean hasTileBelow() {
        // Set the row below
        int rowBelow = row - 1;
        // If the row below is outside the bounds return false
        if (rowBelow < 0) {
            return false;
        }
        // Otherwise return if the square below is empty
        else {
            return !board.getSquareByCoords(rowBelow, col).isEmpty();
        }
    }

    public boolean hasTileAbove() {
        // Set the row above
        int rowAbove = row + 1;
        // If the row above is outside the bounds return false
        if (rowAbove >= Board.NUM_ROWS) {
            return false;
        }
        // Otherwise return if the square above is empty
        else {
            return !board.getSquareByCoords(rowAbove, col).isEmpty();
        }
    }

    public Square getSquareLeft() {
        int colLeft = col - 1;
        return board.getSquareByCoords(row, colLeft);
    }

    public Square getSquareRight() {
        int colRight = col + 1;
        return board.getSquareByCoords(row, colRight);
    }

    public Square getSquareBelow() {
        int rowBelow = row - 1;
        return board.getSquareByCoords(rowBelow, col);
    }

    public Square getSquareAbove() {
        int rowAbove = row + 1;
        return board.getSquareByCoords(rowAbove, col);
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
