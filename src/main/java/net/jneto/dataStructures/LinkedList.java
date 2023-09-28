public class LinkedList {
    
    private Element head;

    public LinkedList() {
        this.head = null;
    }
    
    public LinkedList(Element element) {
        this.head = element;
    }
    
    public void add(Element e) {
        if (head == null) {
            head = e;
            return;
        }
        
        Element cage = head;
        while (cage.next != null) {
            cage = cage.next;
        }
        
        cage.next = e;
    }
    
    public void add(Object value) {
        add(new Element(value, null));
    }
  //TODO other Methodes
}
