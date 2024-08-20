package net.jneto.dataStructures;

public class LinkedBag<ITEM> implements DataStructure<ITEM> {
    private Node<ITEM> head; // First element in the bag
    private int size; // Number of elements in the bag

    /**
     * Constructs an empty bag.
     */
    public LinkedBag() {
        head = null;
        size = 0;
    }

    /**
     * Node class for the bag.
     *
     * @param <ITEM> The type of elements stored in the node.
     */
    private static class Node<ITEM> {
        private ITEM item;
        private Node<ITEM> next;
    }

    /**
     * Adds an item to the bag.
     *
     * @param item The item to be added.
     */
    @Override
    public void add(ITEM item) {
        Node<ITEM> newNode = new Node<>();
        newNode.item = item;
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Removes and returns an item from the bag.
     *
     * @return The removed item, or null if the bag is empty.
     */
    @Override
    public ITEM remove() {
        if (isEmpty()) {
            return null;
        }
        // Removing a random item (first item in this implementation)
        ITEM item = head.item;
        head = head.next;
        size--;
        return item;
    }

    /**
     * Checks if the bag is empty.
     *
     * @return True if the bag is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of elements in the bag.
     *
     * @return The size of the bag.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an item from the bag without removing it.
     *
     * @return The item, or null if the bag is empty.
     */
    @Override
    public ITEM peek() {
        if (isEmpty()) {
            return null;
        }
        return head.item;
    }

    /**
     * Returns a string representation of the bag.
     *
     * @return A string containing all elements in the bag.
     */
    @Override
    public String show() {
        StringBuilder builder = new StringBuilder();
        Node<ITEM> current = head;
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
     * Returns a string representation of the bag in reverse order.
     *
     * @return A string containing all elements in the bag in reverse order.
     */
    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder();
        LinkedBag<ITEM> auxBag = new LinkedBag<>();
        Node<ITEM> current = head;
        boolean isFirst = true;

        // Push all elements onto the auxiliary bag
        while (current != null) {
            auxBag.add(current.item);
            current = current.next;
        }

        // Remove elements from the auxiliary bag to build the reverse string
        while (!auxBag.isEmpty()) {
            ITEM item = auxBag.remove();
            if (isFirst) {
                builder.append(item);
                isFirst = false;
            } else {
                builder.append(", ").append(item);
            }
        }

        return "[" + builder.toString() + "]";
    }

    /**
     * Returns a string with basic information about the bag.
     *
     * @return A string with the bag's size and a prompt to use the show() method.
     */
    @Override
    public String toString() {
        return "This linked bag contains " + size + " elements. To display them, use the show() method.";
    }
}
