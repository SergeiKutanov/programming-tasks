package com.sergeik.greedy;

import java.util.Arrays;

/**
 * You are given a binary matrix matrix of size m x n, and you are allowed to rearrange the columns
 * of the matrix in any order.
 *
 * Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after
 * reordering the columns optimally.
 */
public class LargestSubmatrixWithRearrangements {

    public static void main(String[] args) {

        assert 2 == solution(new int[][]{
                {1,1,0},
                {1,0,1}
        });

        assert 3 == solution(new int[][]{
                {1,0,1,0,1}
        });

        assert 4 == solution(new int[][]{
                {0,0,1},
                {1,1,1},
                {1,0,1}
        });
    }

    private static int solution(int[][] matrix) {
        for (int c = 0; c < matrix[0].length; c++) {
            for (int r = 1; r < matrix.length; r++) {
                if (matrix[r][c] != 0)
                    matrix[r][c] = matrix[r - 1][c] + matrix[r][c];
            }
        }

        int res = 0;
        for (int r = 0; r < matrix.length; r++) {
            int rowMax = 0;
            Arrays.sort(matrix[r]);
            for (int c = matrix[r].length- 1; c >= 0;) {
                while (c >= 0 && matrix[r][c] == 0)
                    c--;
                int max = 0;
                int cStart = c;
                while (c >= 0 && matrix[r][c] > 0) {
                    max = Math.max(max, matrix[r][c] * (cStart - c + 1));
                    c--;
                }
                rowMax = Math.max(max, rowMax);
            }
            res = Math.max(rowMax, res);
        }

        return res;
    }

}
