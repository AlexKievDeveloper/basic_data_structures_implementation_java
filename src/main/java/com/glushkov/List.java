package com.glushkov;

public interface List<T> extends Iterable<T> {

    void add(T value);

    void add(T value, int index);

    T remove(int index);

    T get(int index);

    T set(T value, int index);

    void clear();

    boolean contains(T object);

    int indexOf(T value);

    int lastIndexOf(T value);

    int size();

    boolean isEmpty();

    String toString();
}
