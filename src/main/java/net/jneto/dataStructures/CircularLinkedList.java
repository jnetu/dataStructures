package net.jneto.dataStructures;


public class CircularLinkedList<ITEM> implements DataStructure<ITEM>{
	private Node<ITEM> head;
    private int size;
    
    /**
     * Constructs an empty Circular linked list.
     */
    public CircularLinkedList(){
    	head = null;
        size = 0;
    }
    
    /**
     * Node class for the Circular linked list.
     *
     * @param <ITEM> The type of elements stored in the node.
     */
    private static class Node<ITEM> {
    	private ITEM item;
    	private Node<ITEM> next;
    }
    
    /**
     * Adds an item to the end of the Circular linked list.
     *
     * @param item The item to be added.
     */
    public void add(ITEM item) {
        Node<ITEM> newNode = new Node<>();
        newNode.item = item;

        if (isEmpty()) {
            head = newNode;
            head.next = head;
        } else {
            Node<ITEM> current = head;
            while (current.next != head) {
                current = current.next;
            }
            current.next = newNode;
            newNode.next = head;
            }
        size++;
    }
    
    /**
     * Removes and returns the first item from the Circular linked list.
     *
     * @return The item removed from the list, or null if the list is empty.
     */
    public ITEM remove() {
        if (isEmpty()) {
            return null;
        }
        ITEM item = head.item;

        if (size == 1) {
            head = null;
        } else {
            Node<ITEM> current = head;
            while (current.next != head) {
                current = current.next;
            }
            head = head.next;
            current.next = head;
        }
        size--;
        return item;
    }
    
    /**
     * Returns the first item in the Circular linked list without removing it.
     *
     * @return The first item in the list, or null if the list is empty.
     */
    public ITEM peek() {
        if (isEmpty()) {
        	return null;
        }
        return head.item;
    }
    
    /**
     * Returns a string representation of the Circular linked list from head to tail.
     *
     * @return A string containing all elements in the list.
     */
	public String show() {
		if (isEmpty()) {
			return "[]";
		}

		StringBuilder builder = new StringBuilder();
		Node<ITEM> current = head;
		boolean isFirst = true;

		do {
			if (isFirst) {
				isFirst = false;
			} else {
				builder.append(", ");
			}
			builder.append(current.item);
			current = current.next;
		} while (current != head);

		return "[" + builder.toString() + "]";
	}
	
	/**
     * Returns a string representation of the Circular linked list from tail to head.
     *
     * @return A string containing all elements in reverse order.
     */
    @Override
	public String showReverse() {
		if (isEmpty()) {
			return "[]";
		}

		StringBuilder builder = new StringBuilder();
		Node<ITEM> last = head;
		while (last.next != head) {
			last = last.next;
		}

		Stack<ITEM> stack = new Stack<>();
		Node<ITEM> current = head;
		do {
			stack.add(current.item);
			current = current.next;
		} while (current != head);

		boolean isFirst = true;
		while (!stack.isEmpty()) {
			if (isFirst) {
				isFirst = false;
			} else {
				builder.append(", ");
			}
			builder.append(stack.remove());
		}

		return "[" + builder.toString() + "]";
	}

    /**
     * Checks if the Circular linked list is empty.
     *
     * @return True if the linked list is empty, false otherwise.
     */
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	/**
     * Returns the number of elements in the Circular linked list.
     *
     * @return The size of the linked list.
     */
	@Override
	public int size() {
		return size;
	}

    
}
