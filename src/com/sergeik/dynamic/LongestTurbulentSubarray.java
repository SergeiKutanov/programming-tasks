package com.sergeik.dynamic;

/**
 * Given an integer array arr, return the length of a maximum size turbulent subarray of arr.
 *
 * A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
 *
 * More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:
 *
 * For i <= k < j:
 * arr[k] > arr[k + 1] when k is odd, and
 * arr[k] < arr[k + 1] when k is even.
 * Or, for i <= k < j:
 * arr[k] > arr[k + 1] when k is even, and
 * arr[k] < arr[k + 1] when k is odd.
 */
public class LongestTurbulentSubarray {

    public static void main(String[] args) {
        assert 1 == solution(new int[] {100});
        assert 5 == solution(new int[] {9,4,2,10,7,8,8,1,9});
    }

    private static int solution(int[] arr) {
        int res = 0;
        int[][] dp = new int[arr.length][2];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                dp[i][0] = dp[i - 1][1] + 1;
                res = Math.max(res, dp[i][0]);
            } else if (arr[i - 1] < arr[i]) {
                dp[i][1] = dp[i - 1][0] + 1;
                res = Math.max(res, dp[i][1]);
            }
        }
        return res + 1;
    }

}
