package queue;

import java.util.Iterator;

public class ListIterator implements Iterator {
    private Node node;

    /**
     * @return true if there is a node
     */
    @Override
    public boolean hasNext() {
        return this.node != null;
    }

    /**
     * The code for this method is inspired by the code
     * for the next() method of the ListIterator class in
     * the lecture 5 examples
     * @return data of the node
     */
    @Override
    public Object next() {
        Object data = this.node.getData();
        this.node = this.node.getNext();
        return data;
    }

    /**
     * @param head First node of the list
     */
    public ListIterator(Node head) {
        this.node = head;
    }
}
