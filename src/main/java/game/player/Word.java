package game.player;

import game.Square;

import java.util.LinkedList;

public class Word {

    // *** Constants ***

    // *** Attributes ***
    private final LinkedList<Square> squaresInWord = new LinkedList<>();

    // True if horizontal, false if vertical
    private final boolean isHorizontal;
    private final Square startingSquare;

    // *** Constructor ***
    public Word(Square startingSquare, boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
        this.startingSquare = startingSquare;

        populateWord();
    }

    // *** Methods ***

    private void populateWord() {
        // If the word is horizontal, add the squares to the left,
        // add the starting square and add those to the right
        if (isHorizontal) {
            squaresInWord.addAll(startingSquare.getAllSquaresLeft());
            squaresInWord.add(startingSquare);
            squaresInWord.addAll(startingSquare.getAllSquaresRight());
        }
        // Otherwise If the word is vertical, add the squares above,
        // add the starting square and add those below
        else {
            squaresInWord.addAll(startingSquare.getAllSquaresAbove());
            squaresInWord.add(startingSquare);
            squaresInWord.addAll(startingSquare.getAllSquaresBelow());
        }
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

    /**
     * Returns if a word exists in a list of words
     * Specifically, both the word and the orientation must match
     *
     * @param words the linked list of words to search within
     * @return true if the word is found, false otherwise
     */
    public boolean existsIn(LinkedList<Word> words) {
        for (Word word : words) {

            // If both words have the same orientation and
            // they both represent the same string
            if ((this.isHorizontal == word.isHorizontal) &&
                    (this.toString().equals(word.toString()) )) {
                // Then the word already exists in the list
                return true;
            }
        }

        // Otherwise if the word isn't found return false
        return false;
    }

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

    public LinkedList<Square> getSquaresInWord() {
        return squaresInWord;
    }
}
