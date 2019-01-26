package com.anabol.collections;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public abstract class AbstractListTest {

    protected abstract List<String> getList();

    @Test
    public void testAddAndRemove() {
        List<String> list = getList();
        assertEquals(0, list.size());
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals(3, list.size());
        assertEquals("B", list.remove(1));
        assertEquals(2, list.size());
    }

    @Test
    public void testAddByIndex() {
        List<String> list = getList();
        list.add("A", 0);
        list.add("B", 1);
        list.add("C", 0);
        assertEquals(3, list.size());
        assertEquals("B", list.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddWrongIndex() {
        List<String> list = getList();
        list.add("A", 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddWrongIndex2() {
        List<String> list = getList();
        list.add("A", 0);
        list.add("B", -1);
    }

    @Test
    public void testRemove() {
        List<String> list = getList();
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("C", list.remove(2));
        assertEquals(2, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveWrongIndex() {
        List<String> list = getList();
        list.add("A");
        list.remove(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveWrongIndex2() {
        List<String> list = getList();
        list.add("A");
        list.remove(-1);
    }

    @Test
    public void testGet() {
        List<String> list = getList();
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("B", list.get(1));
        assertEquals(3, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetWrongIndex() {
        List<String> list = getList();
        list.add("A");
        list.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetWrongIndex2() {
        List<String> list = getList();
        list.add("A");
        list.get(-1);
    }

    @Test
    public void testSet() {
        List<String> list = getList();
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("C", list.set("D", 2));
        assertEquals(3, list.size());
        assertEquals("D", list.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetWrongIndex() {
        List<String> list = getList();
        list.add("A");
        list.set("B", 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetWrongIndex2() {
        List<String> list = getList();
        list.add("A");
        list.set("B", -1);
    }

    //    void clear();
    @Test
    public void testClearAndIsEmpty() {
        List<String> list = getList();
        list.add("A");
        list.add("B");
        list.add("C");
        assertFalse(list.isEmpty());
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testSize() {
        List<String> list = getList();
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals(3, list.size());
    }

    @Test
    public void testContains() {
        List<String> list = getList();
        list.add("A");
        list.add("B");
        list.add("C");
        assertTrue(list.contains("A"));
        assertFalse(list.contains("D"));
    }

    @Test
    public void testIndexOf() {
        List<String> list = getList();
        list.add("A");
        list.add("B");
        list.add("B");
        assertEquals(1, list.indexOf("B"));
        assertEquals(-1, list.indexOf("C"));
    }

    @Test
    public void testLastIndexOf() {
        List<String> list = getList();
        list.add("A");
        list.add("B");
        list.add("B");
        assertEquals(2, list.lastIndexOf("B"));
        assertEquals(-1, list.lastIndexOf("C"));
    }

    @Test
    public void testToString() {
        List<String> list = getList();
        assertEquals("[]", list.toString());
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("[A,B,C]", list.toString());
    }

    @Test
    public void testIteratorEmpty() {
        List<String> list = getList();
        assertFalse(list.iterator().hasNext());
    }

    @Test
    public void testIterator() {
        List<String> list = getList();
        list.add("A");
        list.add("B");
        list.add("C");
        Iterator iterator = list.iterator();
        assertEquals("A", iterator.next());
        assertEquals("B", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("C", iterator.next());
        assertFalse(iterator.hasNext());
    }
}
