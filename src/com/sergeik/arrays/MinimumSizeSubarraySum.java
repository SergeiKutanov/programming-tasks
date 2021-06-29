package com.sergeik.arrays;

/**
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a
 * contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 *
 *
 */
public class MinimumSizeSubarraySum {

    public static void main(String[] args) {
        assert 2 == solution(7, new int[] {2,3,1,2,4,3});
        assert 1 == solution(4, new int[] {1,4,4});
        assert 0 == solution(11, new int[] {1,1,1,1,1,1,1,1});
    }

    private static int solution(int target, int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }

        int left = 0, right = 0, res = Integer.MAX_VALUE;
        while (right < nums.length) {
            int sum = nums[right] - (left == 0 ? 0 : nums[left - 1]);
            if (sum >= target) {
                res = Math.min(res, right - left + 1);
                left++;
            } else
                right++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

}
