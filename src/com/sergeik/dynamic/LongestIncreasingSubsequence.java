package com.sergeik.dynamic;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        assert 4 == solutionBinarySearchWithDp(new int[]{10,9,2,5,3,7,101,18});
        assert 4 == solution(new int[]{10,9,2,5,3,7,101,18});
    }

    private static int solutionBinarySearchWithDp(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int n: nums) {
            int i = Arrays.binarySearch(dp, 0, len, n);
            if (i < 0)
                i = -(i + 1);
            dp[i] = n;
            if (i == len)
                len++;
        }
        return len;
    }

    private static int solution(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 0; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    maxval = Math.max(maxval, dp[j]);
            }
            dp[i] = maxval + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}
