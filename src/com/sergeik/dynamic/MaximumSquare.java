package com.sergeik.dynamic;

/**
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing
 * only 1's and return its area.
 */
public class MaximumSquare {

    public static void main(String[] args) {
        assert 4 == solution(new char[][]{
                {'1', '0', '1', '1', '0', '1'},
                {'1', '1', '1', '1', '1', '1'},
                {'0', '1', '1', '0', '1', '1'},
                {'1', '1', '1', '0', '1', '0'},
                {'0', '1', '1', '1', '1', '1'},
                {'1', '1', '0', '1', '1', '1'},
        });
        assert 16 == solution(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'0', '0', '1', '1', '1'}
        });
        assert 9 == solution(new char[][]{
                {'0', '1', '1', '0', '1'},
                {'1', '1', '0', '1', '0'},
                {'0', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1'},
                {'0', '0', '0', '0', '0'}
        });
        assert 1 == solution(new char[][]{
                {'0', '1'},
                {'1', '0'}
        });
        assert 4 == solution(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        });
    }

    private static int solution(char[][] matrix) {
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        int res = 0;
        for (int r = 1; r <= matrix.length; r++) {
            for (int c = 1; c <= matrix[r - 1].length; c++) {
                if (matrix[r - 1][c - 1] == '1') {
                    dp[r][c] = dp[r - 1][c] + 1;
                }
                if (dp[r][c] > 0) {
                    //extend sides
                    int maxOffset = dp[r][c];
                    int minSide = maxOffset;
                    res = Math.max(1, res);
                    for (int i = 1; i < maxOffset && c - i >= 0; i++) {
                        minSide = Math.min(dp[r][c - i], minSide);
                        int m = Math.min(minSide, i + 1);
                        res = Math.max(res, m * m);
                    }
                }
            }
        }

        return res;
    }

}
