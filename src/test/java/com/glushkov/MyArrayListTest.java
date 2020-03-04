package com.glushkov;

import org.junit.Before;
import org.junit.Test;


import java.util.Iterator;

import static org.junit.Assert.*;

public class MyArrayListTest {
    private List<Integer> list;

    private List<String> listWithZeroElements;
    private List<String> listWithFiveElements;
    private List<String> listWithTenElements;

    @Before
    public void before() {
        list = new MyArrayList<>();

        listWithZeroElements = new MyArrayList<>();

        listWithFiveElements = new MyArrayList<>();
        char c = 'A';
        for (int i = 0; i < 5; i++) {
            String value = String.valueOf(c);
            listWithFiveElements.add(value);
            c++;
        }

        listWithTenElements = new MyArrayList<>();
        c = 'A';
        for (int i = 0; i < 10; i++) {
            String value = String.valueOf(c);
            listWithTenElements.add(value);
            c++;
        }
    }

    @Test
    public void add() {
        //prepare
        for (int i = 0; i < 12; i++) {
            list.add(i);
            assertEquals(list.size(), i + 1);
        }
        Integer expected = 10;
        //when
        Integer actual = list.get(10);
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void addValue() {
        //prepare
        assertEquals(0, list.size());
        for (int i = 0; i < 10; i++) {
            list.add(10);
        }
        assertEquals(10, list.size());

        for (int i = 0; i < 10; i++) {
            list.add(20, i);
            assertEquals(i + 11, list.size());
        }

        for (int i = 0; i < 10; i++) {
            Integer expected = 20;
            Integer actual = list.get(i);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void remove() {
        Object removed = listWithFiveElements.remove(1);
        assertEquals(4, listWithFiveElements.size());
        assertEquals("B", removed);
        assertEquals("A", listWithFiveElements.get(0));
        assertEquals("C", listWithFiveElements.get(1));
        assertEquals("D", listWithFiveElements.get(2));
        assertEquals("E", listWithFiveElements.get(3));


        //prepare
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertEquals(list.size(), 10);

        int actual = list.get(4);
        assertEquals(4, actual);
        int expected = 4;
        //when
        actual = list.remove(4);
        //then
        assertEquals(expected, actual);
        actual = list.get(4);
        assertEquals(5, actual);
        assertEquals(9, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get() {
        char c = 'A';
        for (int i = 0; i < 5; i++) {
            String value = String.valueOf(c);
            assertEquals(value, listWithFiveElements.get(i));
            c++;
        }


        //prepare
        for (int i = 0; i < 10; i++) {
            list.add(i);
            int actual = list.get(i);
            assertEquals(i, actual);
        }
        Integer expected = 100;
        //when
        Integer actual = list.get(100);
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void set() {
        char c = 'F';
        for (int i = 0; i < 5; i++) {
            String value = String.valueOf(c);
            listWithFiveElements.set(value, i);
            assertEquals(value, listWithFiveElements.get(i));
            c++;
        }


        for (int i = 0; i < 100; i++) {
            list.add(i);
            int actual = list.get(i);
            assertEquals(i, actual);
        }
        list.set(400, 0);
        int actual = list.get(0);
        assertEquals(400, actual);

        list.set(500, 5);
        actual = list.get(5);
        assertEquals(500, actual);

        list.set(600, 99);
        actual = list.get(99);
        assertEquals(600, actual);
    }

    @Test
    public void clear() {
        assertEquals(5, listWithFiveElements.size());
        listWithFiveElements.clear();
        assertEquals(0, listWithFiveElements.size());
        assertTrue(list.isEmpty());


        for (int i = 0; i < 10; i++) {
            list.add(i);
            int actual = list.get(i);
            assertEquals(i, actual);
        }
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(list.size(), 0);
    }

    @Test
    public void contains() {
        assertTrue(listWithFiveElements.contains("E"));
        assertFalse(listWithFiveElements.contains("F"));


        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertFalse(list.contains(10));
    }

    @Test
    public void indexOf() {
        assertEquals(0, listWithFiveElements.indexOf("A"));
        assertEquals(4, listWithFiveElements.indexOf("E"));


        for (int i = 0; i < 10; i++) {
            list.add(i);
            assertEquals(list.indexOf(i), i);
        }
    }

    @Test
    public void lastIndexOf() {
        listWithTenElements.set("F", 8);
        assertEquals(8, listWithTenElements.lastIndexOf("F"));


        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        assertEquals(list.lastIndexOf(2), 2);
    }

    @Test
    public void size() {
        assertEquals(0, listWithZeroElements.size());
        assertEquals(5, listWithFiveElements.size());
        assertEquals(10, listWithTenElements.size());


        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        assertEquals(list.size(), 3);
    }

    @Test
    public void isEmpty() {
        assertTrue(listWithZeroElements.isEmpty());
        assertFalse(listWithFiveElements.isEmpty());
        assertFalse(listWithTenElements.isEmpty());


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
        assertEquals(list.toString(), "размер: 3, значения: 0, 1, 2");
    }


    @Test
    public void testIterator() {
        Iterator<Integer> testIterator = list.iterator();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        testIterator.remove();

        int expected = 1;
        while (testIterator.hasNext()) {
            int actual = testIterator.next();
            assertEquals(expected, actual);
            if (expected < list.size()) {
                expected++;
            }
        }
    }
}
