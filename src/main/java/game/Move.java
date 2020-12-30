package main.java.game;

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

    // todo do while loop
    // do
    // get input
    // while !input.isValid()

    public void processDisplayInput(char character, int row, int col, boolean turnOver) {

        // todo add more sophisticated validation

        squareSelected = boardCurrent.getSquareByCoords(row, col);
        tileSelected = rackCurrent.getTileByChar(character);


    }

    public void setSquareSelected(Square squareSelected) {
        this.squareSelected = squareSelected;
    }

    public void setTileSelected(Tile tileSelected) {
        this.tileSelected = tileSelected;
    }
}
