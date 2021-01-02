package main.java.game.player;

import main.java.game.*;

import java.util.LinkedList;

public class HumanPlayer extends Player {

    // Attributes

    // Constructor
    public HumanPlayer(ScrabbleGame scrabbleGame, int number) {
        super(scrabbleGame, number);
    }

    // Methods
    @Override
    protected void confirmMoves() {

        // todo maybe invalid/potential moves should be stored separately

        do {
            // The player continues to select their moves
            selectAllMoves();
        }
        // Continue until the selected moves are valid
        while (!movesAreValid(movesSelected));
    }

    // selectAllMoves
    private void selectAllMoves() {
        // todo maybe invalid/potential moves should be stored separately
        // Wipe the contents of selected moves
        movesSelected = new LinkedList<Move>();

        // Access the instance of the display
        DisplayFacade displayFacade = DisplayFacade.getInstance();

        // Check if the player wishes to pass
        if (displayFacade.requestPassInput()) {
            // Trigger the final round
            scrabbleGame.triggerFinalRound(this);
            // End the turn
            return;
        }

        do {
            // Have the player input a new move
            selectOneMove();
        }
        // Continue until the player has finished their turn
        while (!displayFacade.requestTurnFinished());
    }

    private void selectOneMove() {
        DisplayFacade displayFacade = DisplayFacade.getInstance();

        Move move = new Move(scrabbleGame.getBoard(), this.rack);

        Tile tileSelected;
        Square squareSelected;


        // Need to check if char selected matches one from the rack (that hasn't already been played
        do {
            char charSelected = displayFacade.requestCharInput();

            tileSelected = rack.getTileByChar(charSelected);
        }
        // Continue until the tile selected is valid
        while (!tileIsValid(tileSelected));

        // Need to check if in line with previous moves
        // Need to check if square already filled
        do {
            int rowSelected = displayFacade.requestRowInput();
            int colSelected = displayFacade.requestColInput();

            Board board = scrabbleGame.getBoard();
            squareSelected = board.getSquareByCoords(rowSelected, colSelected);
        }
        // Continue until the square selected is valid
        while (!squareIsValid(squareSelected));




        // Once a valid square and tile have been selected we can add them to the move
        move.setTileSelected(tileSelected);
        move.setSquareSelected(squareSelected);


        movesSelected.add(move);
    }


    // todo ensure updated rack is checked
    private boolean tileIsValid(Tile tileSelected) {
        if (tileSelected == null) {
            DisplayFacade.getInstance().renderError("Tile selected is not in the rack");
            return false;
        }
        return true;
    }

    // todo ensure update moves / board are checked
    private boolean squareIsValid(Square squareSelected) {
        DisplayFacade displayFacade = DisplayFacade.getInstance();

        if (!squareSelected.isEmpty()) {
            displayFacade.renderError("Square selected is already occupied.");
            return false;
        }

        if (!squareSelected.isInLine(movesSelected)) {
            displayFacade.renderError("Square selected is not in line.");
            return false;
        }

        return true;
    }

    // moves are valid

    private boolean movesAreValid(LinkedList<Move> selectedMoves) {

        // todo validate

        return true;
    }

}
