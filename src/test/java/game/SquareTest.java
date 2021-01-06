package game;

import game.player.Move;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    // Board for testing
    Board board = new Board();

    @BeforeEach
    void setUp() {
        // Set up a new example board for test (bit messy)

        // Should look something like this
        // Note that row and col start at 0 when stored

        //      ________ Board-Start ________
        //     a b c d e f g h i j k l m n o
        //     _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
        //  1 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
        //  2 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
        //  3 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
        //  4 |_|_|_|c|a|m|e|l|_|_|_|_|_|_|_|
        //  5 |_|_|_|a|_|_|_|o|_|_|_|_|_|_|_|
        //  6 |_|_|_|r|a|d|i|o|_|_|_|_|_|_|_|
        //  7 |_|_|_|_|_|_|_|p|_|_|_|_|_|_|_|
        //  8 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
        //  9 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
        // 10 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
        // 11 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
        // 12 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
        // 13 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
        // 14 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
        // 15 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
        //     _________ Board-End__________

        // Word camel
        board.getSquareByCoords(3,3).setTile(new Tile('c', 1));
        board.getSquareByCoords(3,4).setTile(new Tile('a', 1));
        board.getSquareByCoords(3,5).setTile(new Tile('m', 1));
        board.getSquareByCoords(3,6).setTile(new Tile('e', 1));
        board.getSquareByCoords(3,7).setTile(new Tile('l', 1));

        // Word car
        board.getSquareByCoords(4,3).setTile(new Tile('a', 1));
        board.getSquareByCoords(5,3).setTile(new Tile('r', 1));

        // Word radio
        board.getSquareByCoords(5,4).setTile(new Tile('a', 1));
        board.getSquareByCoords(5,5).setTile(new Tile('d', 1));
        board.getSquareByCoords(5,6).setTile(new Tile('i', 1));
        board.getSquareByCoords(5,7).setTile(new Tile('o', 1));

        // Word loop
        board.getSquareByCoords(4,7).setTile(new Tile('o', 1));
        board.getSquareByCoords(6,7).setTile(new Tile('p', 1));
    }

    @AfterEach
    void tearDown() {
        // blank for now
    }

    @Test
    void isInLine() {
        Square square;
        LinkedList<Square> listOfSquares = new LinkedList<>();

        // If have a series of squares at (8,9) and (8,10)
        listOfSquares.add(board.getSquareByCoords(8,9));
        listOfSquares.add(board.getSquareByCoords(8,10));

        // And if the selected square is (8,8)
        square = board.getSquareByCoords(8,8);

        // Then we should should expect the method to return true
        assertTrue(square.isInLine(listOfSquares));

        // However if the square selected is (9,9)
        square = board.getSquareByCoords(9,9);

        // Then we should should expect the method to return false
        assertFalse(square.isInLine(listOfSquares));
    }

    @Test
    void formsRow() {
        Square square;

        // If we start at: d (5,5)
        square = board.getSquareByCoords(5,5);

        // Then forms row should be true
        assertTrue(square.formsRow());

        // If we start at: a (4,3)
        square = board.getSquareByCoords(4,3);

        // Then forms row should be false
        assertFalse(square.formsRow());
    }

    @Test
    void formsColumn() {
        Square square;

        // If we start at: r (5,3)
        square = board.getSquareByCoords(5,3);

        // Then forms column should be true
        assertTrue(square.formsColumn());

        // If we start at: d (5,5)
        square = board.getSquareByCoords(5,5);

        // Then forms column should be false
        assertFalse(square.formsColumn());
    }

    @Test
    void getAllNeighbours() {
        Square squareInput;
        LinkedList<Square> squaresOutput = new LinkedList<>();

        // If we start at: o (5,7)
        squareInput = board.getSquareByCoords(5,7);

        // We should expect to get: i, o, p
        squaresOutput.add(board.getSquareByCoords(5,6));
        squaresOutput.add(board.getSquareByCoords(4,7));
        squaresOutput.add(board.getSquareByCoords(6,7));

        assertEquals(squaresOutput, squareInput.getAllNeighbours());
    }

    @Test
    void getAllSquaresLeft() {
        Square squareInput;
        LinkedList<Square> squaresOutput = new LinkedList<>();

        // If we start at: o (5,7)
        squareInput = board.getSquareByCoords(5,7);

        // We should expect to get: r, a, d, i
        squaresOutput.add(board.getSquareByCoords(5,3));
        squaresOutput.add(board.getSquareByCoords(5,4));
        squaresOutput.add(board.getSquareByCoords(5,5));
        squaresOutput.add(board.getSquareByCoords(5,6));


        assertEquals(squaresOutput, squareInput.getAllSquaresLeft());
    }

    @Test
    void getAllSquaresRight() {
        Square squareInput;
        LinkedList<Square> squaresOutput = new LinkedList<>();

        // If we start at: r (5,3)
        squareInput = board.getSquareByCoords(5,3);

        // We should expect to get: a, d, i, o
        squaresOutput.add(board.getSquareByCoords(5,4));
        squaresOutput.add(board.getSquareByCoords(5,5));
        squaresOutput.add(board.getSquareByCoords(5,6));
        squaresOutput.add(board.getSquareByCoords(5,7));

        assertEquals(squaresOutput, squareInput.getAllSquaresRight());
    }

    @Test
    void getAllSquaresAbove() {
        Square squareInput;
        LinkedList<Square> squaresOutput = new LinkedList<>();

        // If we start at: p (6,7)
        squareInput = board.getSquareByCoords(6,7);

        // We should expect to get: l, o, o
        squaresOutput.add(board.getSquareByCoords(3,7));
        squaresOutput.add(board.getSquareByCoords(4,7));
        squaresOutput.add(board.getSquareByCoords(5,7));

        assertEquals(squaresOutput, squareInput.getAllSquaresAbove());
    }

    @Test
    void getAllSquaresBelow() {
        Square squareInput;
        LinkedList<Square> squaresOutput = new LinkedList<>();

        // If we start at: l (3,7)
        squareInput = board.getSquareByCoords(3,7);

        // We should expect to get: o, o, p
        squaresOutput.add(board.getSquareByCoords(4,7));
        squaresOutput.add(board.getSquareByCoords(5,7));
        squaresOutput.add(board.getSquareByCoords(6,7));

        assertEquals(squaresOutput, squareInput.getAllSquaresBelow());
    }

    @Test
    void getSquareLeft() {
        Square squareInput;
        Square squareOutput;

        squareInput = board.getSquareByCoords(3,3);
        squareOutput = board.getSquareByCoords(3, 2);

        assertEquals(squareOutput, squareInput.getSquareLeft());
    }

    @Test
    void getSquareRight() {
        Square squareInput;
        Square squareOutput;

        squareInput = board.getSquareByCoords(3,3);
        squareOutput = board.getSquareByCoords(3, 4);

        assertEquals(squareOutput, squareInput.getSquareRight());
    }

    @Test
    void getSquareAbove() {
        Square squareInput;
        Square squareOutput;

        squareInput = board.getSquareByCoords(3,3);
        squareOutput = board.getSquareByCoords(2, 3);

        assertEquals(squareOutput, squareInput.getSquareAbove());
    }

    @Test
    void getSquareBelow() {
        Square squareInput;
        Square squareOutput;

        squareInput = board.getSquareByCoords(3,3);
        squareOutput = board.getSquareByCoords(4, 3);

        assertEquals(squareOutput, squareInput.getSquareBelow());
    }

    @Test
    void getTileCharacter() {
        Square square;

        // Testing for no tile
        square = board.getSquareByCoords(0,0);
        assertEquals('_', square.getTileCharacter());

        // Testing for with tile
        square = board.getSquareByCoords(3,3);
        assertEquals('c', square.getTileCharacter());
    }

    @Test
    void hasTile() {
        Square square;

        // Testing the static method
        square = board.getSquareByCoords(-1,0);
        assertFalse(Square.hasTile(square));

        square = board. getSquareByCoords(0,0);
        assertFalse(Square.hasTile(square));

        square = board.getSquareByCoords(3,3);
        assertTrue(Square.hasTile(square));

        // Testing the instance method (cannot take null)
        square = board.getSquareByCoords(0, 0);
        assertFalse(square.hasTile());

        square = board.getSquareByCoords(3, 3);
        assertTrue(square.hasTile());
    }
}