package com.sergeik.arrays;

import java.util.*;

/**
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
 * The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 */
public class FindKClosestElements {

    public static void main(String[] args) {
        assert Arrays.equals(
                new Integer[] {10},
                solution(new int[] {1,1,1,10,10,10}, 1, 9).toArray()
        );
        assert Arrays.equals(
                new Integer[] {1,2,3,4},
                solution(new int[] {1,2,3,4,5}, 4, -1).toArray()
        );
        assert Arrays.equals(
                new Integer[] {1,2,3,4},
                solution(new int[] {1,2,3,4,5}, 4, 3).toArray()
        );
    }

    private static List<Integer> solution(int[] arr, int k, int x) {
        int left = 0, right = arr.length - 1;
        while (left < right && right - left > k - 1) {
            int d1 = Math.abs(x - arr[left]);
            int d2 = Math.abs(x - arr[right]);
            if (d1 > d2) {
                left++;
            } else if (d2 > d1) {
                right--;
            } else {
                if (arr[left] > arr[right])
                    left++;
                else
                    right--;
            }
        }
        List<Integer> res = new LinkedList<>();
        for (int i = left; i <= right; i++)
            res.add(arr[i]);
        return res;
    }

}
