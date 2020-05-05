package com.glushkov.queue;

import com.glushkov.list.MyLinkedList;

import java.util.Iterator;

public class LinkedQueue<T> implements Deque<T> {
    public int getSize() {
        return size;
    }

    int size;

    Node head;

    public static class NoSuchElementException extends Exception {
        public NoSuchElementException(String textMessage) {
            super(textMessage);
        }
    }

    private class Node {
        T value;
        Node next;

        public Node(T value) {
            this.value = value;
        }
    }

    @Override
    public void addFirst(T value) {
        Node newHead = new Node(value);
        Node temp = head;
        head = newHead;
        newHead.next = temp;
        size++;
    }

    @Override
    public void addLast(T value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    @Override
    public T getFirst() {
        return head.value;
    }

    @Override
    public T getLast() {
        Node newNode = head;
        while (newNode.next != null) {
            newNode = newNode.next;
        }
        return newNode.value;
    }

    @Override
    public boolean offerFirst(T value) {
        Node newHead = new Node(value);
        Node temp = head;
        head = newHead;
        newHead.next = temp;
        size++;
        return true;
    }

    @Override
    public boolean offerLast(T value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public T pop() throws NoSuchElementException {
        if (head != null) {
            Node newNode = head;
            head = head.next;
            size--;
            return newNode.value;
        } else throw new NoSuchElementException("There is no elements in the queue");
    }

    @Override
    public void push(T value) {
        Node newHead = new Node(value);
        Node temp = head;
        head = newHead;
        newHead.next = temp;
        size++;
        //else throw new IllegalStateException();

    }

    @Override
    public T peekFirst() {
        if (head != null) {
            return head.value;
        }
        return null;
    }

    @Override
    public T peekLast() {
        if (head != null) {
            Node newNode = head;
            while (newNode.next != null) {
                newNode = newNode.next;
            }
            return newNode.value;
        }
        return null;
    }

    @Override
    public T pollFirst() {
        if (head != null) {
            Node newNode = head;
            head = head.next;
            size--;
            return newNode.value;
        } else return null;
    }

    @Override
    public T pollLast() {
        if (head != null) {
            Node newNode = head;
            for (int i = 0; i < size - 2; i++) {
                newNode = newNode.next;
            }
            Node LastNode = newNode.next;
            newNode.next = null;
            size--;
            return LastNode.value;
        } else return null;
    }

    @Override
    public T removeLast() throws NoSuchElementException {
        if (head != null) {
            Node newNode = head;
            for (int i = 0; i < size - 2; i++) {
                newNode = newNode.next;
            }
            Node LastNode = newNode.next;
            newNode.next = null;
            size--;
            return LastNode.value;
        } else throw new NoSuchElementException("There are no elements in the queue");
    }

    @Override
    public T removeFirst() throws NoSuchElementException {
        if (head != null) {
            Node newNode = head;
            head = head.next;
            size--;
            return newNode.value;
        } else throw new NoSuchElementException("There is no elements in the queue");
    }

    @Override
    public boolean removeLastOccurrence(T value) throws NoSuchElementException {
        /*Node newNode = new Node(value);
        if (head != null){
            Node temp = head;
            for (int i = 0; i < size-1; i++) {
                temp = temp.next;
            }
                for (int i = 0; i < size-1; i++) {
                    if (temp.next.value.equals(newNode.value)) {
                        temp.next = temp.next.next;
                        size--;
                        return true;
                    }
                    temp = temp.next;
                }
        }*/
        return false;
    }

    @Override
    public boolean removeFirstOccurrence(T value) throws NoSuchElementException {
        Node newNode = new Node(value);
        if (head != null){
            Node temp = head;
            if (temp.value.equals(newNode.value)){
                this.remove();
                return true;
            }
            else {
                for (int i = 0; i < size-1; i++) {
                    if (temp.next.value.equals(newNode.value)) {
                        temp.next = temp.next.next;
                        size--;
                        return true;
                    }
                    temp = temp.next;
                }
            }
        }
        return false;
    }

    @Override
    public boolean add(T value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
        //else throw new IllegalStateException();
    }

    @Override
    public boolean offer(T value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public T remove() throws NoSuchElementException {
        if (head != null) {
            Node newNode = head;
            head = head.next;
            size--;
            return newNode.value;
        } else throw new NoSuchElementException("There is no elements in the queue");
    }

    @Override
    public T poll() {
        if (head != null) {
            Node newNode = head;
            head = head.next;
            size--;
            return newNode.value;
        }
        return null;
    }

    @Override
    public T element() throws NoSuchElementException {
        if (head != null) {
            return head.value;
        } else throw new NoSuchElementException("There is no elements in the queue");
    }

    @Override
    public T peek() {
        if (head != null) {
            return head.value;
        }
        return null;
    }

    public void printAllQueue() {
        Node current = head;
        System.out.println(current.value);
        while (current.next != null) {
            current = current.next;
            System.out.println(current.value);
        }
        System.out.println(size);
        System.out.println();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node current = head;

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
                if (head != null) {
                    Node newNode = head;
                    head = head.next;
                    size--;
                }
            }
        };
    }
}


/*
    Е element() - возвращает элемент из головы очереди. Элемент нe удаляется. Если очередь пуста, инициируется исключение NoSuchElementException.
        Е remove() - удаляет элемент из головы очереди, возвращая его. Инициирует исключение NoSuchElementException, если очередь пуста.
        Е peek() - возвращает элемент из головы очереди. Возвращает null, если очередь пуста. Элемент не удаляется.
        Е роll() - возвращает элемент из головы очереди и удаляет его. Возвращает null, если очередь пуста.
        boolean offer(Е оbj) - пытается добавить оbj в очередь. Возвращает true, если оbj добавлен, и false в противном случае.*/
