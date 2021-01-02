package main.java.display;

import java.util.ArrayList;

public class PlayerRackDisplay {

    public void render(int playerNumber, ArrayList<Character> rackAsCharArray) {
        System.out.print("Player " + playerNumber + "'s rack: ");

        if (rackAsCharArray.size() > 0) {
            System.out.print("|");
            for (char character : rackAsCharArray) {
                System.out.print(character + "|");
            }
        } else {
            System.out.print("Empty");
        }

        // Start a new line at the end
        System.out.print("\n");
    }
}
