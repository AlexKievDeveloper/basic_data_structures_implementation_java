package com.glushkov.map;

public interface Map<K, V> {
    V put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    V putIfAbsent(K key, V value);

    boolean isEmpty();

    int size();

    void clear();
}
