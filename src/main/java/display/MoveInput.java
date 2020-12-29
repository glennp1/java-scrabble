package main.java.display;

import java.util.Scanner;

public class MoveInput {

    // *** Attributes ***

    // Included so that matrices start at 0 not 1
    private final int ROW_OFFSET = 1;

    private final int FIRST_POSITION = 0;

    private final char START_OF_ALPHABET = 'a';
    private final String YES = "y";

    // *** Constructor ***

    public MoveInput() {
    }

    // *** Methods ***

    public boolean inputPass() {

        String userInput = processInput(
                "Do you want to pass this turn (y/n): ",
                "[yn]"
        );

        return userInput.equals(YES);
    }

    public char inputCharacter() {

        String userInput = processInput(
                "Enter the TILE you wish to place (a-z): ",
                "[a-z]"
        );

        return userInput.charAt(FIRST_POSITION);
    }

    public int inputRow() {

        String userInput = processInput(
                "Enter the ROW where you want the tile placed (1-15): ",
                "[1-9]|1[0-5]"
                );

        return Integer.parseInt(userInput) - ROW_OFFSET;
    }

    public int inputCol() {

        String userInput = processInput(
                "Enter the COLUMN where you want the tile placed (a-o): ",
                "[a-o]"
                );


        char userInputChar = userInput.charAt(FIRST_POSITION);
        return userInputChar - START_OF_ALPHABET;
    }

    public boolean inputTurnOver() {

        String userInput = processInput(
                "Do you want to end your turn (y/n): ",
                "[yn]"
        );

        return userInput.equals(YES);
    }

    /**
     *
     *
     * @param request
     * @param regex
     * @return
     */
    private String processInput(String request, String regex) {

        // Create a new Scanner object
        Scanner scanner = new Scanner(System.in);

        // Print request
        System.out.println(request);

        // While the next scanner input does not match the specified regex
        while (!scanner.hasNext(regex)) {
            // Inform the user of invalid input and request further input
            System.out.println("Invalid Input.");
            System.out.println(request);
            scanner.next();
        }

        // Once successful, return the input
        return scanner.next();
    }
}
