package net.jneto.dataStructures;

/**
 * This is a HashSet implementation using a hash table with linked nodes.
 * It stores unique objects and uses hashCode to distribute elements.
 *
 * @param <ITEM> The type of elements stored in the HashSet.
 */
public class HashSet<ITEM> implements Set<ITEM> {
    private static final int DEFAULT_SIZE = 4; // Initial internal array size
    private Node<ITEM>[] set; // Internal array to store buckets
    private int size; // Number of elements in the set

    /**
     * Node class for the hash set buckets.
     *
     * @param <ITEM> The type of elements stored in the node.
     */
    private static class Node<ITEM> {
        private ITEM item;
        private Node<ITEM> next;
    }

    /**
     * Constructs an empty hash set.
     */
    @SuppressWarnings("unchecked")
    public HashSet() {
        set = (Node<ITEM>[]) new Node[DEFAULT_SIZE];
        size = 0;
    }

    /**
     * Adds an object to the set.
     * If the object already exists, it will not be added again.
     *
     * @param item The object to be added.
     */
    @Override
    public void add(ITEM item) {
        if (contains(item)) {
            return;
        }

        if ((float) (size + 1) / set.length >= 0.75) {
            resize(true);
        }

        int index = hash(item);
        Node<ITEM> newNode = new Node<>();
        newNode.item = item;
        newNode.next = set[index];
        set[index] = newNode;
        size++;
    }

    /**
     * Removes and returns an object from the set.
     *
     * @return The removed object, or null if the set is empty.
     */
    @Override
    public ITEM remove() {
        if (isEmpty()) {
            return null;
        }

        for (int i = 0; i < set.length; i++) {
            if (set[i] != null) {
                ITEM item = set[i].item;
                set[i] = set[i].next;
                size--;

                if (set.length > DEFAULT_SIZE && (float) size / set.length <= 0.25) {
                    resize(false);
                }

                return item;
            }
        }

        return null;
    }

    /**
     * Removes the specified object from the set.
     *
     * @param item The object to be removed.
     * @return True if the object was removed, false otherwise.
     */
    @Override
    public boolean remove(ITEM item) {
        int index = hash(item);
        Node<ITEM> current = set[index];
        Node<ITEM> previous = null;

        while (current != null) {
            if (current.item == item || (current.item != null && current.item.equals(item))) {
                if (previous == null) {
                    set[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
                if (set.length > DEFAULT_SIZE && (float) size / set.length <= 0.25) {
                    resize(false);
                }
                return true;
            }
            previous = current;
            current = current.next;
        }

        return false;
    }

    /**
     * Checks if the set contains the specified object.
     *
     * @param item The object to be checked.
     * @return True if the object exists in the set, false otherwise.
     */
    @Override
    public boolean contains(ITEM item) {
        int index = hash(item);
        Node<ITEM> current = set[index];

        while (current != null) {
            if (current.item == item || (current.item != null && current.item.equals(item))) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    /**
     * Checks if the set is empty.
     *
     * @return True if the set contains no objects, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of objects in the set.
     *
     * @return The size of the set.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an object from the set without removing it.
     *
     * @return The object, or null if the set is empty.
     */
    @Override
    public ITEM peek() {
        if (isEmpty()) {
            return null;
        }

        for (int i = 0; i < set.length; i++) {
            if (set[i] != null) {
                return set[i].item;
            }
        }

        return null;
    }

    /**
     * Returns a string representation of the set.
     *
     * @return A string representation of the set.
     */
    @Override
    public String show() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;

        for (int i = 0; i < set.length; i++) {
            Node<ITEM> current = set[i];
            while (current != null) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    builder.append(", ");
                }
                builder.append(current.item);
                current = current.next;
            }
        }

        return "[" + builder.toString() + "]";
    }

    /**
     * Returns a string representation of the set in reverse order.
     *
     * @return A string representation of the reversed set.
     */
    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        LinkedStack<ITEM> auxStack = new LinkedStack<>();
        ITEM element;

        for (int i = 0; i < set.length; i++) {
            Node<ITEM> current = set[i];
            while (current != null) {
                auxStack.add(current.item);
                current = current.next;
            }
        }

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
     * Returns the hash index of the object.
     *
     * @param item The object to calculate the index.
     * @return The index where the object should be stored.
     */
    private int hash(ITEM item) {
        if (item == null) {
            return 0;
        }
        return (item.hashCode() & 0x7fffffff) % set.length;
    }

    /**
     * Resizes the internal array and rehashes all objects.
     *
     * @param increase If true increase the array, if false decrease the array.
     */
    @SuppressWarnings("unchecked")
    private void resize(boolean increase) {
        int newSize;
        if (increase) {
            newSize = set.length * 2;
        } else {
            newSize = set.length / 2;
            if (newSize < DEFAULT_SIZE) {
                newSize = DEFAULT_SIZE;
            }
        }

        Node<ITEM>[] oldSet = set;
        set = (Node<ITEM>[]) new Node[newSize];
        int oldSize = size;
        size = 0;

        for (int i = 0; i < oldSet.length; i++) {
            Node<ITEM> current = oldSet[i];
            while (current != null) {
                add(current.item);
                current = current.next;
            }
        }

        size = oldSize;
    }

    @Override
    public String toString() {
        String out = "HashSet size: " + size + " Internal array size: " + set.length;
        return out + ". To list all elements, use the show method.";
    }
}