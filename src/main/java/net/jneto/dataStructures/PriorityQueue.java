package net.jneto.dataStructures;

import net.jneto.dataStructures.Comparator.Comparator;

/**
 * This is a Priority Queue implementation using an Array.
 * It uses the Comparator interface to compare elements and follows min-heap logic.
 *
 * @param <ITEM> The type of elements stored in the Priority Queue.
 */
public class PriorityQueue<ITEM> implements DataStructure<ITEM> {
    private static final int DEFAULT_SIZE = 2; // Initial array size
    private ITEM[] pqueue; // Array to store the elements
    private int size; // Number of elements in the queue

    private final Comparator<ITEM> comparator;

    /**
     * Constructor for an empty priority queue.
     * A priority queue requires a comparison logic.
     * Implement the Comparator interface and provide any comparison logic you prefer.
     */
    @SuppressWarnings("unchecked")
    public PriorityQueue(Comparator<ITEM> comparator) {
        pqueue = (ITEM[]) new Object[DEFAULT_SIZE];
        this.comparator = comparator;
    }

    /**
     * Adds an object to the priority queue.
     *
     * @param item The object to be added.
     */
    @Override
    public void add(ITEM item) {
        if (size < pqueue.length) {
            pqueue[size] = item;
            swim(size);
            size++;
        } else {
            resize();
            add(item);
        }
    }

    /**
     * Removes and returns the highest priority object from the priority queue.
     *
     * @return The object removed from the priority queue.
     * @throws IllegalStateException If the priority queue is empty.
     */
    @Override
    public ITEM remove() {
        if (isEmpty()) {
            return null;
        }

        ITEM item = pqueue[0];
        pqueue[0] = pqueue[size - 1];
        size--;
        sink(0);
        if ((float) size / pqueue.length <= 0.25) {
            resize();
        }
        return item;
    }

    /**
     * Checks if the priority queue is empty.
     *
     * @return True if the priority queue contains no elements, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the priority queue.
     *
     * @return The size of the priority queue.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the highest priority object without removing it from the priority queue.
     *
     * @return The object at the top of the priority queue.
     */
    @Override
    public ITEM peek() {
        if (isEmpty()) {
            return null;
        }
        return pqueue[0];
    }

    /**
     * Returns a string representation of the priority queue.
     *
     * @return A string representing the elements in the priority queue.
     */
    @Override
    public String show() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        PriorityQueue<ITEM> aux = new PriorityQueue<>(comparator);
        ITEM element;
        while (!isEmpty()) { // Remove elements
            element = remove();
            aux.add(element);
        }
        while (!aux.isEmpty()) { // Re-add elements
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
     * Returns a string representation of the priority queue in reverse order.
     *
     * @return A string representing the elements in reverse order.
     */
    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        ITEM[] aux = (ITEM[]) new Object[size]; // Array to store elements in reverse order
        int auxIndex = 0;
        ITEM element;
        while (!isEmpty()) { // Remove elements
            element = remove();
            aux[auxIndex] = element;
            auxIndex++;
        }
        for (int i = auxIndex - 1; i >= 0; i--) { // Re-add elements in reverse order
            element = aux[i];
            if (isFirst) {
                builder.append(element);
                isFirst = false;
            } else {
                builder.append(", ").append(element);
            }
            add(element);
        }

        return "[" + builder.toString() + "]";
    }

    /**
     * Maintains the min-heap property by moving an element up the heap.
     *
     * @param k The index of the element to be moved up.
     */
    private void swim(int k) {
        // Move the element up the heap by comparing it with its parent
        while (k > 0 && comparator.compare(pqueue[k], pqueue[(k - 1) / 2]) < 0) {
            swap(k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }

    /**
     * Maintains the min-heap property by moving an element down the heap.
     *
     * @param k The index of the element to be moved down.
     */
    private void sink(int k) {
        // Move the element down the heap by comparing it with its children
        while (2 * k + 1 < size) {
            int j = 2 * k + 1;
            if (j + 1 < size && comparator.compare(pqueue[j + 1], pqueue[j]) < 0) {
                j++;
            }
            if (comparator.compare(pqueue[k], pqueue[j]) <= 0) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    /**
     * Swaps two elements in the priority queue.
     *
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    private void swap(int i, int j) {
        // Swap elements at indices i and j in the priority queue
        ITEM temp = pqueue[i];
        pqueue[i] = pqueue[j];
        pqueue[j] = temp;
    }

    /**
     * Resizes the internal array to accommodate more or fewer elements.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        if ((float) size / pqueue.length <= 0.25) { // Decrease size
            ITEM[] aux;
            int newSize;
            if (2 % pqueue.length == 0) {
                newSize = pqueue.length / 2 + 1;
            } else {
                newSize = pqueue.length / 2;
            }
            aux = (ITEM[]) new Object[newSize];
            for (int i = 0; i < aux.length; i++) {
                aux[i] = pqueue[i];
            }
            pqueue = (ITEM[]) new Object[newSize];
            for (int i = 0; i < aux.length; i++) {
                pqueue[i] = aux[i];
            }
            return;
        }
        if (size >= pqueue.length) { // Increase size
            int newSize = pqueue.length * 2;
            ITEM[] aux = (ITEM[]) new Object[newSize];
            for (int i = 0; i < pqueue.length; i++) {
                aux[i] = pqueue[i];
            }
            pqueue = aux;
        }
    }

    @Override
    public String toString() {
        String out = "Priority Queue size: " + size + " Internal array size: " + pqueue.length;
        return out + " to list all elements, use the show method.";
    }
}
