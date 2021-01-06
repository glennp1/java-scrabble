package display;

import java.util.Scanner;

/**
 * <p></p>
 * <p></p>
 */
public class ScrabbleInput {

    // *** Constants ***
    /**
     * Row offset included so that matrices start at 0 not 1
     */
    private static final int ROW_OFFSET = 1;

    /**
     *
     */
    private static final int FIRST_POSITION = 0;

    /**
     *
     */
    private static final char START_OF_ALPHABET = 'a';

    /**
     *
     */
    private static final String YES = "y";

    // *** Attributes ***

    // *** Constructor ***
    /**
     *
     */
    public ScrabbleInput() {
    }

    // *** Methods ***
    /**
     *
     * @return
     */
    public boolean inputPass() {

        String userInput = processInput(
                "Do you want to pass this turn? This will end the game (y/n): ",
                "[yn]"
        );

        return userInput.equals(YES);
    }

    /**
     *
     * @return
     */
    public char inputChar() {

        String userInput = processInput(
                "Enter the TILE from your RACK that you wish to place (a-z): ",
                "[a-z]"
        );

        return userInput.charAt(FIRST_POSITION);
    }

    /**
     *
     * @return
     */
    public int inputRow() {

        String userInput = processInput(
                "Enter the ROW where you want the tile placed (1-15): ",
                "[1-9]|1[0-5]"
                );

        return Integer.parseInt(userInput) - ROW_OFFSET;
    }

    /**
     *
     * @return
     */
    public int inputCol() {

        String userInput = processInput(
                "Enter the COLUMN where you want the tile placed (a-o): ",
                "[a-o]"
                );


        char userInputChar = userInput.charAt(FIRST_POSITION);
        return userInputChar - START_OF_ALPHABET;
    }

    /**
     * 
     * @return
     */
    public boolean inputTurnFinished() {

        String userInput = processInput(
                "Have you finished your turn (y/n): ",
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
