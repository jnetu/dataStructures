package net.jneto.dataStructures;

/**
 * This is a classic Stack (Pilha) implementation using an Array.
 *
 * @param <ITEM> The type of elements stored in the Stack.
 */
public class Stack<ITEM> implements DataStructure<ITEM> {
    private final static int DEFAULT_SIZE = 2; // Initial array size.
    private ITEM[] stack; // Array to store stack elements.
    private int size; // Current number of elements in the stack.

    /**
     * Constructs an empty stack with a default size.
     */
    @SuppressWarnings("unchecked")
    public Stack() {
        stack = (ITEM[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    /**
     * Adds an item to the stack following FILO (First In, Last Out) logic.
     * If the internal array is full, it resizes the array before adding the item.
     *
     * @param item The item to be added to the stack.
     */
    @Override
    public void add(ITEM item) {
        if (size < stack.length) {
            stack[size] = item;
            size++;
        } else {
            resize();
            add(item);
        }
    }

    /**
     * Removes and returns the top item from the stack following FILO (First In, Last Out) logic.
     * If the stack is empty, it returns null.
     * The stack's internal array size is reduced if the number of elements drops below a certain threshold.
     *
     * @return The item removed from the top of the stack, or null if the stack is empty.
     */
    @Override
    public ITEM remove() {
        if (isEmpty()) {
            return null;
        }
        ITEM item = stack[size - 1];
        size--;
        if ((float) size / stack.length <= 0.25) {
            resize();
        }
        return item;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return True if the stack is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items currently in the stack.
     *
     * @return The size of the stack.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Adjusts the size of the stack's internal array.
     * If the number of elements is less than 25% of the array's length, the array size is decreased.
     * If the array is full, its size is increased.
     */
    private void resize() {
        if ((float) size / stack.length <= 0.25) { // Decrease
            Common<ITEM> c = new Common<>();
            stack = c.resize(stack, false);
            return;
        }
        if (size == stack.length) { // Increase
            Common<ITEM> c = new Common<>();
            stack = c.resize(stack, true);
        }
    }

    /**
     * Returns the top item from the stack without removing it.
     * If the stack is empty, it returns null.
     *
     * @return The top item of the stack, or null if the stack is empty.
     */
    @Override
    public ITEM peek() {
        return size == 0 ? null : stack[size - 1];
    }

    /**
     * Returns a string representation of the stack, showing all elements in order from bottom to top.
     * The elements are restored to the stack after being displayed.
     *
     * @return A string representing the stack's elements.
     */
    @Override
    public String show() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        Stack<ITEM> aux = new Stack<>();
        ITEM element;
        while (!isEmpty()) { // Removing elements
            element = remove();
            aux.add(element);
        }

        while (!aux.isEmpty()) { // Restoring elements and building the string
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
     * Returns a string representation of the stack, showing all elements in reverse order (from top to bottom).
     * The elements are restored to the stack after being displayed.
     *
     * @return A string representing the stack's elements in reverse order.
     */
    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        Stack<ITEM> aux = new Stack<>();
        ITEM element;

        while (!isEmpty()) { // Removing elements
            element = remove();
            if (isFirst) {
                builder.append(element);
                isFirst = false;
            } else {
                builder.append(", ").append(element);
            }
            aux.add(element);
        }
        while (!aux.isEmpty()) { // Restoring elements
            element = aux.remove();
            add(element);
        }

        return "[" + builder.toString() + "]";
    }

    /**
     * Returns a string containing information about the stack, including its size and the size of its internal array.
     *
     * @return A string with details about the stack.
     */
    @Override
    public String toString() {
        return "Stack size: " + size + " Internal array size: " + stack.length + ". To list all elements, use the method show.";
    }
}
