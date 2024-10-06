package com.structures.day04customhashmap.exception;

public class KeyNotFoundException extends RuntimeException {
    public KeyNotFoundException(String key) {
        super("Key " + key + " not found");
    }
}
