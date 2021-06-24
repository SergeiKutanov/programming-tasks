package com.sergeik.dynamic;

import java.util.Arrays;

/**
 * There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are
 * allowed to move the ball to one of the four adjacent four cells in the grid (possibly out of the grid crossing
 * the grid boundary). You can apply at most maxMove moves to the ball.
 *
 * Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out
 * of the grid boundary. Since the answer can be very large, return it modulo 109 + 7.
 */
public class OutOfBoundaryPaths {

    static long res = 0;

    public static void main(String[] args) {
        assert 914783380 == solution(8,50,23,5,26);
        assert 12 == solution(1,3,3,0,1);
        assert 6 == solution(2,2,2,0,0);
    }

    private static int bruteSolution(int m, int n, int maxMove, int startRow, int startColumn) {
        if (startRow < 0 || startRow == m || startColumn < 0 || startColumn == n) {
            res++;
            return 0;
        }
        if (maxMove == 0)
            return 0;
        else {
            bruteSolution(m,n,maxMove - 1, startRow - 1, startColumn);
            bruteSolution(m,n,maxMove - 1, startRow + 1, startColumn);
            bruteSolution(m,n,maxMove - 1, startRow, startColumn - 1);
            bruteSolution(m,n,maxMove - 1, startRow, startColumn + 1);
        }
        return (int)(res % 1000000007);
    }

    private static int solution(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] dp = new int[m][n][maxMove + 1];
        for (int[][] i: dp)
            for (int[] j: i)
                Arrays.fill(j, -1);
        int res = dfs(m,n,maxMove,startRow,startColumn, dp);
        return res;
    }

    private static int dfs(int m, int n, int maxMove, int startRow, int startColumn, int[][][] dp) {
        int mod = 1000000007;
        if (startRow == m || startRow < 0 || startColumn == n || startColumn < 0)
            return 1;
        if (maxMove == 0)
            return 0;
        if (dp[startRow][startColumn][maxMove] >= 0)
            return dp[startRow][startColumn][maxMove];
        dp[startRow][startColumn][maxMove] = (
                (dfs(m, n, maxMove - 1, startRow - 1, startColumn, dp) +
                    dfs(m, n, maxMove - 1, startRow + 1, startColumn, dp)) % mod +
                (dfs(m, n, maxMove - 1, startRow, startColumn - 1, dp) +
                    dfs(m, n, maxMove - 1, startRow, startColumn + 1, dp)) % mod
        ) % mod;
        return dp[startRow][startColumn][maxMove];
    }

}
