package com.sergeik.dynamic;

public class MinimumFallingPathSumII {

    public static void main(String[] args) {
        assert 2 == solution(new int[][] {
                {2,1},
                {1,3}
        });
        assert 7 == solution(new int[][] {
                {2,2,1,2,2},
                {2,2,1,2,2},
                {2,2,1,2,2},
                {2,2,1,2,2},
                {2,2,1,2,2}});
        assert 13 == solution(new int[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        });
    }

    private static int solution(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n][n];
        for (int r = n - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (r < n - 1) {
                    int min = Integer.MAX_VALUE;
                    for (int k = 0; k < n; k++) {
                        if (k == c)
                            continue;
                        min = Math.min(dp[r + 1][k], min);
                    }
                    dp[r][c] = grid[r][c] + min;
                } else
                    dp[r][c] = grid[r][c];
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(dp[0][i], res);
        }
        return res;
    }
}
