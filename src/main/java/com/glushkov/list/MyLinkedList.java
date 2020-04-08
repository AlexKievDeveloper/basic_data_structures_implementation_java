package com.glushkov.list;

import java.util.Iterator;

public class MyLinkedList<T> implements List<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    public MyLinkedList() {
    }

    @Override
    public void add(T value) {
        checkValue(value);
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        validateIndex(index);
        Node<T> node = new Node<>(value);

        if (size == 0) {
            first = last = node;
        } else if (index == 0) {
            first.prev = node;
            node.next = first;
            first = node;
        } else if (size == index) {
            node.prev = last;
            last.next = node;
            last = node;
        } else {
            Node<T> temp = getNode(index);
            temp.prev.next = node;
            node.prev = temp.prev;
            node.next = temp;
            temp.prev = node;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        Node<T> result;

        Node<T> temp = getNode(index);
        result = temp;
        if (size == 1) {
            first = last = null;
        } else if (index == 0) {
            first.next.prev = null;
            first = first.next;
        } else if (index == size - 1) {
            last = temp.prev;
            last.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
        size--;

        return result.value;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return getNode(index).value;
    }


    @Override
    public T set(T value, int index) {
        checkValue(value);
        validateIndex(index);
        Node<T> node = getNode(index);
        node.value = value;
        return node.value;
    }

    @Override
    public void clear() {
        last = first = null;
        size = 0;
    }

    @Override
    public boolean contains(T value) {
        checkValue(value);
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(T value) {
        checkValue(value);
        Node<T> temp = first;
        for (int i = 0; i < size; i++) {
            if (temp.value.equals(value)) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        checkValue(value);
        Node<T> temp = last;
        for (int i = size - 1; i >= 0; i--) {
            if (temp.value.equals(value)) {
                return i;
            }
            temp = temp.prev;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Allowed indexes between 0 and " + size +
                    " inclusive, but index was: " + index);
        }
    }

    private void checkValue(T value) {
        if (value == null) {
            throw new NullPointerException("Null value is not allowed");
        }
    }

    private Node<T> getNode(int index) {
        Node<T> node = first;
        if (index < size / 2) {
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else if (index >= size / 2) {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    @Override
    public String toString() {
        Node<T> node = first;
        StringBuilder s = new StringBuilder();
        s.append(node.value);

        for (int i = 0; i < size - 1; i++) {
            node = node.next;
            if (s.length() > 0) s.append(", ");
            s.append(node.value);
        }
        return "размер: " + size + ", значения: " + s;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = first;
            private int index;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T value = current.value;
                current = current.next;
                return value;
            }

            @Override
            public void remove() {
                MyLinkedList.this.remove(index);
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }
}
