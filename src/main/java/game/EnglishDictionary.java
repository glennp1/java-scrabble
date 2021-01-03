package main.java.game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class EnglishDictionary {

    // *** Attributes ***
    private static final String PATH_NAME = "src/main/resources/wordsAlpha.txt";

    private static EnglishDictionary instance = null;

    private ArrayList<String> englishWords;

    // *** Constructor ***
    private EnglishDictionary() {

        readInDictionary();

    }

    // *** Methods ***

    public static EnglishDictionary getInstance() {
        if (instance == null) {
            instance = new EnglishDictionary();
        }
        return instance;
    }

    /**
     * Reads in the english dictionary form a text file and stores it
     * as an array list of strings
     */
    public void readInDictionary() {
        // Declarations
        englishWords = new ArrayList<>();
        File dictionaryWords;
        FileReader fileReader;
        BufferedReader bufferedReader;
        String line;

        try {
            // Create a new file, file reader and buffered reader
            dictionaryWords = new File(PATH_NAME);
            fileReader = new FileReader(dictionaryWords);
            bufferedReader = new BufferedReader(fileReader);

            // Read the first line
            line = bufferedReader.readLine();

            // Continue while the line read is not null
            while (line != null) {
                // Add the line to the dictionary
                englishWords.add(line);

                // Read the next line
                line = bufferedReader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Ensure the englishWords are sorted in ascending order
        Collections.sort(englishWords);
    }

    /**
     * Searches for a specific word within the english dictionary
     *
     * @param word the word to be searched for
     * @return true if word exists, false otherwise
     */
    public boolean checkForWord(String word) {
        // Search for the index of the word within english words
        int indexOfWord = Collections.binarySearch(englishWords, word);

        // If the index is positive then the word exists, otherwise it does not
        return indexOfWord >= 0;
    }
}

// todo could make a separate dictionary class
