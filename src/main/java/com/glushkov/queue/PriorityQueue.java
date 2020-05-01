package com.glushkov.queue;

import java.util.Arrays;
import java.util.Iterator;

public class PriorityQueue<T> implements Queue<T> {

    T[] array;
    private int size;

    public PriorityQueue() {
        array = (T[]) new Object[11];
    }

    @Override
    public boolean add(T e) {
        return offer(e);
    }

    @Override
    public boolean offer(T e) {
        if (e == null){
            throw new NullPointerException();
        }
        if (size < array.length){
        if (size != 0) {
            System.arraycopy(array, 0, array, 1, size);
        }
        array[0] = e;
        Arrays.sort(array);
        size++;
        return true;
    }
        return false;
    }

    @Override
    public T remove() throws LinkedQueue.NoSuchElementException {
        if (size < array.length){
            T temp = array[0];
            System.arraycopy(array, 1, array, 0, size-1);
            size--;
            return temp;
        }
        else throw new LinkedQueue.NoSuchElementException("Queue is empty. Please add elements");
    }

    @Override
    public T poll() {
        if (size < array.length){
            T temp = array[0];
            System.arraycopy(array, 1, array, 0, size-1);
            size--;
            return temp;
        }
        return null;
    }

    @Override
    public T element() throws LinkedQueue.NoSuchElementException {
        return null;
    }

    @Override
    public T peek() {
        if (size > 0) {
            return array[0];
        }
        return null;
    }

    @Override
    public Iterator iterator() {
        return new Iterator<T>() {

            private int index;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return array[index++];
            }

            @Override
            public void remove() {
                if (size < array.length){
                    T temp = array[0];
                    System.arraycopy(array, 1, array, 0, size-1);
                    size--;
                }
            }
        };
    }
}
