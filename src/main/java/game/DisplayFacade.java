package main.java.game;

import main.java.display.BoardDisplay;
import main.java.display.MoveInput;
import main.java.display.PlayerRackDisplay;

import java.util.ArrayList;

/**
 * Facade and Singleton class that provides a consistent,
 * global and single point of access to the Display.
 * This ensures that if the Display changes the game can
 * continue to function uninterrupted (Protected Variations).
 */
public class DisplayFacade {

    // *** Attributes ***
    private static DisplayFacade instance = null;

    private final BoardDisplay boardDisplay = new BoardDisplay();
    private final PlayerRackDisplay playerRackDisplay = new PlayerRackDisplay();

    private final MoveInput moveInput = new MoveInput();

    // *** Constructor ***
    private DisplayFacade() {

    }

    // *** Method ***
    public static DisplayFacade getInstance() {
        if (instance == null) {
            instance = new DisplayFacade();
        }
        return instance;
    }


    public void renderBoard(Board board) {


        boardDisplay.render(board);
    }

    public void renderPlayerRack(Player player) {

        ArrayList<Character> formattedRack = player.getRack().formatForDisplay();
        int playerNumber = player.getNumber();

        playerRackDisplay.render(playerNumber, formattedRack);

    }

    /**
     *
     * @return a move todo
     */
    public Move requestMoveInput() {
        return new Move();
    }
}
