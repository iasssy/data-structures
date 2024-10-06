package com.structures.day04customhashmap;

public class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K, V>> {
    K key;
    V val;

    Pair(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public K getKey() {
        return key;
    }
    public V getValue() {
        return val;
    }

    @Override
    public String toString() {
        return key + " => " + val;
    }


    @Override
    public int compareTo(Pair<K, V> o) {
        return key.compareTo(o.key);
    }
}
