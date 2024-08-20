package net.jneto.dataStructures;

/**
 * This is a classic Queue implementation using an array.
 *
 * @param <ITEM> The type of elements stored in the Queue.
 */
public class Queue<ITEM> implements DataStructure<ITEM> {
    private static final int DEFAULT_SIZE = 2; // Initial internal array size
    private ITEM[] queue; // Internal array to store queue elements
    private int size; // Number of elements in the queue

    /**
     * Constructs an empty queue with a default initial capacity.
     */
    @SuppressWarnings("unchecked")
    public Queue() {
        queue = (ITEM[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    /**
     * Adds an item to the end of the queue.
     *
     * @param item The item to be added.
     */
    @Override
    public void add(ITEM item) {
        if (size >= queue.length) { // If the array is full, resize it
            resize();
            add(item);
        } else {
            queue[size] = item;
            size++;
        }
    }

    /**
     * Removes and returns the item at the front of the queue.
     *
     * @return The item at the front of the queue, or null if the queue is empty.
     */
    @Override
    public ITEM remove() {
        if (isEmpty()) {
            return null; // Return null if the queue is empty
        }
        ITEM item = queue[0];

        // Shift elements to the left to fill the gap
        for (int i = 0; i < size - 1; i++) {
            queue[i] = queue[i + 1];
        }

        queue[size - 1] = null; // Nullify the last position
        size--;

        // Resize the array if the size drops below 25% of the current capacity
        if ((float) size / queue.length <= 0.25) {
            resize();
        }
        return item;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue contains no elements, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return The number of elements in the queue.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the item at the front of the queue without removing it.
     *
     * @return The item at the front of the queue, or null if the queue is empty.
     */
    @Override
    public ITEM peek() {
        if (isEmpty()) {
            return null; // Return null if the queue is empty
        }
        return queue[0];
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return A string containing all elements in the queue.
     */
    @Override
    public String show() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        Queue<ITEM> aux = new Queue<>();
        ITEM element;

        // Remove all elements and add them to a temporary queue
        while (!isEmpty()) {
            element = remove();
            aux.add(element);
        }

        // Reinsert elements back into the original queue and build the string
        while (!aux.isEmpty()) {
            element = aux.remove();
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(", ");
            }
            builder.append(element);
            add(element);
        }

        return "[" + builder.toString() + "]";
    }

    /**
     * Returns a string representation of the queue in reverse order.
     *
     * @return A string containing all elements in the queue in reverse order.
     */
    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        Queue<ITEM> auxQueue = new Queue<>();
        ITEM element;

        for (int i = size - 1; i >= 0; i--) {
            auxQueue.add(queue[i]);
        }


        while (!auxQueue.isEmpty()) {
            element = auxQueue.remove();
            if (isFirst) {
                builder.append(element);
                isFirst = false;
            } else {
                builder.append(", ").append(element);
            }
        }

        return "[" + builder.toString() + "]";
    }




    /**
     * Resizes the internal array to either increase or decrease its capacity.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        Common<ITEM> c = new Common<>();
        if ((float) size / queue.length <= 0.25) { // Decrease size
            queue = c.resize(queue, false);
            return;
        }
        if (size >= queue.length) { // Increase size
            queue = c.resize(queue, true);
        }
    }

    @Override
    public String toString() {
        String out = "Queue size: " + size + " Internal array size: " + queue.length;
        return out + " To list all elements, use the show method.";
    }
}
