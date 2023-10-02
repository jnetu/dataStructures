package net.jneto.dataStructures;

public class LinkedQueue<ITEM> implements DataStructure<ITEM> {
    private int size; // total queue size
    private Node<ITEM> first; // first element that will be removed
    private Node<ITEM> last; // last element added

    public LinkedQueue(){
        first = null;
        last = null;
        size = 0;
    }



    /**
     * Linked class
     * dont need to be static relax the garbage collector will save us
     */
    private class Node<ITEM>{ // Node is a data structure to keep actual item and the next DataStructure
        private ITEM item;
        private Node<ITEM> next;
    }

    /**
     * Adds an object to the data structure.
     *
     * @param item The object to be added.
     */
    @Override
    public void add(ITEM item) {
        Node<ITEM> aux = new Node<ITEM>();
        aux.item = item;
        aux.next = null;
        if(isEmpty()){//its means that dont have queued objects wet, and nothing to link wet
            //if empty, lets add one element, and the element is the last, and the first of the queue
            first = aux;
            last = aux;
        }else{ //goto end pls respect The Queue >:( do not cheat
            last.next = aux;
            last = aux;
        }
        //plus one :)
        size++;
    }

    /**
     * Removes and returns an object from the data structure.
     *
     * @return the first item that will be removed(can be null if empty)
     */
    @Override
    public ITEM remove() {
        //Next that will be free is the first on a Queue
        Node<ITEM> aux = first;

        if(size<=1){ // have just one on queue
            //so lets clean
            first = null;
            last = null;

        }else{
            first = first.next; //the next Object will be the future first
        }
        //decrease one :(
        size--;


        return aux.item;
    }

    /**
     * Checks if the data structure is empty.
     *
     * @return True if the data structure contains no objects, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
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
     * returns the first object that will removed put dont remove
     *
     * @return The removed object.
     * @throws IllegalStateException If the data structure is empty.
     */
    @Override
    public ITEM peek() {
        return size <= 0 ? null : first.item;
    }

    /**
     * Returns a string of the data structure.
     *
     * @return A string representation of the data structure.
     */
    @Override
    public String show() {
        //lets walk the queue with an aux
        Node<ITEM> aux = first;
        StringBuilder builder = new StringBuilder(); // Java 5+ required
        boolean isFirst = true;
        while(aux != null){

            if(isFirst){
                isFirst = false;
            }else{
                builder.append(", ");
            }
            builder.append(aux.item);
            aux = aux.next; //walk to next
        }


        return "[" + builder.toString() + "]";
    }

    /**
     * Returns a string of the data structure in reverse order.
     *
     * @return A string representation of the reversed data structure.
     */
    @Override
    public String showReverse() {
        return "todo"; //i dont have idea how todo showReverse wet, my mint are bloewd when's night
    }

    @Override
    public String toString() {
        return "this linked structure have " + size + " elements, to show then use show() method";
    }
}
