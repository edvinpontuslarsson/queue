package queue;

import java.util.Iterator;

public class GenericQueue<E> implements Iterable<E> {

    private int size = 0;
    private Node first = null;
    private Node last = null;

    /**
     * @return current queue size
     */
    public int size() {
        return this.size;
    }

    /**
     * @return true if queue is empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * @param element add element at end of queue
     */
    public void enqueue(Object element) {
        // sets new Node's data field to the element parameter via its constructor
        Node newNode = new Node(element);

        if (this.isEmpty()) {
            this.first = newNode;
        } else {
            this.last.setNext(newNode);
            newNode.setPrev(this.last);
        }

        this.last = newNode;
        this.size++;
    }

    /**
     * @return and remove first element
     */
    public Object dequeue() throws IndexOutOfBoundsException {
        if (this.size < 1) throw new IndexOutOfBoundsException();

        Node nodeToDequeue = this.first;

        if (this.size == 1) {
            this.first = null;
            this.last = null;
        } else {
            this.first = nodeToDequeue.getNext();
            this.first.setPrev(null);
        }

        this.size--;
        return nodeToDequeue.getData();
    }

    /**
     * @return (without removing) first element
     */
    public Object first() throws IndexOutOfBoundsException {
        if (this.size < 1) throw new IndexOutOfBoundsException();
        return this.first.getData();
    }

    /**
     * @return (without removing) last element
     */
    public Object last() throws IndexOutOfBoundsException {
        if (this.size < 1) throw new IndexOutOfBoundsException();
        return this.last.getData();
    }

    /**
     * @return Iterator of the queue
     */
    @Override
    public Iterator iterator() {
        return new ListIterator(this.first);
    }
}

