package com.anabol.collections;

import java.util.Iterator;
import java.util.StringJoiner;

public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    // add value to the end of the list
    @Override
    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // we can add value by index between [0, size]
    // otherwise throw new IndexOutOfBoundsException
    // [A, B, C] . add("D", [0, 1, 2, 3])
    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (index == size) {
            add(value);
        } else {
            Node<T> newNode = new Node<>(value);
            Node<T> temp = getNode(index);
            newNode.next = temp;
            if (index == 0) {
                head = newNode;
            } else {
                newNode.prev = temp.prev;
                newNode.prev.next = newNode;
            }
            temp.prev = newNode;
            size++;
        }
    }

    // we can remove value by index between [0, size - 1]
    // otherwise throw new IndexOutOfBoundsException
    // [A, B, C] remove = 0
    // [B (index = 0) , C (index = 1)]
    @Override
    public T remove(int index) {
        Node<T> temp = getNode(index);
        if (index == 0) {
            head = temp.next;
        } else {
            temp.prev.next = temp.next;
        }
        if (index == size - 1) {
            tail = temp.prev;
        } else {
            temp.next.prev = temp.prev;
        }
        size--;
        return temp.value;
    }

    // [A, B, C] size = 3
    // we can get value by index between [0, size - 1]
    // otherwise throw new IndexOutOfBoundsException
    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    // we can set value by index between [0, size - 1]
    // otherwise throw new IndexOutOfBoundsException
    @Override
    public T set(T value, int index) {
        Node<T> temp = getNode(index);
        T result = temp.value;
        temp.value = value;
        return result;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(T value) {
        return (indexOf(value) > -1);
    }

    @Override
    public int indexOf(T value) {
        Node<T> temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.value.equals(value)) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        Node<T> temp = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (temp.value.equals(value)) {
                return i;
            }
            temp = temp.prev;
        }
        return -1;
    }

    // [A, B, C]
    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        Node<T> temp = head;
        for (int i = 0; i < size; i++) {
            stringJoiner.add(String.valueOf(temp.value));
            temp = temp.next;
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> position = head;

        @Override
        public boolean hasNext() {
            return position != null;
        }

        @Override
        public T next() {
            T value = position.value;
            position = position.next;
            return value;
        }
    }

    private void checkIndex(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("index should be in [0, " + size + ")");
        }
    }

    private void checkIndexForAdd(int index) {
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException("index should be in [0, " + size + "]");
        }
    }

    private Node<T> getNode(int index) {
        checkIndex(index);
        Node<T> temp;
        if (index <= size / 2) {
            temp = head;
            for (int i = 1; i <= index; i++) {
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i = size - 1; i > index; i--) {
                temp = temp.prev;
            }
        }
        return temp;
    }

    private static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }
}