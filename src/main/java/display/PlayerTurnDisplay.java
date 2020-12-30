package main.java.display;

import main.java.game.Player;

public  class PlayerTurnDisplay {

    // *** Attributes ***


    // *** Constructor ***
    public PlayerTurnDisplay() {

    }

    // *** Methods ***
    public void render(int playerNumber) {
        System.out.printf("It is Player %d's turn.\n", playerNumber);
    }

}
