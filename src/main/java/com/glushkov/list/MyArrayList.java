package com.glushkov.list;

import java.util.Iterator;

public class MyArrayList<T> implements List<T> {

    T[] list;
    private int size;

    public MyArrayList() {
        list = (T[]) new Object[10];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Allowed indexes between 0 and " + size +
                    " inclusive, but index was: " + index);
        }
        ensureCapacityAndGrow();
        checkValue(value);
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
        size++;
    }


    @Override
    public T remove(int index) {
        validateIndex(index);
        T result = list[index];

        if (index != size - 1) {
            System.arraycopy(list, index + 1, list, index, size - index - 1);
        }
        list[size - 1] = null;
        size--;
        return result;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return list[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T set(T value, int index) {
        validateIndex(index);
        checkValue(value);
        T result =  list[index];
        list[index] = value;
        return result;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            list[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(T value) {
        checkValue(value);
        for (int i = 0; i < size; i++)
            if (list[i].equals(value)) {
                return i;
            }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        checkValue(value);
        for (int i = size - 1; i >= 0; i--)
            if (list[i].equals(value)) {
                return i;
            }
        return -1;
    }

    private void ensureCapacityAndGrow() {
        if (size == list.length) {
            T[] temp = (T[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(list, 0, temp, 0, size);
            list = temp;
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Allowed indexes between 0 and " + size +
                    " inclusive, but index was: " + index);
        }
    }

    private void checkValue(T value) {
        if (value == null) {
            throw new NullPointerException("Null value is not allowed");
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        /*for (T x: list)*/
        for (int i = 0; i < size; i++) {
            if (s.length() > 0) s.append(", ");
            s.append(list[i]);
        }
        return "размер: " + size + ", значения: " + s;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int index;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return list[index++];
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(index);
            }
        };
    }
}

