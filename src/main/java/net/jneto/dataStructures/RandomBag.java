package net.jneto.dataStructures;

import net.jneto.dataStructures.util.Random;

public class RandomBag<ITEM> implements DataStructure<ITEM> {
    private final static int DEFAULT_SIZE = 2; // Initial internal array size
    private ITEM[] bag; // Internal array to store items
    private int size; // Number of elements in the bag

    /**
     * Constructs an empty RandomBag.
     */
    public RandomBag() {
        bag = (ITEM[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    /**
     * Adds an item to the RandomBag.
     * If the internal array is full, it resizes the array.
     *
     * @param item The item to add.
     */
    @Override
    public void add(ITEM item) {
        if (size < bag.length) {
            bag[size] = item;
            size++;
        } else {
            resize();
            add(item); // Retry adding the item after resizing
        }
    }

    /**
     * Adds an item at a random position in the RandomBag.
     * If the bag is empty, the item is added directly.
     * If the internal array is full, it resizes the array before adding.
     *
     * @param item The item to add.
     */
    public void randomAdd(ITEM item) {
        if (size == 0) {
            System.out.println("RandomBag is empty");
            add(item);
            return;
        }
        if (size >= bag.length) {
            resize();
            randomAdd(item); // Retry adding the item after resizing
        } else {
            Random r = new Random();
            int index = r.nextInt(size);
            ITEM old = bag[index];
            bag[index] = item;
            add(old); // Add the old item back to the bag
        }
    }

    /**
     * Removes and returns the last item added to the RandomBag.
     * If the internal array is too large after removal, it resizes the array.
     *
     * @return The removed item, or null if the bag is empty.
     */
    @Override
    public ITEM remove() {
        if (isEmpty()) {
            return null;
        }
        ITEM item = bag[size - 1];
        size--;
        if ((float) size / bag.length <= 0.25) {
            resize(); // Resize if bag is less than 25% full
        }
        return item;
    }

    /**
     * Removes and returns a random item from the RandomBag.
     * After removal, shifts elements to fill the gap.
     *
     * @return The removed item.
     */
    public ITEM randomRemove() {
        Random r = new Random();
        int index = r.nextInt(size);
        ITEM item = bag[index];

        bag[index] = null;

        for (int i = index; i < size; i++) {
            bag[i] = bag[i + 1]; // Shift elements left to fill the gap
        }
        size--;
        resize(); // Resize after removal
        return item;
    }

    /**
     * Checks if the RandomBag is empty.
     *
     * @return True if the RandomBag is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the RandomBag.
     *
     * @return The number of items in the bag.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the last item added to the RandomBag without removing it.
     *
     * @return The last item added.
     */
    @Override
    public ITEM peek() {
        return bag[size - 1];
    }

    /**
     * Returns the item at the specified index without removing it.
     *
     * @param index The index of the item to peek.
     * @return The item at the specified index, or null if the index is invalid.
     */
    public ITEM peek(int index) {
        if (index < size && index >= 0) {
            return bag[index];
        }
        return null;
    }

    /**
     * Returns a random item from the RandomBag without removing it.
     *
     * @return A random item from the bag.
     */
    public ITEM randomPeek() {
        Random r = new Random();
        int index = r.nextInt(size);
        return bag[index];
    }

    /**
     * Returns a string representation of the RandomBag's contents in order.
     *
     * @return A string representing the contents of the RandomBag.
     */
    @Override
    public String show() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        Bag<ITEM> aux = new Bag<>();
        ITEM element;

        while (!isEmpty()) { // Remove elements and add to auxiliary bag
            element = remove();
            aux.add(element);
        }

        while (!aux.isEmpty()) { // Add elements back to the RandomBag
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
     * Returns a string representation of the RandomBag's contents in reverse order.
     *
     * @return A string representing the contents of the RandomBag in reverse order.
     */
    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        Bag<ITEM> aux = new Bag<>();
        ITEM element;

        while (!isEmpty()) { // Remove elements and process in reverse order
            element = remove();
            if (isFirst) {
                builder.append(element);
                isFirst = false;
            } else {
                builder.append(", ").append(element);
            }
            aux.add(element);
        }

        while (!aux.isEmpty()) { // Add elements back to the RandomBag
            element = aux.remove();
            add(element);
        }

        return "[" + builder.toString() + "]";
    }

    /**
     * Resizes the internal array to either increase or decrease its size.
     * Decreases the size if the array is less than 25% full, otherwise increases the size.
     */
    private void resize() {
        if ((float) size / bag.length <= 0.25) { // Decrease size
            Common<ITEM> c = new Common<ITEM>();
            bag = c.resize(bag, false);
            return;
        }
        if (size >= bag.length) { // Increase size
            Common<ITEM> c = new Common<ITEM>();
            bag = c.resize(bag, true);
        }
    }

    /**
     * Returns a string representation of the RandomBag object, including its size and internal array capacity.
     *
     * @return A string summarizing the RandomBag.
     */
    @Override
    public String toString() {
        String out = "  Random bag size: " + size + " Internal array size: " + bag.length;
        return out + " to list all elements, use the method show.";
    }
}
