package cs146_test02;
import java.util.ArrayList;
/**
 * Encapsulates an error found by the SpellChecker.
 * @author Christian
 */
public class Error {
    String value; //The incorrect word
    int line; //The line number it was found in
    ArrayList<String> suggestions;  //A list of possible corrections
    /**
     * Instantiates an error
     * @param w the word
     * @param l the line
     */
    public Error(String w, int l)
    {
        value = w;
        line = l;
    }
}
