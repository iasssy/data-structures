package com.structures.day02arraylistownimpl;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayOfIntsTest {
    private CustomArrayOfInts array;

    @BeforeEach
    public void setUp() {
        array = new CustomArrayOfInts();
    }

    @Test
    public void testAdd() {
        array.add(5);
        array.add(10);
        assertEquals(2, array.size());
        assertEquals(5, array.get(0));
        assertEquals(10, array.get(1));
    }


    @Test
    public void testInsertValueAtIndex() {
        array.add(5);
        array.add(10);
        array.insertValueAtIndex(7, 1);
        assertEquals(3, array.size());
        assertEquals(7, array.get(1));
    }

    @Test
    public void testDeleteByIndex() {
        array.add(5);
        array.add(10);
        array.deleteByIndex(0);
        assertEquals(1, array.size());
        assertEquals(10, array.get(0));
    }
    @Test
    public void testDeleteByValue() {
        array.add(5);
        array.add(10);
        assertTrue(array.deleteByValue(5));
        assertEquals(1, array.size());
    }

    @Test
    public void testGetSlice() {
        array.add(1);
        array.add(2);
        array.add(3);
        int[] slice = array.getSlice(0, 2);
        assertArrayEquals(new int[] {1, 2},
                slice);
    }


    @Test
    public void testToArray() {
        array.add(5);
        array.add(10);
        array.add(2);
        array.add(3);
        int[] newArray = array.toArray();
        assertArrayEquals(new int[] {5, 10, 2, 3}, newArray);

    }
}
