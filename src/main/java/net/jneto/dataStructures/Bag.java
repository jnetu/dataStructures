package net.jneto.dataStructures;

/**
 * This is a classic Bag(Saco) implementation with Array
 * t's an Array Bag, so it be ordered by Array Java logic
 * @param <ITEM> The type of elements stored in the Bag.
 */
public class Bag<ITEM> implements DataStructure<ITEM> {
    private final static int DEFAULT_SIZE = 2; // internal ArraySize
    private ITEM[] bag; // Array
    private int size; // Used array size

    /**
     *  Constructor empty bag
     */
    public Bag() {
        bag = (ITEM[]) new Object[DEFAULT_SIZE];
        size = 0;
    }
    /**
     * Adds an object to the data structure.
     *
     * @param item The object to be added.
     */
    @Override
    public void add(ITEM item) {
        if(size < bag.length){
            bag[size] = item;
            size++;
        }else{
            resize();
            add(item);
        }
        
    }

    /**
     * Removes and returns an object from the data structure.
     *
     * @return The removed object. null If the data structure is empty.
     */
    @Override
    public ITEM remove() {
        if (isEmpty()){
            return null;
        }
        ITEM item = bag[size - 1];
        size--;
        if ((float) size / bag.length <= 0.25) {
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
        return size ==0;
    }

    /**
     * Returns the size (number of objects) in the data structure.
     *
     * @return The size of the data structure.
     */
    @Override
    public int size() {
        return bag.length;
    }

    /**
     * returns the first object that will removed put dont remove
     *
     * @return The removed object. null If the data structure is empty.
     */
    @Override
    public ITEM peek() {
        return size == 0 ? null : bag[size - 1];
    }

    /**
     * Returns a string of the bag.
     * A bag dont have order... but its a ArrayBag implementation
     * @return A string representation of the data structure.
     */
    @Override
    public String show() {
        StringBuilder builder = new StringBuilder(); // Java 5+ required
        boolean isFirst = true;
        Bag<ITEM> aux = new Bag<ITEM>();
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

    /**
     * Returns a string of the data structure in reverse order.
     * A bag dont have order... but its a ArrayBag implementation
     * @return A string representation of the reversed data structure.
     */
    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder();
        String out = "";
        boolean isFirst = true;
        Bag<ITEM> aux = new Bag<ITEM>();
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



    /*
     * Method to increase or decrease the bag Array
     *
     */
    private void resize() {
        if((float) size / bag.length <= 0.25) { //Decrease
            Common<ITEM> c = new Common<ITEM>();
            bag = c.resize(bag,false);
            return;
        }
        if(size >= bag.length){ //increase
            Common<ITEM> c = new Common<ITEM>();
            bag = c.resize(bag,true);
        }
    }

    @Override
    public String toString() {
        String out = "  Bag size: " + size + " Internal array size: " + bag.length;
        return out + " to list all element use method show";
    }
}
