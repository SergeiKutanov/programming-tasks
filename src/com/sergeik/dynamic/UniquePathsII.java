package com.sergeik.dynamic;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach
 * the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 */
public class UniquePathsII {

    public static void main(String[] args) {
        assert 2 == solution(new int[][]{
                {0,0,0},
                {0,1,0},
                {0,0,0}
        });
        assert 1 == solution(new int[][]{
                {0,1},
                {0,0}
        });
    }

    private static int solution(int[][] obstacleGrid) {
        return dynamicSolution(obstacleGrid);
    }

    private static int dynamicSolution(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1)
            return 0;

        obstacleGrid[0][0] = 1;
        for (int i = 1; i < rows; i++)
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1: 0;

        for (int i = 1; i < cols; i++)
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1: 0;

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (obstacleGrid[r][c] == 0) {
                    obstacleGrid[r][c] = obstacleGrid[r - 1][c] + obstacleGrid[r][c - 1];
                } else {
                    obstacleGrid[r][c] = 0;
                }
            }
        }

        return obstacleGrid[rows - 1][cols - 1];
    }

    private static int dpAllSolution(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1)
            return 0;

        int[][] dirs = new int[][] {
                {0, -1},
                {-1, 0}
        };
        int dp[][] = new int[m][n];
        dp[0][0] = 1;
        for (int r = 0; r < dp.length; r++) {
            for (int c = 0; c < dp[0].length; c++) {
                if (obstacleGrid[r][c] == 1) {
                    dp[r][c] = 0;
                } else {
                    for (int[] d: dirs) {
                        int x = r + d[0];
                        int y = c + d[1];
                        if (x >= 0 && x < m && y >= 0 && y < n) {
                            dp[r][c] += dp[x][y];
                        }
                    }
                }
            }
        }
        return dp[m - 1][n - 1];
    }

}
