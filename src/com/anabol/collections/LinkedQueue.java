package com.anabol.collections;

public class LinkedQueue {
    Node head;
    int size;

    void enqueue(Object value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.prev != null) { // find last node
                temp = temp.prev;
            }
            temp.prev = newNode;
        }
        size++;
    }

    Object dequeue() {
        Object result = head.value;
        if (size > 0) {
            head = head.prev;
            size--;
        }
        return result;
    }

    Object peek() {
        return head.value;
    }

    void removeAll(Object value) {
        if (size > 0) {
            Node temp = head;
            Node next = null;
            while (temp != null) {
                if (temp.value.equals(value)) { // check node and delete
                    if (temp == head) {
                        head = temp.prev; // delete first node
                    } else {
                        next.prev = temp.prev;  // delete 2nd+ node
                    }
                    size--;
                } else {
                    next = temp;
                }
                temp = temp.prev; // move to the next node
            }
        }
    }

    private class Node {
        Object value;
        Node prev;

        Node(Object value) {
            this.value = value;
        }
    }
}