package main.java.display;

public  class PlayerTurnDisplay {

    // *** Attributes ***


    // *** Constructor ***
    public PlayerTurnDisplay() {

    }

    // *** Methods ***
    public void renderStart(int playerNumber) {
        System.out.printf("\nIt is Player %d's turn.\n", playerNumber);
    }

    public void renderEnd(int playerNumber) {
        System.out.printf("Player %d has ended their turn.\n", playerNumber);

    }

}
