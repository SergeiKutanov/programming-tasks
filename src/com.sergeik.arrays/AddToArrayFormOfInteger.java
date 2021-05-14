package com.sergeik.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * The array-form of an integer num is an array representing its digits in left to right order.
 *
 * For example, for num = 1321, the array form is [1,3,2,1].
 * Given num, the array-form of an integer, and an integer k, return the array-form of the integer num + k.
 */
public class AddToArrayFormOfInteger {

    public static void main(String[] args) {
        assert Arrays.equals(
                new Integer[]{1,0,0,0,0,0,0,0,0,0,0},
                solution(new int[]{9,9,9,9,9,9,9,9,9,9}, 1).toArray()
        );
        assert Arrays.equals(
                new Integer[]{1,0,2,1},
                solution(new int[]{2,1,5}, 806).toArray()
        );
        assert Arrays.equals(
                new Integer[]{4,5,5},
                solution(new int[]{2,7,4}, 181).toArray()
        );
        assert Arrays.equals(
                new Integer[]{1,2,3,4},
                solution(new int[]{1,2,0,0}, 34).toArray()
        );
    }

    private static List<Integer> solution(int[] num, int k) {
        List<Integer> res = new LinkedList<>();
        int carryOver = 0;

        int[] result = new int[Math.max(num.length, String.valueOf(k).length()) + 1];
        int index = result.length - 1;
        for (int i = num.length - 1; i >= 0; i--) {
            int d = k % 10;
            k /= 10;
            d = d + num[i] + carryOver;
            carryOver = d > 9 ? d / 10 : 0;
            result[index--] = d % 10;
        }

        while (k > 0) {
            int d = carryOver + k % 10;
            carryOver = d > 9 ? d / 10 : 0;
            result[index--] = d % 10;
            k /= 10;
        }
        if (carryOver > 0)
            result[index--] = carryOver;

        int start = result[0] == 0 ? 1 : 0;
        for (int i = start; i < result.length; i++) {
            res.add(result[i]);
        }

        return res;
    }

}
