package main.java.display;

public class WinnerDisplay {

    // *** Attributes ***

    // *** Constructor ***

    public WinnerDisplay() {
    }

    // *** Methods ***
    public void render(int playerNumber) {

        System.out.printf("\n The winner is player %d!\n", playerNumber);
    }

    public void renderDraw() {
        System.out.println("\n It's a draw!");
    }


}
