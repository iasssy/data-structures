package com.structures.day02arraylistownimpl;

public class CustomArrayOfInts {
    private int[] data = new int[2]; // only grows by doubling size, never shrinks
    private int size = 0; // how many items do you really have

    public int size() {
        return size;
    }

    public void add(int value) {
        doubleArrayIfNeeded();
        data[size++] = value;
    }

    public void deleteByIndex(int index) {
        // check if index exists
        if (!throwIndexOfBounds(index)) {
            for (int i = index; i < size - 1; i++) {
                data[i] = data[i + 1];
            }
            size--;
        } else System.out.println("The item cannot be deleted - out of bounds");
    }


    // delete first value matching, true if value found, false otherwise
    public boolean deleteByValue(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                deleteByIndex(i);
                return true;
            }
        }
        return false;
    }


    public void insertValueAtIndex(int value, int index) {
        if (!throwIndexOfBounds(index)) {
            doubleArrayIfNeeded();
            for (int i = size; i > index; i--) {
                data[i] = data[i - 1];
            }
            data[index] = value;
            size++;
        } else System.out.println("The item cannot be inserted - out of bounds");
    }

    public void clear() {
        size = 0;
    }

    public int get(int index) { // may throw ArrayIndexOutOfBoundsException
        if (!throwIndexOfBounds(index)) {
            return data[index];
        } else return 0; // should be something else!!!! the element can be of value "0"!!!!
    }

    public int[] getSlice(int startIdx, int length) { // may throw ArrayIndexOutOfBoundsException

        if (!throwIndexOfBoundsSlice(startIdx, length)) {
            int[] slice = new int[length];
            for (int i = 0; i < length; i++) {
                slice[i] = data[startIdx + i];
            }
            return slice;
        }
        return null;
    }


    public int[] toArray() { // ensure no reference leak
        int[] dataNew = new int[size];
        // System.arraycopy(data, 0, dataNew, 0, size);
        for (int i = 0; i < size; i++) {
            dataNew[i] = data[i];
        }
        return dataNew;
    }


    @Override
    public String toString() {
        String dataString = "[";
        for (int i = 0; i < size; i++) {
            dataString += data[i] + "";
            if (i < size - 1) {
                dataString += ",";
            }
        }
        dataString += "]";
        return dataString;
    } // returns String similar to: [3, 5, 6, -23]


    public static void main(String[] args) {
        CustomArrayOfInts array = new CustomArrayOfInts();
        array.add(16);
        array.add(-32);
        array.add(0);
        array.add(8);
        System.out.println("Array size: " + array.size());
        System.out.println(array);

        // array.deleteByIndex(2);
        // System.out.println(array);
        // array.deleteByValue(-32);
        // System.out.println(array);


        // array.insertValueAtIndex(4, 5);
        // System.out.println(array);

        array.insertValueAtIndex(234, 1);
        System.out.println(array);
        System.out.println(array.get(1));


        int[] slice = array.getSlice(5, 2);
        if (slice != null) {
            System.out.print("Slice: ");
            String sliceString = "[";
            for (int i = 0; i < slice.length; i++) {
                sliceString += slice[i] + "";
                if (i < slice.length - 1) {
                    sliceString += ", ";
                }
            }
            sliceString += "]";
            System.out.print(sliceString);

        }

    }


    private void doubleArrayIfNeeded() {
        if (size >= data.length) {
            int[] newData = new int[data.length * 2];
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }

    private boolean throwIndexOfBounds(int index) {
        if (index < 0 || index >= size) {
            // throw new ArrayIndexOutOfBoundsException("Invalid index");
            System.out.println("Invalid index");
            return true;
        }
        return false;
    }

    private boolean throwIndexOfBoundsSlice(int startIdx, int length) {
        if (data == null || size == 0 || startIdx < 0 || startIdx >= size || (startIdx + length) > size) {
            // throw new ArrayIndexOutOfBoundsException("Invalid index");
            System.out.println("Invalid interval to be sliced");
            return true;
        }
        return false;
    }
}
