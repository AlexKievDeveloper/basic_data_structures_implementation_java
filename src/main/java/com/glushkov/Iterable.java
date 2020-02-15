package com.glushkov;

public interface Iterable<T> {

    interface Iterator<T> {
        boolean hasNext();

        Object next();

        void remove();
    }

    Iterator<T> iterator();
}
