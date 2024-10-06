package com.structures.day01cachingfibonacci.entity;

import java.util.HashMap;

public class Fibonacci {
    private boolean isCacheOn;
    Fibonacci(boolean cacheOn) {
        isCacheOn = cacheOn;
        fibsCached.put(0,0L); // #0
        fibsCached.put(1,1L); // #1
    }

    private HashMap<Integer,Long> fibsCached = new HashMap<>();

    private int fibsCompCount = 2; // may not be needed

    public long getNthFib(int n) {
        if (n<0){
            System.out.println("No negative fibonacci number exists.");
            return 0;
        }
        if (isCacheOn && fibsCached.containsKey(n)) {
            return fibsCached.get(n);
        }
        long result = computeNthFib(n);
        if (isCacheOn) {
            fibsCached.put(n, result);
            fibsCompCount = fibsCached.size();
        }
        return result;

    }

    private long computeNthFib(int n) {
        if (fibsCached.containsKey(n)) {
            return fibsCached.get(n);
        }

        int lastCachedIndex = fibsCached.size() - 1;
        long prev = fibsCached.get(lastCachedIndex - 1);
        long current = fibsCached.get(lastCachedIndex);

        for (int i = lastCachedIndex + 1; i <= n; i++) {
            long nextFib = prev + current;
            prev = current;
            current = nextFib;

            if (isCacheOn) {
                fibsCached.put(i, nextFib);
            }
        }
        return current;
    }

    public int getCountOfFibsComputed() {
        return fibsCompCount;
    }

    @Override
    public String toString() {
        String result = "";
        for (Long value : fibsCached.values()) {
            result += value + ", ";
        }
        if (!result.isEmpty()) result = result.substring(0, result.length() - 2);
        return result;
    }

    public static void main(String[] args) {
        Fibonacci fibCached = new Fibonacci(true);
        Fibonacci fibNonCached = new Fibonacci(false);

        // Test with cache enabled
        System.out.println("With caching:");
        System.out.println(fibCached.getNthFib(-2));
        System.out.println("Fib(10) = " + fibCached.getNthFib(10));
        System.out.println("Computed count: " + fibCached.getCountOfFibsComputed());
        System.out.println("Cached values: " + fibCached.toString());

        System.out.println();

        System.out.println("Without caching:");
        System.out.println("Fib(10) = " + fibNonCached.getNthFib(10));
        System.out.println("Computed count: " + fibNonCached.getCountOfFibsComputed());
        System.out.println("Cached values: " + fibNonCached.toString());
    }



}
