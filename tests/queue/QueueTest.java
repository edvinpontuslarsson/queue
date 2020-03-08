package queue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Scanner;

class QueueTest {
    private GenericQueue queue = new GenericQueue();

    // various values that the queue should be able to handle
    private int a = 100;
    private int b = 42;
    private int minus = -1;
    private int zero = 0;
    private int million = 1000000;
    private int hundredMillion = 100000000;
    private int[] allInts = new int[]{
            a,
            b,
            minus,
            zero,
            million,
            hundredMillion,
            Integer.MAX_VALUE,
            Integer.MIN_VALUE
    };
    private String string = "testing";
    private String string2 = "testing again";
    private String[] strings = new String[]{string2, string};
    private Scanner scanner = new Scanner(System.in);

    private Object[] objects = new Object[] {
            a,
            b,
            minus,
            zero,
            million,
            hundredMillion,
            Integer.MAX_VALUE,
            Integer.MIN_VALUE,
            allInts,
            string,
            string2,
            strings,
            scanner,
            queue,
            null,
            this,
    };

    @Test
    void size() {
        assertEquals(0, queue.size());

        ////
        // asserts for size after enqueue
        ////

        for (int x = 3; x <= 12; x++) {
            queue.enqueue(x);
            assertEquals(x-2, queue.size());
        }

        int currentSize = queue.size();

        // to make sure it can handle large amounts of elements
        for (int x = 1; x <= million; x++)
            queue.enqueue(x);

        currentSize += million;

        assertEquals(currentSize, queue.size());

        ////
        // asserts size after dequeue
        ////

        for (int x = currentSize; x <= million; x--) {
            queue.dequeue();
            currentSize--;

            assertEquals(currentSize, queue.size());
        }

        for (int x = currentSize; x > 0; x--)
            queue.dequeue();

        currentSize = 0;

        assertEquals(currentSize, queue.size());
    }

    @Test
    void isEmpty() {
        // should be empty at first
        assertTrue(queue.isEmpty());

        // should not be empty after enqueue
        queue.enqueue(a);
        assertFalse(queue.isEmpty());

        // should be empty after dequeue of only element
        queue.dequeue();
        assertTrue(queue.isEmpty());

        // should not be empty after one dequeue of two elements
        queue.enqueue(a);
        queue.enqueue(b);
        queue.dequeue();
        assertFalse(queue.isEmpty());

        // but should be empty after this second dequeue
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    void enqueue() {
        Object start = null;
        for (int i = 0; i < objects.length; i++) {
            queue.enqueue(objects[i]);

            if (i == 0) {
                start = objects[i];
            }

            assertEquals(start, queue.first());
            assertEquals(objects[i], queue.last());
        }
    }

    @Test
    void dequeue() {
        // trying to dequeue empty queue should throw exception
        assertThrows(IndexOutOfBoundsException.class, ()->{queue.dequeue();});

        queue.enqueue(1);
        queue.enqueue(42);

        int firstDequeue = (int) queue.dequeue();
        assertEquals(1, firstDequeue);

        // asserts that, when 2 added, second is first and last after a dequeue
        assertEquals(42, queue.first());
        assertEquals(42, queue.last());
    }

    @Test
    void first() {
        // trying to get from empty queue should throw exception
        assertThrows(IndexOutOfBoundsException.class, ()->{queue.first();});

        // after 1 enqueue & 1 dequeue, should be empty & should throw exception
        queue.enqueue(a);
        queue.dequeue();
        assertThrows(IndexOutOfBoundsException.class, ()->{queue.first();});

        // this method is tested more thoroughly in the enqueue & dequeue tests
    }

    @Test
    void last() {
        // trying to get from empty queue should throw exception
        assertThrows(IndexOutOfBoundsException.class, ()->{queue.last();});

        // after 1 enqueue & 1 dequeue, should be empty & should throw exception
        queue.enqueue(a);
        queue.dequeue();
        assertThrows(IndexOutOfBoundsException.class, ()->{queue.last();});

        // this method is tested more thoroughly in the enqueue & dequeue tests
    }

    @Test
    void iterator() {
        // first add objects
        for (Object object : objects) queue.enqueue(object);
        // index for using with the objects
        int i = 0;

        Iterator it = queue.iterator();

        int timesLooped = 0;
        // iterates and asserts correctness
        while (it.hasNext()) {
            Object next = it.next();

            assertEquals(objects[i], next);

            i++;
            timesLooped++;
        }

        assertEquals(objects.length, timesLooped);
    }
}