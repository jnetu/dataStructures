package net.jneto;

public class Queue<ITEM> implements DataStructure<ITEM> {
    private static final int DEFAULT_SIZE = 2; // internal ArraySize
    private ITEM[] queue; // Array
    private int size; // Used array size
    private int front; // First index
    private int end; // Last index

    @SuppressWarnings("unchecked")
    public Queue() {
        queue = (ITEM[]) new Object[DEFAULT_SIZE];
        size = 0;
        front = 0;
        end = -1;
    }

    @Override
    public void add(ITEM item) {
        if (size >= queue.length) {
            resize();
        }
        end = (end + 1) % queue.length;
        queue[end] = item;
        size++;
    }

    @Override
    public ITEM remove() {
        if (isEmpty()) {
            return null;
        }
        ITEM item = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
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
        return size == 0 ? null : queue[front];
    }

    @Override
    public String show() {
        StringBuilder result = new StringBuilder();
        int currentIndex = front;
        for (int i = 0; i < size; i++) {
            result.append(queue[currentIndex]);
            if (i < size - 1) {
                result.append(", ");
            }
            currentIndex = (currentIndex + 1) % queue.length;
        }
        return "[" + result.toString() + "]";
    }

    @Override
    public String showReverse() {
        StringBuilder result = new StringBuilder();
        int currentIndex = front;
        for (int i = 0; i < size; i++) {
            result.insert(0, queue[currentIndex]);
            if (i < size - 1) {
                result.insert(0, ", ");
            }
            currentIndex = (currentIndex + 1) % queue.length;
        }
        return "[" + result.toString() + "]";
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
