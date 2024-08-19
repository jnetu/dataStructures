package net.jneto.dataStructures;

/**
 * Deque implementation using an Array as the underlying data structure.
 *
 * @param <ITEM> The type of elements stored in the Deque.
 */
public class Deque<ITEM> implements DataStructure<ITEM> {
    private static final int DEFAULT_CAPACITY = 2; // Initial capacity of the internal array
    private ITEM[] deque; // Internal array for storing elements
    private int size; // Number of elements in the deque

    /**
     * Constructor empty deque
     */
    @SuppressWarnings("unchecked")
    public Deque() {
        deque = (ITEM[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Adds an item to the end of the deque.
     *
     * @param item The item to be added.
     */
    @Override
    public void add(ITEM item) {
        if (size < deque.length) {
            deque[size] = item;
            size++;
        } else {
            resize();
            add(item);
        }
    }

    /**
     * Adds an item to the front of the deque.
     *
     * @param item The item to be added.
     */
    public void addFront(ITEM item) {
        if (size < deque.length) {
            for (int i = size; i > 0; i--) { // Shift elements to the right
                deque[i] = deque[i - 1];
            }
            deque[0] = item;
            size++;
        } else {
            resize();
            addFront(item);
        }
    }

    /**
     * Adds an item to the end of the deque.
     *
     * @param item The item to be added.
     */
    public void addEnd(ITEM item) {
        if (size < deque.length) {
            deque[size] = item;
            size++;
        } else {
            resize();
            addEnd(item);
        }
    }

    /**
     * Removes and returns the last item in the deque.
     *
     * @return The removed item, or null if the deque is empty.
     */
    @Override
    public ITEM remove() {
        if (isEmpty()) {
            return null;
        }
        ITEM item = deque[size - 1];
        size--;
        if ((float) size / deque.length <= 0.25) {
            resize();
        }
        return item;
    }

    /**
     * Removes and returns the first item in the deque.
     *
     * @return The removed item, or null if the deque is empty.
     */
    public ITEM removeFront() {
        if (isEmpty()) {
            return null;
        }
        ITEM item = deque[0]; // Get the front item
        for (int i = 0; i < size - 1; i++) { // Shift elements to the left
            deque[i] = deque[i + 1];
        }
        size--;
        if ((float) size / deque.length <= 0.25) {
            resize();
        }
        return item;
    }

    /**
     * Removes and returns the last item in the deque.
     *
     * @return The removed item, or null if the deque is empty.
     */
    public ITEM removeEnd() {
        if (isEmpty()) {
            return null;
        }
        ITEM item = deque[size - 1];
        size--;
        if ((float) size / deque.length <= 0.25) {
            resize();
        }
        return item;
    }

    /**
     * Checks if the deque is empty.
     *
     * @return True if the deque is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * Returns the number of items in the deque.
     *
     * @return The number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the last item in the deque without removing it.
     *
     * @return The last item in the deque, or null if the deque is empty.
     */
    @Override
    public ITEM peek() {
        return size <= 0 ? null : deque[size - 1];
    }

    /**
     * Returns the first item in the deque without removing it.
     *
     * @return The first item in the deque, or null if the deque is empty.
     */
    public ITEM peekFront() {
        return size <= 0 ? null : deque[0];
    }

    /**
     * Returns the last item in the deque without removing it.
     *
     * @return The last item in the deque, or null if the deque is empty.
     */
    public ITEM peekEnd() {
        return size <= 0 ? null : deque[size - 1];
    }

    /**
     * Returns a string representation of the deque.
     *
     * @return A string representation of the deque.
     */
    @SuppressWarnings("unchecked")
    @Override
    public String show() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        ITEM[] aux = (ITEM[]) new Object[size];
        for (int i = 0; i < size; i++) {
            aux[i] = deque[i];
            if (isFirst) {
                builder.append(aux[i]);
                isFirst = false;
            } else {
                builder.append(", ").append(aux[i]);
            }
        }
        return "[" + builder.toString() + "]";
    }

    /**
     * Returns a string representation of the deque in reverse order.
     *
     * @return A string representation of the deque in reverse order.
     */
    @SuppressWarnings("unchecked")
    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        ITEM[] aux = (ITEM[]) new Object[size];
        for (int i = size - 1; i >= 0; i--) {
            aux[i] = deque[i];
            if (isFirst) {
                builder.append(aux[i]);
                isFirst = false;
            } else {
                builder.append(", ").append(aux[i]);
            }
        }
        return "[" + builder.toString() + "]";
    }

    /**
     * Resizes the internal array, either increasing or decreasing its capacity.
     */
    private void resize() {
        if ((float) size / deque.length <= 0.25) { // Decrease capacity
            Common<ITEM> c = new Common<>();
            deque = c.resize(deque, false);
            return;
        }
        if (size >= deque.length) { // Increase capacity
            Common<ITEM> c = new Common<>();
            deque = c.resize(deque, true);
        }
    }

    @Override
    public String toString() {
        return "Deque size: " + size + " Internal array size: " + deque.length + " To list all elements, use the 'show' method.";
    }
}
