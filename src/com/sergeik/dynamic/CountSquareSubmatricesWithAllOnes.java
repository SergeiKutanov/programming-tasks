package com.sergeik.dynamic;

/**
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 */
public class CountSquareSubmatricesWithAllOnes {

    public static void main(String[] args) {
        assert 15 == dpSolution(new int[][] {
                {0,1,1,1},
                {1,1,1,1},
                {0,1,1,1}
        });
    }

    private static int dpSolution(int[][] matrix) {
        int res = 0;
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] > 0 && r > 0 && c > 0) {
                    matrix[r][c] = Math.min(matrix[r - 1][c - 1], Math.min(matrix[r - 1][c], matrix[r][c - 1])) + 1;
                }
                res += matrix[r][c];
            }
        }
        return res;
    }

    private static int solution(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                dp[r][c] = matrix[r - 1][c - 1];
                dp[r][c] += dp[r - 1][c];
                dp[r][c] += dp[r][c - 1];
                dp[r][c] -= dp[r - 1][c - 1];
            }
        }

        int res = 0;
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                for (int side = 0; side < rows; side++) {
                   if (side + r > rows || side + c > cols)
                       continue;
                   int sq = (side + 1) * (side + 1);
                   int x = Math.min(r + side, rows);
                   int y = Math.min(c + side, cols);
                   int upperBoundary = x - side - 1;
                   int leftBoundary = y - side - 1;
                   int sum = dp[x][y] - dp[x][leftBoundary] - dp[upperBoundary][y] + dp[upperBoundary][leftBoundary];
                   if (sum == sq)
                       res++;
                }
            }
        }

        return res;
    }

}
