package com.sergeik.dynamic;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
 * which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        assert 12 == solution(new int[][]{
                {1,3,1},
                {4,5,6}
        });
        assert 7 == solution(new int[][]{
                {1,3,1},
                {1,5,1},
                {1,2,1}
        });
    }

    /**
     * DP approach keeping the minimum sum path to current cell
     * @param grid
     * @return
     */
    private static int solution(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];

        dp[0][0] = grid[0][0];

        for (int r = 1; r < rows; r++)
            dp[r][0] = dp[r - 1][0] + grid[r][0];

        for (int c = 1; c < cols; c++)
            dp[0][c] = dp[0][c - 1] + grid[0][c];


        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                dp[r][c] = Math.min(dp[r - 1][c], dp[r][c - 1]) + grid[r][c];
            }
        }

        return dp[rows - 1][cols - 1];

    }

}
