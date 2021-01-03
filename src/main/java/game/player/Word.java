package main.java.game.player;

import main.java.game.Square;

import java.util.ArrayList;

public class Word {


    // *** Attributes ***
    private ArrayList<Square> squaresInWord = new ArrayList<>();

    // *** Constructor ***


    // *** Methods ***


    /**
     * Returns the total points a word is worth
     *
     * @return an integer representing the total points
     */
    public int getPoints() {

        int totalPoints = 0;

        for (Square square : squaresInWord) {

            int squarePoints = square.getPoints();
            int tilePoints = square.getTile().getPoints();

            totalPoints += squarePoints * tilePoints;
        }

        return totalPoints;
    }


    /**
     * Converts an instance of the word class to a string
     *
     * @return a string representation of the word
     */
    public String toString() {
        // Use a string builder to construct the string
        StringBuilder wordAsString = new StringBuilder();

        // For each square in the word
        for (Square square : squaresInWord) {
            // Add the character within the tile on the square to the string
            wordAsString.append(square.getTile().getCharacter());
        }

        return wordAsString.toString();
    }

}
