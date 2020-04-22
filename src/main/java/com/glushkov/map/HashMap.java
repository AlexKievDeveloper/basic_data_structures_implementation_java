package com.glushkov.map;

import com.glushkov.list.List;
import com.glushkov.list.MyArrayList;

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

        if (buckets.length * 0.75 < size) {
            increaseNumberOfBuckets();
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
        if (size == 0) {
            for (int i = 0; i < INITIAL_CAPACITY; i++) {
                buckets[i] = new ArrayList<>();
            }
        }

        V oldValue = null;
        int index = key.hashCode() % buckets.length;
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.hashCode() == key.hashCode()) {
                if (entry.key.equals(key)) {
                    oldValue = entry.value;
                }
            }
        }

        if (oldValue == null) {
            put(key, value);
        }

        if (buckets.length * 0.75 < size) {
            increaseNumberOfBuckets();
        }

        return oldValue;
    }

    @Override
    public void putAll(Map<K, V> hashMap) {
        hashMap.keys();
        for (int i = 0; i < hashMap.size(); i++) {
            this.put(hashMap.keys().get(i), hashMap.values().get(i));
        }
    }

    @Override
    public List<K> keys() {
        List<K> listOfKeys = new MyArrayList<>();

        for (ArrayList<Entry<K, V>> list : buckets) {
            for (Entry<K, V> kvEntry : list) {
                listOfKeys.add(kvEntry.key);
            }
        }
        return listOfKeys;
    }

    @Override
    public List<V> values() {
        List<V> listOfValues = new MyArrayList<>();

        for (ArrayList<Entry<K, V>> list : buckets) {
            for (Entry<K, V> entry : list) {
                listOfValues.add(entry.value);
            }
        }
        return listOfValues;
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

    private void increaseNumberOfBuckets() {
        ArrayList<Entry<K, V>>[] tempArray = buckets.clone();
        buckets = new ArrayList[buckets.length * 2];

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (ArrayList<Entry<K, V>> entries : tempArray) {
            for (Entry<K, V> entry : entries) {
                int index = entry.key.hashCode() % buckets.length;
                buckets[index].add(entry);
            }
        }
    }

    Integer getBucketLength() {
        return buckets.length;
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




/*    public void printAllEntries() {
        for (ArrayList<Entry<K, V>> list : buckets) {
            for (int i = 0; i < list.size(); i++) {
                if (list.size() > 1) {
                    System.out.println();
                    System.out.println("Attention! Collision! Bucket have more than one element!");
                    System.out.println("buckets.length = " + buckets.length * 0.75 + "  vs  " + "size: " + size);
                }
                System.out.println(list);
                System.out.println(list.get(i).key + " " + list.get(i).value);
            }
        }
    }*/