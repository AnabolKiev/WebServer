package com.anabol.collections;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedQueueTest {

    @Test
    public void testEnqueu() {
        LinkedQueue linkedQueue = new LinkedQueue();
        Assert.assertEquals(0, linkedQueue.size);
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("B");
        linkedQueue.enqueue("A");
        Assert.assertEquals(3, linkedQueue.size);
    }

    @Test
    public void testPeek() {
        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("B");
        linkedQueue.enqueue("C");
        Assert.assertEquals("A", linkedQueue.peek());
        Assert.assertEquals(3, linkedQueue.size);
    }

    @Test
    public void testDequeu() {
        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("B");
        linkedQueue.enqueue("C");
        Assert.assertEquals("A", linkedQueue.dequeue());
        Assert.assertEquals(2, linkedQueue.size);
    }

    @Test
    public void testRemoveAll() {
        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("B");
        linkedQueue.enqueue("A");
        linkedQueue.removeAll("A");
        Assert.assertEquals(1, linkedQueue.size);
    }

    @Test
    public void testRemoveAll2() {
        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.removeAll("A");
        Assert.assertEquals(0, linkedQueue.size);
        assertNull(linkedQueue.head);
    }

    @Test
    public void testRemoveAll3() {
        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.enqueue("A");
        linkedQueue.removeAll("A");
        Assert.assertEquals(0, linkedQueue.size);
        assertNull(linkedQueue.head);
    }
}
