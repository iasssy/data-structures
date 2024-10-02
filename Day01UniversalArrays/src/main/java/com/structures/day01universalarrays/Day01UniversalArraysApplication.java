package com.structures.day01universalarrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class Day01UniversalArraysApplication {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(Day01UniversalArraysApplication.class, args);

        // Ask user for each row/column value on a single line and save to data2d

        //System.out.println("Please enter array values");
        int[][] data2 = new int[2][4];
        // inputArray(data2);
        // printArray(data2);

        System.out.println();

        /*
        int[] arr1 = new int[4];
        inputArray(arr1);
        System.out.print("Array arr1: ");
        printArray(arr1);
        System.out.println();
        int[] arr2 = new int[3];
        inputArray(arr2);
        System.out.print("Array arr2: ");
        printArray(arr2);
        System.out.println();
         */

        // 1d arrays
        int[] arr1 = {1, 2, 3, 2, 3, 5};
        int[] arr2 = {7, 5, 3, 3, 0};
        int[] duplicatesArray = findDuplicates(arr1, arr2);
        System.out.print("Duplicates array: ");
        printArray(duplicatesArray);

        // 2d arrays
        int[][] a1_2d = {{1, 2, 3}, {4, 5, 6}};
        int[][] a2_2d = {{3, 4, 7}, {2, 5, 8}};
        int[] duplicates2d = findDuplicates(a1_2d, a2_2d);
        System.out.println("Duplicates in 2D arrays: ");
        printArray(duplicates2d);
    }


    static int inputInt() {
        while (true) {
            try {
                return input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine(); // to consume the invalid input
                System.out.print("Not an integer, please try again.");
            }
        }
    }

    // single dimensional
    static void inputArray(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.printf("Please enter value for index %d: ", i + 1);
            data[i] = inputInt();
        }
    }

    // print on a single line, comma-separated
    static void printArray(int[] data) {
        for (int i = 0; i < data.length; i++) {
           /*
            System.out.print(data[i]);
            if (i < data.length - 1) {
                System.out.print(", ");
            }
            */
            System.out.printf("%s%d", (i == 0 ? "" : ", "), data[i]);
        }
        System.out.println();
    }


    // two-dimensional, could be *jagged* array
    static void inputArray(int[][] data2d) {
        for (int i = 0; i < data2d.length; i++) {
            for (int j = 0; j < data2d[0].length; j++) {
                System.out.printf("Please enter value row %d column %d: ", i + 1, j + 1);
                data2d[i][j] = inputInt();
            }
        }

    }


    static void printArray(int[][] data2d) {
        for (int row = 0; row < data2d.length; row++) {
            for (int col = 0; col < data2d[row].length; col++) {
                System.out.printf("%s%d", (col == 0 ? "" : ", "), data2d[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // could be a *jagged* array
    static void printArrayJagged0(int[][] data2d) {
        //   int[] lenColumn = new int[data2d[0].length];
        int[] colsSizes = new int[data2d[0].length];
        //    lenColumn[0] = Character.charCount(data2d[0][0]);
        for (int j = 0; j < data2d[0].length; j++) {
            for (int i = 0; i < data2d.length; i++) {
                int maxColumnLength = String.valueOf(data2d[i][j]).length();
                if (colsSizes[j] < maxColumnLength) {
                    colsSizes[j] = maxColumnLength;
                }
            }
        }
        System.out.println("Computed column sizes: " + Arrays.toString(colsSizes));
        for (int[] d : data2d) {
            for (int j = 0; j < data2d[0].length; j++) {
                System.out.printf("%" + (colsSizes[j] + 1) + "d", d[j]);
                if (j < data2d[0].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    // dynamically adjust column sizes before printing
    static void printArraySmart(int[][] data2d) {
        // 1. find the maximum row length
        int maxRowLength = 0;
        for (int[] ints : data2d) maxRowLength = Math.max(maxRowLength, ints.length);
        /*
        for (int i = 0; i < data2d.length; i++)
            // maxRowLength = (maxRowLength > data2d[i].length) ? maxRowLength : data2d[i].length;
            maxRowLength = Math.max(maxRowLength, data2d[i].length);
         */

        // 2: allocate colWidth array and fill it in with column widths (max string size of each cell)
        int[] colsWidth = new int[maxRowLength];
        for (int row = 0; row < data2d.length; row++) {

            for (int col = 0; col < data2d[row].length; col++) {
            // String dataStr = data2d [row] [col] + "";
            // int length dataStr.length();
                int length = String.valueOf(data2d[row][col]).length();
                colsWidth[col] = Math.max(colsWidth[col], length);
            }
        }
        // 3: print data with columns appropriately sized
        for (int row = 0; row < data2d.length; row++) {

            for (int col = 0; col < data2d[row].length; col++) {
                System.out.printf("%s:" + colsWidth[col] + "d", (col == 0 ? "" : ", "), data2d[row][col]);
            }
            System.out.println();

        }
    }

    static int[] findDuplicates(int[] a1, int[] a2) {
        int maxArraySize = Math.max(a1.length, a2.length);
        int[] duplicatesTemp = new int[maxArraySize];
        int counter = 0;

        for (int i = 0; i < a1.length; i++) {
            boolean found = false;
            for (int j = 0; j < a2.length; j++) {
                if (a1[i] == a2[j]) {
                    boolean alreadyFound = false;
                    for (int k = 0; k < counter; k++) {
                        if (duplicatesTemp[k] == a1[i]) {
                            alreadyFound = true;
                            break;
                        }
                    }
                    if (!alreadyFound) {
                        duplicatesTemp[counter++] = a1[i];
                    }
                    break;

                }
            }
        }
        int[] duplicates = new int[counter];
        System.arraycopy(duplicatesTemp, 0, duplicates, 0, counter);

        return duplicates;
    }

    static int[] findDuplicates(int[][] a1, int[][] a2) {
        int maxSize = a1.length * a1[0].length;
        int[] duplicatesTemp = new int[maxSize];
        int counter = 0;

        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < a1[i].length; j++) {
                boolean alreadyFound = false;
                for (int k = 0; k < a2.length; k++) {
                    for (int l = 0; l < a2[k].length; l++) {
                        if (a1[i][j] == a2[k][l]) {
                            for (int m = 0; m < counter; m++) {
                                if (duplicatesTemp[m] == a1[i][j]) {
                                    alreadyFound = true;
                                    break;
                                }
                            }
                            if (!alreadyFound) {
                                duplicatesTemp[counter++] = a1[i][j];
                            }
                        }
                    }
                }
            }
        }

        int[] duplicates = new int[counter];
        System.arraycopy(duplicatesTemp, 0, duplicates, 0, counter);
        return duplicates;
    }


}
