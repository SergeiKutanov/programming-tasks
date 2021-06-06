package com.sergeik.arrays;

/**
 * A split of an integer array is good if:
 *
 * The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from
 * left to right.
 * The sum of the elements in left is less than or equal to the sum of the elements in mid, and the sum of
 * the elements in mid is less than or equal to the sum of the elements in right.
 * Given nums, an array of non-negative integers, return the number of good ways to split nums. As the number may
 * be too large, return it modulo 109 + 7.
 *
 *
 */
public class WaysToSplitArrayIntoThreeSubarrays {

    public static void main(String[] args) {
        assert 10 == solution(new int[] {8975,9106,5349,7891,3795,983,3532,3121,9341,6891,5416,6221,1247,5343,7006});
        assert 0 == solution(new int[] {7,0,5});
        assert 21 == solution(new int[] {0,0,0,0,0,0,0,0});
        assert 3 == solution(new int[] {2,3,5,10});
        assert 0 == solution(new int[] {3,2,1});
        assert 1 == solution(new int[] {1,1,1});
        assert 3 == solution(new int[] {1,2,2,2,5,0});

    }

    private static int solution(int[] nums) {
        int[] dp = new int[nums.length + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1] + nums[i - 1];
        }

        long res = 0;
        for (int i = dp.length - 2; i > 1; i--) {
            //sum of the right subarray
            int sumR = dp[dp.length - 1] - dp[i];
            //minimum end index of left subarray
            int min = findMinIdx(sumR, i, dp);
            if (min == 0)
                min++;
            if (min == i)
                continue;
            //max index of left subarray
            int max = findMaxIdx(min, i, dp);
            res += Math.max(0, max - min + 1);
        }
        return (int) (res % 1000000007);
    }

    private static int findMaxIdx(int leftIdx, int rightIdx, int[] dp) {
        int l = leftIdx;
        int r = rightIdx - 1;
        while (l < r) {
            int m = (l + r) / 2;
            int leftSum = dp[m];
            int rightSum = dp[rightIdx] - dp[m];
            if (leftSum <= rightSum) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        int leftSum = dp[r];
        int rightSum = dp[rightIdx] - dp[r];
        if (leftSum <= rightSum)
            r++;
        return --r;
    }

    private static int findBruteMaxIdx(int leftIdx, int rightIdx, int[] dp) {
        int idx = leftIdx;
        int leftSum = dp[idx];
        int rightSum = dp[rightIdx] - dp[idx];
        while (leftSum <= rightSum && idx < rightIdx) {
            idx++;
            leftSum = dp[idx];
            rightSum = dp[rightIdx] - dp[idx];
        }
        return --idx;
    }

    private static int findMinIdx(int sumR, int rightIdx, int[] dp) {
        int l = 1;
        int r = rightIdx;
        while (l < r) {
            int m = (l + r) / 2;
            int sum = dp[rightIdx] - dp[m == 0 ? 0 : m - 1];
            if (sum > sumR) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        if (l > 0 && dp[rightIdx] - dp[l - 1] > sumR)
            l++;
        return --l;
    }

}
