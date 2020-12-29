package main.java.game;

public class Board {

    // Attributes

    // todo remove
    private final int NUM_ROWS = 15;
    private final int NUM_COLS = 15;

    private Square[][] squaresGrid;

    private final SquareFactory squareFactory = new SquareFactory();

    // Constructor
    public Board() {

        squaresGrid = squareFactory.getNewSquaresGrid(NUM_ROWS, NUM_COLS);

        System.out.println(squaresGrid[0][0]);

    }

    // Methods

    /**
     * Sets the tile of a specific square on the board, specified
     * by a row and a column
     *
     * @param row
     * @param col
     * @param tile
     */
    private void setSquare(int row, int col, Tile tile) {
        squaresGrid[row][col].setTile(tile);
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
}
