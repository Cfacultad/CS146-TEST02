package cs146_test02;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class contains a hash table of strings that we can use to quickly look
 * up if a word exists or not.
 *
 * @author Christian Facultad
 */
public class DictionaryHashTable {

    private static final int DEFAULT_SIZE = 100; //Default size of Dictionary
    private static final float DEFAULT_LOAD = 0.75f; //Default load factor of hash table

    private float load;
    private int capacity;
    private int size;
    private Bucket<String>[] a; //The array of Buckets to put our strings in.

    /**
     * Instantiates an empty dictionary.
     */
    public DictionaryHashTable() {
        a = new Bucket[DEFAULT_SIZE];
        capacity = DEFAULT_SIZE;
        load = DEFAULT_LOAD;
        size = 0;
    }

    /**
     * Instantiates a dictionary from a file
     *
     * @param file the file to instantiate a dictionary from
     */
    public DictionaryHashTable(File file) {
        a = new Bucket[DEFAULT_SIZE];
        capacity = DEFAULT_SIZE;
        load = DEFAULT_LOAD;
        size = 0;
        load(file);
    }

    /**
     * Loads words from a file into the dictionary. CONVENTIONS FOR FILES: 1.
     * Must be a UTF-8 Encoded file 2. Each word that is to be added into the
     * dictionary must be in beginning of each separate line 3. Program begins
     * parsing from the very beginning of file.
     *
     * @param file
     */
    public void load(File file) {
        try {
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                //Each line in the file is a word.
                add(in.nextLine());
            }
            in.close();
        } catch (FileNotFoundException e) {
            P.pp("File not found, dictionary not loaded.");
        }

    }

    /**
     * Add a new word to the dictionary.
     *
     * @param word the String to add to the dictionary.
     */
    public void add(String word) {
        word = word.toLowerCase();
        if (calculateLoad() >= getLoad()) {
            resize();
        }
        Entry e = new Entry(word);
        if (a[e.hashCode() % a.length] == null) {
            a[e.hashCode() % a.length] = new Bucket();
        }
        a[e.hashCode() % a.length].add(new Entry(word));
        size++;
    }

    /**
     * Check to see if a specified string value is in the dictionary.
     *
     * @param word the string to check existence of
     * @return if the string exists or not
     */
    public boolean exists(String word) {
        return (a[word.hashCode() % a.length].exists(word.toLowerCase()));
    }

    /**
     * Calculate the load factor of the hash table
     *
     * @return the load factor.
     */
    public float calculateLoad() {
        return size() / getCapacity();
    }

    /**
     * Double the array size of the dictionary. Might be pretty slow since we're
     * basically reinserting every single entry into a new one.
     */
    private void resize() {
        Bucket[] n = new Bucket[a.length * 2];
        for (Bucket b : a) {
            while (b.size() > 0) {
                add(b.pop(), n);
            }
        }
    }

    /**
     * adds a specified entry into a specified bucket array. Used for resizing.
     * Same as public add method, but to a specified bucket.
     *
     * @param e the entry
     * @param array the array.
     */
    private void add(Entry e, Bucket<String>[] array) {
        if (array[e.hashCode() % array.length] == null) {
            array[e.hashCode() % array.length] = new Bucket();
        }
        array[e.hashCode() % a.length].add(e);
    }
    
    
    
    // UNIMPORTANT ENCAPSULATION METHODS BELOW ...
    
    
    
    /**
     * @return the load
     */
    public float getLoad() {
        return load;
    }

    /**
     * @param load the load to set
     */
    public void setLoad(float load) {
        this.load = load;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the size
     */
    public int size() {
        return size;
    }
}
