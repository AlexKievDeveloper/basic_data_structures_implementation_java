package com.glushkov.map;

import com.glushkov.list.List;

public interface Map<K, V> {
    V put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    V putIfAbsent(K key, V value);

    void putAll(Map<K, V> hashMap);

    List<K> keys();

    List<V> values();

    boolean isEmpty();

    int size();

    void clear();
}
