package com.glushkov.queue;

import org.junit.Before;
import org.junit.Test;


import java.util.Iterator;

import static org.junit.Assert.assertEquals;


public class ArrayDequeTest extends ArrayDeque {
    ArrayDeque<String> deque = new ArrayDeque<>();

    @Before
    public void setUp() {
        deque.add("Four");
        deque.add("Three");
        deque.add("Two");
        deque.add("One");
    }

    @Test
    public void testAddFirst() throws LinkedQueue.NoSuchElementException {
        String expected = "First";
        deque.addFirst("First");
        String actual = deque.getFirst();
        assertEquals(expected, actual);
    }

    @Test
    public void testAddLast() throws LinkedQueue.NoSuchElementException {
        String expected = "Last";
        deque.addFirst("Last");
        String actual = deque.getLast();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetFirst() throws LinkedQueue.NoSuchElementException {
        assertEquals(4, deque.getSize());
        String expected = "One";
        String actual = deque.getFirst();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetLast() throws LinkedQueue.NoSuchElementException {
        String expected = "Four";
        String actual = deque.getLast();
        assertEquals(expected, actual);
    }

    @Test
    public void testOfferFirst() throws LinkedQueue.NoSuchElementException {
        boolean expected = true;
        boolean actual = deque.offerFirst("First");

        assertEquals(expected, actual);

        assertEquals(5, deque.getSize());
        assertEquals("First", deque.getFirst());
    }

    @Test
    public void testOfferLast() throws LinkedQueue.NoSuchElementException {
        boolean expected = true;
        boolean actual = deque.offerLast("Last");

        assertEquals(expected, actual);

        assertEquals(5, deque.getSize());
        assertEquals("Last", deque.getLast());
    }

    @Test
    public void testPop() throws LinkedQueue.NoSuchElementException {
        String expected = "One";
        String actual = deque.pop();

        assertEquals(expected, actual);
        assertEquals(3, deque.getSize());
        assertEquals("Two", deque.getFirst());
    }

    @Test
    public void testPush() throws LinkedQueue.NoSuchElementException {
        deque.push("First");
        String expected = "First";
        String actual = deque.getFirst();

        assertEquals(expected, actual);
        assertEquals(5, deque.getSize());
    }

    @Test
    public void testPeekFirst() {
        String expected = "One";
        String actual = deque.peekFirst();
        assertEquals(expected, actual);
    }

    @Test
    public void testPeekLast() {
        String expected = "Four";
        String actual = deque.peekLast();
        assertEquals(expected, actual);
    }

    @Test
    public void testPollFirst() {
        String expected = "One";
        String actual = deque.pollFirst();

        assertEquals(expected, actual);
        assertEquals(3, deque.getSize());
    }

    @Test
    public void testPollLast() {
        String expected = "Four";
        String actual = deque.pollLast();

        assertEquals(expected, actual);
        assertEquals(3, deque.getSize());
    }

    @Test
    public void testRemoveLast() {
        String expected = "Four";
        String actual = deque.removeLast();

        assertEquals(expected, actual);
        assertEquals(3, deque.getSize());
    }

    @Test
    public void testRemoveFirst() {
        String expected = "One";
        String actual = deque.removeFirst();

        assertEquals(expected, actual);
        assertEquals(3, deque.getSize());
    }

    @Test
    public void testRemoveLastOccurrence() {
        boolean expected = false;
        boolean actual = deque.removeLastOccurrence("Five");
        assertEquals(expected, actual);

        boolean expected1 = true;
        boolean actual1 = deque.removeLastOccurrence("Four");
        assertEquals(expected1, actual1);
        assertEquals(3, deque.getSize());
    }

    @Test
    public void testRemoveFirstOccurrence() {
        boolean expected = false;
        boolean actual = deque.removeFirstOccurrence("Five");
        assertEquals(expected, actual);

        boolean expected1 = true;
        boolean actual1 = deque.removeFirstOccurrence("One");
        assertEquals(expected1, actual1);
        assertEquals(3, deque.getSize());
    }

    @Test
    public void testAdd() throws LinkedQueue.NoSuchElementException {
        String expected = "First";
        deque.add("First");
        String actual = deque.getFirst();
        assertEquals(expected, actual);
    }

    @Test
    public void testOffer() {
        boolean expected = true;
        boolean actual = deque.offer("First");

        assertEquals(expected, actual);
        assertEquals(5, deque.getSize());
    }

    @Test
    public void testRemove() throws LinkedQueue.NoSuchElementException {
        String expected = "One";
        String actual = deque.remove();

        assertEquals(expected, actual);
    }

    @Test
    public void testPoll() throws LinkedQueue.NoSuchElementException {
        String expected = "One";
        String actual = deque.remove();

        assertEquals(expected, actual);
    }

    @Test
    public void testElement() throws LinkedQueue.NoSuchElementException {
        String expected = "One";
        String actual = deque.element();
        assertEquals(expected, actual);
    }

    @Test
    public void testPeek() {
        String expected = "One";
        String actual = deque.peek();
        assertEquals(expected, actual);
    }

    @Test
    public void testIterator() {
        Iterator<String> iterator = deque.iterator();

        String[] stringsArray = new String[] {"One", "Two", "Three", "Four"};
        for (int i = 0; i < deque.getSize(); i++) {
            assertEquals(iterator.next(), stringsArray[i]);
        }
    }
}
