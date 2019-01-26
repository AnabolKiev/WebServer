package com.anabol.collections;

// if size > buckets.length * 0.75 (load factor) ->
// new buckets.length = buckets.length * 2
// Iterable <Entry>, next on iterator should return Entry
public interface Map<K, V> extends Iterable<HashMap.Entry<K, V>> {
    V put(K key, V value);

    V putIfAbsent(K key, V value);

    void putAll(Map<K, V> map);

    void putAllIfAbsent(Map<K, V> map);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    int size();
}
