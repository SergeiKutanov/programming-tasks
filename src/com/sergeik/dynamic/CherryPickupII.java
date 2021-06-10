package com.sergeik.dynamic;

/**
 * Given a rows x cols matrix grid representing a field of cherries. Each cell in grid represents the number
 * of cherries that you can collect.
 *
 * You have two robots that can collect cherries for you, Robot #1 is located at the top-left corner (0,0) ,
 * and Robot #2 is located at the top-right corner (0, cols-1) of the grid.
 *
 * Return the maximum number of cherries collection using both robots  by following the rules below:
 *
 * From a cell (i,j), robots can move to cell (i+1, j-1) , (i+1, j) or (i+1, j+1).
 * When any robot is passing through a cell, It picks it up all cherries, and the cell becomes an empty cell (0).
 * When both robots stay on the same cell, only one of them takes the cherries.
 * Both robots cannot move outside of the grid at any moment.
 * Both robots should reach the bottom row in the grid.
 */
public class CherryPickupII {

    public static void main(String[] args) {
        assert 24 == solution(new int[][]{
                {3,1,1},
                {2,5,1},
                {1,5,5},
                {2,1,1}
        });
    }

    private static int solution(int[][] grid) {
        Integer[][][] dp = new Integer[grid.length][grid[0].length][grid[0].length];
        int res = dfs(dp, 0, 0, grid[0].length - 1, grid);
        return res;
    }

    private static int dfs(Integer[][][] dp, int r, int c, int c2, int[][] grid) {

        int cols = grid[0].length;
        if (r == grid.length)
            return 0;
        if (dp[r][c][c2] != null)
            return dp[r][c][c2];
        int res = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nextC1 = c + i;
                int nextC2 = c2 + j;
                if (nextC1 >= 0 && nextC1 < cols && nextC2 >= 0 && nextC2 < cols) {
                    int cMax = dfs(dp, r + 1, nextC1, nextC2, grid);
                    res = Math.max(res, cMax);
                }
            }
        }
        int collected = c == c2 ? grid[r][c] : grid[r][c] + grid[r][c2];
        dp[r][c][c2] = collected + res;
        return dp[r][c][c2];
    }

}
