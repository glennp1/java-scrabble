package main.java.game;

import main.java.display.*;
import main.java.game.player.Player;

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
    private final PlayerScoreDisplay playerScoreDisplay = new PlayerScoreDisplay();
    private final PlayerRackDisplay playerRackDisplay = new PlayerRackDisplay();

    private final PlayerInput playerInput = new PlayerInput();

    private final WinnerDisplay winnerDisplay = new WinnerDisplay();

    private final ErrorDisplay errorDisplay = new ErrorDisplay();

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

    public void renderBoardAndPlayer(Board board, Player player) {
        renderBoard(board);
        renderPlayerRack(player);
        renderPlayerScore(player);
    }


    public void renderBoard(Board board) {

        char[][] formattedBoard = board.formatForDisplay();

        boardDisplay.render(formattedBoard);
    }

    public void renderPlayerStartTurn(Player player) {
        int playerNumber = player.getNumber();

        playerTurnDisplay.renderStart(playerNumber);
    }

    public void renderPlayerScore(Player player) {
        int playerNumber = player.getNumber();
        int playerScore = player.getScore();

        playerScoreDisplay.render(playerNumber, playerScore);
    }

    public void renderPlayerRack(Player player) {

        ArrayList<Character> formattedRack = player.getRack().formatForDisplay();
        int playerNumber = player.getNumber();

        playerRackDisplay.render(playerNumber, formattedRack);

    }

    public void renderPlayerEndTurn(Player player) {
        int playerNumber = player.getNumber();

        playerTurnDisplay.renderEnd(playerNumber);
    }


    public boolean requestPassInput() {
        return playerInput.inputPass();
    }

    // todo split this up
    /**
     *
     * @return if the turn is over
     */
    public char requestCharInput() {
        return playerInput.inputChar();
    }

    public int requestRowInput() {
        return playerInput.inputRow();
    }

    public int requestColInput() {
        return playerInput.inputCol();
    }

    public boolean requestTurnFinished() {
        return playerInput.inputTurnFinished();
    }

    public void renderWinner (Player winner) {
        int playerNumber = winner.getNumber();

        winnerDisplay.render(playerNumber);
    }

    public void renderDraw(){
        winnerDisplay.renderDraw();
    }

    public void renderError(String error) {
        errorDisplay.render(error);
    }
}
