package com.structures.day01arraycross;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day01ArrayCrossApplication {

    public static void main(String[] args) {
        SpringApplication.run(Day01ArrayCrossApplication.class, args);
        int[][] data2D = {
                {1, 3, 6, 8},
                {7, 1, 2, 3},
                {8, 3, 2, 1},
                {1, 7, 1, 9},
        };

        int[][] data2DJagged = {
                {1, 3, 6, 8, 9, 1},
                {7, 1, 2, 3},
                {8, 3, 2},
                {1, 7, 1, 9},
        };


        print2D(data2D);
        System.out.println();
        print2D(data2DJagged);

        int result = getIfExists(data2DJagged, 3, 4);
        System.out.println("If exists result: " + result);

        int resultSum = sumOfCross(data2DJagged, 0, 5);
        System.out.println("If sum result: " + resultSum);

        int minSum = sumOfCross(data2D, 0, 0);
        int minRow = 0;
        int minCol = 0;

        for (int i=0; i<data2D.length; i++) {
            for (int j=0; j<data2D[i].length; j++) {
                int crossSum = sumOfCross(data2D, i, j);
                if (minSum > crossSum) {
                    minSum = crossSum;
                    minRow = i;
                    minCol = j;
                }
            }
        }
        System.out.println("Min sum: " + minSum + ", min row: " + minRow + ", min col: " + minCol);


        // PART B: Create a new array to store cross-sums
        int[][] data2DSums = duplicateEmptyArray2D(data2D);

        // Fill data2DSums with the cross-sums
        for (int i = 0; i < data2D.length; i++) {
            for (int j = 0; j < data2D[i].length; j++) {
                data2DSums[i][j] = sumOfCross(data2D, i, j);
            }
        }

        System.out.println("Cross-Sums Array:");
        print2D(data2DSums);
    }


    /**
     * @param data
     * @param row
     * @param col
     * @return
     */
    static int getIfExists(int[][] data, int row, int col) {
        // If exists, return the element, otherwise return 0
       /* if (row >= 0 && row < data.length) {
            if (col >= 0 && col < data[row].length) {
                return data[row][col];
            }
        }
        return 0;
        */
        try{
            return data[row][col];
        } catch (IndexOutOfBoundsException e){
            return 0  ;
        }
    }

    static int sumOfCross(int[][] data, int row, int col) {
        // return sum of the element at row/col
        // plus (if they exist) element above, below, to the left and right of it
        return getIfExists(data, row, col) + getIfExists(data, row - 1, col) + getIfExists(data, row + 1, col)
                + getIfExists(data, row, col + 1) + getIfExists(data, row, col - 1);
    }

    static int[] getSmallestCrossSum (int[][] data, int row, int col) {
        int minVal = sumOfCross(data, 0, 0); // Integer.MAX_VALUE;
        int minRow = 0;
        int minCol = 0;

        for (int i=0; i<data.length; i++) {
            for (int j=0; j<data[i].length; j++) {
                int valSum = sumOfCross(data, i, j);
                if (minVal > valSum) {
                    minVal = valSum;
                    minRow = i;
                    minCol = j;
                }
            }
        }
        return new int[]{minVal, minRow, minCol};
    }

    // useful helper for debugging
    static void print2D(int[][] data2d) {
        /*
        for (int i = 0; i < data2d.length; i++) {
            for (int j = 0; j < data2d[i].length; j++) {
                System.out.printf(data2d[i][j] + "");
                if (j < data2d[i].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
         */
        for (int[] row : data2d) {
            for (int elem : row) {
                System.out.print(elem + "\t");
            }
            System.out.println();
        }

    }

    // duplicate a jagged array
    static int[][] duplicateEmptyArray2D(int[][] orig2d) {
        int[][] duplicate = new int[orig2d.length][];
        for (int i = 0; i < orig2d.length; i++) {
            duplicate[i] = new int[orig2d[i].length];
        }
        return duplicate;
    }


}
