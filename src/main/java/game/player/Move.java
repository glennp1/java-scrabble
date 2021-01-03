package main.java.game.player;

import main.java.game.*;

public class Move {

    // *** Attributes ***
    private Board boardCurrent;
    private Rack rackCurrent;

    private Square squareSelected;
    private Tile tileSelected;

    // *** Constructor ***

    public Move(Board boardCurrent, Rack currentRack) {
        this.boardCurrent = boardCurrent;
        this.rackCurrent = currentRack;
    }

    // *** Methods ***

    public void execute() {
        // Remove the tile from the rack
        rackCurrent.removeTile(tileSelected);

        // Place it on the square
        squareSelected.setTile(tileSelected);
    }

    public void setSquareSelected(Square squareSelected) {
        this.squareSelected = squareSelected;
    }

    public void setTileSelected(Tile tileSelected) {
        this.tileSelected = tileSelected;
    }

    public Square getSquareSelected() {
        return squareSelected;
    }
}
