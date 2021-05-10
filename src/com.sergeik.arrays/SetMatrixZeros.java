package com.sergeik.arrays;

import com.sergeik.utils.Compare;

/**
 * Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.
 *
 * Follow up:
 *
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class SetMatrixZeros {

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };
        solutionInPlace(matrix);
        assert Compare.compareMatrix(new int[][] {
                {1,0,1},
                {0,0,0},
                {1,0,1}
        }, matrix);

        matrix = new int[][] {
                {0,1,2,0},
                {3,4,5,2},
                {1,3,1,5}
        };
        solutionInPlace(matrix);
        assert Compare.compareMatrix(new int[][] {
                {0,0,0,0},
                {0,4,5,0},
                {0,3,1,0}
        }, matrix);

        matrix = new int[][] {
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };
//        solution(matrix);
        solutionInPlace(matrix);
        assert Compare.compareMatrix(
                new int[][] {
                        {1,0,1},
                        {0,0,0},
                        {1,0,1}
                },
                matrix
        );

        matrix = new int[][] {
                {0,1,2,0},
                {3,4,5,2},
                {1,3,1,5}
        };
        solutionInPlace(matrix);

//        solution(matrix);
        assert Compare.compareMatrix(new int[][] {
                {0,0,0,0},
                {0,4,5,0},
                {0,3,1,0}
        }, matrix);
    }

    private static void solution(int[][] matrix) {
        boolean[] zeroRows = new boolean[matrix.length];
        boolean[] zeroCols = new boolean[matrix[0].length];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] == 0) {
                    zeroRows[r] = true;
                    zeroCols[c] = true;
                }
            }
        }
        for (int r = 0; r < zeroRows.length; r++) {
            if (zeroRows[r]) {
                for (int c = 0; c < matrix[r].length; c++)
                    matrix[r][c] = 0;
            }
        }
        for (int c = 0; c < zeroCols.length; c++) {
            if (zeroCols[c]) {
                for (int r = 0; r < matrix.length; r++)
                    matrix[r][c] = 0;
            }
        }
    }

    private static void solutionInPlace(int[][] matrix) {
        boolean isFirstColZero = false;

        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row][0] == 0) isFirstColZero = true;
            for (int col = 1; col < matrix[0].length; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }

        for (int row = matrix.length - 1; row >= 0; row--) {
            for (int col = matrix[0].length - 1; col >= 1; col--) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
            if (isFirstColZero)
                matrix[row][0] = 0;
        }
    }

}
