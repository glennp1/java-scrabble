package main.java;

import java.util.Scanner;

public class ScrabbleGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Create a Scanner object

        // todo develop board template
        System.out.print(
                "     ________ Board-Start ________ \n" +
                "     A B C D E F G H I J K L M N O \n" +
                "     _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n" +
                "  1 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|\n" +
                "  2 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|\n" +
                "  3 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|\n" +
                "  4 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|\n" +
                "  5 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|\n" +
                "  6 |_|_|_|_|c|a|t|_|_|_|_|_|_|_|_|\n" +
                "  7 |_|_|_|_|_|_|a|_|_|_|_|_|_|_|_|\n" +
                "  8 |_|_|_|_|_|_|i|_|_|_|_|_|_|_|_|\n" +
                "  9 |_|_|_|_|_|_|l|_|_|_|_|_|_|_|_|\n" +
                " 10 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|\n" +
                " 11 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|\n" +
                " 12 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|\n" +
                " 13 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|\n" +
                " 14 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|\n" +
                " 15 |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|\n" +
                "     _________ Board-End__________ \n");

        System.out.println("It is Player 1's turn.");

        String move = scanner.nextLine(); // Read user input

    }
}