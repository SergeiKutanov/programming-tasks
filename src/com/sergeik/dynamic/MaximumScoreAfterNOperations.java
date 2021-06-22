package com.sergeik.dynamic;


/**
 * You are given nums, an array of positive integers of size 2 * n. You must perform n operations on this array.
 *
 * In the ith operation (1-indexed), you will:
 *
 * Choose two elements, x and y.
 * Receive a score of i * gcd(x, y).
 * Remove x and y from nums.
 * Return the maximum score you can receive after performing n operations.
 *
 * The function gcd(x, y) is the greatest common divisor of x and y.
 */
public class MaximumScoreAfterNOperations {

    public static void main(String[] args) {
        assert 366 == solution(new int[] {698308,409009,310455,528595,524079,18036,341150,641864,913962,421869,943382,295019});
        assert 527 == solution(new int[]{109497,983516,698308,409009,310455,528595,524079,18036,341150,641864,913962,421869,943382,295019});
        assert 14 == solution(new int[]{1,2,3,4,5,6});
        assert 11 == solution(new int[]{3,4,6,8});
        assert 1 == solution(new int[]{1,2});
    }

    private static int solution(int[] nums) {
        return dfs(nums, new int[nums.length / 2 + 1][1 << nums.length], 1, 0);
    }

    private static int dfs(int[] nums, int[][] dp, int i, int mask) {
        if (i > nums.length / 2)
            return 0;
        if (dp[i][mask] == 0) {
            for (int j = 0; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int newMask = (1 << j) + (1 << k);
                    if ((mask & newMask) == 0)
                        dp[i][mask] = Math.max(
                                dp[i][mask],
                                i * gcd(nums[j], nums[k]) + dfs(nums, dp, i + 1, mask + newMask)
                        );
                }
            }
        }
        return dp[i][mask];
    }

    private static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

}
