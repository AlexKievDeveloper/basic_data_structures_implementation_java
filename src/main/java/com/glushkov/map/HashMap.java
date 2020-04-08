package com.glushkov.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    private static final int INITIAL_CAPACITY = 5;
    private ArrayList<Entry<K, V>>[] buckets = new ArrayList[INITIAL_CAPACITY];
    private int size;

    @Override
    public V put(K key, V value) {
        if (size == 0) {
            for (int i = 0; i < INITIAL_CAPACITY; i++) {
                buckets[i] = new ArrayList<>();
            }
        }

        V oldValue = null;
        boolean update = false;
        int index = key.hashCode() % buckets.length;

        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.hashCode() == key.hashCode()) {
                if (entry.key.equals(key)) {
                    oldValue = entry.value;
                    entry.value = value;
                    update = true;
                }
            }
        }
        if (!update) {
            buckets[index].add(new Entry<>(key, value));
            size++;
        }

        return oldValue;
    }

    @Override
    public V get(K key) {

        int index = key.hashCode() % buckets.length;
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.hashCode() == (key.hashCode())) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        if (size == 0) {
            for (int i = 0; i < INITIAL_CAPACITY; i++) {
                buckets[i] = new ArrayList<>();
            }
        }
        V elemValue = null;
        Entry<K, V> tempEntry = null;
        int index = key.hashCode() % buckets.length;
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.hashCode() == key.hashCode()) {
                if (entry.key.equals(key)) {
                    elemValue = entry.value;
                    tempEntry = entry;
                }
            }
        }

        if (elemValue != null) {
            buckets[index].remove(tempEntry);
            size--;
        }
        return elemValue;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        V oldValue = null;
        int index = key.hashCode() % buckets.length;
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.hashCode() == key.hashCode()) {
                if (entry.key.equals(key)) {
                    oldValue = entry.value;
                }
            }
        }
        if (oldValue == null){
            put(key, value);
        }
        return oldValue;
    }


    @Override
    public boolean containsKey(K key) {
        int index = key.hashCode() % buckets.length;
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.hashCode() == (key.hashCode())) {
                if (entry.key.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(buckets, null);
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void printAllBuckets() {
        for (ArrayList<Entry<K, V>> entry : buckets) {
            System.out.println(entry);
        }
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || Entry.class != o.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return key.equals(entry.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}




