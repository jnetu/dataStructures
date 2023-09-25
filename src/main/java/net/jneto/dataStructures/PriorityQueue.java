package net.jneto.dataStructures;

import net.jneto.dataStructures.Comparator.Comparator;

/**
 * This is a Priority Queue(Fila de prioridade) implementation with Array
 * it use Interface comparator to compare elements and min-heap logic vector
 *
 * @param <ITEM> The type of elements stored in the Stack.
 */
public class PriorityQueue<ITEM> implements DataStructure<ITEM>{
    private static final int DEFAULT_SIZE = 2; // internal ArraySize
    private ITEM[] pqueue; // Array
    private int size; // Used array size

    private Comparator<ITEM> comparator;

    /**
     * Constructor empty priority queue
     * a priority queue needs a comparator logic,
     * just implement interface Comparator and use
     * any logic you want
     */
    @SuppressWarnings("unchecked")
    public PriorityQueue(Comparator<ITEM> comparator){
        pqueue = (ITEM[]) new Object[DEFAULT_SIZE];
        this.comparator = comparator;
    }

    /**
     * Adds an object to the data structure.
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
     * Removes and returns an object from the data structure.
     *
     * @return The removed object.
     * @throws IllegalStateException If the data structure is empty.
     */
    @Override
    public ITEM remove() {
        if (isEmpty()) {
            return null;
        }

        ITEM item = pqueue[0];
        pqueue[0] = pqueue[size-1];
        size--;
        sink(0);
        if ((float) size / pqueue.length <= 0.25) {
            resize();
        }
        return item;
    }

    /**
     * Checks if the data structure is empty.
     *
     * @return True if the data structure contains no objects, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size==0;
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
     *
     */
    @Override
    public ITEM peek() {
        if (isEmpty()) {
            return null;
        }
        return size == 0 ? null : pqueue[0];
    }

    /**
     * Returns a string of the data structure.
     *
     * @return A string representation of the data structure.
     */
    @Override
    public String show() {
        StringBuilder builder = new StringBuilder(); // Java 5+ required
        boolean isFirst = true;
        PriorityQueue<ITEM> aux = new PriorityQueue<ITEM>(comparator);
        ITEM element;
        while (!isEmpty()) { // removing
            element = remove();
            aux.add(element);
        }

        while (!aux.isEmpty()) { // adding again

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
     * Returns a string of the data structure in reverse order.
     *
     * @return A string representation of the reversed data structure.
     */
    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;

        for (int i = size - 1; i >= 0; i--) {
            ITEM element = pqueue[i];
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
     * a priority queue use min-heap structure methods.
     * implemented bellow:
     */
    /**
     * Moves an element up the heap to maintain the min-heap property.
     *
     * @param k The index of the element to be moved.
     */
    private void swim(int k) {
        // Swim the element up the heap by comparing it with its parent
        while (k > 0 && comparator.compare(pqueue[k], pqueue[(k - 1) / 2]) < 0) {
            swap(k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }

    /**
     * Moves an element down the heap to maintain the min-heap property.
     *
     * @param k The index of the element to be moved.
     */
    private void sink(int k) {
        // Sink the element down the heap by comparing it with its children
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
     * Swaps two elements in the heap.
     *
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    private void swap(int i, int j) {
        // Swap elements at indices i and j in the heap
        ITEM temp = pqueue[i];
        pqueue[i] = pqueue[j];
        pqueue[j] = temp;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        if((float) size / pqueue.length <= 0.25) { //Decrease
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
        if(size >= pqueue.length){ //increase
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
        String out = "  Priority Queue size: " + size + " Internal array size: " + pqueue.length;
        return out + " to list all element use method show";
    }
}

