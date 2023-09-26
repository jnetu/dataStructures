package net.jneto.dataStructures;


/**
 * Deque implementation with Array DataStructure
 *
 * @param <ITEM>
 */
public class Deque<ITEM> implements DataStructure<ITEM> {
    private static final int DEFAULT_CAPACITY = 2; // internal ArraySize
    private ITEM[] deque; // Array
    private int size; // Used array size

    public Deque() {
        deque = (ITEM[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Adds an object to the data structure.
     *
     * @param item The object to be added.
     */
    @Override
    public void add(ITEM item) {
        if (size < deque.length) {
            deque[size] = item;
            size++;
        } else {
            resize();
            add(item);
        }
    }

    /**
     * In Deque you can Add Front
     */
    public void addFront(ITEM item) {
        if (size < deque.length) {

            for (int i = size; i > 0; i--) {//reloc itens
                deque[i] = deque[i - 1];
            }
            deque[0] = item;
            size++;
        } else {
            resize();
            addFront(item);
        }
    }

    /**
     * In Deque you can also Add End
     */
    public void addEnd(ITEM item) {
        if (size < deque.length) {
            deque[size] = item;
            size++;
        } else {
            resize();
            addEnd(item);
        }
    }

    /**
     * Removes and returns an object from the end Deque.
     * use removeEnd and removeFront to do correctly logic
     *
     * @return The removed object.
     */
    @Override
    public ITEM remove() {
        if (isEmpty()) {
            return null;
        }
        ITEM item = deque[size - 1];
        size--;
        if ((float) size / deque.length <= 0.25) {
            resize();
        }
        return item;
    }


    /**
     * Removes and returns an object from the Front Deque.
     *
     * @return The removed object.
     */
    public ITEM removeFront() {
        if (isEmpty()) {
            return null;
        }
        ITEM item = deque[0]; //front item

        for (int i = 0; i < size - 1; i++) { //reloc
            deque[i] = deque[i + 1];
        }
        return item;
    }


    /**
     * Remove logic on end deque, like remove overrided default method
     *
     * @return - Object removed
     */
    public ITEM removeEnd() {
        if (isEmpty()) {
            return null;
        }
        ITEM item = deque[size - 1];
        size--;
        if ((float) size / deque.length <= 0.25) {
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
        return size <= 0;
    }

    /**
     * Returns the size (number of objects) in the data structure.
     *
     * @return The size of the data structure.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * returns the first object that will removed put don't remove
     * use peekFront and peekEnd to do correctly logic
     *
     * @return The removed object.
     */
    @Override
    public ITEM peek() {
        return size <= 0 ? null : deque[size - 1];
    }


    /**
     * returns the front object that will removed put don't remove
     *
     * @return The removed object.
     */
    public ITEM peekFront() {
        return size <= 0 ? null : deque[0];
    }


    /**
     * returns the End object that will removed put don't remove
     *
     * @return The removed object.
     */
    public ITEM peekEnd() {
        return size <= 0 ? null : deque[size - 1];
    }

    /**
     * Returns a string of the data structure.
     *
     * @return A string representation of the data structure.
     */
    @SuppressWarnings("unchecked")
    @Override
    public String show() {
        StringBuilder builder = new StringBuilder(); // Java 5+ required
        boolean isFirst = true;
        ITEM[] aux = (ITEM[]) new Object[size];
        for (int i = 0; i < size - 1; i++) {
            aux[i] = deque[i];
            if (isFirst) {
                builder.append(aux[i]);
                isFirst = false;
            } else {
                builder.append(", ").append(aux[i]);
            }
        }

        return "[" + builder.toString() + "]";
    }

    /**
     * Returns a string of the data structure in reverse order.
     *
     * @return A string representation of the reversed data structure.
     */
    @SuppressWarnings("unchecked")
    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder(); // Java 5+ required
        boolean isFirst = true;
        ITEM[] aux = (ITEM[]) new Object[size];
        for (int i = size - 1; i >= 0; i--) {
            aux[i] = deque[i];
            if (isFirst) {
                builder.append(aux[i]);
                isFirst = false;
            } else {
                builder.append(", ").append(aux[i]);
            }
        }

        return "[" + builder.toString() + "]";
    }

    /**
     * Method to increase or decrease the deque Array
     */
    private void resize() {
        if ((float) size / deque.length <= 0.25) { //Decrease
            Common<ITEM> c = new Common<ITEM>();
            deque = c.resize(deque, false);
            return;
        }
        if (size >= deque.length) { //increase
            Common<ITEM> c = new Common<ITEM>();
            deque = c.resize(deque, true);
        }
    }

    @Override
    public String toString() {
        String out = "  Deque size: " + size + " Internal array size: " + deque.length;
        return out + " to list all elements use method show";
    }
}
