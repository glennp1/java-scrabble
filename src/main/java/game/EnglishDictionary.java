package game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Singleton class that provides a consistent,
 * global and single point of access to the english dictionary.
 * This ensures that words can easily be checked if they are valid,
 * regardless of the class
 */
public class EnglishDictionary {

    // *** Constants ***
    /**
     * The file path that points to the dictionary text file
     */
    private static final String PATH_NAME = "src/main/resources/wordsAlpha.txt";

    // *** Attributes ***
    /**
     * The current instance of the english dictionary (singleton pattern)
     */
    private static EnglishDictionary instance = null;

    /**
     * Stores all english words in an array list once they are passed in
     * from the text file
     */
    private ArrayList<String> englishWords;

    // *** Constructor ***
    /**
     * Creates a new instance of the english dictionary and passes in
     * all words from the text file
     */
    private EnglishDictionary() {
        readInDictionary();
    }

    // *** Methods ***
    /**
     * Gets an atomic instance of the english dictionary class,
     * creating one if it doesnt exist
     *
     * @return single instance of the english dictionary
     */
    public static EnglishDictionary getInstance() {
        if (instance == null) {
            instance = new EnglishDictionary();
        }
        return instance;
    }

    /**
     * Reads in the english dictionary form a text file and stores it
     * as a sorted array list of strings, which can then be binary searched
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
        // This enable binary search
        Collections.sort(englishWords);
    }

    /**
     * Checks if a word is a valid word in the english dictionary.
     * Uses binary search algorithm.
     *
     * @param word the word to be searched for
     * @return true if word is valid, false otherwise
     */
    public boolean checkIfValid(String word) {
        // Search for the index of the word within english words
        int indexOfWord = Collections.binarySearch(englishWords, word);

        // If the index is positive then the word exists, otherwise it does not
        return indexOfWord >= 0;
    }
}
