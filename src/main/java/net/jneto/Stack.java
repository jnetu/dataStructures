package net.jneto;

/**
 * This is a classic Stack(Pilha) implementation with Array
 */
public class Stack<ITEM> implements DataStructure<ITEM> {
    private final static int DEFAULT_SIZE = 2; // internal ArraySize
    private ITEM[] stack; // Array
    private int size; // Used array size

    /*
     * Constructor empty stack
     */
    public Stack() {
        stack = (ITEM[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    /*
     * Add Logic FILO
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

    /*
     * Remove Logic FILO
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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    /*
     * Method to increase or decrease the stack Array
     *
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        if ((float) size / stack.length <= 0.25) { // Decrease
            ITEM[] auxStack;
            int newSize;
            if (2 % stack.length == 0) {
                newSize = stack.length / 2 + 1;
            } else {
                newSize = stack.length / 2;
            }
            auxStack = (ITEM[]) new Object[newSize];
            for (int i = 0; i < auxStack.length; i++) {
                auxStack[i] = stack[i];
            }
            stack = (ITEM[]) new Object[newSize];
            for (int i = 0; i < auxStack.length; i++) {
                stack[i] = auxStack[i];
            }

            return;
        }
        if (size == stack.length) {//increase
            int newSize = stack.length * 2;
            ITEM[] aux = (ITEM[]) new Object[newSize];

            for (int i = 0; i < stack.length; i++) {
                aux[i] = stack[i];
            }
            stack = aux;
        }
    }

    @Override
    public ITEM peek() {
        return size == 0 ? null : stack[size - 1];
    }

    /*
     * Shows a String with all elements
     *
     * @return String
     */
    @Override
    public String show() {
        StringBuilder builder = new StringBuilder(); // Java 5+ required
        boolean isFirst = true;
        Stack<ITEM> aux = new Stack<ITEM>();
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

    /*
     * Shows a String with all elements in reverse order
     *
     * @return String
     */
    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder();
        String out = "";
        boolean isFirst = true;
        Stack<ITEM> aux = new Stack<ITEM>();
        ITEM element;

        while (!isEmpty()) { // removing

            element = remove();
            if (isFirst) {
                //out = out + element;
                builder.append(element);
                isFirst = false;
            } else {
                builder.append(", ").append(element);
            }

            aux.add(element);
        }
        while (!aux.isEmpty()) { // adding again
            element = aux.remove();
            add(element);
        }

        return "[" + builder.toString() + "]";

    }

    @Override
    public String toString() {
        String out = "  Stack size: " + size + " Internal array size: " + stack.length;
        return out + " to list all element use method show";
    }

}