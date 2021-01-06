package game;

/**
 * <p>Factory helper class used to instantiate each square.</p>
 * <p>Separated from the Board class so as to increase cohesion,
 * and to isolate the logic used in the creation of squares in case this
 * is updated </p>
 */
public class SquareFactory {

    // *** Constants ***
    /**
     * Represents a square that is empty, that is, one that's tile is null
     */
    private static final Tile DEFAULT_TILE = null;

    // *** Attributes ***
    /**
     * The default points that a square is worth, may be changed later
     */
    private static final int DEFAULT_SQUARE_POINTS = 1;

    /**
     * The board the squares are being created for
     */
    private final Board board;

    // *** Constructor ***
    /**
     * Creates a new instance of the Square Factory, the board is required by the
     * class to determine the dimensions needed
     *
     * @param board the board the squares are being created for
     */
    public SquareFactory(Board board) {
        this.board = board;
    }

    // *** Methods ***
    /**
     * Creates and returns a new two dimensional array of squares
     * The dimensions are created with respect to the scrabble board.
     *
     * @return a two dimensional array of squares
     */
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
