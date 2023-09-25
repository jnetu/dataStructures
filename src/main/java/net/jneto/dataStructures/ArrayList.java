package net.jneto.dataStructures;


/**
 * This is a classic ArrayList(Lista) implementation with Array
 * @param <ITEM> The type of elements stored in the ArrayList.
 */
public class ArrayList<ITEM> implements DataStructure<ITEM> {
    private static final int DEFAULT_SIZE = 2; // internal ArraySize
    private ITEM[] list; // Array
    private int size; // Used array size

    /**
     * Constructor empty list
     */
    @SuppressWarnings("unchecked")
    public ArrayList(){
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
     *  A list logic you can put Objects in any position
     * @param item - The object to be added
     * @param index - index where object will be added
     */
    public void add(ITEM item, int index) {
        //INDEX VERIFY
        if(index > list.length || index < 0){
            return;
        }
        //VERIFY SPACE
        if (size >= list.length) {
            resize();
        }

        //ARRAY REALOCATION
        //LOGIC:
        //[0,1,2,3,4,5, null, null]
        //add 2 in index 1:
        //index = 1
        //RESIZE because size == 8 == list.length
        //list[7] = list[6]
        //list[6] = list[5]
        //...
        //list[index==1] STOP
        //list[index==1] = new item
        //[0,2,1,2,3,4,5, null, null, null, null, null, null, null, null, null, null]

        for(int i = size; i > index; i--){
            list[i] = list[i - 1];
        }
        list[index] = item;
        size++;
    }

    /**
     *  A list logic you can get Objects in any position
     * @param index - index where object will be returned
     */
    public ITEM get(int index) {
        if(index > list.length || index < 0){
            return null;
        }
        return list[index];
    }

    /**
     * Removes and returns the last added item from the array.
     *
     * @return The removed object.
     * @throws IllegalStateException If the data structure is empty.
     */
    @Override
    public ITEM remove() {

        if(isEmpty()){
            return null;
        }
        ITEM item = list[size - 1];
        size--;

        if((float) size / list.length <= 0.25){
            resize();
        }

        return item;
    }

    /**
     *  A list logic you can remove Objects in any position
     * @param index - index where object will be removed
     */
    public ITEM remove(int index) {
        if(index > list.length || index < 0){
            return null;
        }
        //ARRAY REALOC
        //LOGIC
        //[1,2,3,4,5,6,whatever,whatever] - size = 6 list[].length = 8
        //           |> last index list[size]
        //remove(index = 1)
        //[1,3,4,5,6,whatever,whatever,whatever] - size = 5 list[].length = 8
        //         |> last index list[size]
        //

        ITEM removed = list[index];
        //after 'remove' realoc after removed item
        for(int i = index; i < size - 1; i++){
            list[i] = list[i + 1];

        }
        //[1,2,3,4,5,6,whatever,whatever]
        //after for
        //[1,3,4,5,6,6,whatever,whatever]
        //lets clean last index(its inaccessible but why not clean it)
        list[size - 1] = null;
        size--;
        if ((float) size / list.length <= 0.25) { //resize if 0.25 less used
            resize();
        }

        return removed;
    }

    /**
     * Removes and returns the first occurrence from the array.
     *
     * @return removed successfully.
     * @throws IllegalStateException If the data structure is empty.
     */
    public boolean remove(ITEM item){
        for(int i = 0; i < size; i++){
            if(list[i] == item || (list[i] != null && list[i].equals(item))){
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
        return size <=0;
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
     * returns the first object that will be removed but don't remove
     *
     * @return The removed object.
     * @throws IllegalStateException If the data structure is empty.
     */
    @Override
    public ITEM peek() {
        return list[size - 1];
    }

    /**
     * returns the indexed object but don't remove
     *
     * @return The removed object.
     * @throws IllegalStateException If the data structure is empty.
     */
    public ITEM peek(int index) {
        //[1,2,3]
        //peek(0)
        // 1
        //[1] --size = 1 list.length = 2
        //peek(0)
        // 1  (list[0])
        if(index > size || index < 0){
            return null;
        }
        return list[index - 1];
    }

    /**
     * Returns a string of the data structure.
     *
     * @return A string representation of the data structure.
     */
    @Override
    public String show() {
        StringBuilder builder = new StringBuilder(); // Java 5+ required
        boolean isFirst = true;
        ArrayList<ITEM> aux = new ArrayList<ITEM>();
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
     *
     * @return A string representation of the reversed data structure.
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

    /*
     * Method to increase or decrease the list Array
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        if((float) size / list.length <= 0.25) { //Decrease
            Common<ITEM> c = new Common<ITEM>();
            list = c.resize(list,false);
            return;
        }
        if(size >= list.length){ //increase
            Common<ITEM> c = new Common<ITEM>();
            list = c.resize(list,true);
        }
    }
    @Override
    public String toString() {
        String out = "  List size: " + size + " Internal array size: " + list.length;
        return out + " to list all element use method show";
    }
}
