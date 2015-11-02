package cs146_test02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Christian
 */
public class SpellChecker {

    DictionaryHashTable dict;
    private Scanner in;

    /**
     * Instantiates a SpellChecker from a specified DictionaryHashTable object.
     *
     * @param dictionary the DictionaryHashTable.
     */
    public SpellChecker(DictionaryHashTable dictionary) {
        dict = dictionary;
    }

    /**
     * Instantiates a SpellChecker from a dictionary file. CONVENTIONS FOR
     * FILES: 1. Must be a UTF-8 Encoded file 2. Each word that is to be added
     * into the dictionary must be in beginning of each separate line 3. Program
     * begins parsing from the very beginning of file.
     */
    public SpellChecker(File file) {
        dict = new DictionaryHashTable(file);
    }

    /**
     * Loads words from a file into the dictionary. CONVENTIONS FOR FILES: 1.
     * Must be a UTF-8 Encoded file 2. Each word that is to be added into the
     * dictionary must be in beginning of each separate line 3. Program begins
     * parsing from the very beginning of file.
     *
     * @param file
     */
    public void addDictionary(File file) {
        dict.load(file);
    }

    /**
     * Parses through a file, checking each word to see if it exists or not.
     * CONVENTIONS FOR FILES: 1. Must be a UTF-8 Encoded file 2. Each word that
     * is to be added into the dictionary must be in beginning of each separate
     * line 3. Program begins parsing from the very beginning of file.
     *
     * @param file
     */
    public void check(File file) {
        ArrayList<Error> errors = new ArrayList();
        try {
            in = new Scanner(file);
            int line = 0;
            while (in.hasNextLine()) {
                line++;
                Scanner lineIn = new Scanner(in.nextLine());
                lineIn.useDelimiter(" ");
                String word = lineIn.next();
                if (!dict.exists(word.toLowerCase()))
                    errors.add(new Error(word, line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private void checkSubsitutions(Error e) {
        for(int i = 0; i < e.value.length() ; i++ )
        {
            String w = e.value.substring(i) + e.value.substring(0, i);
            for(int j = 0; j <= 24; j++)
            {
                Character c = new Character();
                c.
            }
        }
    }

    private void checkDeletions(Error e) {

    }
}
