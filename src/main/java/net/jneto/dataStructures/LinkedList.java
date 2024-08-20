package net.jneto.dataStructures;

public class LinkedList<ITEM> implements DataStructure<ITEM> {
    private Node<ITEM> head; // First node in the list
    private int size; // Number of elements in the list

    /**
     * Constructs an empty linked list.
     */
    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Node class for the linked list.
     *
     * @param <ITEM> The type of elements stored in the node.
     */
    private static class Node<ITEM> {
        private ITEM item;
        private Node<ITEM> next;
    }

    /**
     * Adds an item to the end of the linked list.
     *
     * @param item The item to be added.
     */
    @Override
    public void add(ITEM item) {
        Node<ITEM> newNode = new Node<>();
        newNode.item = item;

        if (isEmpty()) {
            head = newNode;
        } else {
            Node<ITEM> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Removes and returns the first item from the linked list.
     *
     * @return The item removed from the list, or null if the list is empty.
     */
    @Override
    public ITEM remove() {
        if (isEmpty()) {
            return null;
        }
        ITEM item = head.item;
        head = head.next;
        size--;
        return item;
    }

    /**
     * Checks if the linked list is empty.
     *
     * @return True if the linked list is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of elements in the linked list.
     *
     * @return The size of the linked list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the first item in the linked list without removing it.
     *
     * @return The first item in the list, or null if the list is empty.
     */
    @Override
    public ITEM peek() {
        if (isEmpty()) {
            return null;
        }
        return head.item;
    }

    /**
     * Returns a string representation of the linked list from head to tail.
     *
     * @return A string containing all elements in the list.
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
     * Returns a string representation of the linked list from tail to head.
     *
     * @return A string containing all elements in reverse order.
     */
    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        Node<ITEM> current = head;
        Node<ITEM> previous = null;
        Node<ITEM> next;

        // Reverse the linked list
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        // Build the string representation of the reversed list
        current = previous;
        while (current != null) {
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(", ");
            }
            builder.append(current.item);
            current = current.next;
        }

        // Restore the original order of the linked list by reversing it again
        current = previous;
        previous = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        return "[" + builder.toString() + "]";
    }

    /**
     * Returns a string with basic information about the linked list.
     *
     * @return A string with the list's size and a prompt to use the show() method.
     */
    @Override
    public String toString() {
        return "This linked structure contains " + size + " elements. To display them, use the show() method.";
    }
}
