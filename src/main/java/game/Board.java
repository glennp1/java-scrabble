package game;

/**
 * <p></p>
 * <p></p>
 */
public class Board {

    // *** Constants ***
    /**
     *
     */
    public static final int NUM_ROWS = 15;

    /**
     *
     */
    public static final int NUM_COLS = 15;

    // *** Attributes ***
    /**
     *
     */
    private final Square[][] squaresGrid;

    /**
     *
     */
    private final SquareFactory squareFactory = new SquareFactory(this);

    // *** Constructor ***

    /**
     *
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
     *
     * @param row
     * @param col
     * @return
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
     *
     * @return
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
     *
     * @return
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
     *
     * @param boardBackup
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
