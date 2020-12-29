package main.java.game;

public class Turn {

    // *** Attributes ***
    private Board boardAtStart;
    private Board boardCurrent;
    private Board boardNext;

    // *** Constructor ***
    public Turn(Board boardAtStart) {
        this.boardAtStart = boardAtStart;
        this.boardCurrent = boardAtStart;

        processMoves();
    }

    // *** Methods ***
    private void processMoves() {

    }

}
