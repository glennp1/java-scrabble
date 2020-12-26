package main.java;

import java.util.Scanner;

public class ScrabbleGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        System.out.print("Enter your name: \r");

        String userName = scanner.nextLine(); // Read user input

        System.out.print("Hello " + userName);


    }
}