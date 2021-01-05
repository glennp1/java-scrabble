package main.java.display;

import java.util.ArrayList;

public class PlayerScoreDisplay {

    public void render(int playerNumber, int playerScore) {
        System.out.printf("Player %d's score: %d\n", playerNumber, playerScore);
    }

    public void renderWordPoints(int playerNumber, String word, int points) {
        System.out.printf("Player %d formed %s for %d points.\n",
                playerNumber, word, points);
    }
}
