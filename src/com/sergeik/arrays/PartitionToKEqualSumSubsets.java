package com.sergeik.arrays;

import java.util.Arrays;

/**
 * Given an integer array nums and an integer k, return true if it is possible to divide this array into k
 * non-empty subsets whose sums are all equal.
 */
public class PartitionToKEqualSumSubsets {

    public static void main(String[] args) {
        assert solution(new int[] {4,3,2,3,5,2,1}, 4);
    }

    private static boolean solution(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0)
            return false;
        int chunkSum = sum / k;
        return backtrack(nums, 0, new boolean[nums.length], k, 0, chunkSum);
    }

    private static boolean backtrack(int[] nums, int idx, boolean[] used, int k, int sum, int target) {
        if (k == 1)
            return true;
        if (sum == target)
            return backtrack(nums, 0, used, k - 1, 0, target);
        for (int i = idx; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                if (backtrack(nums, i + 1, used, k, sum + nums[i], target))
                    return true;
                used[i] = false;
            }
        }
        return false;
    }

}
