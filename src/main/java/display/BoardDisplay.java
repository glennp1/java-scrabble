package main.java.display;

import main.java.game.Board;
import main.java.game.Player;

import java.util.Scanner;

public class BoardDisplay {

    // Attributes

    private final String BOARD_HEADER =
            "     ________ Board-Start ________ \n" +
            "     a b c d e f g h i j k l m n o \n" +
            "     _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n";

    private final String BOARD_FOOTER =
            "     _________ Board-End__________ \n";

    private final int FIRST_ROW = 1;

    // Constructor

    public BoardDisplay() {
    }

    // Methods
    public void render(char[][] formattedBoard) {

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

    // todo introduce new move class
    public void handleNewMoveInput() {

    }

    public void renderPlayerTurn(Player player) {
        System.out.println("It is Player 1's turn.");
    }
}
