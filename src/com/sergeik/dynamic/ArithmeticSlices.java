package com.sergeik.dynamic;

/**
 * An integer array is called arithmetic if it consists of at least three elements and
 * if the difference between any two consecutive elements is the same.
 *
 * For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
 * Given an integer array nums, return the number of arithmetic subarrays of nums.
 *
 * A subarray is a contiguous subsequence of the array.
 */
public class ArithmeticSlices {

    public static void main(String[] args) {
        assert 1 == solution(new int[] {1,2,3});
        assert 0 == solution(new int[] {0});
        assert 3 == solution(new int[] {1,2,3,4});
        assert 6 == solution(new int[] {1,2,3,4,7,6,4,3,2,1});

        assert 1 == twoPointersSolution(new int[] {1,2,3});
        assert 0 == twoPointersSolution(new int[] {0});
        assert 3 == twoPointersSolution(new int[] {1,2,3,4});
        assert 6 == twoPointersSolution(new int[] {1,2,3,4,7,6,4,3,2,1});
    }

    private static int solution(int[] nums) {
        if (nums.length < 3)
            return 0;

        int[] dp = new int[nums.length];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i - 2] - nums[i - 1] == nums[i - 1] - nums[i]) {
                dp[i] = Math.max(3, dp[i - 1] + 1);
            }
        }

        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] != 0)
                res += dp[i] - 2;
        }
        return res;
    }

    private static int twoPointersSolution(int[] nums) {
        if (nums.length < 3)
            return 0;
        int res = 0, l = 0, r = 1, diff;
        while (r < nums.length) {
            diff = nums[l] - nums[r];
            while (r < nums.length - 1 && (nums[r] - nums[r + 1] == diff)) {
                r++;
            }
            int len = r - l + 1;
            if (len > 2) {
                int seqEnd = len - 2;
                res += seqEnd * ((seqEnd + 1.0) / 2) ;
            }
            l = r;
            r = l + 1;
        }

        return res;
    }

}
