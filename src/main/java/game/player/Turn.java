package main.java.game.player;

import main.java.game.Board;
import main.java.game.player.Player;
import main.java.game.player.Rack;

public class Turn {

    // *** Attributes ***

    private Player player;

    // todo potentially just store the board at start and just keep
    //  track of an arrayList of moves

    // series of moves
    // squares involved

    Board boardAtStart = new Board();
    Rack rackAtStart = new Rack();

    boolean passed = false;


    // *** Constructor ***
    public Turn(Board boardAtStart, Player player) {
//        this.boardAtStart = boardAtStart;
//        this.rackAtStart = player.getRack();
        this.player = player;

        processAllMoves();
    }

    // *** Methods ***
    public void processAllMoves() {


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


    public boolean movesAreValid() {

        // todo add condition
        boolean condition = true;
        if (condition) {
            return true;
        }
        else {
            return false;
        }
    }

}
