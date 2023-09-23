package net.jneto.dataStructures;

/**
 * Main Function to see functions DataStructure created
 */
public class Main {

    /**
     * Main default constructor java
     */
    public Main(){

    }
    /**
     * Java jar entry point
     * @param args - java -jar arguments
     */
    public static void main(String[] args) {
        output("Stack");
        stackDemonstration();
        output("Queue");
        queueDemonstration();
        output("Bag");
        bagDemonstration();
    }

    private static void queueDemonstration() {
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

    private static void stackDemonstration(){
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
    private static void bagDemonstration(){
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
    private static void output(String name){
        System.out.println("/*******************************************************");
        System.out.println("/* " + name + " Demonstration");
        System.out.println("/*******************************************************");

    }

    private static void print(Object out){
        System.out.println(out);
    }
}