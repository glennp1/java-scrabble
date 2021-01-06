package game;

import java.util.LinkedList;

/**
 * <p>Represents a square on the the scrabble board.<p/>
 * <p>Each square can contain a single tile, and has an associated number of points.
 * The square class also has a number of methods that enable it to
 * retrieve the squares around it and the tiles in those squares <p/>
 */
public class Square {

    // *** Constants ***

    // *** Attributes ***
    /**
     * The tile placed placed in the square, null is interpretted as "_"
     */
    private Tile tile;

    /**
     * The points that a square is worth,
     * for now this is always the default of 1
     */
    private final int points;

    /**
     * The row that the square belongs to, starts at 0 not 1
     */
    private final int row;

    /**
     * The column that the square belongs to, starts at 0 not 1
     */
    private final int col;

    /**
     * The board that contains the square, used to retrieve information
     * about neighbouring squares
     */
    private final Board board;

    // *** Constructor ***
    /**
     * Creates an instance of a Square using the specified parameters
     *
     * @param tile the tile placed placed in the square
     * @param points the points that a square is worth
     * @param row the row that the square belongs to
     * @param col the column that the square belongs to
     * @param board the board that contains the square
     */
    public Square(Tile tile, int points, int row, int col, Board board) {
        this.tile = tile;
        this.points = points;
        this.row = row;
        this.col = col;
        this.board = board;
    }

    // *** Methods ***
    /**
     * Returns if a square is in line with a linked listed of squares
     * That is, they share either a row or a column with one another
     *
     * @param listOfSquares A linked list of squares
     * @return true if squares are in line, false otherwise
     */
    public boolean isInLine(LinkedList<Square> listOfSquares) {
        // To store if the row or column match
        boolean sameRow = true;
        boolean sameCol = true;

        // Iterate through each of the squares, recording if the row or column
        // deviates from the row or column of this square
        for (Square square : listOfSquares) {
            if (this.row != square.getRow()) {
                sameRow = false;
            }
            if (this.col != square.getCol()) {
                sameCol = false;
            }
        }

        // Return if either true
        return (sameRow || sameCol);
    }

    /**
     * Returns if the square forms a row, that is, it has a square with a tile in it
     * either to the left or to the right
     *
     * @return true if forms a row, false otherwise
     */
    public boolean formsRow() {
        return hasTile(getSquareLeft()) || hasTile(getSquareRight());
    }

    /**
     * Returns if the square forms a column, that is, it has a square with a tile in it
     * either above or below
     *
     * @return true if forms a column, false otherwise
     */
    public boolean formsColumn() {
        return hasTile(getSquareAbove()) || hasTile(getSquareBelow());
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

    /**
     * Returns a linked list of squares to the left of this square,
     * ordered in ascending order
     *
     * @return linked list of squares to the left, in ascending order
     */
    public LinkedList<Square> getAllSquaresLeft() {
        Square currentSquare = this;
        LinkedList<Square> allSquaresLeft = new LinkedList<>();

        // Get each square to the left and add it to the START of the list
        while (hasTile(currentSquare.getSquareLeft())) {
            currentSquare = currentSquare.getSquareLeft();
            allSquaresLeft.addFirst(currentSquare);
        }

        return allSquaresLeft;
    }

    /**
     * Returns a linked list of squares to the right of this square,
     * ordered in ascending order
     *
     * @return linked list of squares to the right, in ascending order
     */
    public LinkedList<Square> getAllSquaresRight() {
        Square currentSquare = this;
        LinkedList<Square> allSquaresRight = new LinkedList<>();

        // Get each square to the right and add it to the END of the list
        while (hasTile(currentSquare.getSquareRight())) {
            currentSquare = currentSquare.getSquareRight();
            allSquaresRight.addLast(currentSquare);
        }

        return allSquaresRight;
    }

    /**
     * Returns a linked list of squares above this square,
     * ordered in ascending order
     *
     * @return linked list of squares above, in ascending order
     */
    public LinkedList<Square> getAllSquaresAbove() {
        Square currentSquare = this;
        LinkedList<Square> allSquaresAbove = new LinkedList<>();

        // Get each square above and add it to the START of the list
        while (hasTile(currentSquare.getSquareAbove())) {
            currentSquare = currentSquare.getSquareAbove();
            allSquaresAbove.addFirst(currentSquare);
        }

        return allSquaresAbove;
    }

    /**
     * Returns a linked list of squares below this square,
     * ordered in ascending order
     *
     * @return linked list of squares below, in ascending order
     */
    public LinkedList<Square> getAllSquaresBelow() {
        Square currentSquare = this;
        LinkedList<Square> allSquaresBelow = new LinkedList<>();

        // Get each square below and add it to the END of the list
        while (hasTile(currentSquare.getSquareBelow())) {
            currentSquare = currentSquare.getSquareBelow();
            allSquaresBelow.addLast(currentSquare);
        }

        return allSquaresBelow;
    }

    /**
     * Returns the square to the left of this square, can be null as per the
     * getSquareByCoords method
     *
     * @return the square to the left of this square, null if no square exists
     */
    public Square getSquareLeft() {
        int colLeft = col - 1;
        return board.getSquareByCoords(row, colLeft);
    }

    /**
     * Returns the square to the right of this square, can be null as per the
     * getSquareByCoords method
     *
     * @return the square to the right of this square, null if no square exists
     */
    public Square getSquareRight() {
        int colRight = col + 1;
        return board.getSquareByCoords(row, colRight);
    }

    /**
     * Returns the square above this square, can be null as per the
     * getSquareByCoords method
     *
     * @return the square above this square, null if no square exists
     */
    public Square getSquareAbove() {
        int rowAbove = row - 1;
        return board.getSquareByCoords(rowAbove, col);
    }

    /**
     * Returns the square below this square, can be null as per the
     * getSquareByCoords method
     *
     * @return the square below this square, null if no square exists
     */
    public Square getSquareBelow() {
        int rowBelow = row + 1;
        return board.getSquareByCoords(rowBelow, col);
    }

    /**
     * Gets the character corresponding to the square's tile. In the event the tile is
     * null, the "_" character is returned instead
     *
     * @return the character corresponding to the square, "_" if null
     */
    public char getTileCharacter() {
        if (tile != null) {
            return tile.getCharacter();
        } else {
            return '_';
        }
    }

    /**
     * Returns if a specified square has a tile, can handle a square that is null
     * This is very useful for when specified square cannot be found
     *
     * @param square a specified square, can be null
     * @return true if the square exists and has a tile, false otherwise
     */
    public static boolean hasTile(Square square) {
        // If the square doesn't exist then return false
        if (square == null ) {
            return false;
        }
        // Otherwise return if there is a tile or not
        return square.tile != null;
    }

    /**
     * Returns if the square has a tile, this cannot be used on a null object
     *
     * @return true if the square has a tile, false otherwise
     */
    public boolean hasTile() {
        return tile != null;
    }

    /**
     * Getter for the tile attribute, can be null
     *
     * @return the tile placed in a square
     */
    public Tile getTile() {
        return tile;
    }

    /**
     * Setter for the tile attribute, can be null
     *
     * @param tile the tile placed in a square
     */
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    /**
     * Getter for the points attribute, the points that a square is worth
     *
     * @return the points that a square is worth
     */
    public int getPoints() {
        return points;
    }

    /**
     * Getter for the row attribute, note that the row starts at 0 not 1
     *
     * @return the row that the square belongs to
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter for the column attribute, note that the col starts at 0 not 1
     *
     * @return the column that the square belongs to
     */
    public int getCol() {
        return col;
    }

}
