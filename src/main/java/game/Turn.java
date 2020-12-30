package main.java.game;

public class Turn {

    // *** Attributes ***

    private Player player;

    // todo potentially just store the board at start and just keep
    //  track of an arrayList of moves
    private Board boardAtStart;
    private Board boardCurrent;

    private Rack rackAtStart;
    private Rack rackCurrent;

    // *** Constructor ***
    public Turn(Board boardAtStart, Player player) {
        this.boardAtStart = boardAtStart;
        this.boardCurrent = boardAtStart;
        this.rackAtStart = player.getRack();
        this.rackCurrent = player.getRack();
        this.player = player;

        DisplayFacade displayFacade = DisplayFacade.getInstance();

        // Render the board
        displayFacade.renderBoard(boardAtStart);


        // Render the turn
        displayFacade.renderPlayerTurn(player);

        // Render the player's rack
        displayFacade.renderPlayerRack(player);

        // todo handle pass
        boolean pass = displayFacade.requestPassInput();
        if (pass) {
            return;
        }


        Move move = new Move(boardCurrent, rackCurrent);

        // todo do while
        // Request a move
        DisplayFacade.getInstance().requestMoveInput(move);
        // todo move conditions need to be met


        // todo validate turn

        // Place tiles

        // End Turn

        // Replace placed tiles
        rackCurrent.fillFromBag();

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
