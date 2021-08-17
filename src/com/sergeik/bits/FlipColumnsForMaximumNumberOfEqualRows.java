package com.sergeik.bits;

import java.util.Arrays;

/**
 * You are given an m x n binary matrix matrix.
 *
 * You can choose any number of columns in the matrix and flip every cell in that column (i.e., Change the value of
 * the cell from 0 to 1 or vice versa).
 *
 * Return the maximum number of rows that have all values equal after some number of flips.
 */
public class FlipColumnsForMaximumNumberOfEqualRows {

    public static void main(String[] args) {
        assert 2 == solution(new int[][]{
                {0,0,0},
                {0,0,1},
                {1,1,0}
        });
    }

    private static int solution(int[][] matrix) {
        int res = 0;
        int m = matrix.length, n = matrix[0].length;
        boolean[] checked = new boolean[m];
        for (int i = 0; i < m; i++) {
            if (checked[i]) continue;;
            int count = 0;
            int[] flip = new int[n];
            for (int j = 0; j < n; j++)
                flip[j] = 1 - matrix[i][j];
            for (int j = i; j < m; j++)
                if (Arrays.equals(matrix[i], matrix[j]) || Arrays.equals(matrix[j], flip)) {
                    count++;
                    checked[j] = true;
                }
            res = Math.max(res, count);
        }
        return res;
    }

}
