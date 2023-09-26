package net.jneto.dataStructures;

import net.jneto.dataStructures.Comparator.BiggerIntegerComparator;
import net.jneto.dataStructures.Comparator.BiggerStringLengthComparator;
import net.jneto.dataStructures.Comparator.Comparator;

/**
 * Main Function to see functions DataStructure created
 */
public class Main {

    /**
     * Main default constructor java
     */
    public Main() {

    }

    /**
     * Java jar entry point
     *
     * @param args - java -jar arguments
     */
    public static void main(String[] args) {
        output("Stack");
        arrayStackDemo();
        output("Queue");
        arrayQueueDemo();
        output("Bag");
        arrayBagDemo();
        output("ArrayList");
        arrayListDemon();

        output("ArrayList additional methods ");
        advancedArrayListStructureDemo();
        output("Priority queue Array-min-heap based ");
        priorityQueueDemo();

        output("Deque");
        arrayDequeDemo();

    }

    private static void arrayQueueDemo() {
        Queue<String> structure = new Queue<String>();
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure.show());
        print(structure);
        structure.add("b");
        print(structure.show());
        print(structure);
        structure.add("c");
        print(structure.show());
        print(structure);
        structure.add("d");
        print(structure);
        structure.add("e");
        print(structure);
        structure.add("f");
        print(structure.show());
        print(structure.showReverse());
        print(structure.remove());
        print(structure.remove());
        print(structure.show());
        print(structure.remove());
        print(structure.remove());
        print(structure.show());
        print(structure);
        structure.add("g");
        structure.remove();
        structure.remove();
        structure.remove();
        structure.remove();
        print(structure);
        structure.remove();
        structure.remove();
        structure.remove();
        print(structure);

    }

    private static void arrayStackDemo() {
        Stack<String> structure = new Stack<String>();
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure.show());
        print(structure);
        structure.add("b");
        print(structure.show());
        print(structure);
        structure.add("c");
        print(structure.show());
        print(structure);
        structure.add("d");
        print(structure);
        structure.add("e");
        print(structure);
        structure.add("f");
        print(structure.show());
        print(structure.showReverse());
        print(structure.remove());
        print(structure.remove());
        print(structure.show());
        print(structure.remove());
        print(structure.remove());
        print(structure.show());
        print(structure);
        structure.add("g");
        structure.remove();
        structure.remove();
        structure.remove();
        structure.remove();
        print(structure);
        structure.remove();
        structure.remove();
        structure.remove();
        print(structure);
    }

    private static void arrayBagDemo() {
        Bag<String> structure = new Bag<String>();
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure.show());
        print(structure);
        structure.add("b");
        print(structure.show());
        print(structure);
        structure.add("c");
        print(structure.show());
        print(structure);
        structure.add("d");
        print(structure);
        structure.add("e");
        print(structure);
        structure.add("f");
        print(structure.show());
        print(structure.showReverse());
        print(structure.remove());
        print(structure.remove());
        print(structure.show());
        print(structure.remove());
        print(structure.remove());
        print(structure.show());
        print(structure);
        structure.add("g");
        structure.remove();
        structure.remove();
        structure.remove();
        structure.remove();
        print(structure);
        structure.remove();
        structure.remove();
        structure.remove();
        print(structure);
    }

    private static void arrayListDemon() {
        ArrayList<String> structure = new ArrayList<String>();
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure.show());
        print(structure);
        structure.add("b");
        print(structure.show());
        print(structure);
        structure.add("c");
        print(structure.show());
        print(structure);
        structure.add("d");
        print(structure);
        structure.add("e");
        print(structure);
        structure.add("f");
        print(structure.show());
        print(structure.showReverse());
        print(structure.remove());
        print(structure.remove());
        print(structure.show());
        print(structure.remove());
        print(structure.remove());
        print(structure.show());
        print(structure);
        structure.add("g");
        structure.remove();
        structure.remove();
        structure.remove();
        structure.remove();
        print(structure);
        structure.remove();
        structure.remove();
        structure.remove();
        print(structure);
    }

    /**
     * A list have more complex methods to-do
     */
    private static void advancedArrayListStructureDemo() {
        ArrayList<String> structure = new ArrayList<String>();
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure.show());
        print(structure);
        structure.add("b");
        print(structure.show());
        print(structure);
        structure.add("c");
        print(structure.show());
        print(structure);
        structure.add("d");
        print(structure);
        structure.add("e");
        print(structure);
        structure.add("f");
        print(structure.show());
        print(structure.showReverse());
        print(structure.remove());
        print(structure.remove());
        print(structure.show());
        print(structure.remove());
        print(structure.remove());
        print(structure.show());
        print(structure);
        structure.add("g");
        structure.remove();
        structure.remove();
        structure.remove();
        structure.remove();
        print(structure);
        structure.remove();
        structure.remove();
        structure.remove();
        print(structure);
        structure.add("a");
        structure.add("b");
        structure.add("c");
        structure.add("d");
        structure.add("e");
        print(structure.show());
        print("removed f: " + structure.remove("f"));
        print("removed d: " + structure.remove("d"));
        print(structure.show());
        print(structure.show());
        ArrayList<Queue<String>> test2 = new ArrayList<>();
        Queue<String> test3 = new Queue<String>();
        test3.add("Queued-item-into-list1");
        test3.add("Queued-item-into-list2");
        test2.add(test3);
        print("Queue into List: " + test2.peek().show());

    }

    /**
     * How to use Priority Queue demonstration
     * and implements Compare Interface demonstration to
     * use in any Objects
     */
    private static void priorityQueueDemo() {
        Comparator<String> stringComparator = new Comparator<String>() { //how to make your comparator system for any Object
            @Override
            public int compare(String t1, String t2) {
                if (t1.length() < t2.length()) { //If bigger - more priority
                    return 1;
                } else if (t1.length() > t2.length()) {//else - less priority
                    return -1;
                }

                return 0; //zero if it's equals
            }
        };
        Comparator<String> cp = new BiggerStringLengthComparator(); //another way into Comparator Interface example
        PriorityQueue<String> pq = new PriorityQueue<String>(stringComparator);
        pq.add("n1");
        pq.add("nome maior AAAAAAAAAa");
        pq.add("b22");
        pq.add("nome maior CCCCCCCCCc");
        pq.add("d333");
        pq.add("e4444");
        pq.add("f55555");
        pq.add("g666666");
        pq.add("a7777777");
        print(pq.peek());
        print(pq);
        print(pq.show());
        print(pq.showReverse());
        print(pq.remove());
        print(pq.show());
        print(pq.remove());
        print(pq.show());
        print(pq.remove());
        print(pq.show());
        print(pq.remove());
        print(pq.show());
        print(pq.peek());
        print(pq.remove());
        print(pq.show());
        pq.add("c");
        print(pq.show());
        print(pq.remove());
        print(pq.peek());
        print(pq.remove());
        print(pq.show());
        print(pq.peek());
        Comparator<Integer> compint = new BiggerIntegerComparator();
        PriorityQueue<Integer> pqint = new PriorityQueue<Integer>(compint);

        pqint.add(1);
        pqint.add(2);
        pqint.add(33);
        pqint.add(4);


        print(pqint.show());
    }

    private static void arrayDequeDemo() {
        Deque<String> structure = new Deque<String>();
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure);
        structure.add("a");
        print(structure.show());
        print(structure);
        structure.add("b");
        print(structure.show());
        print(structure);
        structure.add("c");
        print(structure.show());
        print(structure);
        structure.add("d");
        print(structure);
        structure.add("e");
        print(structure);
        structure.add("f");
        print(structure.show());
        print(structure.showReverse());
        print(structure.remove());
        print(structure.remove());
        print(structure.show());
        print(structure.remove());
        print(structure.remove());
        print(structure.show());
        print(structure);
        structure.add("g");
        structure.remove();
        structure.remove();
        structure.remove();
        structure.remove();
        print(structure);
        structure.remove();
        structure.remove();
        structure.remove();
        print(structure);
        structure.addFront("frontA");
        structure.addFront("frontB");
        structure.addFront("frontC");
        structure.addEnd("endA");
        structure.addEnd("endB");
        structure.addEnd("endC");
        structure.addFront("frontd");
        structure.addFront("fronte");
        structure.addFront("frontf");
        print(structure.show());
        print(structure.removeFront());
        print(structure.show());
        print(structure.removeFront());
        print(structure.show());
        print(structure.showReverse());
        print(structure.removeEnd());
    }

    private static void output(String name) {
        System.out.println("/*******************************************************");
        System.out.println("/* " + name + " Demonstration");
        System.out.println("/*******************************************************");

    }

    private static void print(Object out) {
        System.out.println(out);
    }
}