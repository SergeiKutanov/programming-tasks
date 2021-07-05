package com.sergeik.arrays;

/**
 * In MATLAB, there is a handy function called reshape which can reshape an m x n matrix into a new one with a
 * different size r x c keeping its original data.
 *
 * You are given an m x n matrix mat and two integers r and c representing the row number and column number of the
 * wanted reshaped matrix.
 *
 * The reshaped matrix should be filled with all the elements of the original matrix in the same row-traversing
 * order as they were.
 *
 * If the reshape operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise,
 * output the original matrix.
 */
public class ReshapeTheMatrix {

    public static void main(String[] args) {
        int[][] exp, res;
        exp = new int[][]{
                {1,2,3,4}
        };
        res = solution(new int[][]{
                {1,2},
                {3,4}
        }, 1,4);
        for (int i = 0; i < exp.length; i++) {
            for (int j = 0; j < exp[i].length; j++) {
                assert exp[i][j] == res[i][j];
            }
        }
    }

    private static int[][] solution(int[][] mat, int r, int c) {
        if (mat.length * mat[0].length != r * c)
            return mat;
        int[] data = new int[mat.length * mat[0].length];
        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat[i].length; j++)
                data[i * mat[i].length + j] = mat[i][j];

        int[][] res = new int[r][c];
        int idx = 0;
        for (int i = 0; i < res.length; i++)
            for (int j = 0; j < res[i].length; j++)
                res[i][j] = data[idx++];

        return res;
    }

}
