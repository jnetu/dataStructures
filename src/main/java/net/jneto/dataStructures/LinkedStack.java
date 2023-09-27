package net.jneto.dataStructures;

public class LinkedStack<ITEM> implements DataStructure<ITEM>{
    /**
     * Adds an object to the data structure.
     *
     * @param item The object to be added.
     */
    @Override
    public void add(ITEM item) {

    }

    /**
     * Removes and returns an object from the data structure.
     *
     * @return The removed object.
     * @throws IllegalStateException If the data structure is empty.
     */
    @Override
    public ITEM remove() {
        return null;
    }

    /**
     * Checks if the data structure is empty.
     *
     * @return True if the data structure contains no objects, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns the size (number of objects) in the data structure.
     *
     * @return The size of the data structure.
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * returns the first object that will removed put dont remove
     *
     * @return The removed object.
     * @throws IllegalStateException If the data structure is empty.
     */
    @Override
    public ITEM peek() {
        return null;
    }

    /**
     * Returns a string of the data structure.
     *
     * @return A string representation of the data structure.
     */
    @Override
    public String show() {
        return null;
    }

    /**
     * Returns a string of the data structure in reverse order.
     *
     * @return A string representation of the reversed data structure.
     */
    @Override
    public String showReverse() {
        return null;
    }
}
