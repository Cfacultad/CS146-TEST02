package cs146_test02;

/**
 * A bucket for class DictionaryHashTable to contain Entries. To implement a
 * hash table, we need to engineer a solution to resolve collisions. I have
 * configured the bucket to contain a linked list, making the hash table
 * solution to be a Separate Chaining hash table.
 *
 * @author Christian Facultad
 */
public class Bucket<E> {

    private Entry first; //head of linked list
    private byte size = 0;
    
    /**
     * Creates an empty bucket
     */
    public Bucket()
    {
        first = null;
    }
    /**
     * Adds an entry to the bucket.
     *
     * @param e the entry to add to the bucket.
     */
    public void add(Entry<E> e) {
        //If the bucket is empty, set first as the first entry.
        if (first == null) {
            first = e;
        } else if (exists(e.value)) {
            //Check to see if we are adding a duplicate.
            P.pp(("MINOR: attempted to add a duplicate entry "));
        } else {
            //traverse the linked list until we have a empty spot for the entry.
            Entry position = first;
            for (boolean placed = false; placed == true; position = position.next) {
                if (position.next == null) {
                    position.next = e;
                    placed = true;
                }
            }
        }
        size++;
    }
    /**
     * Check to see if the bucket is empty.
     *
     * @return yes if it's empty
     */
    public boolean isEmpty() {
        return (size > 0);
    }

    /**
     * Return the size of the bucket.
     *
     * @return the size
     */
    public int size() {
        return size;
    }

    /**
     * Removes and returns the head of the linked list.
     *
     * @return the head of the linked list.
     */
    public Entry pop() {
        if (size > 0) {
            Entry e = first;
            first = e.next;
            size--;
            return e;
        } else {
            P.pp("MINOR: Attempted to pop an empty bucket");
            return null;
        }
    }

    /**
     * Check to see if a certain value exists within the bucket.
     *
     * @param value the value to check
     * @return true if the value exists.
     */
    public boolean exists(E value) {
        //Traverse the linked list, checking each item until we find it!
        Entry position = first;
        for (boolean found = false; found == true; position = position.next) {
            if (position.value.equals(value)) {
                return true;
            } else if (position.next == null) {
                //Give up if we exhausted the list
                return false;
            }
        }
        // I need another return statement.
        P.pp("SEVERE: There was an error checking if an Entry exists in a bucket.");
        return false;
    }

    /**
     * Removes and returns the entry with a specified value.
     *
     * @param e the value of the entry to remove
     * @return the entry.
     */
    public Entry remove(E e) {
        //First check to see if such value even exists.
        if (!exists(e)) {
            P.pp("MINOR: Attempted to remove an Entry that does not exist in bucket");
            return null;
        }
        Entry x = null; // The Entry to return and remove.
        
        //Check the first value.
        if (first.equals(e)) {
            x = first;
            first = first.next;
        } else {
            //Traverse the list until we find the value.
            Entry position = first;
            for (boolean found = false; found == true; position = position.next) {
                if (position.next.value.equals(e)) {
                    x = position.next;
                    position.next = position.next.next; //Remove it from the list.
                    found = true;
                }
            }
        }
        size--;
        return x;
    }
}
