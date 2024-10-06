package com.structures.day04customhashmap;

import com.structures.day04customhashmap.exception.KeyNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCustomHashMap {
    CustomHashMapStringString customHashMap = new CustomHashMapStringString();

    @BeforeEach
    void setUp() {
        customHashMap = new CustomHashMapStringString();
    }

    @AfterEach
    void tearDown() {
        customHashMap = null;
    }

    @Test
    void testAdd() {
        customHashMap.putValue("Julie", "Math");
        customHashMap.putValue("Harry", "Music");
        customHashMap.putValue("Simon", "Physical training");

        assertEquals("Math", customHashMap.getValue("Julie"));
        assertEquals("Music", customHashMap.getValue("Harry"));
        assertEquals("Physical training", customHashMap.getValue("Simon"));
        assertEquals(3, customHashMap.getSize());
    }

    @Test
    void testShouldHasKey() {
        customHashMap.putValue("Julie", "Math");
        customHashMap.putValue("Harry", "Music");
        customHashMap.putValue("Simon", "Physical training");
        Assertions.assertTrue(customHashMap.hasKey("Julie"));
        Assertions.assertFalse(customHashMap.hasKey("Mary"));
        Assertions.assertTrue(customHashMap.hasKey("Harry"));
    }

    @Test
    void testShouldGetValue() {
        customHashMap.putValue("Julie", "Math");
        customHashMap.putValue("Harry", "Music");
        Assertions.assertEquals("Math", customHashMap.getValue("Julie"));
        Assertions.assertEquals("Music", customHashMap.getValue("Harry"));
        Assertions.assertThrows(KeyNotFoundException.class, () -> customHashMap.getValue("Simon"));
    }

    @Test
    public void testDeleteByKey() {
        customHashMap.putValue("Julie", "Math");
        customHashMap.putValue("Harry", "Music");
        customHashMap.putValue("Simon", "Physical training");
        customHashMap.deleteByKey("Julie");
        Assertions.assertThrows(KeyNotFoundException.class, () -> customHashMap.getValue("Julie"));
        customHashMap.deleteByKey("Harry");
        Assertions.assertThrows(KeyNotFoundException.class, () -> customHashMap.getValue("Harry"));
        Assertions.assertEquals(1, customHashMap.getSize());
    }

    @Test
    public void testGetAllKeys() {
        customHashMap.putValue("Julie", "Math");
        customHashMap.putValue("Harry", "Music");
        customHashMap.putValue("Simon", "Physical training");
        Assertions.assertArrayEquals(new String[]{"Simon", "Harry", "Julie"}, customHashMap.getAllKeys());
        Assertions.assertEquals("[Simon => Physical training, Harry => Music, Julie => Math]", customHashMap.toString());
        Assertions.assertArrayEquals(new String[]{"Simon", "Harry", "Julie"}, customHashMap.getAllKeys());
    }

    @Test
    public void testPutValue() {
        customHashMap.putValue("Julie", "Math");
        customHashMap.putValue("Harry", "Music");
        customHashMap.putValue("Harry", "Literature");  // duplicated

        Assertions.assertEquals("Literature", customHashMap.getValue("Harry"));
        Assertions.assertEquals(2, customHashMap.getSize());
    }

    @Test
    public void testGetAllValPairs() {
        customHashMap.putValue("Julie", "Math");
        customHashMap.putValue("Harry", "Music");
        customHashMap.putValue("Simon", "Physical training");

        Pair<String, String>[] pairs = customHashMap.getAllKeyValPairs();
        Assertions.assertAll("pair",
                () -> assertEquals("Julie", pairs[2].key),
                () -> assertEquals("Math", pairs[2].val)
        );
        Assertions.assertAll("pair",
                () -> assertEquals("Harry", pairs[1].key),
                () -> assertEquals("Music", pairs[1].val)
        );
    }

    @Test
    public void testExpandHashTable() {
        customHashMap.putValue("Julie", "Math");
        customHashMap.putValue("Harry", "Music");
        customHashMap.putValue("Simon", "Physical training");
        customHashMap.putValue("Alice", "Art");
        customHashMap.putValue("Bob", "Biology");
        customHashMap.putValue("Charlie", "Computer Science");
        customHashMap.putValue("Diana", "History");
        customHashMap.putValue("Ethan", "Geography");
        customHashMap.putValue("Fiona", "Literature");
        customHashMap.putValue("George", "Chemistry");
        customHashMap.putValue("Hannah", "Philosophy");
        customHashMap.putValue("Joseph", "Literature");
        customHashMap.putValue("Jack", "Geography");
        customHashMap.putValue("Sophie", "Chemistry");
        customHashMap.putValue("Michael", "Physics");
        customHashMap.putValue("Olivia", "Statistics");

        customHashMap.putValue("Diana", "Literature"); // duplicate


        customHashMap.printDebug();
        Assertions.assertEquals(16, customHashMap.getSize());

    }

}
