package game;

import display.*;
import game.player.Player;
import game.player.Word;

import java.util.ArrayList;

/**
 * Facade and Singleton class that provides a consistent,
 * global and single point of access to the Display.
 * This ensures that if the Display changes the game can
 * continue to function uninterrupted (Protected Variations).
 */
public class DisplayFacade {

    // *** Attributes ***
    /**
     *
     */
    private static DisplayFacade instance = null;

    /**
     *
     */
    private final ScrabbleInput scrabbleInput = new ScrabbleInput();

    /**
     *
     */
    private final ScrabbleOutput scrabbleOutput = new ScrabbleOutput();

    // *** Constructor ***
    /**
     *
     */
    private DisplayFacade() {

    }

    // *** Methods ***
    /**
     *
     * @return
     */
    public static DisplayFacade getInstance() {
        if (instance == null) {
            instance = new DisplayFacade();
        }
        return instance;
    }

    /**
     *
     * @param board
     * @param player
     */
    public void renderBoardAndPlayer(Board board, Player player) {
        renderBoard(board);
        renderPlayerRack(player);
        renderPlayerScore(player);
    }

    /**
     *
     * @param board
     */
    public void renderBoard(Board board) {

        char[][] formattedBoard = board.formatForDisplay();

        scrabbleOutput.outputBoard(formattedBoard);
    }

    /**
     *
     * @param player
     */
    public void renderPlayerRack(Player player) {

        ArrayList<Character> formattedRack = player.getRack().formatForDisplay();
        int playerNumber = player.getNumber();

        scrabbleOutput.outputPlayerRack(playerNumber, formattedRack);

    }

    /**
     *
     * @param player
     */
    public void renderPlayerScore(Player player) {
        int playerNumber = player.getNumber();
        int playerScore = player.getScore();

        scrabbleOutput.outputPlayerScore(playerNumber, playerScore);
    }

    /**
     *
     * @param player
     * @param word
     */
    public void renderWordPoints(Player player, Word word) {
        int playerNumber = player.getNumber();
        String wordAsString = word.toString();
        int points = word.getPoints();

        scrabbleOutput.outputPlayerWordPoints(playerNumber, wordAsString, points);
    }

    /**
     *
     * @param player
     */
    public void renderPlayerStartTurn(Player player) {
        int playerNumber = player.getNumber();

        scrabbleOutput.outputPlayerStartTurn(playerNumber);
    }

    /**
     *
     * @param player
     */
    public void renderPlayerEndTurn(Player player) {
        int playerNumber = player.getNumber();

        scrabbleOutput.outputPlayerEndTurn(playerNumber);
    }

    /**
     *
     * @param winner
     */
    public void renderWinner (Player winner) {
        int playerNumber = winner.getNumber();

        scrabbleOutput.outputWinner(playerNumber);
    }

    /**
     *
     */
    public void renderDraw(){
        scrabbleOutput.outputDraw();
    }

    /**
     *
     * @param error
     */
    public void renderError(String error) {
        scrabbleOutput.outputError(error);
    }

    /**
     *
     * @return
     */
    public boolean requestPassInput() {
        return scrabbleInput.inputPass();
    }

    /**
     *
     * @return
     */
    public char requestCharInput() {
        return scrabbleInput.inputChar();
    }

    /**
     *
     * @return
     */
    public int requestRowInput() {
        return scrabbleInput.inputRow();
    }

    /**
     *
     * @return
     */
    public int requestColInput() {
        return scrabbleInput.inputCol();
    }

    /**
     *
     * @return
     */
    public boolean requestTurnFinished() {
        return scrabbleInput.inputTurnFinished();
    }

}