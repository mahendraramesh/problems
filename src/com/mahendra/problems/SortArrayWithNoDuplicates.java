package com.mahendra.problems;

import java.util.BitSet;
import java.util.Random;

/**
 *
 */
public class SortArrayWithNoDuplicates {
    public static void main(String[] args) {
        int[] arr = new Random().ints(10, 1, 20).toArray();

        sortArray(arr);
    }

    private static void sortArray(int[] arr) {
        boolean res[] = new boolean[arr.length];

        for(int i = 0; i < arr.length; i++) {
            int index = arr[i];

            res[index] = true;
        }

        for(int i = 0; i < res.length; i++) {
            if(res[i] == true) {
                System.out.print(i + " ,");
            }
        }
    }

    private static void sortArrayBitSet(int[] arr) {
        BitSet bitSet = new BitSet();

        for(int i = 0; i < arr.length; i++) {
            int val = arr[i];
            bitSet.set(val, true);
        }

        int index = 0;

        do{
            index = bitSet.nextSetBit(index);
            if(index < 0) {
                break;
            }
            System.out.println(index);
        } while(true);

    }
}
