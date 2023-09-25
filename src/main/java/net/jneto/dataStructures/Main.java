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
        /*output("Stack");
        stackDemonstration();
        output("Queue");
        queueDemonstration();
        output("Bag");
        bagDemonstration();
        output("ArrayList");
        listDemonstration();*/
        ArrayList<String> test = new ArrayList<>();
        test.add("a");
        test.add("b");
        test.add("c");
        test.add("d");
        test.add("e");
        print(test);
        print(test.show());
        test.remove(1);
        print(test.show());
        print(test.remove("d"));
        print(test.show());
        ArrayList<Queue<String>> test2 = new ArrayList<>();
        Queue<String> test3 = new Queue<String>();
        test2.add(test3);
        test2.peek();
        print(test2.show());
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

    private static void listDemonstration(){
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
        structure.add("a");
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