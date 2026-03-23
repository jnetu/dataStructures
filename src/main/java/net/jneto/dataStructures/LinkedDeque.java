package net.jneto.dataStructures;

public class LinkedDeque<ITEM> implements DataStructure<ITEM> {
    private Node<ITEM> first; // First element in the deque
    private Node<ITEM> last; // Last element in the deque
    private int size; // Number of elements in the deque

    /**
     * Constructs an empty linked deque.
     */
    public LinkedDeque() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Node class for the linked deque.
     *
     * @param <ITEM> The type of elements stored in the node.
     */
    private static class Node<ITEM> {
        private ITEM item;
        private Node<ITEM> next;
    }

    /**
     * Adds an item to the end of the deque.
     *
     * @param item The item to be added.
     */
    @Override
    public void add(ITEM item) {
        Node<ITEM> newNode = new Node<>();
        newNode.item = item;
        newNode.next = null;

        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    /**
     * Adds an item to the front of the deque.
     *
     * @param item The item to be added.
     */
    public void addFront(ITEM item) {
        Node<ITEM> newNode = new Node<>();
        newNode.item = item;
        newNode.next = first;

        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            first = newNode;
        }
        size++;
    }

    /**
     * Adds an item to the end of the deque.
     *
     * @param item The item to be added.
     */
    public void addEnd(ITEM item) {
        add(item);
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

        ITEM item = last.item;

        if (size <= 1) {
            first = null;
            last = null;
        } else {
            Node<ITEM> current = first;
            while (current.next != last) {
                current = current.next;
            }
            current.next = null;
            last = current;
        }

        size--;
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

        ITEM item = first.item;

        if (size <= 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
        }

        size--;
        return item;
    }

    /**
     * Removes and returns the last item in the deque.
     *
     * @return The removed item, or null if the deque is empty.
     */
    public ITEM removeEnd() {
        return remove();
    }

    /**
     * Checks if the deque is empty.
     *
     * @return True if the deque is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return first == null;
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
        return isEmpty() ? null : last.item;
    }

    /**
     * Returns the first item in the deque without removing it.
     *
     * @return The first item in the deque, or null if the deque is empty.
     */
    public ITEM peekFront() {
        return isEmpty() ? null : first.item;
    }

    /**
     * Returns the last item in the deque without removing it.
     *
     * @return The last item in the deque, or null if the deque is empty.
     */
    public ITEM peekEnd() {
        return isEmpty() ? null : last.item;
    }

    /**
     * Returns a string representation of the deque.
     *
     * @return A string representation of the deque.
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
     * Returns a string representation of the deque in reverse order.
     *
     * @return A string representation of the deque in reverse order.
     */
    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        LinkedStack<ITEM> auxStack = new LinkedStack<>();
        ITEM element;
        Node<ITEM> current = first;

        // Push all elements onto the auxiliary stack
        while (current != null) {
            auxStack.add(current.item);
            current = current.next;
        }

        // Pop elements from the auxiliary stack to build the reverse string
        while (!auxStack.isEmpty()) {
            element = auxStack.remove();
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
     * Returns a string with basic information about the linked deque.
     *
     * @return A string with the deque's size and a prompt to use the show() method.
     */
    @Override
    public String toString() {
        return "This linked deque contains " + size + " elements. To display them, use the show() method.";
    }
}