package net.jneto.dataStructures;

public class LinkedQueue<ITEM> implements DataStructure<ITEM> {
    private int size; // Total size of the queue
    private Node<ITEM> first; // First element that will be removed (front of the queue)
    private Node<ITEM> last; // Last element added (end of the queue)

    public LinkedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Node class represents an element in the linked queue.
     * It doesn't need to be static, as the garbage collector handles unused objects.
     */
    private class Node<ITEM> {
        private ITEM item; // The actual item stored in the node
        private Node<ITEM> next; // Reference to the next node in the queue
    }

    /**
     * Adds an item to the end of the queue.
     *
     * @param item The item to be added.
     */
    @Override
    public void add(ITEM item) {
        Node<ITEM> aux = new Node<>(); // Create a new node to hold the item
        aux.item = item;
        aux.next = null;

        if (isEmpty()) { // If the queue is empty, the new node becomes both first and last
            first = aux;
            last = aux;
        } else { // Otherwise, add the new node to the end of the queue
            last.next = aux;
            last = aux;
        }

        size++; // Increase the size of the queue
    }

    /**
     * Removes and returns the first item from the queue.
     *
     * @return The first item in the queue, or null if the queue is empty.
     */
    @Override
    public ITEM remove() {
        if (isEmpty()) {
            return null;
        }

        Node<ITEM> aux = first; // Store the current first node

        if (size <= 1) { // If there's only one item in the queue, clear the queue
            first = null;
            last = null;
        } else { // Otherwise, move the first pointer to the next node
            first = first.next;
        }

        size--; // Decrease the size of the queue
        return aux.item; // Return the removed item
    }

    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue contains no items, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the queue.
     *
     * @return The size of the queue.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the first item in the queue without removing it.
     *
     * @return The first item, or null if the queue is empty.
     */
    @Override
    public ITEM peek() {
        return isEmpty() ? null : first.item;
    }

    /**
     * Returns a string representation of the queue with all elements in order.
     *
     * @return A string representation of the queue.
     */
    @Override
    public String show() {
        Node<ITEM> aux = first; // Start from the first node
        StringBuilder builder = new StringBuilder(); // Build the string representation
        boolean isFirst = true;

        while (aux != null) { // Traverse the queue

            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(", ");
            }

            builder.append(aux.item); // Append the item to the string
            aux = aux.next; // Move to the next node
        }

        return "[" + builder.toString() + "]";
    }

    /**
     * Returns a string representation of the queue with all elements in reverse order.
     *
     * @return A string representation of the reversed queue.
     */
    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        LinkedQueue<ITEM> auxQueue = new LinkedQueue<>();
        ITEM[] elements = (ITEM[]) new Object[size];
        ITEM element;
        int index = 0;
        Node<ITEM> current = first;

        // Remove and store elements in an array
        while (current != null) {
            elements[index] = current.item;
            current = current.next;
            auxQueue.add(elements[index]);
            index++;
        }

        // Build the reverse string
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
     * Returns a string with basic information about the queue.
     *
     * @return A string with the queue's size and a prompt to use the show() method.
     */
    @Override
    public String toString() {
        return "This linked structure contains " + size + " elements. To display them, use the show() method.";
    }
}
