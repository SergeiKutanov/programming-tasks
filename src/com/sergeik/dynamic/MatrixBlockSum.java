package com.sergeik.dynamic;

/**
 * Given a m x n matrix mat and an integer k, return a matrix answer where each answer[i][j]
 * is the sum of all elements mat[r][c] for:
 *
 * i - k <= r <= i + k,
 * j - k <= c <= j + k, and
 * (r, c) is a valid position in the matrix.
 */
public class MatrixBlockSum {

    public static void main(String[] args) {
        int[][] exp;
        int[][] res;
        res = solution(new int[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        }, 1);
        exp = new int[][] {
                {12,21,16},
                {27,45,33},
                {24,39,28}
        };
        for (int i = 0; i < exp.length; i++) {
            for (int j = 0; j < exp[i].length; j++)
                assert exp[i][j] == res[i][j];
        }
    }

    private static int[][] solution(int[][] mat, int k) {
        int rows = mat.length, cols = mat[0].length;
        int[][] res = new int[rows][cols];
        int[][] dp = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dp[r][c] = mat[r][c];
                if (r > 0)
                    dp[r][c] += dp[r - 1][c];
                if (c > 0)
                    dp[r][c] += dp[r][c - 1];
                if (r > 0 && c > 0)
                    dp[r][c] -= dp[r - 1][c - 1];
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int x = Math.min(r + k, rows - 1);
                int y = Math.min(c + k, cols - 1);
                res[r][c] = dp[x][y];
                int upperBorderRow = r - k - 1;
                int leftBorderCol = c - k - 1;
                if (upperBorderRow >= 0)
                    res[r][c] -= dp[upperBorderRow][y];
                if (leftBorderCol >= 0)
                    res[r][c] -= dp[x][leftBorderCol];
                if (upperBorderRow >= 0 && leftBorderCol >= 0)
                    res[r][c] += dp[upperBorderRow][leftBorderCol];
            }
        }

        return res;
    }

}
