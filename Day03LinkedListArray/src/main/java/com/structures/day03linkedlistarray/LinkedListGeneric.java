package com.structures.day03linkedlistarray;

import java.lang.reflect.Array;

public class LinkedListGeneric<T> {
    private class ContainerGeneric {
        ContainerGeneric next;
        private T value;

        public ContainerGeneric() {
            value = null;
            next = null;
        }

        public ContainerGeneric(T value) {
            this.value = value;
            next = null;
        }
    }

    private ContainerGeneric start, end;
    private int size;

    public LinkedListGeneric() {
        this.start = null;
        this.end = null;
    }

    public void add(T value) {
        ContainerGeneric containerGeneric = new ContainerGeneric();
        containerGeneric.value = value;
        if (start == null) {
            start = containerGeneric;
        } else {
            end.next = containerGeneric;
        }
        end = containerGeneric;
        size++;
    }

    public ContainerGeneric getContainerGeneric(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        ContainerGeneric current = start;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public T get(int index) throws IndexOutOfBoundsException {
        return getContainerGeneric(index).value;
    }

    public void insertValueAtIndex(int index, T value) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            add(value);
        } else {
            ContainerGeneric previous = getContainerGeneric(index - 1);
            ContainerGeneric newContainer = new ContainerGeneric(value);
            newContainer.next = previous.next;
            previous.next = newContainer;
            size++;
        }

    }

    public void replaceValueAtIndex(int index, T value) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        ContainerGeneric current = getContainerGeneric(index);
        current.value = value;
    }

    public void deleteAtIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            start = start.next;
        } else {
            ContainerGeneric previous = getContainerGeneric(index - 1);
            previous.next = previous.next.next;

        }
        size--;
    }

    public boolean deleteByValue(T value) {
        if (start == null) {
            return false;
        }
        ContainerGeneric current = start;
        if (current.value.equals(value)) {
            start = start.next;
            if (start == null) {
                end = null;
            }
            size--;
            return true;
        }
        while (current.next != null) {
            if (current.next.value.equals(value)) {
                current.next = current.next.next;
                if (current.next == null) {
                    end = current;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        ContainerGeneric current = start;
        buffer.append("[");
        while (current != null) {
            buffer.append(current.value);
            if (current.next != null) {
                buffer.append(",");
            }
            current = current.next;
        }
        buffer.append("]");
        return buffer.toString();
    }

    // public <T> T[] toArray() {
    public T[] toArray() {
        Class<T> classT = (Class<T>) start.value.getClass();
        System.out.println("ClassT: " + classT);
        T[] array = (T[]) Array.newInstance(classT, size);
        if (start == null) {
            System.out.println("start is null");
            System.out.println("List is empty, returning an empty array.");
            return (T[]) Array.newInstance(Object.class, 0);
        }
        // ContainerGeneric current = start;
        for (int i = 0; i < size; i++) {
            array[i] = (T) getContainerGeneric(i).value;
            // array[i] = (T) current.value;
            // current = current.next;
            System.out.println("array[" + i + "]: " + array[i]);

        }
        return array;
    }

    public static void main(String[] args) {
        LinkedListGeneric<String> linkedList = new LinkedListGeneric<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");
        System.out.println();
        System.out.println("Initial: " + linkedList);
        linkedList.insertValueAtIndex(2, "E");
        System.out.println("After insertion: " + linkedList);


        System.out.println();
        System.out.print("List to array: [");
        for (int i = 0; i < linkedList.getSize(); i++) {
            System.out.print(linkedList.get(i));
            if (i != linkedList.getSize() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        System.out.println();
        linkedList.deleteAtIndex(3);
        System.out.println("After deletion at index: " + linkedList);


        System.out.println();
        linkedList.deleteByValue("B");
        System.out.println("After deletion by value: " + linkedList);
    }

}
