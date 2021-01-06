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

    // *** Constants ***

    // *** Attributes ***
    /**
     * The current instance of the display facade (singleton pattern)
     */
    private static DisplayFacade instance = null;

    /**
     * Stores a new instance to handle input to the game
     */
    private final ScrabbleInput scrabbleInput = new ScrabbleInput();

    /**
     * Stores a new instance to handle output of the game
     */
    private final ScrabbleOutput scrabbleOutput = new ScrabbleOutput();

    // *** Constructor ***
    /**
     * Creates an instance of the display facade
     */
    private DisplayFacade() {

    }

    // *** Methods ***
    /**
     * Gets an atomic instance of the display facade class,
     * creating one if it doesnt exist
     *
     * @return single instance of the display facade
     */
    public static DisplayFacade getInstance() {
        if (instance == null) {
            instance = new DisplayFacade();
        }
        return instance;
    }

    /**
     * Renders a specified board, player rack and player score
     *
     * @param board the board to render
     * @param player the player to render
     */
    public void renderBoardAndPlayer(Board board, Player player) {
        renderBoard(board);
        renderPlayerRack(player);
        renderPlayerScore(player);
    }

    /**
     * Renders a specified board, formatting the board as a two
     * dimensional array of characters so it can be outputted
     *
     * @param board the board to render
     */
    public void renderBoard(Board board) {

        char[][] formattedBoard = board.formatForDisplay();

        scrabbleOutput.outputBoard(formattedBoard);
    }

    /**
     * Renders a specified rack, formatting the rack as an arraylist of
     * characters so that it can be outputted
     *
     * @param player the player who's rack is being rendered
     */
    public void renderPlayerRack(Player player) {

        ArrayList<Character> formattedRack = player.getRack().formatForDisplay();
        int playerNumber = player.getNumber();

        scrabbleOutput.outputPlayerRack(playerNumber, formattedRack);
    }

    /**
     * Renders a specified score for a player
     *
     * @param player the player who's score is being rendered
     */
    public void renderPlayerScore(Player player) {
        int playerNumber = player.getNumber();
        int playerScore = player.getScore();

        scrabbleOutput.outputPlayerScore(playerNumber, playerScore);
    }

    /**
     * Renders the points a word is worth when it is formed, attributing
     * it to the player that formed it. Formats the word so that it can
     * be outputted
     *
     * @param player the player that formed the word
     * @param word the word that was formed
     */
    public void renderWordPoints(Player player, Word word) {
        int playerNumber = player.getNumber();
        String wordAsString = word.toString();
        int points = word.getPoints();

        scrabbleOutput.outputPlayerWordPoints(playerNumber, wordAsString, points);
    }

    /**
     * Renders that a specified player is starting their turn
     *
     * @param player the player starting their turn
     */
    public void renderPlayerStartTurn(Player player) {
        int playerNumber = player.getNumber();

        scrabbleOutput.outputPlayerStartTurn(playerNumber);
    }

    /**
     * Renders that a specified player is ending their turn
     *
     * @param player the player ending their turn
     */
    public void renderPlayerEndTurn(Player player) {
        int playerNumber = player.getNumber();

        scrabbleOutput.outputPlayerEndTurn(playerNumber);
    }

    /**
     * Renders that a specified player is the winner
     *
     * @param winner the winning player
     */
    public void renderWinner (Player winner) {
        int playerNumber = winner.getNumber();

        scrabbleOutput.outputWinner(playerNumber);
    }

    /**
     * Renders that the game ended in a draw
     */
    public void renderDraw(){
        scrabbleOutput.outputDraw();
    }

    /**
     * Renders a specified error, formatted as a string
     *
     * @param error a string representation of the error
     */
    public void renderError(String error) {
        scrabbleOutput.outputError(error);
    }

    /**
     * Requests if the player wishes to pass
     *
     * @return true if the player wishes to pass, false otherwise
     */
    public boolean requestPassInput() {
        return scrabbleInput.inputPass();
    }

    /**
     * Requests the player to input a character from their rack that
     * they wish to place
     *
     * @return the character selected
     */
    public char requestCharInput() {
        return scrabbleInput.inputChar();
    }

    /**
     * Requests the player to input a row they wish to place
     * their selected character
     *
     * @return the row selected
     */
    public int requestRowInput() {
        return scrabbleInput.inputRow();
    }

    /**
     * Requests the player to input a column they wish to place
     * their selected character
     *
     * @return the column selected
     */
    public int requestColInput() {
        return scrabbleInput.inputCol();
    }

    /**
     * Requests if the player wishes to end their turn
     *
     * @return true if they wish to end, false otherwise
     */
    public boolean requestTurnFinished() {
        return scrabbleInput.inputTurnFinished();
    }

}