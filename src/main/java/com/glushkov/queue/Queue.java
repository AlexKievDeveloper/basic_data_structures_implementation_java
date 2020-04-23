package com.glushkov.queue;

import java.util.NoSuchElementException;

public interface Queue<T> extends Iterable<T> {
    boolean add(T e);

    boolean offer(T e);

    T remove() throws LinkedQueue.NoSuchElementException;

    T poll();

    T element() throws LinkedQueue.NoSuchElementException;

    T peek();
}
