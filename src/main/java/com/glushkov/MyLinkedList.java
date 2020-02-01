package com.glushkov;

public class MyLinkedList<T> implements List<T>, MyIterator{

    private Node first;
    private Node last;
    private int size = 0;

    public MyLinkedList() {
    }


    @Override
    public void add(Object value) {
        if (size == 0) {
            last = first = new Node(null, value, null);
        }
        Node node = new Node(null, value, null);
        last.next = node;
        node.prev = last;
        last = node;
        size++;
    }

    @Override
    public void addValue(Object value, int index) {
        if ((index >= 0) && (index <= size)) {
            Node temp = first;
            if (size == 0) {
                add(value);
            }
            else if (index < size/2) {
                for (int i = 0; i <= index; i++) {
                    temp = temp.next;
                }
            }
            else {
                temp = last;
                for (int i = size; i > index+1  ; i--) {
                    temp = temp.prev;
                }
            }
            Node node = new Node(null, value, null);
            temp.prev.next = node;
            node.prev = temp.prev;
            node.next = temp;
            temp.prev = node;
            size++;
        }
    }

    @Override
    public boolean hasNext() {
        return first.next != null;
    }

    @Override
    public T next() {
        if (first.next != null) {
            this.first = this.first.next;
            return (T) first.current;
        }
        return null;
    }

    @Override
    public void remove(Object object) {

    }

    @Override
    public void remove(int index) {
        if ((index >= 0) && (index <= size)) {
            Node temp = first;
            if (index < size/2) {
                for (int i = 0; i <= index; i++) {
                    temp = temp.next;
                }
            }
            else {
                temp = last;
                for (int i = size; i > index+1 ; i--) {
                    temp = temp.prev;
                }
            }
            if (index != size - 1) {
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
            }
            else {
                temp.prev.next = null;
                temp.prev = temp;
            }
            size--;
        }
    }

    @Override
    public Object get(int index) {
        Node node = first;
        if ((index < 0) || (index > size)) {
            return -1;
        }
        else if (index < size / 2) {
            for (int i = 0; i <= index; i++) {
                node = node.next;
            }
        }
        else {
            node = last;
            for (int i = size; i > index+1; i--) {
                node = node.prev;
            }
        }
        return node.current;
    }


    @Override
    public void set(Object value, int index) {
        Node node = first;
        if (index < size/2) {
            for (int i = 0; i <= index; i++) {
                node = node.next;
            }
        }
        else {
            node = last;
            for (int i = size; i > index+1; i--) {
                node=node.prev;
            }
        }
        node.current = value;
    }


    @Override
    public void clear() {
        last = first = null;
        size = 0;
    }

    @Override
    public boolean contains(Object object) {
        boolean isTrue = false;
        Node temp = first;
        for (int i = 0; i < size; i++) {
            temp = temp.next;

            if (temp.current.equals(object)) {
                isTrue = true;
                break;
            }
        }
        return isTrue;
    }

    @Override
    public int indexOf(Object value) {
        Node temp = first;
        for (int i = 0; i <= size; i++) {
            temp = temp.next;
            if (temp.current.equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        Node temp = last;
        if (temp.current != value) {
            for (int i = size-2; i >= 0; i--) {
                temp = temp.prev;
                if (temp.current.equals(value)) {
                    return i;
                }
            }
        }
        else return size-1;

        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size < 1;
    }

    @Override
    public String toString() {
        Node node = first;
        String s = "";
        for (int i = 0; i < size; i++) {
            node = node.next;
            s += node.current + ", ";
        }
        return "размер: " + size + ", значения: " + s;
    }


    private static class Node {
        Object current;
        Node prev;
        Node next;

        Node(Node prev, Object current, Node next) {
            this.current = current;
            this.prev = prev;
            this.next = next;
        }
    }
}
