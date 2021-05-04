package com.sergeik.arrays;

/**
 * Given an array nums with n integers, your task is to check if it could become non-decreasing
 * by modifying at most one element.
 *
 * We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for
 * every i (0-based) such that (0 <= i <= n - 2).
 */
public class NonDecreasingArray {

    public static void main(String[] args) {
        assert !solution(new int[]{3,4,2,3});
        assert solution(new int[]{4,2,3});
        assert !solution(new int[]{4,2,1});
    }

    private static boolean solution(int[] nums) {
        boolean changed = false;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (changed)
                    return false;
                if (i != 0 && nums[i-1] > nums[i+1]) {
                    nums[i+1] = nums[i];
                }
                changed = true;
            }
        }
        return true;
    }

}
