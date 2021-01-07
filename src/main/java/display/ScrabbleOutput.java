package display;

import java.util.ArrayList;

/**
 * <p>Handles the output of the scrabble game to the console.</p>
 * <p>Has been separated from the game logic in case changes are
 * made in the future. Is currently a class rather than an
 * interface but could be either depending on implementation</p>
 */
public class ScrabbleOutput {

    // *** Constants ***
    /**
     * A string representation of the top of the board
     */
    private static final String BOARD_HEADER =
            "\n" +
                    "     ________ Board-Start ________ \n" +
                    "     a b c d e f g h i j k l m n o \n" +
                    "     _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n";

    /**
     * A string representation of the bottom of the board
     */
    private static final String BOARD_FOOTER =
            "     _________ Board-End__________ \n\n";

    /**
     * The first row starts at 1 on the display, unlike in the
     * game logic where the row array starts at 0
     */
    private static final int FIRST_ROW = 1;

    // *** Attributes ***

    // *** Constructor ***
    /**
     * Creates an instance of the scrabble output
     */
    public ScrabbleOutput() {
    }

    // *** Methods ***

    /**
     * Outputs the given formatted board to the console
     *
     * @param formattedBoard a board formatted as a two dimensional
     *                       array of characters
     */
    public void outputBoard(char[][] formattedBoard) {

        System.out.print(BOARD_HEADER);

        // Keep track of the  row
        int rowCounter = FIRST_ROW;

        for (char[] boardRow : formattedBoard) {
            // Display the row number and increment it afterwards
            System.out.printf("%3d |", rowCounter++);

            // Display each character in the board row
            for (char character : boardRow) {
                System.out.print(character + "|");
            }

            // End the line
            System.out.print("\n"); }

        System.out.print(BOARD_FOOTER);
    }

    /**
     * Outputs the given player's rack to the console
     *
     * @param playerNumber integer representing a player's number
     * @param rackAsCharArray a rack formatted as an array list
     *                        of characters
     */
    public void outputPlayerRack(int playerNumber,
                                 ArrayList<Character> rackAsCharArray) {
        System.out.print("Player " + playerNumber + "'s rack: ");

        if (rackAsCharArray.size() > 0) {
            System.out.print("|");
            for (char character : rackAsCharArray) {
                System.out.print(character + "|");
            }
        } else {
            System.out.print("Empty");
        }

        // Start a new line at the end
        System.out.print("\n");
    }

    /**
     * Outputs the given player's score to the console
     *
     * @param playerNumber integer representing a player's number
     * @param playerScore integer representing the player's score
     */
    public void outputPlayerScore(int playerNumber, int playerScore) {
        System.out.printf("Player %d's score: %d\n", playerNumber, playerScore);
    }

    /**
     * Outputs the given player's word formed and its points to the console
     *
     * @param playerNumber integer representing a player's number
     * @param word string representation of the word formed
     * @param points integer representing the points the word is word
     */
    public void outputPlayerWordPoints(int playerNumber,
                                       String word, int points) {
        System.out.printf("Player %d formed %s for %d points.\n",
                playerNumber, word, points);
    }

    /**
     * Outputs that it is the start of the given player's turn to the console
     *
     * @param playerNumber integer representing a player's number
     */
    public void outputPlayerStartTurn(int playerNumber) {
        System.out.printf("\nIt is Player %d's turn.\n", playerNumber);
    }

    /**
     * Outputs that it is the end of the given player's turn to the console
     *
     * @param playerNumber integer representing a player's number
     */
    public void outputPlayerEndTurn(int playerNumber) {
        System.out.printf("Player %d has ended their turn.\n", playerNumber);

    }

    /**
     * Outputs that the given player is the game winner to the console
     *
     * @param playerNumber integer representing a player's number
     */
    public void outputWinner(int playerNumber) {

        System.out.printf("\n The winner is player %d!\n", playerNumber);
    }

    /**
     * Outputs that the game is a draw to the console
     *
     */
    public void outputDraw() {
        System.out.println("\n It's a draw!");
    }

    /**
     * Outputs the given error to the console
     *
     * @param error string representation of the error
     */
    public void outputError(String error) {
        System.out.println("Error: " + error);
    }


}
