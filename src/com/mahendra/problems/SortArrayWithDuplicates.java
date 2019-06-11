package com.mahendra.problems;

import java.util.Random;

/**
 *
 */
public class SortArrayWithDuplicates {
    public static void main(String[] args) {
        int[] arr = new Random().ints(10, 1, 20).toArray();

        sortArray(arr);
    }

    private static void sortArray(int[] arr) {
        short res[] = new short[arr.length];

        for(int i = 0; i < arr.length; i++) {
            int index = arr[i];
            if(res[index] == 0) {
                res[index] = 1;
            } else {
                res[index] = (short)(res[index] + 1);
            }

        }

        for(int i = 0; i < res.length; i++) {
            if(res[i] > 0) {
                short number = res[i];
                for(int j = 0; j < number; j++) {
                    System.out.print(i + " ,");
                }

            }
        }
    }
}
