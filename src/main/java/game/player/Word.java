package game.player;

import game.Square;

import java.util.LinkedList;

/**
 * <p>Represents a word on the the scrabble board</p>
 * <p>Is used both for completed words and also to help validate
 * moves that a player makes</p>
 */
public class Word {

    // *** Constants ***

    // *** Attributes ***
    /**
     * The squares on the board that make up the given word
     */
    private final LinkedList<Square> squaresInWord = new LinkedList<>();

    /**
     * The direction of the word, true if horizontal, false if vertical
     */
    private final boolean isHorizontal;

    /**
     * The square that a word starts at, the remaining squares
     * are automatically found and added
     */
    private final Square startingSquare;

    // *** Constructor ***

    /**
     * Creates a new word instance, based on both the starting square
     * and the direction of the word. These two parameters are used
     * to then populate the word (see method)
     *
     * @param startingSquare the square the word starts at
     * @param isHorizontal the direction of the word
     */
    public Word(Square startingSquare, boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
        this.startingSquare = startingSquare;

        populateWord();
    }

    // *** Methods ***
    /**
     * Populates all of the squares in a give word by starting at the
     * starting square and finding the adjacent squares in the same
     * column / row
     */
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
     * Converts an instance of the word class to a string, so that it
     * can be outputted
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

    /**
     * Getter for squares in word, used within turn
     *
     * @return The squares on the board that make up the given word
     */
    public LinkedList<Square> getSquaresInWord() {
        return squaresInWord;
    }
}
