package com.structures.day03linkedlistarray;

public class LinkedListArrayOfStrings {
    private class Container {
        Container next;
        String value;

        Container() {
            next = null;
            value = null;
        }

        Container(String val) {
            this.value = val;
        }
    }

    private Container start, end;
    private int size;

    public LinkedListArrayOfStrings() {
        start = end = new Container();
        size = 0;
    }

    public void add(String newValue) {
        // if the list is empty
        if (start == null) {
            start = end = new Container(newValue);
        } else {
            end.next = new Container(newValue);
            end = end.next;
        }
        size++;
    }

    public String get(int index) {

        if (!checkIfIndexValid(index)) {
            return null;
        }
        Container current = start;
        for (int i = 0; i <= index; i++) {
            current = current.next;
        }
        return current.value;
    }

    public void insertValueAtIndex(String value, int index) {
        if (!checkIfIndexValid(index)) {
            return;
        }
        Container newNode = new Container(value);
        if (index == 0) {
            newNode.next = start;
            start = newNode;
        } else {
            Container current = start;
            for (int i = 0; i < index; i++) {
                current = current.next;

            }
            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
    }

    public void replaceValueAtIndex(String value, int index) { // put
        if (!checkIfIndexValid(index)) {
            return;
        }
        Container current = start;
        for (int i = 0; i <= index; i++) {
            current = current.next;
        }
        current.value = value;
    }


    public void deleteByIndex(int index) {
        if (!checkIfIndexValid(index)) {
            return;
        }
        Container current = start;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
    }

    public boolean deleteByValue(String value) { // delete first value found
        if (start == null) {
            return false;
        }
        if (start.value != null && start.value.equals(value)) {
            start = start.next;
            size--;
            return true;
        }

        Container previous = start;
        Container current = start.next;
        for (int i = 0; i < size; i++) {
            if (current.value.equals(value)) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;

        }
        return false;
    }


    public int getSize() {
        return size;
    }


    @Override
    public String toString() {  // comma-separated values list similar to: [5,8,11]
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Container current = start;

        for (int i = 0; i < size; i++) {
            stringBuilder.append(current.next.value);
            if (i != size - 1) {
                stringBuilder.append(",");
            }
            current = current.next;
        }

        stringBuilder.append("]");
        return stringBuilder.toString();

    }

    public String[] toArray() {  // could be used for Unit testing
        String[] array = new String[size];
        Container current = start.next;
        for (int i = 0; i < size; i++) {
            array[i] = current.value;
            current = current.next;
        }
        return array;
    }


    private boolean checkIfIndexValid(int index) {
        if (index < 0 || index > size) {
            System.out.println("Invalid index: " + index);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        LinkedListArrayOfStrings lal = new LinkedListArrayOfStrings();

        lal.add("Anna");
        lal.add("Bob");
        lal.add("Charlie");
        lal.add("Anna2");
        System.out.println(lal.getSize());
        System.out.println(lal);

        System.out.println(lal.get(1));
        lal.insertValueAtIndex("Lily",2);
        System.out.println(lal);

        System.out.println();
        LinkedListArrayOfStrings arrayOfStrings = new LinkedListArrayOfStrings();

        arrayOfStrings.add("Lily");
        arrayOfStrings.add("Bob");
        arrayOfStrings.add("Charlie");
        arrayOfStrings.add("Anna");
        System.out.print("Before insertValueAtIndex: " + arrayOfStrings);
        arrayOfStrings.insertValueAtIndex("Suzy", 2);
        System.out.println();
        System.out.print("After insertValueAtIndex: " + arrayOfStrings);

        System.out.println();
        arrayOfStrings.replaceValueAtIndex("Catherine", 4);
        System.out.println("After replaceValueAtIndex: " + arrayOfStrings);

        System.out.println();
        System.out.println("toArray: ");
        String[] newArray = arrayOfStrings.toArray();
        for (String s : newArray) {
            System.out.print(s + " ");
        }

        System.out.println();
        arrayOfStrings.deleteByIndex(1);
        System.out.print("After deleteByIndex: " + arrayOfStrings);


        System.out.println();
        System.out.println();
        arrayOfStrings.add("Yulia");
        arrayOfStrings.add("Chris");
        System.out.print("Before deleteByValue: " + arrayOfStrings);
        System.out.println();

        if(arrayOfStrings.deleteByValue("Yulia")){
            System.out.println("deletion is successful");
        } else {
            System.out.println("deletion is not successful");
        }
        System.out.println();
        System.out.print("After deleteByValue: " + arrayOfStrings);
        System.out.println();
    }

}
