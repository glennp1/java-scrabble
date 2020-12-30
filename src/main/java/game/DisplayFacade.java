package main.java.game;

import main.java.display.*;

import java.util.ArrayList;

/**
 * Facade and Singleton class that provides a consistent,
 * global and single point of access to the Display.
 * This ensures that if the Display changes the game can
 * continue to function uninterrupted (Protected Variations).
 */
public class DisplayFacade {

    // *** Attributes ***
    private static DisplayFacade instance = null;

    private final BoardDisplay boardDisplay = new BoardDisplay();
    private final PlayerTurnDisplay playerTurnDisplay = new PlayerTurnDisplay();
    private final PlayerRackDisplay playerRackDisplay = new PlayerRackDisplay();

    private final PlayerInput playerInput = new PlayerInput();

    private final WinnerDisplay winnerDisplay = new WinnerDisplay();

    // *** Constructor ***
    private DisplayFacade() {

    }

    // *** Method ***
    public static DisplayFacade getInstance() {
        if (instance == null) {
            instance = new DisplayFacade();
        }
        return instance;
    }


    public void renderBoard(Board board) {

        char[][] formattedBoard = board.formatForDisplay();

        boardDisplay.render(formattedBoard);
    }

    public void renderPlayerTurn(Player player) {
        int playerNumber = player.getNumber();

        playerTurnDisplay.render(playerNumber);
    }

    public void renderPlayerRack(Player player) {

        ArrayList<Character> formattedRack = player.getRack().formatForDisplay();
        int playerNumber = player.getNumber();

        playerRackDisplay.render(playerNumber, formattedRack);

    }


    public boolean requestPassInput() {
        return playerInput.inputPass();
    }

    // todo split this up
    /**
     *
     * @return a move
     */
    public void requestMoveInput(Move move) {

        char character = playerInput.inputCharacter();
        int row = playerInput.inputRow();
        int col = playerInput.inputCol();
        boolean turnOver = playerInput.inputTurnOver();

        move.processDisplayInput(character, row, col, turnOver);

    }

    public void renderWinner (Player winner) {
        int playerNumber = winner.getNumber();

        winnerDisplay.render(playerNumber);
    }

    public void renderDraw(){
        winnerDisplay.renderDraw();
    }
}
