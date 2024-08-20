package net.jneto.dataStructures;

public class LinkedStack<ITEM> implements DataStructure<ITEM> {
    private Node<ITEM> top; // Top element of the stack
    private int size; // Number of elements in the stack

    /**
     * Constructs an empty stack.
     */
    public LinkedStack() {
        top = null;
        size = 0;
    }


    /**
     * Node class for the stack.
     *
     * @param <ITEM> The type of elements stored in the node.
     */
    private static class Node<ITEM> {
        private ITEM item;
        private Node<ITEM> next;
    }

    /**
     * Adds an item to the top of the stack.
     *
     * @param item The item to be added.
     */
    @Override
    public void add(ITEM item) {
        Node<ITEM> newNode = new Node<>();
        newNode.item = item;
        newNode.next = top;
        top = newNode;
        size++;
    }

    /**
     * Removes and returns the item from the top of the stack.
     *
     * @return The item from the top of the stack, or null if the stack is empty.
     */
    @Override
    public ITEM remove() {
        if (isEmpty()) {
            return null;
        }
        ITEM item = top.item;
        top = top.next;
        size--;
        return item;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return True if the stack is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return The size of the stack.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the item at the top of the stack without removing it.
     *
     * @return The item at the top of the stack, or null if the stack is empty.
     */
    @Override
    public ITEM peek() {
        if (isEmpty()) {
            return null;
        }
        return top.item;
    }

    /**
     * Returns a string representation of the stack from top to bottom.
     *
     * @return A string containing all elements in the stack.
     */
    @Override
    public String show() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        LinkedStack<ITEM> auxStack = new LinkedStack<>();
        Node<ITEM> current = top;

        // Push all elements onto the auxiliary stack
        while (current != null) {
            auxStack.add(current.item);
            current = current.next;
        }

        // Pop elements from the auxiliary stack to build the reverse string
        while (!auxStack.isEmpty()) {
            ITEM item = auxStack.remove();
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(", ");
            }
            builder.append(item);
        }

        return "[" + builder.toString() + "]";







    }

    /**
     * Returns a string representation of the stack from bottom to top.
     *
     * @return A string containing all elements in the stack in reverse order.
     */
    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder();
        Node<ITEM> current = top;
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
     * Returns a string with basic information about the stack.
     *
     * @return A string with the stack's size and a prompt to use the show() method.
     */
    @Override
    public String toString() {
        return "This linked structure contains " + size + " elements. To display them, use the show() method.";
    }

}
