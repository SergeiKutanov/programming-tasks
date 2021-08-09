package com.sergeik.arrays;

import java.util.Arrays;

/**
 * You are given an integer array nums sorted in non-decreasing order.
 *
 * Build and return an integer array result with the same length as nums such that result[i] is equal to the
 * summation of absolute differences between nums[i] and all the other elements in the array.
 *
 * In other words, result[i] is equal to sum(|nums[i]-nums[j]|) where 0 <= j < nums.length and j != i (0-indexed).
 */
public class SumOfAbsoluteDifferencesInASortedArray {

    public static void main(String[] args) {
        assert Arrays.equals(new int[] {24,15,13,15,21}, solution(new int[] {1,4,6,8,10}));
    }

    private static int[] solution(int[] nums) {
        int[] pref = new int[nums.length + 1], res = new int[nums.length];
        for (int i = 1; i < pref.length; i++)
            pref[i] = pref[i - 1] + nums[i - 1];
        for (int i = 0; i < res.length; i++) {
            int first = Math.abs(nums[i] * (nums.length - i) - (pref[pref.length - 1] - pref[i]));
            int second = 0;
            if (i > 0) {
                second = Math.abs(nums[i] * i - (pref[i]));
            }
            res[i] = first + second;
        }
        return res;
    }

}
