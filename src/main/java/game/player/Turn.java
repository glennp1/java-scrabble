package main.java.game.player;

import main.java.game.*;

import java.util.LinkedList;

public class Turn {

    // *** Attributes ***

    // todo remove
    private int count = 0;

    private Player player;
    private Board boardCurrent;
    private Rack rackCurrent;

    private Board boardAtStart;
    private Rack rackAtStart;

    // Stores if the player wishes to pass
    private boolean pass = false;

    // A set of moves completed by the player
    private LinkedList<Move> movesCompleted = new LinkedList<>();

    // A set of words formed by the player
    private LinkedList<Word> wordsFormed = new LinkedList<>();

    // *** Constructor ***
    public Turn(Board board, Player player) {

        // Initialise
        this.player = player;
        boardCurrent = board;
        rackCurrent = player.rack;

        // Create backups of board and rack
        boardAtStart = board.createBackup();
        rackAtStart = player.rack.createBackup();

        signalStart();
        completeMoves();
        signalEnd();
    }

    // *** Methods ***

    /**
     * Signal that it is the start of the player's turn
     */
    private void signalStart() {
        DisplayFacade.getInstance().renderPlayerStartTurn(player);
    }

    /**
     * Signal that it is the end of the player's turn
     */
    private void signalEnd() {
        DisplayFacade.getInstance().renderPlayerEndTurn(player);
    }

    private void completeMoves() {
        // Continue to loop until the turn is passed or valid moves have been made
        while (true) {
            selectPass();
            if (pass) {
                break;
            }

            selectAllMoves();
            if (movesAreValid()) {
                break;
            }
            // Otherwise reset the turn and repeat
            else {
                reset();
            }
        }
    }

    /**
     * The player selects if they wish to pass their current turn
     */
    private void selectPass() {
        DisplayFacade display = DisplayFacade.getInstance();

        // Render the current board and player
        display.renderBoardAndPlayer(boardCurrent, player);

        // Check if the player wishes to pass
        if (display.requestPassInput()) {
            pass = true;
            // The player should trigger the final round
            player.scrabbleGame.triggerFinalRound(player);
        }
    }

    // selectAllMoves
    private void selectAllMoves() {
        do {
            DisplayFacade.getInstance().renderBoardAndPlayer(boardCurrent, player);

            // Have the player select a new move
            Move oneMove = new Move(this);

            // Add it to the completed moves
            movesCompleted.add(oneMove);
        }
        // Continue until the player has finished their turn
        while (!DisplayFacade.getInstance().requestTurnFinished());
    }

    // reset turn
    private void reset() {
        // Restore the board and the rack from their respective backups
        boardCurrent.restoreBackup(boardAtStart);
        rackCurrent.restoreBackup(rackAtStart);

        // Reset the completed moves
        movesCompleted = new LinkedList<>();
    }

    /**
     * Returns
     *
     * @param movesPotential
     * @return
     */
    private boolean movesAreValid() {
        // Check for words across or down for each move
        Word wordAcross;
        Word wordDown;


        // todo test what happens when not valid
        count++;
        return count >= 2;

//        // for each move in moves
//        for (Move move : movesCompleted) {
//
//            wordAcross = null;
//            wordDown = null;
//            EnglishDictionary englishDictionary = EnglishDictionary.getInstance();
//
//            if (move.formsRow()) {
//
//                wordAcross = move.findWordAcross();
//
//                if (englishDictionary.checkForWord(wordAcross)) {
//
//                }
//
//
//            }
//
//
//            if (move.formsColumn()) {
//
//
//                wordDown = move.findWordDown();
//
//            }
//
//
//
//
//        }

        // check for connecting rows

        // check for connecting columns

        // --> if connection exists, go both ways until each end is reached

        // check if wordPotential is not real word
        // discard

        // check if wordPotential is already in wordsFormed AND is same direction
        // discard

        // otherwise store

        // iterate to next




        // todo update the words formed in the process
    }

    public Board getBoardCurrent() {
        return boardCurrent;
    }

    public Rack getRackCurrent() {
        return rackCurrent;
    }

    public LinkedList<Move> getMovesCompleted() {
        return movesCompleted;
    }

    public LinkedList<Word> getWordsFormed() {
        return wordsFormed;
    }
}
