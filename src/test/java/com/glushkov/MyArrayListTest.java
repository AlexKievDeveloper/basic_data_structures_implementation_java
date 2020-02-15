package com.glushkov;

import org.junit.Test;


import static org.junit.Assert.*;

public class MyArrayListTest {
    private MyArrayList<Integer> list = new MyArrayList<>();

    @Test
    public void add() {

        for (int i = 0; i < 12; i++) {
            list.add(i);
            assertEquals(list.size(), i + 1);
        }

        for (int i = 0; i < 12; i++) {
            assertEquals(list.get(i), i);
        }
    }

    @Test
    public void addValue() {
        assertEquals(list.size(), 0);

        for (int i = 0; i < 10; i++) {
            list.add(10);
        }

        assertEquals(list.size(), 10);

        for (int i = 0; i < 10; i++) {
            list.addValue(20, i);
            assertEquals(list.size(), i + 11);
        }

        for (int i = 0; i < 10; i++) {
            assertEquals(list.get(i), 20);
        }

        for (int i = 10; i < 20; i++) {
            assertEquals(list.get(i), 10);
        }
    }

    @Test
    public void remove() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertEquals(list.size(), 10);
        assertEquals(list.get(4), 4);
        list.remove(4);
        assertEquals(list.get(4), 5);
        assertEquals(list.size(), 9);
    }

    @Test
    public void get() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
            assertEquals(list.get(i), i);
        }
        assertEquals(list.get(-100), -1);
        assertEquals(list.get(100), -1);
    }

    @Test
    public void set() {
        for (int i = 0; i < 100; i++) {
            list.add(i);
            assertEquals(list.get(i), i);
        }
        list.set(400, 0);
        assertEquals(list.get(0), 400);
        list.set(500, 5);
        assertEquals(list.get(5), 500);
        list.set(600, 99);
        assertEquals(list.get(99), 600);
    }

    @Test
    public void clear() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
            assertEquals(list.get(i), i);
        }
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(list.size(), 0);
    }

    @Test
    public void contains() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertFalse(list.contains(10));
    }

    @Test
    public void indexOf() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
            assertEquals(list.indexOf(i), i);
        }
    }

    @Test
    public void lastIndexOf() {
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        assertEquals(list.lastIndexOf(2), 2);
    }

    @Test
    public void size() {
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        assertEquals(list.size(), 3);
    }

    @Test
    public void isEmpty() {
        assertTrue(list.isEmpty());
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertFalse(list.isEmpty());
    }

    @Test
    public void testToString() {
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        assertEquals(list.toString(), "размер: 3, значения: 0, 1, 2, ");
    }


    @Test
    public void testIterator() {
        Iterable.Iterator<Integer> testIterator = list.iterator();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        testIterator.remove();

        int j = 1;
        while (testIterator.hasNext()) {
            assertEquals(testIterator.next(), j);
            if (j < list.size()) {
                j++;
            }
        }
    }
}
