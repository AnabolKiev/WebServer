package com.anabol.collections;

import java.util.Iterator;

public class HashMap<K, V> implements Map<K, V> {
    private static final int INITIAL_BUCKETS_SIZE = 2;
    private static final double LOAD_FACTOR = 0.75;
    private static final int BUCKETS_INCREASE = 2;

    private int size;
    private List<Entry<K, V>>[] buckets;

    HashMap() {
        buckets = new ArrayList[INITIAL_BUCKETS_SIZE];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    @Override
    public V put(K key, V value) {
        checkAndExtend();
        List<Entry<K, V>> list = getBucket(key);
        int i = list.indexOf(new Entry<>(key, null));
        if (i > -1) {    // key was found
            Entry<K, V> entry = list.set(new Entry<>(key, value), i);
            return entry.value;
        } else {         // key not found
            checkAndExtend();
            list.add(new Entry<>(key, value));
            size++;
        }
        return null;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        checkAndExtend();
        List<Entry<K, V>> list = getBucket(key);
        int i = list.indexOf(new Entry<>(key, null));
        if (i > -1) {    // key was found
            Entry<K, V> entry = list.get(i);
            return entry.value;
        } else {         // key not found
            checkAndExtend();
            list.add(new Entry<>(key, value));
            size++;
        }
        return null;
    }

    @Override
    public void putAll(Map<K, V> map) {
        for (Entry<K, V> entry : map) {
            put(entry.key, entry.value);
        }
    }

    @Override
    public void putAllIfAbsent(Map<K, V> map) {
        for (Entry<K, V> entry : map) {
            putIfAbsent(entry.key, entry.value);
        }

    }

    @Override
    public V get(K key) {
        List<Entry<K, V>> list = getBucket(key);
        int i = list.indexOf(new Entry<>(key, null));
        if (i > -1) {
            Entry<K, V> result = list.get(i);
            return result.value;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        List<Entry<K, V>> list = getBucket(key);
        int i = list.indexOf(new Entry<>(key, null));
        if (i > -1) { // found
            Entry<K, V> result = list.remove(i);
            size--;
            return result.value;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return getBucket(key).contains(new Entry<>(key, null));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new EntryIterator();
    }

    private class EntryIterator implements Iterator<Entry<K, V>> {
        int bucketIndex;
        Iterator<Entry<K, V>> listIterator = buckets[0].iterator();

        @Override
        public boolean hasNext() {
            return (bucketIndex != buckets.length - 1) || (listIterator.hasNext());
        }

        @Override
        public Entry<K, V> next() {
            while (hasNext()) {
                if (listIterator.hasNext()) {
                    return listIterator.next();
                } else {
                    listIterator = buckets[++bucketIndex].iterator();
                }
            }
            return null;
        }
    }

    // if size > buckets.length * 0.75 (load factor) ->
    // new buckets.length = buckets.length * 2
    private void checkAndExtend() {
        if (size >= buckets.length * LOAD_FACTOR) {  // create bigger structure
            int newLength = buckets.length * BUCKETS_INCREASE;
            List<Entry<K, V>>[] newBuckets = new ArrayList[(newLength)];
            for (int i = 0; i < newLength; i++) { // initialize
                newBuckets[i] = new ArrayList<Entry<K, V>>();
            }
            for (List<Entry<K, V>> bucketList : buckets) { // copy all elements to the new structure
                for (Entry<K, V> entry : bucketList
                        ) {
                    int bucketIndex = entry.key.hashCode() % newLength;
                    newBuckets[bucketIndex].add(entry);
                }
            }
            buckets = newBuckets;
        }
    }

    private List<Entry<K, V>> getBucket(K key) {
        int bucketIndex = Math.abs(key.hashCode() % buckets.length);
        return buckets[bucketIndex];
    }

    static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entry)) return false;
            Entry<K, V> entry = (Entry<K, V>) o;
            return key != null ? key.equals(entry.key) : entry.key == null;

        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

}
