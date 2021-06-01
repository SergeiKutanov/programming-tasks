package com.sergeik.sortsearch;

import java.util.Arrays;


/**
 * You are given an array of positive integers arr. Perform some operations (possibly none) on arr
 * so that it satisfies these conditions:
 *
 * The value of the first element in arr must be 1.
 * The absolute difference between any 2 adjacent elements must be less than or equal to 1. In other
 * words, abs(arr[i] - arr[i - 1]) <= 1 for each i where 1 <= i < arr.length (0-indexed). abs(x) is
 * the absolute value of x.
 * There are 2 types of operations that you can perform any number of times:
 *
 * Decrease the value of any element of arr to a smaller positive integer.
 * Rearrange the elements of arr to be in any order.
 * Return the maximum possible value of an element in arr after performing the operations to satisfy the conditions.
 */
public class MaximumElementAfterDecreasingAndRearranging {

    public static void main(String[] args) {
        assert 3 == solution(new int[]{100,1,1000});
        assert 2 == solution(new int[]{2,2,1,2,1});
    }

    private static int solution(int[] arr) {
        Arrays.sort(arr);
        int max = 0;
        for (int n: arr) {
            max = Math.min(n, max + 1);
        }
        return max;
    }

    private static int fullSolution(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        int max = 1;
        for (int i = 1; i < arr.length; i++) {
            int prev = arr[i - 1];
            int cur = arr[i];
            if (cur > prev + 1)
                cur = prev + 1;
            arr[i] = cur;
            max = Math.max(max, cur);
        }
        return max;
    }

}
