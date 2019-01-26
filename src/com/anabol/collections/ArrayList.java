package com.anabol.collections;

import java.util.Iterator;
import java.util.StringJoiner;

public class ArrayList<T> implements List<T> {
    private Object[] array;
    private int size;

    ArrayList() {
        this(10);
    }

    ArrayList(int initialCapacity) {
        array = new Object[initialCapacity];
    }

    @Override
    public void add(T value) {
        checkAndExtend();
        array[size] = value;
        size++;
    }

    // we can add value by index between [0, size]
    // otherwise throw new IndexOutOfBoundsException
    // [A, B, C] . add("D", [0, 1, 2, 3])
    @Override
    public void add(T value, int index) {
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException("index should be in [0, " + size + "]");
        } else {
            checkAndExtend();
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        }
    }

    // we can remove value by index between [0, size - 1]
    // otherwise throw new IndexOutOfBoundsException
    // [A, B, C] remove = 0
    // [B (index = 0) , C (index = 1)]
    @Override
    public T remove(int index) {
        T result;
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("index should be in [0, " + (size - 1) + "]");
        } else {
            result = (T) array[index];
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
            size--;
            array[size] = null; // to avoid memory leak
        }
        return result;
    }

    // [A, B, C] size = 3
    // we can get value by index between [0, size - 1]
    // otherwise throw new IndexOutOfBoundsException
    @Override
    public T get(int index) {
        T result;
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("index should be in [0, " + (size - 1) + "]");
        } else {
            result = (T) array[index];
        }
        return result;
    }

    // we can set value by index between [0, size - 1]
    // otherwise throw new IndexOutOfBoundsException
    @Override
    public T set(T value, int index) {
        T result = (T) array[index];
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("index should be in [0, " + (size - 1) + "]");
        } else {
            array[index] = value;
        }
        return result;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    // [A, B, C]
    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        for (int i = 0; i < size; i++) {
            stringJoiner.add(String.valueOf(array[i]));
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        int position;

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public T next() {
            return get(position++);
        }
    }

    // in case of end of space, please grow in 1.5 times
    private void checkAndExtend() {
        if (size >= array.length) {
            Object[] newArray = new Object[(int) (array.length * 1.5) + 1];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

}