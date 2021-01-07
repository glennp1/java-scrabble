package game.player;

import game.*;

import java.util.LinkedList;

/**
 * <p>Represents a single players turn.</p>
 * <p>Prompts the player to enter their moves and ensures they are valid.
 * In the even moves are not valid a rollback of the game is performed
 * and the player is prompted to enter their moves again</p>
 * <p>In future this class may be made into a superclass
 * with two subclasses, one to represent a human turn
 * and the other to represent a npc turn</p>
 */
public class Turn {

    // *** Constants ***

    // *** Attributes ***
    /**
     * The player whose turn it is
     */
    private final Player player;

    /**
     * The current board state that the turn is acting on
     */
    private final Board boardCurrent;

    /**
     * The current player's rack that the turn is acting on
     */
    private final Rack rackCurrent;

    /**
     * A backup of the board from the start of the player's turn.
     */
    private final Board boardAtStart;

    /**
     * A backup of the player's rack from the start of the player's turn.
     */
    private final Rack rackAtStart;

    /**
     * Stores if it is the first turn of the game
     */
    private final boolean firstTurn;

    /**
     * A set of moves completed by the player
     */
    private LinkedList<Move> movesCompleted = new LinkedList<>();

    /**
     * A set of words formed by the player
     */
    private LinkedList<Word> wordsFormed = new LinkedList<>();

    // *** Constructor ***
    /**
     * Creates an instance of the turn class, initialises the
     * parameters and then also creating backups of the
     * board and player's rack
     *
     * @param board the current state of the board
     * @param player the player whose turn it is
     * @param firstTurn if this is the first turn of the game
     */
    public Turn(Board board, Player player, boolean firstTurn) {
        // Initialise
        this.player = player;
        boardCurrent = board;
        rackCurrent = player.getRack();
        this.firstTurn = firstTurn;

        // Create backups of board and rack
        boardAtStart = board.createBackup();
        rackAtStart = player.getRack().createBackup();
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

    /**
     * The player selects all of their moves until they indicate they have
     * finished their turn
     */
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


    /**
     * Rolls back both the board and the player's rack to the
     * stored backups, also resets the completed moves
     */
    private void reset() {
        // Restore the board and the rack from their respective backups
        boardCurrent.restoreBackup(boardAtStart);
        rackCurrent.restoreBackup(rackAtStart);

        // Reset the completed moves
        movesCompleted = new LinkedList<>();
    }

    /**
     * Returns if the the moves completed by the player are valid
     *
     * @return true if all are valid, false otherwise
     */
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

    /**
     * Returns if the words formed by the player are valid, that is,
     * they are english words, and at least one word was formed
     *
     * @return true if the words are all valid, false otherwise
     */
    private boolean wordsFormedAreValid() {
        DisplayFacade display = DisplayFacade.getInstance();
        EnglishDictionary englishDictionary = EnglishDictionary.getInstance();

        // Find the potential words
        LinkedList<Word> potentialWords = findPotentialWords();

        // For each potential word
        for (Word word : potentialWords) {

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
     * Returns if the moves completed by the player are all connected,
     * both with an existing move, if it is not the first turn, and with
     * one another, if there is more that one move
     *
     * @return true if they are adequately connected, false otherwise
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

    /**
     * Returns if the moves completed are orientated horizontally
     *
     * @return true if horizontal, false if vertical
     */
    private boolean checkMovesHorizontal() {
        // Get rows of the first two moves
        int rowFirstMove = movesCompleted.getFirst().getSquareSelected().getRow();
        int rowLastMove = movesCompleted.getLast().getSquareSelected().getRow();
        // If the rows are the same, then the moves are horizontal
        // Note that the row or column has already been enforced during input so
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
                if (!isSquareWithinMoves(neighbour)) {
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
    private boolean isSquareWithinMoves(Square square) {
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

    /**
     * Returns a linked list of all the potential words, that is,
     * sets of squares that are adjacent to any move placed
     *
     * @return linked list of potential word the player has formed
     */
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

    /**
     * Returns a list of squares that correspond to all of the
     * completed moves
     *
     * @return a list of squares from moves completed
     */
    public LinkedList<Square> getMovesCompletedSquares() {
        LinkedList<Square> squares = new LinkedList<>();
        for (Move move : movesCompleted) {
            squares.add(move.getSquareSelected());
        }
        return squares;
    }

    /**
     * Getter for the current board, used when a square is
     * selected within the move class
     *
     * @return the current board
     */
    public Board getBoardCurrent() {
        return boardCurrent;
    }

    /**
     * Getter for the current rack, also used in determining the
     * player's move
     *
     * @return the current rack
     */
    public Rack getRackCurrent() {
        return rackCurrent;
    }

    /**
     * Getter for the words formed, used when the score is updated
     *
     * @return a list of the words formed by the player
     */
    public LinkedList<Word> getWordsFormed() {
        return wordsFormed;
    }
}
