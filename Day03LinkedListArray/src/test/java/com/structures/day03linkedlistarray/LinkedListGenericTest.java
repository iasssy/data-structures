package com.structures.day03linkedlistarray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListGenericTest {
    LinkedListGeneric<String> list;
    LinkedListGeneric<Integer> listInt;

    @BeforeEach
    void setUp() {
        list = new LinkedListGeneric<>();
        listInt = new LinkedListGeneric<>();
    }

    @AfterEach
    void tearDown() {
        list = null;
        listInt = null;
    }

    @Test
    void testAddString() {
        // no values in linked list
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        list.add("Bob");
        list.add("Ellie");
        assertEquals("Ellie", list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertEquals("[Bob,Ellie]", list.toString());
        assertArrayEquals(new String[]{"Bob", "Ellie"}, list.toArray());
    }

    @Test
    void testAddInteger() {
        listInt.add(1233);
        listInt.add(-12);
        listInt.add(123);
        assertEquals(123, listInt.get(2));
    }

    @Test
    void testInsertValueAtIndexString(){
        // no values in linked list
        assertThrows(IndexOutOfBoundsException.class,() -> list.insertValueAtIndex(2, "F"));
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.insertValueAtIndex(2,"Yes");
        assertEquals("Yes", list.get(2));
        assertEquals("C", list.get(3));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
        assertEquals(5, list.getSize());
    }

    @Test
    void testInsertValueAtIndexInteger(){
        // no values in linked list
        assertThrows(IndexOutOfBoundsException.class,() -> listInt.insertValueAtIndex(2, 324));
        listInt.add(131);
        listInt.add(-12);
        listInt.add(123);
        listInt.insertValueAtIndex(2,2425);
        assertEquals(2425, listInt.get(2));
        assertEquals(123, listInt.get(3));
        assertThrows(IndexOutOfBoundsException.class, () -> listInt.get(5));
        assertEquals(4, listInt.getSize());
    }

    @Test
    void testDeleteAtIndex(){
        list.add("Anna");
        list.add("Will");
        list.add("Jack");
        list.deleteAtIndex(0);
        assertEquals("Will", list.get(0));
        assertEquals(2, list.getSize());
        assertThrows(IndexOutOfBoundsException.class, () -> list.deleteAtIndex(2));
    }

    @Test
    void testDeleteByValue() {
        list.add("Harry");
        list.add("Jack");
        list.add("Anna");
        list.add("Will");
        assertFalse(list.deleteByValue("Michael"));
        assertEquals(4, list.getSize());
        assertTrue(list.deleteByValue("Harry"));
        assertArrayEquals(new String[]{"Jack", "Anna", "Will"}, list.toArray());
    }
}
