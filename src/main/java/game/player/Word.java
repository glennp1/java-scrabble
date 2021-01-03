package main.java.game.player;

import main.java.game.Square;

import java.util.ArrayList;
import java.util.LinkedList;

public class Word {

    // *** Attributes ***
    private LinkedList<Square> squaresInWord = new LinkedList<>();

    // True if horizontal, false if vertical
    private boolean isHorizontal;
    private Square startingSquare;

    // *** Constructor ***
    public Word(boolean isHorizontal, Square startingSquare) {
        this.isHorizontal = isHorizontal;
        this.startingSquare = startingSquare;

        populateWord();
    }

    // *** Methods ***

    private void populateWord() {

        // Add starting square to the word
        squaresInWord.add(startingSquare);

        // If the word is horizontal, add the squares to the left and right
        if (isHorizontal) {
            addSquaresLeft();
            addSquaresRight();
        }
        // Otherwise If the word is vertical, add the squares above and below
        else {
            addSquaresAbove();
            addSquaresBelow();
        }
    }

    private void addSquaresLeft() {
        Square currentSquare = startingSquare;
        while (currentSquare.hasTileLeft()) {
            currentSquare = currentSquare.getSquareLeft();
            squaresInWord.addFirst(currentSquare);
        }
    }

    private void addSquaresRight() {
        Square currentSquare = startingSquare;
        while (currentSquare.hasTileRight()) {
            currentSquare = currentSquare.getSquareRight();
            squaresInWord.addLast(currentSquare);
        }
    }

    private void addSquaresAbove() {
        Square currentSquare = startingSquare;
        while (currentSquare.hasTileAbove()) {
            currentSquare = currentSquare.getSquareAbove();
            squaresInWord.addFirst(currentSquare);
        }
    }

    private void addSquaresBelow() {
        Square currentSquare = startingSquare;
        while (currentSquare.hasTileBelow()) {
            currentSquare = currentSquare.getSquareBelow();
            squaresInWord.addLast(currentSquare);
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
