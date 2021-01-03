package main.java.game.player;

import main.java.game.*;

public class Move {

    // *** Attributes ***

    private Square squareSelected;
    private Tile tileSelected;
    private Turn parentTurn;

    // *** Constructor ***

    public Move(Turn parentTurn) {
        this.parentTurn = parentTurn;

        selectTile();
        selectSquare();
        execute();
    }

    // *** Methods ***

    private void selectTile() {
        DisplayFacade displayFacade = DisplayFacade.getInstance();
        Rack rackCurrent = parentTurn.getRackCurrent();

        // Request a tile input
        do {
            char charSelected = displayFacade.requestCharInput();
            tileSelected = rackCurrent.getTileByChar(charSelected);
        }
        // Continue until the tile selected is valid
        while (!tileIsValid());
    }

    // todo ensure updated rack is checked
    private boolean tileIsValid() {
        if (tileSelected == null) {
            DisplayFacade.getInstance().renderError("Tile selected is not in the rack");
            return false;
        }
        return true;
    }

    private void selectSquare() {
        DisplayFacade displayFacade = DisplayFacade.getInstance();
        Board boardCurrent = parentTurn.getBoardCurrent();

        // Request a square input
        do {
            int rowSelected = displayFacade.requestRowInput();
            int colSelected = displayFacade.requestColInput();
            squareSelected = boardCurrent.getSquareByCoords(rowSelected, colSelected);
        }
        // Continue until the square selected is valid
        while (!squareIsValid());
    }

    /**
     * Returns if a square selected as a part of a move is valid
     *
     * @return true if the square is valid, false otherwise
     */
    private boolean squareIsValid() {
        DisplayFacade displayFacade = DisplayFacade.getInstance();

        // Ensure the square selected is not already occupied by a tile
        if (!squareSelected.isEmpty()) {
            displayFacade.renderError("Square selected is already occupied.");
            return false;
        }

        // Ensure the square selected is in line with the other moves in the turn
        if (!squareSelected.isInLine(parentTurn.getMovesCompleted())) {
            displayFacade.renderError("Square selected is not in line.");
            return false;
        }

        // The square is valid if neither of the above errors are triggered
        return true;
    }

    /**
     * Executes the specific move, causing a tile to be removed from the rack
     * and placed on the board
     *
     */
    private void execute() {
        // Remove the tile from the rack
        parentTurn.getRackCurrent().removeTile(tileSelected);

        // Place it on the square
        squareSelected.setTile(tileSelected);
    }

    public Square getSquareSelected() {
        return squareSelected;
    }

    public Tile getTileSelected() {
        return tileSelected;
    }
}
