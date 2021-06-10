package com.sergeik.dynamic;

/**
 * Given an integer array arr, partition the array into (contiguous) subarrays of length at most k.
 * After partitioning, each subarray has their values changed to become the maximum value of that subarray.
 *
 * Return the largest sum of the given array after partitioning. Test cases are generated so that the answer
 * fits in a 32-bit integer.
 */
public class PartitionArrayForMaximumSum {

    public static void main(String[] args) {
        assert 84 == solution(new int[] {1,15,7,9,2,5,10}, 3);
    }

    private static int solution(int[] arr, int k) {
        int[] dp = new int[arr.length + 1];
        for (int i = 1; i < dp.length; i++) {
            int max = 0;
            int best = 0;
            for (int j = 1; j <= k && i - j >= 0; j++) {
                int idx = i - j;
                if (idx >= 0) {
                    max = Math.max(max, arr[idx]);
                    best = Math.max(best, dp[idx] + max * j);
                }
            }
            dp[i] = best;
        }
        return dp[arr.length];
    }

}
