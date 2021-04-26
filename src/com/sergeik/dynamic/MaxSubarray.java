package com.sergeik.dynamic;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 */
public class MaxSubarray {

    public static void main(String[] args) {
        int[] nums = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        assert 6 == solution(nums);
        assert 1 == solution(new int[] {1});
        assert -1 == solution(new int[] {-2, -1});
        assert -1 == solution(new int[] {-1});
        assert 23 == solution(new int[] {5,4,-1,7,8});
    }

    private static int solution(int[] nums) {
        int bestSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            if (bestSum < currentSum)
                bestSum = currentSum;
            if (currentSum < 0)
                currentSum = 0;
        }
        return bestSum;
    }

}
