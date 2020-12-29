package main.java.game;

/**
 * Factory helper class used to instantiate each square.
 * Separated from the Board class so as to increase cohesion,
 * and to isolate the logic used in the creation of squares in case this
 * is updated
 */
public class SquareFactory {


    // *** Attributes ***
    private final Tile DEFAULT_TILE = null;
    private final int DEFAULT_POINTS = 1;


    // *** Constructor ***
    public SquareFactory() {
    }

    // *** Methods ***
    public Square[][] getNewSquaresGrid(int numRows, int numCols) {
        Square[][] squaresGrid = new Square[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                squaresGrid[i][j] = new Square(DEFAULT_TILE, DEFAULT_POINTS);
            }
        }

        return squaresGrid;
    }

}
