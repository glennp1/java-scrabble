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

}
