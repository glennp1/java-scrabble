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
    private final int DEFAULT_SQUARE_POINTS = 1;
    private final Board board;

    // *** Constructor ***
    public SquareFactory(Board board) {
        this.board = board;
    }

    // *** Methods ***
    public Square[][] getNewSquaresGrid() {
        Square[][] squaresGrid = new Square[Board.NUM_ROWS][Board.NUM_COLS];

        for (int i = 0; i < Board.NUM_ROWS; i++) {
            for (int j = 0; j < Board.NUM_COLS; j++) {
                squaresGrid[i][j] = new Square(DEFAULT_TILE, DEFAULT_SQUARE_POINTS, i, j, board);
            }
        }

        return squaresGrid;
    }

}
