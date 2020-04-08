package com.glushkov.map;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HashMapTest {
    Map<Integer, String> map = new HashMap<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 20; i++) {
            map.put(i, "Test = " + i);
        }
    }

    @Test
    public void put() {
        assertFalse(map.isEmpty());
        assertEquals(20, map.size());

        assertNull(map.put(21, "Test - 21"));

        map.put(2, "New TWO");
        assertEquals(21, map.size());
        assertEquals("New TWO", map.get(2));
    }

    @Test
    public void get() {
        String expected = "Test = 10";

        String actual = map.get(10);

        assertEquals(expected, actual);
    }

    @Test
    public void remove() {
        String expected = "Test = 10";

        String actual = map.remove(10);

        assertEquals(expected, actual);
        assertEquals(19, map.size());

        assertNull(map.remove(100));
    }

    @Test
    public void putIfAbsent() {
        String expected = "Test = 10";

        String actual = map.putIfAbsent(10, "Test = 100500");

        assertEquals(expected, actual);
        assertEquals(20, map.size());

        assertNull(map.putIfAbsent(21, "Hello"));
        assertEquals("Hello", map.get(21));
        assertEquals(21, map.size());
    }

    @Test
    public void containsKey() {
        assertTrue(map.containsKey(0));
        assertFalse(map.containsKey(20));
    }

    @Test
    public void size() {
        map.put(100, "StO");
        int expected = 21;

        int actual = map.size();

        assertEquals(expected, actual);

        map.remove(100);
        assertEquals(20, map.size());
    }

    @Test
    public void isEmpty() {
        assertFalse(map.isEmpty());
        assertEquals(20, map.size());

        map.clear();

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    @Test
    public void clear() {
        assertFalse(map.isEmpty());
        map.clear();
        assertTrue(map.isEmpty());
    }
}
