package display;

import java.util.ArrayList;

/**
 * Handles the output of the scrabble game to the console.
 * Has been separated from the game logic in case changes are made in the future
 */
public class ScrabbleOutput {

    // *** Constants ***

    private static final String BOARD_HEADER =
            "\n" +
                    "     ________ Board-Start ________ \n" +
                    "     a b c d e f g h i j k l m n o \n" +
                    "     _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n";

    private static final String BOARD_FOOTER =
            "     _________ Board-End__________ \n\n";

    private static final int FIRST_ROW = 1;

    // *** Attributes ***

    // *** Constructor ***

    public ScrabbleOutput() {
    }

    // *** Methods ***
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

    public void outputPlayerRack(int playerNumber, ArrayList<Character> rackAsCharArray) {
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

    public void outputPlayerScore(int playerNumber, int playerScore) {
        System.out.printf("Player %d's score: %d\n", playerNumber, playerScore);
    }

    public void outputPlayerWordPoints(int playerNumber, String word, int points) {
        System.out.printf("Player %d formed %s for %d points.\n",
                playerNumber, word, points);
    }

    public void outputPlayerStartTurn(int playerNumber) {
        System.out.printf("\nIt is Player %d's turn.\n", playerNumber);
    }

    public void outputPlayerEndTurn(int playerNumber) {
        System.out.printf("Player %d has ended their turn.\n", playerNumber);

    }

    public void outputWinner(int playerNumber) {

        System.out.printf("\n The winner is player %d!\n", playerNumber);
    }

    public void outputDraw() {
        System.out.println("\n It's a draw!");
    }

    public void outputError(String error) {
        System.out.println("Error: " + error);
    }


}
