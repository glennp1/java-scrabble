# java-scrabble

## Description
A basic scrabble game built using java that runs using a command line user interface.

I've included comprehensive javadoc comments that explain the inner workings  
of each of the classes.

I've also completed a set of unit tests for the Square class using JUnit 5.  
However, as I was pushed for time, and am still relatively new to testing in Java  
I have left out the testing of other classes for the time being.

I am willing to produce formal diagrams of the project structure (DCD etc...)  
upon request, however, during development I just used rough sketches.

## Rules and Gameplay

A full set of rules including the spec of the project can be found in the root folder.  
> "project-spec.txt"

The interface is fairly intuitive. A player's turn proceeds as follows:
1. The player decides if they wish to pass their turn
    - They respond 'y' or 'n'
2. The player enters the first character of their move
    - This is a single character from any of those in their rack
3. The player then enters a row that corresponds to where they would like to move
    - This is a number ranging from 1-15
4. The player then enters a column that corresponds to where they would like to move
    - This is a single character ranging from a-o
5. The player then decides if they have completed their turn
    - They respond 'y' or 'n'
        - If they respond 'y' then the turn ends, and their score is calculated
        - If they respond 'n' then they are prompted to enter another move
        
If at any stage the player enters an invalid input, invalid move,  
or invalid set of moves, an error is displayed and the respective  
process restarts.

## To run and compile

***Ideally, the project can just be compiled and run through an IDE such as IntelliJ.***  
This is what I used.

Alternatively...

### To compile in cmd
> Navigate to root directory  
> Run javac src/main/java/*.java -d out/production/java-scrabble

### To run in cmd
> From the root directory navigate to: out/production/java-scrabble  
> Run: java main.java.game.ScrabbleGame