package com.glushkov.queue;

import com.glushkov.list.MyArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T> {
/*    ArrayDeque() - создает пустую двунаправленную очередь с вместимостью 16 элементов.
            ArrayDeque(Collection<? extends E> c) - создает двунаправленную очередь из элементов коллекции
            c в том порядке, в котором они возвращаются итератором коллекции c.
    ArrayDeque(int numElements) - создает пустую двунаправленную очередь с вместимостью numElements.*/

    T[] array;
    private int size;

    public int getSize(){
        return size;
    }

    public ArrayDeque() {
        array = (T[]) new Object[16];
    }

    public ArrayDeque(int numElements) {
        array = (T[]) new Object[numElements];
    }

    @Override
    public void addFirst(T obj) {
        if (size < array.length){
            if (size != 0) {
                System.arraycopy(array, 0, array, 1, size);
            }
            array[0] = obj;
            size++;
        }
        else {
            throw new IllegalStateException();
        }
/*        void addFirst(Е obj)  - добавляет obj в голову двунаправленной очереди.
                Возбуждает исключение IllegalStateException, если в очереди ограниченной емкости нет места.*/
    }

    @Override
    public void addLast(T obj) {
        if (size < array.length){
            if (size == 0){
                array[0] = obj;
            }
            else {
                array[size] = obj;
            }
            size++;
        }
        else {
            throw new IllegalStateException();
        }
/*        void addLast(Е obj) - добавляет obj в хвост двунаправленной очереди.
        Возбуждает исключение IllegalStateException, если в очереди ограниченной емкости нет места.*/
    }

    @Override
    public T getFirst() throws LinkedQueue.NoSuchElementException {
        if (size > 0){
            return array[0];
        }
        else throw new LinkedQueue.NoSuchElementException("Queue is empty. Please add elements");
    }

    @Override
    public T getLast() throws LinkedQueue.NoSuchElementException {
        if (size > 0){
            return array[size-1];
        }
        else throw new LinkedQueue.NoSuchElementException("Queue is empty. Please add elements");
    }

    @Override
    public boolean offerFirst(T obj) {
        if (size < array.length){
            if (size != 0) {
                System.arraycopy(array, 0, array, 1, size);
            }
            array[0] = obj;
            size++;
            return true;
        }
        return false;


/*      пытается добавить obj в голову двунаправленной очереди. Возвращает true, если obj добавлен,
        и false в противном случае. Таким образом, этот метод возвращает false при попытке добавить obj в
        полную двунаправленную очередь ограниченной емкости.*/
    }

    @Override
    public boolean offerLast(T obj) {
        if (size < array.length){
            if (size == 0){
                array[0] = obj;
            }
            else {
                array[size] = obj;
            }
            size++;
            return true;
        }
        return false;
    }

    @Override
    public T pop() throws LinkedQueue.NoSuchElementException {
        if (size < array.length){
            T temp = array[0];
            System.arraycopy(array, 1, array, 0, size-1);
            size--;
            return temp;
        }
        else throw new LinkedQueue.NoSuchElementException("Queue is empty. Please add elements");
/*      Е рор() - возвращает элемент, находящийся в голове двунаправленной очереди,
        одновременно удаляя его из очереди. Возбуждает исключение NoSuchElementException, если очередь пуста.*/
    }

    @Override
    public void push(T obj) {
        if (size < array.length){
            if (size != 0) {
                System.arraycopy(array, 0, array, 1, size);
            }
            array[0] = obj;
            size++;
        }
        else {
            throw new IllegalStateException();
        }
    }

    @Override
    public T peekFirst() {
        if (size > 0){
            return array[0];
        }
        return null;
    }

    @Override
    public T peekLast() {
        if (size > 0){
            return array[size-1];
        }
        return null;
    }

    @Override
    public T pollFirst() {
        if (size < array.length){
            T temp = array[0];
            System.arraycopy(array, 1, array, 0, size-1);
            size--;
            return temp;
        }
        return null;
    }

    @Override
    public T pollLast() {
        if (size < array.length){
            T temp = array[size-1];
            array[size-1] = null;
            size--;
            return temp;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if (size < array.length){
            T temp = array[size-1];
            array[size-1] = null;
            size--;
            return temp;
        }
        else throw new NoSuchElementException("Queue is empty. Please add elements");
    }

    @Override
    public T removeFirst() {
        if (size < array.length){
            T temp = array[0];
            System.arraycopy(array, 1, array, 0, size-1);
            size--;
            return temp;
        }
        else throw new NoSuchElementException("Queue is empty. Please add elements");
    }

    @Override
    public boolean removeLastOccurrence(Object obj) {
        for (int i = size-1; i > 0; i--) {
            if (array[i].equals(obj)){
                if (i != size - 1) {
                    System.arraycopy(array, i + 1, array, i, size - i - 1);
                }
                array[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

/*    boolean removeLastOccurrence(Object obj) - удаляет последнее вхождение obj из двунаправленной очереди.
        Возвращает true в случае успеха и false если очередь не содержала obj.
    boolean removeFirstOccurrence(Object obj) - удаляет первое вхождение obj из двунаправленной очереди.
        Возвращает true в случае успеха и false, если очередь не содержала obj.*/

    @Override
    public boolean removeFirstOccurrence(Object obj) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(obj)){
                if (i != size - 1) {
                    System.arraycopy(array, i + 1, array, i, size - i - 1);
                }
                array[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(T e) {
        if (size < array.length){
            if (size != 0) {
                System.arraycopy(array, 0, array, 1, size);
            }
            array[0] = e;
            size++;
            return true;
        }
        return false;
    }


    @Override
    public boolean offer(T e) {
        if (size < array.length){
            if (size != 0) {
                System.arraycopy(array, 0, array, 1, size);
            }
            array[0] = e;
            size++;
            return true;
        }
        return false;
    }
    /* boolean offer(Е оbj) - пытается добавить оbj в очередь. Возвращает true, если оbj добавлен, и false в противном случае.*/
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
    /*
    Е remove() - удаляет элемент из головы очереди, возвращая его. Инициирует исключение NoSuchElementException,
    если очередь пуста.
*/

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
/*    Е роll() - возвращает элемент из головы очереди и удаляет его. Возвращает null, если очередь пуста.*/
    @Override
    public T element() throws LinkedQueue.NoSuchElementException {
        if (size > 0){
            return array[0];
        }
        else throw new LinkedQueue.NoSuchElementException("Queue is empty. Please add elements");
    }

/*    Е element() - возвращает элемент из головы очереди. Элемент нe удаляется. Если очередь пуста,
    инициируется исключение NoSuchElementException.*/

    @Override
    public T peek() {
        if (size > 0) {
            return array[0];
        }
        return null;
    }
/*    Е peek() - возвращает элемент из головы очереди. Возвращает null, если очередь пуста. Элемент не удаляется.*/

    public void printAll(){
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }
        System.out.println();
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

