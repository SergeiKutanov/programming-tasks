package com.sergeik.dynamic;

/**
 * You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colors:
 * Red, Yellow, or Green while making sure that no two adjacent cells have the same color (i.e., no two cells that
 * share vertical or horizontal sides have the same color).
 *
 * Given n the number of rows of the grid, return the number of ways you can paint this grid. As the answer may grow
 * large, the answer must be computed modulo 109 + 7.
 */
public class NumberOfWaysToPaintNThreeGrid {

    public static void main(String[] args) {
        assert 30228214 == solution(5000);
        assert 54 == solution(2);
        assert 12 == solution(1);
    }

    /**
     * Let dp[idx][prev1col][prev2col][prev3col] be the number of ways to color the rows of the grid from idx to n-1
     * keeping in mind that the previous row (idx - 1) has colors prev1col, prev2col and prev3col. Build the dp array
     * to get the answer.
     * @param n
     * @return
     */
    private static int solution(int n) {
        int[][][][] dp = new int[n + 1][4][4][4];
        return dfs(n, 0, 0, 0, dp);
    }

    private static int dfs(int n, int a0, int b0, int c0, int[][][][] dp) {
        if (n == 0)
            return 1;
        if (dp[n][a0][b0][c0] != 0)
            return dp[n][a0][b0][c0];
        int ans = 0;
        int[] colors = new int[] {1,2,3};
        for (int a: colors) {
            if (a == a0) continue;
            for (int b: colors) {
                if (b == b0 || b == a) continue;
                for (int c: colors) {
                    if (c == c0 || c == b) continue;
                    ans += dfs(n - 1, a, b, c, dp);
                    ans %= 1_000_000_007;
                }
            }

        }
        return dp[n][a0][b0][c0] = ans;
    }

}
