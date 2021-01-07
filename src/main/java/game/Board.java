package game;

/**
 * <p>Stores all of the squares in the game and is used to access them</p>
 * <p>The creation logic of each of the squares is instead handled
 * by square factory</p>
 */
public class Board {

    // *** Constants ***
    /**
     * The number of rows on the board
     */
    public static final int NUM_ROWS = 15;

    /**
     * The number of columns on the board
     */
    public static final int NUM_COLS = 15;

    // *** Attributes ***
    /**
     * Stores all of the squares in a two dimensional array
     */
    private final Square[][] squaresGrid;

    /**
     * Stores an instance of the square factory, responsible for creating
     * the squares on the board
     */
    private final SquareFactory squareFactory = new SquareFactory(this);

    // *** Constructor ***

    /**
     * Creates an instance of the board and populates the squares with the
     * help of the square factory
     */
    public Board() {
        squaresGrid = squareFactory.getNewSquaresGrid();
    }

    // *** Methods ***
    /**
     * Gets a specific square on the board, specified
     * by a row and a column
     *
     * @param row
     * @param col
     */
    public Square getSquareByCoords(int row, int col) {
        // If the coords are not valid, return null
        if (!checkCoordsValid(row, col)) {
            return null;
        }

        return squaresGrid[row][col];
    }

    /**
     * Returns if a set of coordinates are valid, that is,
     * they are inside the bounds of the squares grid
     *
     * @param row the row to validate
     * @param col the column to validate
     * @return true if both the row and column are valid, false otherwise
     */
    public boolean checkCoordsValid(int row, int col) {
        // Return false if the row is outside the bounds
        if (row < 0 || col >= Board.NUM_ROWS) {
            return false;
        }

        // Return false if the column is outside the bounds
        if (col < 0 || col >= Board.NUM_COLS) {
            return false;
        }

        // Otherwise return true
        return true;
    }

    /**
     * Returns the all the squares on the board formatted as a
     * two dimensional array of characters
     * This is so that they can be displayed
     *
     * @return two dimensional array of characters on the board
     */
    public char[][] formatForDisplay() {
        char[][] boardFormatted = new char[NUM_ROWS][NUM_COLS];

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                boardFormatted[i][j] = squaresGrid[i][j].getTileCharacter();
            }
        }

        return boardFormatted;
    }

    /**
     * Creates and returns a backup of the current board
     * This is used in case a players turn is invalid and the
     * game needs to be rolled back
     *
     * @return a partial clone of the current board
     */
    public Board createBackup() {
        Board boardBackup = new Board();
        Tile tileCurrent;

        // For each row and column
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {

                // Store the tile that is currently in each square
                tileCurrent = this.squaresGrid[i][j].getTile();
                boardBackup.squaresGrid[i][j].setTile(tileCurrent);
            }
        }

        return boardBackup;
    }

    /**
     * Restores the current board from a specified board backup,
     * this is invoke when the game needs to be rolled back due to
     * an invalid set of moves
     *
     * @param boardBackup the old board that is restored
     */
    public void restoreBackup(Board boardBackup) {
        Tile tileBackup;

        // For each row and column
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {

                // Set the tile in each square to the one in the backup
                tileBackup = boardBackup.squaresGrid[i][j].getTile();
                this.squaresGrid[i][j].setTile(tileBackup);
            }
        }
    }
}
