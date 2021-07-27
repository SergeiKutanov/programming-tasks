package com.sergeik.bits;

import javafx.util.Pair;

import java.util.Arrays;

/**
 * Given an integer array arr. You have to sort the integers in the array in ascending order by the number of 1's
 * in their binary representation and in case of two or more integers have the same number of 1's you have to sort
 * them in ascending order.
 *
 * Return the sorted array.
 */
public class SortIntegersByNumberOf1Bits {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[] {0,1,2,4,8,3,5,6,7},
                solution(new int[] {0,1,2,3,4,5,6,7,8})
        );
    }

    private static int[] solution(int[] arr) {
        Pair<Integer, Integer>[] count = new Pair[arr.length];
        int idx = 0;
        for (int n: arr) {
            int ones = 0;
            int d = n;
            while (d > 0) {
                ones += d & 1;
                d >>= 1;
            }
            count[idx++] = new Pair<Integer, Integer>(n, ones);
        }
        Arrays.sort(count, (a, b) -> {
            if (a.getValue() == b.getValue())
                return a.getKey() - b.getKey();
            return a.getValue() - b.getValue();
        });
        idx = 0;
        for (Pair<Integer, Integer> pair: count) {
            arr[idx++] = pair.getKey();
        }
        return arr;
    }

}
