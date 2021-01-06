package game.player;

import game.*;

import java.util.LinkedList;

public class Turn {

    // *** Attributes ***

    private Player player;
    private Board boardCurrent;
    private Rack rackCurrent;

    private Board boardAtStart;
    private Rack rackAtStart;

    // Stores if it is the first turn of the game
    private boolean firstTurn;

    // Stores if the player wishes to pass
    private boolean pass = false;

    // A set of moves completed by the player
    private LinkedList<Move> movesCompleted = new LinkedList<>();

    // A set of words formed by the player
    private LinkedList<Word> wordsFormed = new LinkedList<>();

    // *** Constructor ***
    public Turn(Board board, Player player, boolean firstTurn) {

        // Initialise
        this.player = player;
        boardCurrent = board;
        rackCurrent = player.rack;
        this.firstTurn = firstTurn;

        // Create backups of board and rack
        boardAtStart = board.createBackup();
        rackAtStart = player.rack.createBackup();
    }

    // *** Methods ***

    /**
     * Signal that it is the start of the player's turn
     */
    public void signalStart() {
        DisplayFacade.getInstance().renderPlayerStartTurn(player);
    }

    /**
     * Signal that it is the end of the player's turn
     */
    public void signalEnd() {
        DisplayFacade.getInstance().renderPlayerEndTurn(player);
    }

    /**
     * Has the player select and complete all their moves for their turn,
     * returning true once valid. If the player choose to pass instead
     * the method returns false.
     *
     * @return true if valid, false if pass
     */
    public boolean completeMoves() {
        // Continue to loop until the turn is passed or valid moves have been made
        while (true) {
            if (selectPass()) {
                return false;
            }

            selectAllMoves();
            if (allMovesAreValid()) {
                return true;
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
    private boolean selectPass() {
        DisplayFacade display = DisplayFacade.getInstance();

        // Render the current board and player
        display.renderBoardAndPlayer(boardCurrent, player);

        // Check if the player wishes to pass
        return display.requestPassInput();
    }

    // selectAllMoves
    private void selectAllMoves() {
        do {
            DisplayFacade.getInstance().renderBoardAndPlayer(boardCurrent, player);

            // Have the player select a new move
            Move oneMove = new Move(this);

            oneMove.selectTile();
            oneMove.selectSquare();
            oneMove.execute();

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


    private boolean allMovesAreValid() {
        // If the moves are not connect appropriately, they are not valid
        if (!allMovesAreConnected()) {
            return false;
        }

        // If the words formed are invalid or absent, the moves are invalid
        if (!wordsFormedAreValid()) {
            return false;
        }

        // If no issues were raised, all moves are valid
        return true;
    }

    private boolean wordsFormedAreValid() {
        DisplayFacade display = DisplayFacade.getInstance();
        EnglishDictionary englishDictionary = EnglishDictionary.getInstance();

        // Find the potential words
        LinkedList<Word> potentialWords = findPotentialWords();

        // For each potential word
        for (Word word : potentialWords) {

            // todo remove
            System.out.println(word.toString());

            // If the word is NOT a valid english word, the moves are not valid
            if (!englishDictionary.checkIfValid(word.toString())) {
                display.renderError(
                        "Set of moves selected form an invalid word. Please try again.");
                return false;
            }

            // If the word (of the same orientation) is not in words formed
            if (!word.existsIn(wordsFormed)) {
                // Add the word to words formed
                wordsFormed.add(word);
            }
        }

        // If no words have been formed then the moves are not valid
        if (wordsFormed.size() == 0) {
            display.renderError(
                    "Set of moves selected do not form a word. Please try again.");
            return false;
        }

        // If no issues were raised, all words are valid
        return true;
    }

    /**
     *
     * @return
     */
    private boolean allMovesAreConnected() {
        DisplayFacade display = DisplayFacade.getInstance();

        // If it is not the first turn
        if (!firstTurn) {
            // If moves do not connect with an existing tile, they are not valid
            if (!allMovesConnectWithOther()) {
                display.renderError("Set of moves do not connect with an " +
                        "existing tile. Please try again");
                return false;
            }
        }

        // If there is more than one move
        if (movesCompleted.size() > 1) {
            // If moves do not connect with one another, they are not valid
            if (!allMovesConnectWithSelf()) {
                display.renderError("Set of moves do not connect with " +
                        "one another. Please try again");
                return false;
            }
        }

        // If no issues are found, moves must be connected appropriately
        return true;
    }

    /**
     * Returns if the moves completed connect with one another to form a line
     * This is inclusive of any pre existing tiles that might form part of the line
     *
     * @return true if the moves completed connect with one another, false otherwise
     */
    private boolean allMovesConnectWithSelf() {
        // Create a new 'word' of connected squares
        // Starting from the first move and in the specified direction
        Square firstMove = movesCompleted.getFirst().getSquareSelected();
        boolean isHorizontal = checkMovesHorizontal();
        Word squaresConnectedWord = new Word (firstMove, isHorizontal);

        // Extract the squares from the word
        LinkedList<Square> squaresConnected = squaresConnectedWord.getSquaresInWord();

        // For each move
        for (Move move : movesCompleted) {

            // Check that the move's square is present in the squares connected
            boolean found = false;
            for (Square square: squaresConnected) {
                if (move.getSquareSelected() == square) {
                    found = true;
                    break;
                }
            }

            // If the move's square is not found
            if (!found) {
                // Then it is not connected to the rest of the move
                return false;
            }
        }

        // If all moves are successfully found, then return true
        return true;
    }

    private boolean checkMovesHorizontal() {
        // Get rows of the first two moves
        int rowFirstMove = movesCompleted.getFirst().getSquareSelected().getRow();
        int rowLastMove = movesCompleted.getLast().getSquareSelected().getRow();
        // If the rows are the same, then the moves are horizontal
        // Note that the row / column has already been enforced during input so
        // We only need to check two moves not the entire set
        return (rowFirstMove == rowLastMove);
    }



    /**
     * Returns if the moves completed connect with a pre existing tile
     *
     * @return true if a move is found to connect with a pre existing tile, false otherwise
     */
    private boolean allMovesConnectWithOther() {
        Square currSquare;
        LinkedList<Square> neighbours;

        // Fore each move in the completed moves
        for (Move move : movesCompleted) {

            // Find each of the neighbouring tiles to the current square
            currSquare = move.getSquareSelected();
            neighbours = currSquare.getAllNeighbours();

            // If any of these neighbouring squares is not a part of moves
            for (Square neighbour : neighbours) {
                if (!squareWithinMoves(neighbour)) {
                    // Then the moves connect with another existing square
                    return true;
                }
            }
        }

        // Otherwise they do not connect with a pre existing tile
        return false;
    }

    /**
     * Returns if a given square is contained within a list of moves
     *
     * @param square the square to check
     * @return true if the square is present in moves, false otherwise
     */
    private boolean squareWithinMoves(Square square) {
        // If one of the squares within the moves completed matches
        for (Move move :movesCompleted) {
            if (square == move.getSquareSelected()) {
                // Then the square is contained within the move
                return true;
            }
        }

        // Otherwise the square must not be in the moves
        return false;
    }

    private LinkedList<Word> findPotentialWords() {
        // New linked list of potential words
        LinkedList<Word> potentialWords = new LinkedList<>();

        // for each move in moves
        for (Move move : movesCompleted) {

            // Set the starting square
            Square startingSquare = move.getSquareSelected();

            // If the starting square forms a row
            if (startingSquare.formsRow()) {
                // Add the new horizontal word to the potential words
                potentialWords.add(new Word(startingSquare, true));
            }

            // If the starting square forms a column
            if (startingSquare.formsColumn()) {
                // Add the new vertical word to the potential words
                potentialWords.add(new Word(startingSquare, false));
            }
        }

        return potentialWords;
    }


        // todo update the words formed in the process

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
