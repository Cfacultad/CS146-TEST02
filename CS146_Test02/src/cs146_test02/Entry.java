/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs146_test02;

/**
 * An entry on a dictionary. Acts as a linked-list node for hash table collision resolution.
 * @author Christian Facultad
 */
public class Entry<E> {
    E value; //The actual value of the entry.
    Entry next; //The next Entry it points to.
    int hashCode; // Pre-calculated hashcode
    /**
     * Instantiates an Entry with a specified value.
     * Also calculates the hashCode, for faster lookup times!
     * @param value The value of the Entry.
     */
    public Entry(E value)
    {
        this.value = value;
        hashCode = value.hashCode();
    }
    @Override
    public int hashCode()
    {
        return hashCode;
    }
}
