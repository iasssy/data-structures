package com.structures.day04customhashmap;

import com.structures.day04customhashmap.exception.KeyNotFoundException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class CustomHashMapStringString {
    private class Container {
        Container next;
        String key;
        String value;
    }


    // size must be a prime number always
    private Container[] hashTable = new Container[5];

    private int totalItems;

    int getSize() {
        return totalItems;
    }

    // expand hashTable by about *2 when totalItems > 3*hashTable.length

    /**
     * Expand the current hastTable length, The size of the hashTable must always be a prime number,
     * Double the hashTable length and find the prime number or next probably prime number.
     */
    private void expandHashTable() {
        // double current length
        int newLength = hashTable.length * 2;
        // check if the current doubling is a prime of find the next probable prime number
        BigInteger b = new BigInteger(String.valueOf(newLength));
        newLength *= Integer.parseInt((b.isProbablePrime(1)) ? b.toString() : b.nextProbablePrime().toString());

        // create new hashTable
        Container[] oldHashTable = hashTable;
        hashTable = new Container[newLength];

        /*
        // Rehash the existing nodes
        for (Container container : oldHashTable) {
            Container current = container;

            while (current != null) {
                // Compute the new hash value
                int newIndex = computeHashValue(current.key) % hashTable.newLength;

                // Insert the node into the new hash table
                Container newContainer = new Container();
                newContainer.key = current.key;
                newContainer.value = current.value;
                newContainer.next = hashTable[newIndex];
                hashTable[newIndex] = newContainer;

                // Move to the next node
                current = current.next;
            }
        }

         */


        // pull the nodes into array
        Container[] allNodes = new Container[totalItems];
        int index = 0;

        for (Container container : oldHashTable) {
            Container current = container;

            while (current != null) {
                allNodes[index++] = current;
                current = current.next;
            }
        }

        // Re-add all nodes, rehashed into the new table
        for (Container node : allNodes) {
            int hash = computeHashValue(node.key) % newLength;
            node.next = hashTable[hash];
            hashTable[hash] = node;
        }



    }

    private int computeHashValue(String key) { // hashing function
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash <<= 2;  // same as: hash *= 4
            char c = key.charAt(i);
            hash += c;
        }
        return Math.abs(hash);
    }

    /**
     * Add key, value to hashmap table. If the key already exists, its value will be overwritten.
     * Expand the table once reaching threshold of totalItems > 3*hashTable.length.
     *
     * @param key   the given key
     * @param value the given value
     */
    void putValue(String key, String value) {
        int index = computeHashValue(key) % hashTable.length;
        Container current = hashTable[index];

        // if key exists, modifying the value (changing to new)
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value; // update existing value
                return;
            }
            current = current.next;
        }

        Container newContainer = new Container();
        newContainer.key = key;
        newContainer.value = value;
        // The next of the newly created container is pointing to the current start of the hashTable
        // at computed index (to the existing containers in the linked list).
        // If there is no container, current container is null, and we are pointing as the next to null,
        // so new container is the start and the end of the linked list at computed index
        newContainer.next = hashTable[index];
        // new container is assigned to be the start of the hashTable at computed index
        hashTable[index] = newContainer;
        totalItems++;

        if (totalItems > 3 * hashTable.length) {
            System.out.println("Expanding the hash table...");
            expandHashTable();
        }

    }

    boolean hasKey(String key) {
        int index = computeHashValue(key) % hashTable.length;
        Container current = hashTable[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Get the value associated with the given key.
     *
     * @param key the given key
     * @return the value
     * @throws KeyNotFoundException if the key doesn't exist
     */
    String getValue(String key) throws KeyNotFoundException {
        int index = computeHashValue(key) % hashTable.length;
        Container current = hashTable[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        throw new KeyNotFoundException(key);
    }


    /**
     * Deletes an entry by key
     *
     * @param key the given key
     * @throws KeyNotFoundException if the key is not found
     */
    void deleteByKey(String key) throws KeyNotFoundException {
        int index = computeHashValue(key) % hashTable.length;
        Container current = hashTable[index];
        Container previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                // removing the first item on the list (special case)
                if (previous == null) {
                    hashTable[index] = current.next;
                } else {
                    // remove one of the later items
                    previous.next = current.next;
                }
                totalItems--;
                return;

            }
        }
        throw new KeyNotFoundException("Key " + key + " not found");
    }

    public String[] getAllKeys() { // Generic version: public K[] getAllKeys(K[] template) { ... }
        String[] keys = new String[totalItems];
        int index = 0;

        for (Container container : hashTable) {
            while (container != null) {
                keys[index++] = container.key;
                container = container.next;
            }
            // or
            /*
            for (int i=0; i< hashTable.length; i++){
                for (Container current = hashTable[i]; current != null; current = curren.next){
                keys[index++] = current.key;
                }

            }
             */

        }
        return keys;
    }


    public Pair<String, String>[] getAllKeyValPairs() {
        Pair<String, String>[] result = new Pair[totalItems];
        int index = 0;

        for (Container container : hashTable) {
            while (container != null) {
                result[index++] = new Pair<>(container.key, container.value);
                container = container.next;
            }
        }
        return result;
    }


    public void printDebug() {// print hashTable content
        for (int i = 0; i < hashTable.length; i++) {
            System.out.printf("Entry %d:\n", i);
            Container current = hashTable[i];
            while (current != null) {
                System.out.print(current.key + " => " + current.value);
                if (current.next != null) {
                    System.out.print(", ");
                }
                current = current.next;
            }
            System.out.println();
        }
    }


    @Override
    public String toString() {

        ArrayList<String> arrayKeys = new ArrayList<>();

        for (Container container : hashTable) {
            Container current = container;
            while (current != null) {
                arrayKeys.add(String.format("%s => %s", current.key, current.value));
                current = current.next;
            }
        }
        Collections.sort(arrayKeys);
        return arrayKeys.toString();

        /*
        // another variant without sorting
        String[] keys = getAllKeys();

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < keys.length; i++ ) {
            sb.append(keys[i]).append(" => ").append(getValue(keys[i]));
            if (i !=keys.length-1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();

         */
    } // comma-separated key->value pair list
    // to be able to use this as validation in Unit tests keys must be sorted
    // e.g. [ Key1 => Val1, Key2 => Val2, ... ]

    public String toStringWithLambda() {
        String[] keys = getAllKeys();
        // sort the keys
        Arrays.sort(keys);

        // using lambda
        String result = Arrays.stream(keys)
                .map(key -> {
                    try {
                        return key + " => " + getValue(key);
                    } catch (KeyNotFoundException e) {
                        return key + " => null";
                    }
                })
                .collect(Collectors.joining(", "));
        return "[" + result + "]";
    }

    public String toStringWithPairsAndLambda(){
        Pair<String, String>[] pairs = getAllKeyValPairs();

        Arrays.sort(pairs, (pair1, pair2) -> pair1.getKey().compareTo(pair2.getKey()));

        // using lambda, format each pair into the string
        String result = Arrays.stream(pairs)
                .map(pair -> pair.getKey() + " => " + pair.getValue())
                .collect(Collectors.joining(", "));

        return "[" + result + "]";
    }

    public static void main(String[] args) {
        CustomHashMapStringString hashTable = new CustomHashMapStringString();

        hashTable.putValue("key81", "value1");
        hashTable.putValue("key2", "value2");
        hashTable.putValue("key35", "value3");
        hashTable.putValue("key0", "value4");
        hashTable.putValue("key51", "value5");
        hashTable.putValue("key16", "value6");
        hashTable.putValue("key7", "value7");


        System.out.println(hashTable.getSize());
        System.out.println();
        System.out.println("printDebug: ");
        hashTable.printDebug();
        System.out.println();
        System.out.println("toString: ");
        System.out.println(hashTable.toString());
        System.out.println();
        System.out.println("toStringWithLambda: ");
        System.out.println(hashTable.toStringWithLambda());
        System.out.println();
        System.out.println("toStringWithPairsAndLambda: ");
        System.out.println(hashTable.toStringWithPairsAndLambda());

    }
}




