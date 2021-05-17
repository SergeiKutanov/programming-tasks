package com.sergeik.arrays;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum
 * is closest to target. Return the sum of the three integers. You may assume that each input would have exactly
 * one solution.
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
        assert 3 == solution(new int[]{0,1,2}, 0);
        assert 3 == solution(new int[]{0,1,2}, 3);
        assert 2 == solution(new int[]{-1,2,1,-4}, 1);
    }

    private static int solution(int[] nums, int target) {
        Arrays.sort(nums);
        Integer res = null;
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[end] + nums[start];
                int diff = Math.abs(target - sum);
                if (res == null || Math.abs(target - res) > diff) {
                    res = sum;
                }
                if (sum > target)
                    end--;
                if (sum < target)
                    start++;
                if (diff == 0)
                    return sum;
            }
        }
        return res;
    }

}
