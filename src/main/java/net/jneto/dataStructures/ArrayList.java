package net.jneto.dataStructures;

/**
 * This is a classic ArrayList implementation using an array.
 *
 * @param <ITEM> The type of elements stored in the ArrayList.
 */
public class ArrayList<ITEM> implements DataStructure<ITEM> {
    private static final int DEFAULT_SIZE = 2; // Internal array size
    private ITEM[] list; // Array
    private int size; // Number of elements in the array

    /**
     * Constructs an empty list.
     */
    @SuppressWarnings("unchecked")
    public ArrayList() {
        list = (ITEM[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    /**
     * Adds an object to the data structure.
     *
     * @param item The object to be added.
     */
    @Override
    public void add(ITEM item) {
        if (size >= list.length) {
            resize();
        }
        list[size] = item;
        size++;
    }

    /**
     * In an ArrayList, you can insert objects at any position.
     *
     * @param item  The object to be added.
     * @param index The index where the object will be added.
     */
    public void add(ITEM item, int index) {
        // Verify index
        if (index > list.length || index < 0) {
            return;
        }
        // Verify space
        if (size >= list.length) {
            resize();
        }

        // Array reallocation
        // Logic:
        // [0,1,2,3,4,5, null, null]
        // Add 2 at index 1:
        // index = 1
        // Resize because size == 8 == list.length
        // list[7] = list[6]
        // list[6] = list[5]
        // ...
        // list[index==1] STOP
        // list[index==1] = new item
        // [0,2,1,2,3,4,5, null, null, null, null, null, null, null, null, null, null]

        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = item;
        size++;
    }

    /**
     * In an ArrayList, you can get objects at any position.
     *
     * @param index The index where the object will be retrieved.
     * @return The object at the specified index.
     */
    public ITEM get(int index) {
        if (index > list.length || index < 0) {
            return null;
        }
        return list[index];
    }

    /**
     * Removes and returns the last added item from the array.
     *
     * @return The removed object, or null if the structure is empty.
     */
    @Override
    public ITEM remove() {
        if (isEmpty()) {
            return null;
        }
        ITEM item = list[size - 1];
        size--;

        if ((float) size / list.length <= 0.25) {
            resize();
        }

        return item;
    }

    /**
     * In an ArrayList, you can remove objects at any position.
     *
     * @param index The index where the object will be removed.
     * @return The removed object, or null if the index is out of bounds.
     */
    public ITEM remove(int index) {
        if (index >= list.length || index < 0) {
            return null;
        }

        ITEM removed = list[index];
        // Reallocation after removing the item
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }

        // Clean the last index (inaccessible but good practice to clean)
        list[size - 1] = null;
        size--;
        if ((float) size / list.length <= 0.25) { // Resize if less than 25% used
            resize();
        }

        return removed;
    }

    /**
     * Removes and returns the first occurrence of the specified item.
     *
     * @param item The item to remove.
     * @return True if the item was removed successfully, false otherwise.
     */
    public boolean remove(ITEM item) {
        for (int i = 0; i < size; i++) {
            if (list[i] == item || (list[i] != null && list[i].equals(item))) {
                remove(i);
                return true;
            }
        }

        return false;
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
     * Returns the number of objects in the data structure.
     *
     * @return The size of the data structure.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the last object that was added without removing it.
     *
     * @return The last object added, or null if the structure is empty.
     */
    @Override
    public ITEM peek() {
        return list[size - 1];
    }

    /**
     * Returns the object at the specified index without removing it.
     *
     * @param index The index of the object to peek.
     * @return The object at the specified index, or null if the index is out of bounds.
     */
    public ITEM peek(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return list[index];
    }

    /**
     * Returns a string representation of the data structure.
     *
     * @return A string representation of the data structure.
     */
    @Override
    public String show() {
        StringBuilder builder = new StringBuilder(); // Java 5+ required
        boolean isFirst = true;
        ArrayList<ITEM> aux = new ArrayList<>();
        ITEM element;
        while (!isEmpty()) { // Removing items
            element = remove();
            aux.add(element);
        }

        while (!aux.isEmpty()) { // Adding items back
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
     * Returns a string representation of the data structure in reverse order.
     *
     * @return A string representation of the reversed data structure.
     */
    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        Stack<ITEM> aux = new Stack<>();
        ITEM element;

        while (!isEmpty()) { // Removing items
            element = remove();
            if (isFirst) {
                builder.append(element);
                isFirst = false;
            } else {
                builder.append(", ").append(element);
            }

            aux.add(element);
        }
        while (!aux.isEmpty()) { // Adding items back
            element = aux.remove();
            add(element);
        }

        return "[" + builder.toString() + "]";
    }

    /**
     * Method to increase or decrease the list array size.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        if ((float) size / list.length <= 0.25) { // Decrease
            Common<ITEM> c = new Common<>();
            list = c.resize(list, false);
            return;
        }
        if (size >= list.length) { // Increase
            Common<ITEM> c = new Common<>();
            list = c.resize(list, true);
        }
    }

    @Override
    public String toString() {
        String out = "List size: " + size + " Internal array size: " + list.length;
        return out + ". To list all elements, use the show method.";
    }
}
