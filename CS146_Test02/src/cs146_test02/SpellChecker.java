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
    private final static String letters = "qwertyuiopasdfghjklzxcvbnm'-";

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
                if (!dict.exists(word.toLowerCase())) {
                    errors.add(new Error(word, line));
                }
            }
            for (Error e : errors) {
                checkSubsitutions(e);
                checkDeletions(e);
                P.pp("Line " + e.line + ": " + e.value);
                if (e.suggestions.size() > 0) {
                    P.p(" - Suggestions: ");
                    for (String s : e.suggestions) {
                        P.p(s + " ");
                    }
                } else {
                    P.p(" Can not find any suggestions for the misspelled word.");
                }
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
    
    private void checkSubsitutions(Error e) {
        
        for (int i = 0; i < e.value.length(); i++) {
            for (int j = 0; j <= letters.length(); j++) {
                String p = e.value.substring(i) + letters.charAt(j) + e.value.substring(0, i);
                if (!p.equals(e.value) && dict.exists(p)) {
                    e.suggestions.add(p);
                }
            }
        }
    }
    
    private void checkDeletions(Error e) {
        for (int i = 0; i < e.value.length(); i++) {
            String p = e.value.substring(i) + e.value.substring(0, i);
            if (!p.equals(e.value) && dict.exists(p)) {
                e.suggestions.add(p);
            }
        }
        
    }
}
