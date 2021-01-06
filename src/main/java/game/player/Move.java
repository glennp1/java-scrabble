package game.player;

import game.*;

/**
 * <p>Handles the input of, storage of and execution of
 * a single set of commands relating to a player's turn</p>
 * <p>In future this class may be made into a superclass
 * with two subclasses, one to represent a human move
 * and the other to represent a npc move</p>
 */

public class Move {

    // *** Constants ***

    // *** Attributes ***
    /**
     * Stores the square that has been selected for the move
     */
    private Square squareSelected;

    /**
     * Stores the tile that has been selected for the move
     */
    private Tile tileSelected;

    /**
     * Stores the parent turn, that is, the turn to which this move belongs
     */
    private final Turn parentTurn;

    // *** Constructor ***
    /**
     * Creates an instance of move with reference to the specified turn.
     *
     * @param parentTurn the specified turn the move is a part of
     */
    public Move(Turn parentTurn) {
        this.parentTurn = parentTurn;
    }

    // *** Methods ***
    /**
     * Prompts the player to select a tile for their move, ensuring
     * the tile is valid in the process. Then the tile is stored.
     */
    public void selectTile() {
        DisplayFacade display = DisplayFacade.getInstance();
        Rack rackCurrent = parentTurn.getRackCurrent();

        // Request a tile input
        do {
            char charSelected = display.requestCharInput();
            tileSelected = rackCurrent.getTileByChar(charSelected);
        }
        // Continue until the tile selected is valid
        while (!tileIsValid());
    }

    /**
     * Returns if the tile selected is contained within the player's rack
     *
     * @return true if the tile selected is not null, false otherwise
     */
    private boolean tileIsValid() {
        if (tileSelected == null) {
            DisplayFacade.getInstance().renderError("Tile selected is not in the rack");
            return false;
        }
        return true;
    }

    /**
     * Prompts the player to select a square for their move, ensuring
     * the square is valid in the process. Then the square is stored.
     */
    public void selectSquare() {
        DisplayFacade display = DisplayFacade.getInstance();
        Board boardCurrent = parentTurn.getBoardCurrent();

        // Request a square input
        do {
            int rowSelected = display.requestRowInput();
            int colSelected = display.requestColInput();
            squareSelected = boardCurrent.getSquareByCoords(rowSelected, colSelected);
        }
        // Continue until the square selected is valid
        while (!squareIsValid());
    }

    /**
     * Returns if a square selected as a part of a move is valid.
     * The selected square must be empty, and in line with other moves.
     *
     * @return true if the square is valid, false otherwise
     */
    private boolean squareIsValid() {
        DisplayFacade display = DisplayFacade.getInstance();

        // If the square selected is already occupied by a tile, it is not valid
        if (squareSelected.hasTile()) {
            display.renderError("Square selected is already occupied.");
            return false;
        }

        // If the square is not in line with the other squares from the
        // moves in the turn, it is not valid
        if (!squareSelected.isInLine(parentTurn.getMovesCompletedSquares())) {
            display.renderError("Square selected is not in line.");
            return false;
        }

        // The square is valid if neither of the above errors are triggered
        return true;
    }

    /**
     * Executes the specific move, Causing a tile to be removed from the rack
     * and placed on the board.
     */
    public void execute() {
        // Remove the tile from the rack
        parentTurn.getRackCurrent().removeTile(tileSelected);

        // Place it on the square
        squareSelected.setTile(tileSelected);
    }

    /**
     * Getter for the square selected as a part of the move
     * @return the square selected as a part of the move
     */
    public Square getSquareSelected() {
        return squareSelected;
    }
}
