package com.glushkov;

public interface List<T> extends Iterable<T> {

    void add(T value);

    void addValue(T value, int index);

    void remove(int index);

    Object get(int index);

    void set(T value, int index);

    void clear();

    boolean contains(T object);

    int indexOf(T value);

    int lastIndexOf(T value);

    int size();

    boolean isEmpty();

    String toString();
}
