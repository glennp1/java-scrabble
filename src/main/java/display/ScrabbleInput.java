package display;

import java.util.Scanner;

/**
 * <p>Handles the input console to the scrabble game.</p>
 * <p>Has been separated from the game logic in case changes are
 * made in the future. Is currently a class rather than an
 * interface but could be either depending on implementation</p>
 */
public class ScrabbleInput {

    // *** Constants ***
    /**
     * Row offset included so that matrices start at 0 not 1
     */
    private static final int ROW_OFFSET = 1;

    /**
     * The first position of all matrices is 0 not 1
     */
    private static final int FIRST_POSITION = 0;

    /**
     * The first letter in the alphabet, used in character calculations
     */
    private static final char START_OF_ALPHABET = 'a';

    /**
     * A string representation of yes, used by both input pass and
     * input turn finished
     */
    private static final String YES = "y";

    // *** Attributes ***

    // *** Constructor ***
    /**
     * Creates an instance of the scrabble input
     */
    public ScrabbleInput() {
    }

    // *** Methods ***
    /**
     * Requests if the player wishes to pass
     *
     * @return true if the player wishes to pass, false otherwise
     */
    public boolean inputPass() {

        String userInput = processInput(
                "Do you want to pass this turn? This will end the game (y/n): ",
                "[yn]"
        );

        return userInput.equals(YES);
    }

    /**
     * Requests the player to input a character from their rack that
     * they wish to place
     *
     * @return the character selected
     */
    public char inputChar() {

        String userInput = processInput(
                "Enter the TILE from your RACK that you wish to place (a-z): ",
                "[a-z]"
        );

        return userInput.charAt(FIRST_POSITION);
    }

    /**
     * Requests the player to input a row they wish to place
     * their selected character
     *
     * @return the row selected
     */
    public int inputRow() {

        String userInput = processInput(
                "Enter the ROW where you want the tile placed (1-15): ",
                "[1-9]|1[0-5]"
                );

        return Integer.parseInt(userInput) - ROW_OFFSET;
    }

    /**
     * Requests the player to input a column they wish to place
     * their selected character
     *
     * @return the column selected
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
     * Requests if the player wishes to end their turn
     *
     * @return true if they wish to end, false otherwise
     */
    public boolean inputTurnFinished() {

        String userInput = processInput(
                "Have you finished your turn (y/n): ",
                "[yn]"
        );

        return userInput.equals(YES);
    }

    /**
     * Returns a string that is inputted from the console following
     * a given request. For the string to be returned, it must match
     * the given regex, otherwise the user is prompted to enter
     * the input again
     *
     * @param request the request to the user prior to input
     * @param regex the regular expression that the input must match
     * @return the string that is inputted
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
