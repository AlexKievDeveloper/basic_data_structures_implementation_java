package com.glushkov;

public class MyArrayList<T> implements List<T> {

    Object[] list;
    private int size;
    private int index = 0;

    public MyArrayList() {
        list = new Object[10];
    }

    @Override
    public void add(Object value) {
        addValue(value, size);
    }

    @Override
    public void addValue(Object value, int index) {
        if ((index >= 0) && (index <= size)) {
            if (size >= list.length) {
                Object[] temp = new Object[size * 3 / 2 + 1];
                System.arraycopy(list, 0, temp, 0, list.length);
                list = temp;
            }
            System.arraycopy(list, index, list, index + 1, size - index);
            list[index] = value;
            size++;
        }
    }


    @Override
    public void remove(int index) {
        if ((index >= 0) && (index < size)) {
            Object[] temp = new Object[size - 1];
            System.arraycopy(list, 0, temp, 0, index);
            System.arraycopy(list, index + 1, temp, index, size - index - 1);
            list = temp;
            size--;
        }
    }

    @Override
    public Object get(int index) {
        if ((index < 0) || (index > size)) {
            return -1;
        }
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
    public void set(Object value, int index) {
        if ((index >= 0) && (index < size)) {
            list[index] = value;
        }
    }

    @Override
    public void clear() {
        list = new Object[10];
        size = 0;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    @Override
    public int indexOf(Object value) {

        for (int i = 0; i < size; i++)
            if (list[i].equals(value)) {
                return i;
            }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {

        for (int i = size - 1; 0 <= i; i--)
            if (list[i].equals(value)) {
                return i;
            }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(list[i]).append(", ");
        }
        return "размер: " + size + ", значения: " + s;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return index < list.length;
            }

            @Override
            public Object next() {
                if (index < size) {
                    return list[index++];
                }
                return -1;
            }

            @Override
            public void remove() {
                if ((index >= 0) && (index < size)) {
                    Object[] temp = new Object[size - 1];
                    System.arraycopy(list, 0, temp, 0, index);
                    System.arraycopy(list, index + 1, temp, index, size - index - 1);
                    list = temp;
                    size--;
                }
            }
        };
    }
}
