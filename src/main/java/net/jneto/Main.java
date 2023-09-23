package net.jneto;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        output("Stack");
        demonstrationStack();

    }

    private static void demonstrationStack(){
        Stack<String> stack = new Stack<String>();
        stack.add("a");
        print(stack);
        stack.add("a");
        print(stack);
        stack.add("a");
        print(stack);
        stack.add("a");
        print(stack.show());
        print(stack);
        stack.add("b");
        print(stack.show());
        print(stack);
        stack.add("c");
        print(stack.show());
        print(stack);
        stack.add("d");
        print(stack);
        stack.add("e");
        print(stack);
        stack.add("f");
        print(stack.show());
        print(stack.showReverse());
        print(stack.remove());
        print(stack.remove());
        print(stack.show());
        print(stack.remove());
        print(stack.remove());
        print(stack.show());
        print(stack);
        stack.add("g");
        stack.remove();
        stack.remove();
        stack.remove();
        stack.remove();
        print(stack);
        stack.remove();
        stack.remove();
        stack.remove();
        print(stack);
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