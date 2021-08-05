package com.sergeik.arrays;

/**
 * Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.
 *
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.
 */
public class ToeplitzMatrix {

    public static void main(String[] args) {
        assert solution(new int[][] {
                {1,2,3,4},
                {5,1,2,3},
                {9,5,1,2}
        });
    }

    private static boolean solution(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            int n = matrix[i][0];
            int x = i + 1, y = 1;
            while (x < matrix.length && y < matrix[x].length) {
                if (matrix[x][y] != n)
                    return false;
                x++;y++;
            }
        }
        for (int j = 0; j < matrix[0].length; j++) {
            int n = matrix[0][j];
            int x = 1, y = j + 1;
            while (x < matrix.length && y < matrix[x].length) {
                if (matrix[x][y] != n)
                    return false;
                x++; y++;
            }
        }
        return true;
    }

}
