package com.structures.day03linkedlistarray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListArrayOfStringsTest {

    LinkedListArrayOfStrings linked;

    @BeforeEach
    void setUp() {
        linked = new LinkedListArrayOfStrings();
    }


    @AfterEach
    void tearDown() {
        linked = null;
    }



    @Test
    void testAdd() {
        linked.add("Jenny");
        linked.add("James");
        assertEquals("Jenny", linked.get(0));
        assertEquals("James", linked.get(1));
        assertEquals(2, linked.getSize());
    }

    @Test
    void testInsertValueAtIndex() {
        linked.add("Jenny");
        linked.add("Anna");
        linked.add("James");
        linked.add("John");
        linked.insertValueAtIndex("Chris", 2);
        assertEquals("James", linked.get(3));
        assertEquals(5, linked.getSize());
        assertEquals("John", linked.get(4));
      //  assertThrows(IndexOutOfBoundsException.class, () -> linked.get(6));
    }


    @Test
    void testReplaceValueAtIndex() {
        linked.add("Jenny");
        linked.add("James");
        linked.add("John");
        linked.replaceValueAtIndex("Will", 1);
        assertEquals("Will", linked.get(1));
        assertEquals(3, linked.getSize());
    }

    @Test
    void testDeleteByIndex() {
        linked.add("Kate");
        linked.add("Jack");
        linked.add("Anna");
        linked.add("Marc");
        linked.deleteByIndex(1);
        assertEquals("Anna", linked.get(1));
        assertEquals(3, linked.getSize());

    }

    @Test
    void testDeleteByValue() {
        linked.add("Jennifer");
        linked.add("Eric");
        linked.add("Charlie");

        assertTrue(linked.deleteByValue("Eric"));
        assertFalse(linked.deleteByValue("Daniel"));
        assertEquals(2, linked.getSize());
    }

}
