package net.jneto.dataStructures;

public class DoublyLinkedList<ITEM> extends LinkedList<ITEM>{
	private Node<ITEM> head; // First node in the list
    private int size; // Number of elements in the list

	/**
     * Constructs an empty linked list.
     */
    public DoublyLinkedList() {
        head = null;
        size = 0;
    }
	
    /**
     * Node class for the Doubly linked list.
     *
     * @param <ITEM> The type of elements stored in the node.
     */
    private static class Node<ITEM> {
        private ITEM item;
        private Node<ITEM> next;
        private Node<ITEM> prev;
    }
    
    /**
     * Adds an item to the end of the linked list.
     *
     * @param item The item to be added.
     */
	@Override
	public void add(ITEM item) {
		Node<ITEM> newNode = new Node<>();
		newNode.item = item;
		if (isEmpty()) {
            head = newNode;
        } else {
            Node<ITEM> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
        }
        size++;
	}
	
	/**
     * Removes and returns the first item from the linked list.
     *
     * @return The item removed from the list, or null if the list is empty.
     */
	@Override
	public ITEM remove() {
		if (isEmpty()) {
            return null;
        }
        ITEM item = head.item;
        head = head.next;
        
        if(head != null) {
        	head.prev = null;
        }
        
        size--;
        return item;
	}
	
	/**
     * Checks if the linked list is empty.
     *
     * @return True if the linked list is empty, false otherwise.
     */
	@Override
	public boolean isEmpty() {
		return head == null;
	}
	
	/**
     * Returns the number of elements in the linked list.
     *
     * @return The size of the linked list.
     */
	@Override
	public int size() {
		return size;
	}
	
	/**
     * Returns the first item in the doubly linked list without removing it.
     *
     * @return The first item in the list, or null if the list is empty.
     */
	@Override
	public ITEM peek() {
		if (isEmpty()) {
            return null;
        }
        return head.item;
	}
	
	/**
     * Returns a string representation of the doubly linked list from head to tail.
     *
     * @return A string containing all elements in the list.
     */
	@Override
	public String show() {
		StringBuilder builder = new StringBuilder();
        Node<ITEM> current = head;
        boolean isFirst = true;

        while (current != null) {
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(", ");
            }
            builder.append(current.item);
            current = current.next;
        }

        return "[" + builder.toString() + "]";
	}
	
	/**
     * Returns a string representation of the doubly linked list from tail to head.
     *
     * @return A string containing all elements in reverse order.
     */
	@Override
	public String showReverse() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        Node<ITEM> current = head;
        Node<ITEM> previous = null;
        Node<ITEM> next;

        // Reverse the linked list
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        // Build the string representation of the reversed list
        current = previous;
        while (current != null) {
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(", ");
            }
            builder.append(current.item);
            current = current.next;
        }

        // Restore the original order of the linked list by reversing it again
        current = previous;
        previous = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        return "[" + builder.toString() + "]";
    }
	
	/**
     * Returns a string with basic information about the doubly linked list.
     *
     * @return A string with the list's size and a prompt to use the show() method.
     */
    @Override
    public String toString() {
        return "This linked structure contains " + size + " elements. To display them, use the show() method.";
    }
    
    
    /**
     * Adds an item to the First of the doubly linked list.
     *
     * @param item The item to be added.
     */
    public void addFirst(ITEM item) {
        Node<ITEM> newNode = new Node<>();
        newNode.item = item;

        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;        
        }
        size++;
    }
    
    /**
     * Adds an item to the end(Last) of the doubly linked list.
     * Call add method - add at end
     * @param item The item to be added.
     */
    public void addLast(ITEM item) {
        add(item);
    }
    
    /**
     * Adds an item to the any location of the doubly linked list.
     * 
     * @param index The index to where to be added.
     * @param item The item to be added.
     */
    public void addAt(int index, ITEM item) {
        if (index < 0 || index > size) {
        	 System.out.println("DoublyLinkedList - addAt(index,item) Index Out Of Bounds! - " + "Index: " + index + ", Size: " + size);
        	 return;
        }
        if (index == 0) {
            addFirst(item);
            return;
        }
        if (index == size) {
            addLast(item);
            return;
        }

        Node<ITEM> newNode = new Node<>();
        newNode.item = item;

        Node<ITEM> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        Node<ITEM> previous = current.prev;

        previous.next = newNode;
        newNode.prev = previous;
        newNode.next = current;
        current.prev = newNode;    
        size++;
    }
    
    /**
     * Removes an item to the any location of the doubly linked list.
     * 
     * @param index The index to where to be removed.
     */
    public ITEM removeAt(int index) {
    	if (isEmpty() || index < 0 || index >= size) {
            return null;
        }
    	
    	if (index == 0) {
            return remove();
    	}
    	
    	 Node<ITEM> current = head;
    	 for(int i = 0; i < index; i++) {
    		 current = current.next;
    		 }
    	 
    	 Node<ITEM> previous = current.prev;
    	 Node<ITEM> next = current.next;
    	 
    	 previous.next = next;
    	 if (next != null){
    		 next.prev = previous;
    		 }
    	 size--;
    	 return current.item;
    }
    

}
