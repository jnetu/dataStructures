package net.jneto.dataStructures;

/**
 * This is a classic Queue(Fila) implementation with Array
 * @param <ITEM> The type of elements stored in the Queue.
 */
public class Queue<ITEM> implements DataStructure<ITEM> {
    private static final int DEFAULT_SIZE = 2; // internal ArraySize
    private ITEM[] queue; // Array
    private int size; // Used array size


    /**
     * Constructor empty queue
     */
    @SuppressWarnings("unchecked")
    public Queue() {
        queue = (ITEM[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public void add(ITEM item) {
        if (size >= queue.length) {
            resize();
            add(item);
        }else{
            queue[size] = item;
            size++;
        }

    }

    @Override
    public ITEM remove() {
        if (isEmpty()) {
            return null;
        }
        ITEM item = queue[0];
        //REALOC

        for (int i = 0; i < size - 1; i++) {
            queue[i] = queue[i + 1];
        }

        //ENDREALOC
        queue[size - 1] = null;
        size--;
        if ((float) size / queue.length <= 0.25) {
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

    @Override
    public ITEM peek() {
        if (isEmpty()) {
            return null;
        }
        return size == 0 ? null : queue[0];
    }

    @Override
    public String show() {
        StringBuilder builder = new StringBuilder(); // Java 5+ required
        boolean isFirst = true;
        Queue<ITEM> aux = new Queue<ITEM>();
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

    @Override
    public String showReverse() {
        StringBuilder builder = new StringBuilder(); // Java 5+ required
        String out = "";
        boolean isFirst = true;
        ITEM[] aux = (ITEM[]) new Object[size];
        int auxIndex = 0;
        ITEM element;
        while (!isEmpty()) { // removing
            element = remove();
            aux[auxIndex] = element;
            auxIndex++;
        }
        for(int i = auxIndex - 1; i >=0; i--){ // adding
            element = aux[i];
            if (isFirst) {
                builder.append(element);
                isFirst = false;
            } else {
                builder.append(", ").append(element);
            }
            add(element);
        }

        return "[" + builder.toString() + "]";
    }

    /*
     * Method to increase or decrease the queue Array
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        if((float) size / queue.length <= 0.25) { //Decrease
            Common<ITEM> c = new Common<ITEM>();
            queue = c.resize(queue,false);
            return;
        }
        if(size >= queue.length){ //increase
            Common<ITEM> c = new Common<ITEM>();
            queue = c.resize(queue,true);
        }
    }

    @Override
    public String toString() {
        String out = "  Queue size: " + size + " Internal array size: " + queue.length;
        return out + " to list all elements use method show";
    }
}
