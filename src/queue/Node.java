package queue;

public class Node {
    private Object data;
    private Node prev;
    private Node next;

    /**
     * @return The value in this node
     */
    public Object getData() {
        return data;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * @param data the value in this node
     */
    public Node(Object data) {
        this.data = data;
    }
}
