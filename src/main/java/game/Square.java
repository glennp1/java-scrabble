package main.java.game;

import main.java.game.player.*;

import java.util.LinkedList;

public class Square {

    // *** Attributes ***
    private Tile tile;
    private int points;
    private int row;
    private int col;
    private Board board;
    // *** Constructor ***

    public Square(Tile tile, int points, int row, int col, Board board) {
        this.tile = tile;
        this.points = points;
        this.row = row;
        this.col = col;
        this.board = board;
    }

    // *** Methods ***



    public boolean hasTile() {
        return tile != null;
    }

    /**
     * Returns if a specified square has a tile, can handle null input
     *
     * @param square a specified square, can be null
     * @return true if the square exists and has a tile, false otherwise
     */
    private static boolean hasTile(Square square) {
        // If the square doesn't exist then return false
        if (square == null ) {
            return false;
        }
        // Otherwise return if there is a tile or not
        return square.tile != null;
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

    /**
     * Returns the all neighbouring squares that have tiles in them
     *
     * @return a linked list of neighbouring squares that have a tile
     */
    public LinkedList<Square> getAllNeighbours() {
        LinkedList<Square> neighbours = new LinkedList<>();

        if (hasTile(getSquareLeft())) {
            neighbours.add(getSquareLeft());
        }
        if (hasTile(getSquareRight())) {
            neighbours.add(getSquareRight());
        }
        if (hasTile(getSquareAbove())) {
            neighbours.add(getSquareAbove());
        }
        if (hasTile(getSquareBelow())) {
            neighbours.add(getSquareBelow());
        }
        return neighbours;
    }

    public boolean formsRow() {
        return hasTile(getSquareLeft()) || hasTile(getSquareRight());
    }

    public boolean formsColumn() {
        return hasTile(getSquareAbove()) || hasTile(getSquareBelow());
    }

    public LinkedList<Square> getAllSquaresLeft() {
        Square currentSquare = this;
        LinkedList<Square> allSquaresLeft = new LinkedList<>();

        while (hasTile(currentSquare.getSquareLeft())) {
            currentSquare = currentSquare.getSquareLeft();
            allSquaresLeft.addFirst(currentSquare);
        }

        return allSquaresLeft;
    }

    public LinkedList<Square> getAllSquaresRight() {
        Square currentSquare = this;
        LinkedList<Square> allSquaresRight = new LinkedList<>();

        while (hasTile(currentSquare.getSquareRight())) {
            currentSquare = currentSquare.getSquareRight();
            allSquaresRight.addLast(currentSquare);
        }

        return allSquaresRight;
    }

    public LinkedList<Square> getAllSquaresAbove() {
        Square currentSquare = this;
        LinkedList<Square> allSquaresAbove = new LinkedList<>();

        while (hasTile(currentSquare.getSquareAbove())) {
            currentSquare = currentSquare.getSquareAbove();
            allSquaresAbove.addFirst(currentSquare);
        }

        return allSquaresAbove;
    }

    public LinkedList<Square> getAllSquaresBelow() {
        Square currentSquare = this;
        LinkedList<Square> allSquaresBelow = new LinkedList<>();

        while (hasTile(currentSquare.getSquareBelow())) {
            currentSquare = currentSquare.getSquareBelow();
            allSquaresBelow.addLast(currentSquare);
        }

        return allSquaresBelow;
    }


    public Square getSquareLeft() {
        int colLeft = col - 1;
        return board.getSquareByCoords(row, colLeft);
    }

    public Square getSquareRight() {
        int colRight = col + 1;
        return board.getSquareByCoords(row, colRight);
    }

    public Square getSquareAbove() {
        int rowAbove = row - 1;
        return board.getSquareByCoords(rowAbove, col);
    }

    public Square getSquareBelow() {
        int rowBelow = row + 1;
        return board.getSquareByCoords(rowBelow, col);
    }

    public char getTileCharacter() {
        if (tile != null) {
            return tile.getCharacter();
        } else {
            return '_';
        }
    }

    public Tile getTile() {
        return tile;
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
