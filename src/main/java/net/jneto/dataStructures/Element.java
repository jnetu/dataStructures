package net.jneto.dataStructures;
/*
* Element class that ist the basis for a lot of other classes for example LinkedLists
*/

public class Element() {
    private Object value;
    private Element next;
    
    public Element(Object value, Element next) {
        this.value = value;
        this.next = next;
    }
}
