package com.sergeik.sortsearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of integers arr, sort the array by performing a series of pancake flips.
 *
 * In one pancake flip we do the following steps:
 *
 * Choose an integer k where 1 <= k <= arr.length.
 * Reverse the sub-array arr[0...k-1] (0-indexed).
 * For example, if arr = [3,2,1,4] and we performed a pancake flip choosing k = 3, we reverse the sub-array
 * [3,2,1], so arr = [1,2,3,4] after the pancake flip at k = 3.
 *
 * Return an array of the k-values corresponding to a sequence of pancake flips that sort arr. Any valid answer that
 * sorts the array within 10 * arr.length flips will be judged as correct.
 */
public class PancakeSorting {

    public static void main(String[] args) {
        assert Arrays.equals(new Integer[] {4,2,4,3}, solution(new int[] {3,2,4,1}).toArray());
    }

    private static List<Integer> solution(int[] arr) {
        List<Integer> res = new LinkedList<>();
        for (int x = arr.length, i; x > 0; x--) {
            for (i = 0; arr[i] != x; i++);
            reverse(arr, i + 1);
            res.add(i + 1);
            reverse(arr, x);
            res.add(x);
        }
        return res;
    }

    private static void reverse(int[] arr, int idx) {
        for (int i = 0, j = idx - 1; i < j; i++, j--) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

}
