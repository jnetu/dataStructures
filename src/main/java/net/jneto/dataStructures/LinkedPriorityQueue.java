package net.jneto.dataStructures;

import net.jneto.dataStructures.Comparator.Comparator;

public class LinkedPriorityQueue<ITEM> implements DataStructure<ITEM> {
    private Node<ITEM> first; // First element in the priority queue
    private int size; // Number of elements in the queue

    private final Comparator<ITEM> comparator;

    /**
     * Node class for the priority queue.
     *
     * @param <ITEM> The type of elements stored in the node.
     */
    private static class Node<ITEM> {
        private ITEM item;
        private Node<ITEM> next;
    }

    /**
     * Constructor for an empty linked priority queue.
     * A priority queue requires a comparison logic.
     * Implement the Comparator interface and provide any comparison logic you prefer.
     */
    public LinkedPriorityQueue(Comparator<ITEM> comparator) {
        this.first = null;
        this.size = 0;
        this.comparator = comparator;
    }

    /**
     * Adds an item to the priority queue in the correct position based on its priority.
     *
     * @param item The item to be added.
     */
    @Override
    public void add(ITEM item) {
        Node<ITEM> newNode = new Node<>();
        newNode.item = item;

        if (isEmpty() || comparator.compare(item, first.item) < 0) {
            newNode.next = first;
            first = newNode;
        } else {
            Node<ITEM> current = first;
            while (current.next != null && comparator.compare(item, current.next.item) >= 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
    }

    /**
     * Removes and returns the highest priority item from the priority queue.
     *
     * @return The item with the highest priority, or null if the queue is empty.
     */
    @Override
    public ITEM remove() {
        if (isEmpty()) {
            return null;
        }

        ITEM item = first.item;
        first = first.next;
        size--;

        return item;
    }

    /**
     * Checks if the priority queue is empty.
     *
     * @return True if the priority queue contains no elements, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return first == null;
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
     * Returns the highest priority item without removing it from the priority queue.
     *
     * @return The item with the highest priority, or null if the queue is empty.
     */
    @Override
    public ITEM peek() {
        if (isEmpty()) {
            return null;
        }
        return first.item;
    }

    /**
     * Returns a string representation of the priority queue.
     *
     * @return A string representing the elements in the priority queue.
     */
    @Override
    public String show() {
        StringBuilder builder = new StringBuilder();
        Node<ITEM> current = first;
        boolean isFirst = true;

        while (current != null) {
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(", ");
            }
            builder.append(current.item);
            current = current.next;
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
        LinkedStack<ITEM> stack = new LinkedStack<>();
        Node<ITEM> current = first;
        boolean isFirst = true;

        // Push elements onto the stack
        while (current != null) {
            stack.add(current.item);
            current = current.next;
        }

        // Pop elements from the stack to build the reverse string
        while (!stack.isEmpty()) {
            ITEM item = stack.remove();
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(", ");
            }
            builder.append(item);
        }

        return "[" + builder.toString() + "]";
    }

    @Override
    public String toString() {
        return "This linked priority queue contains " + size + " elements. To display them, use the show() method.";
    }
}
