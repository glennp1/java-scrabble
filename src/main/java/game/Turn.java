package main.java.game;

public class Turn {

    // *** Attributes ***
    private Board boardAtStart;

    // todo potentially just store the board at start and just keep
    //  track of an arrayList of moves
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


        // nested do while loop maybe?
        // outer one for while word/words are not valid
        // inner one for while !turn.hasEnded (when all moves are done)

        // something to store pass --> to tell ScrabbleGame
        // something to store that the turn is official (maybe a board final)

        // todo do while loop
        // do
        // get input
        // while !input.isValid()
        // todo might be a bit more nuanced
        // meed to check word at end of turn
    }

}
