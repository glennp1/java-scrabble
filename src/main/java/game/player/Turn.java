package main.java.game.player;

import main.java.game.Board;
import main.java.game.DisplayFacade;
import main.java.game.Square;
import main.java.game.Tile;
import main.java.game.player.Player;
import main.java.game.player.Rack;

import java.util.LinkedList;

public class Turn {

    // *** Attributes ***

    private Player player;
    private Board boardCurrent;
    private Rack rackCurrent;

    private Board boardAtStart;
    private Rack rackAtStart;

    // A set of moves confirmed by the player
    private LinkedList<Move> movesConfirmed = new LinkedList<>();

    // A set of words formed by the player
    private LinkedList<Word> wordsFormed = new LinkedList<>();

    // *** Constructor ***
    public Turn(Board board, Player player) {

        this.player = player;
        boardCurrent = board;
        rackCurrent = player.rack;

        boardAtStart = board.createBackup();
        rackAtStart = player.rack.createBackup();

        // Access the instance of the display
        DisplayFacade displayFacade = DisplayFacade.getInstance();

        // Render the turn
        displayFacade.renderPlayerTurn(player);

        confirmAllMoves();

        updateScore();

        // Replace placed tiles from the bag
        rackCurrent.fill(player.scrabbleGame.getBag());

        // End Turn
        displayFacade.renderPlayerEndTurn(player);

    }

    // *** Methods ***

    private void confirmAllMoves() {

        LinkedList<Move> movesPotential;

        do {

            // Restore the board and the rack from their respective backups
            boardCurrent.restoreBackup(boardAtStart);
            rackCurrent.restoreBackup(rackAtStart);

            // Have the player select their moves
            movesPotential = selectAllMoves();
        }
        // Continue until the selected moves are valid
        while (!movesAreValid(movesPotential));

        // Confirm the potential moves
        movesConfirmed = movesPotential;

    }

    // selectAllMoves
    private LinkedList<Move> selectAllMoves() {

        // Create a new set of potential moves
        LinkedList<Move> movesPotential = new LinkedList<Move>();

        // Access the instance of the display
        DisplayFacade displayFacade = DisplayFacade.getInstance();

        // Render the board
        displayFacade.renderBoard(boardCurrent);

        // Render the player's rack
        displayFacade.renderPlayerRack(player);
        // Render the player's score
        displayFacade.renderPlayerScore(player);

        // Check if the player wishes to pass
        if (displayFacade.requestPassInput()) {
            // Trigger the final round
            player.scrabbleGame.triggerFinalRound(player);
            // End the turn
            return null;
        }

        do {
            // Have the player select a new move
            Move oneMove = selectOneMove();

            // Add it to the potential moves
            movesPotential.add(oneMove);

            // Execute the move
            oneMove.execute();
        }
        // Continue until the player has finished their turn
        while (!displayFacade.requestTurnFinished());

        return movesPotential;
    }

    private Move selectOneMove() {
        DisplayFacade displayFacade = DisplayFacade.getInstance();
        Move oneMove = new Move(boardCurrent, rackCurrent);
        Tile tileSelected;
        Square squareSelected;

        // Display the current board and rack
        displayFacade.renderBoard(boardCurrent);
        displayFacade.renderPlayerRack(player);

        // Need to check if char selected matches one from the rack (that hasn't already been played
        do {
            char charSelected = displayFacade.requestCharInput();

            tileSelected = rackCurrent.getTileByChar(charSelected);
        }
        // Continue until the tile selected is valid
        while (!tileIsValid(tileSelected));

        // Need to check if in line with previous moves
        // Need to check if square already filled
        do {
            int rowSelected = displayFacade.requestRowInput();
            int colSelected = displayFacade.requestColInput();

            squareSelected = boardCurrent.getSquareByCoords(rowSelected, colSelected);
        }
        // Continue until the square selected is valid
        while (!squareIsValid(squareSelected));

        // Once a valid square and tile have been selected we can add them to the move
        oneMove.setTileSelected(tileSelected);
        oneMove.setSquareSelected(squareSelected);

        return oneMove;
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

        if (!squareSelected.isInLine(movesConfirmed)) {
            displayFacade.renderError("Square selected is not in line.");
            return false;
        }

        return true;
    }

    private boolean movesAreValid(LinkedList<Move> movesPotential) {

        // todo validate

        // todo test what happens when not valid

        // todo update the words formed in the process

        return true;
    }

    /**
     * Updates the score of the player at the end of their turn
     * Calculated by adding the points of each word formed
     */
    private void updateScore() {
        int currScore = player.getScore();

        for (Word word : wordsFormed) {
            currScore += word.getPoints();
        }

        player.setScore(currScore);
    }
}
