package net.jneto.dataStructures;

/**
 * This interface represents a generic data structure that can store objects. It
 * defines common operations for adding, removing, checking emptiness, and
 * obtaining the size of the data structure. Also used in Sort Algorithms
 *
 * @param <ITEM> The type of objects to be stored in the data structure.
 * @author joao neto
 */

public interface DataStructure<ITEM> {

    /**
     * Adds an object to the data structure.
     *
     * @param item The object to be added.
     */
    void add(ITEM item);

    /**
     * Removes and returns an object from the data structure.
     *
     * @return The removed object.
     * @throws IllegalStateException If the data structure is empty.
     */
    ITEM remove();

    /**
     * Checks if the data structure is empty.
     *
     * @return True if the data structure contains no objects, false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the size (number of objects) in the data structure.
     *
     * @return The size of the data structure.
     */
    int size();

    /**
     * returns the first object that will removed put dont remove
     *
     * @return The removed object.
     * @throws IllegalStateException If the data structure is empty.
     */
    ITEM peek();

    /**
     * Returns a string of the data structure.
     *
     * @return A string representation of the data structure.
     */
    String show();

    /**
     * Returns a string of the data structure in reverse order.
     *
     * @return A string representation of the reversed data structure.
     */
    String showReverse();

    /**
     * Returns a string of the Stack size and Internal array size.
     *
     * @return A string representation of the data structure.
     */
    String toString();

}