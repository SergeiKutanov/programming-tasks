package com.sergeik.dynamic;

/**
 * There are n piles of stones arranged in a row. The ith pile has stones[i] stones.
 *
 * A move consists of merging exactly k consecutive piles into one pile, and the cost of this move is equal
 * to the total number of stones in these k piles.
 *
 * Return the minimum cost to merge all piles of stones into one pile. If it is impossible, return -1.
 */
public class MinimumCostToMergeStones {

    public static void main(String[] args) {
        assert 20 == solution(new int[] {3,2,4,1}, 2);
        assert -1 == solution(new int[] {3,2,4,1}, 3);
        assert 25 == solution(new int[] {3,5,1,2,6}, 3);
    }

    private static int solution(int[] stones, int k) {
        int n = stones.length;
        //won't merge
        if ((n - 1) % (k - 1) > 0)
            return -1;

        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + stones[i];

        int[][] dp = new int[n][n];
        for (int m = k; m <= n; m++) {
            for (int i = 0; i + m <= n; i++) {
                int j = i + m - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int mid = i; mid < j; mid += k - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid + 1][j]);
                }
                if ((j - i) % (k - 1) == 0)
                    dp[i][j] += prefix[j + 1] - prefix[i];
            }
        }
        return dp[0][n - 1];
    }

}
