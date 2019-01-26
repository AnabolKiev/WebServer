package com.anabol.collections;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.notification.RunListener;

public class HashMapTest {

    @Test
    public void simpleTest() {
        HashMap<String, String> hashMap = new HashMap<>();
        assertEquals(0, hashMap.size());
    }

    @Test
    public void getTest() {
        HashMap<String, String> hashMap = new HashMap<>();
        assertNull(hashMap.get("A"));
        hashMap.put("User", "user1");
        assertEquals("user1", hashMap.get("User"));
    }

    @Test
    public void putTest() {
        HashMap<String, String> hashMap = new HashMap<>();
        assertNull(hashMap.put("User", "user1"));
        hashMap.put("Password", "12345");
        hashMap.put("Email", "user1@mail.com");
        assertEquals(3, hashMap.size());
        assertEquals("user1", hashMap.put("User", "user2"));
        assertEquals("user2", hashMap.get("User"));
    }

    @Test
    public void putIfAbsentTest() {
        HashMap<String, String> hashMap = new HashMap<>();
        assertNull(hashMap.putIfAbsent("User", "user1"));
        assertEquals(1, hashMap.size());
        assertEquals("user1", hashMap.putIfAbsent("User", "user2"));
        assertEquals("user1", hashMap.get("User"));
    }

    @Test
    public void putAllTest() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("User", "user1");
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("User", "user2");
        hashMap2.put("Password", "12345");
        hashMap2.put("Email", "user@mail.com");
        hashMap.putAll(hashMap2);
        assertEquals("user2", hashMap.get("User"));
        assertEquals(3, hashMap.size());
    }

    @Test
    public void putAllIfAbsentTest() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("User", "user1");
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("User", "user2");
        hashMap2.put("Password", "12345");
        hashMap2.put("Email", "user@mail.com");
        hashMap.putAllIfAbsent(hashMap2);
        assertEquals("user1", hashMap.get("User"));
        assertEquals(3, hashMap.size());
    }

    @Test
    public void containsKeyTest() {
        HashMap<String, String> hashMap = new HashMap<>();
        assertFalse(hashMap.containsKey("User"));
        hashMap.put("User", "user1");
        assertTrue(hashMap.containsKey("User"));
    }

    @Test
    public void removeTest() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("User", "user1");
        assertEquals("user1", hashMap.remove("User"));
        assertEquals(0, hashMap.size());
    }
}
