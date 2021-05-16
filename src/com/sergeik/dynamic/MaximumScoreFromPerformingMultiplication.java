package com.sergeik.dynamic;

/**
 * You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m.
 * The arrays are 1-indexed.
 *
 * You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:
 *
 * Choose one integer x from either the start or the end of the array nums.
 * Add multipliers[i] * x to your score.
 * Remove x from the array nums.
 * Return the maximum score after performing m operations.
 */
public class MaximumScoreFromPerformingMultiplication {

    public static void main(String[] args) {
        assert 14 == solution(
                new int[]{1,2,3},
                new int[]{3,2,1}
        );
        assert 102 == solution(
                new int[]{-5,-3,-3,-2,7,1},
                new int[]{-10,-5,3,4,6}
        );
    }

    /**
     * DP contains previously calculated recursive calls.
     * Complexity
     * -----------
     * Time: O(2 * m^2), where m <= 10^3
     * Space: O(m^2)
     *
     * @param nums
     * @param multipliers
     * @return
     */
    private static int solution(int[] nums, int[] multipliers) {
        Integer[][] memo = new Integer[multipliers.length][multipliers.length];
        return dp(0, 0, nums, multipliers, memo);
    }

    private static int dp(int start, int i, int[] nums, int[] multipliers, Integer[][] memo) {
        if (i == multipliers.length)
            return 0;
        if (memo[start][i] != null)
            return memo[start][i];
        int end = nums.length - i + start - 1;
        int pickLeft = nums[start] * multipliers[i] + dp(start + 1, i + 1, nums, multipliers, memo);
        int pickRight = nums[end] * multipliers[i] + dp(start, i + 1, nums, multipliers, memo);
        memo[start][i] = Math.max(pickLeft, pickRight);
        return memo[start][i];
    }

}
