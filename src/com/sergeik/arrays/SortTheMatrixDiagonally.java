package com.sergeik.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortTheMatrixDiagonally {

    public static void main(String[] args) {
        int[][] exp = new int[][] {
                {1,1,1,1},
                {1,2,2,2},
                {1,2,3,3}
        };
        int[][] res = solution(new int[][] {
                {3,3,1,1},
                {2,2,1,2},
                {1,1,1,2}
        });
        for (int i = 0; i < exp.length; i++)
            for (int j = 0; j < exp[i].length; j++)
                assert exp[i][j] == res[i][j];
    }

    private static int[][] solution(int[][] mat) {
        for (int r = 0; r < mat.length; r++) {
            int[] data = new int[mat.length - r];
            int i = r, c = 0, idx = 0;
            while (i < mat.length && c < mat[i].length) {
                data[idx++] = mat[i++][c++];
            }
            Arrays.sort(data);
            i = r; c = 0; idx = 0;
            while (i < mat.length && c < mat[i].length) {
                mat[i++][c++] = data[idx++];
            }
        }

        for (int c = 1; c < mat[0].length; c++) {
            List<Integer> data = new ArrayList<>();
            int i = c, r = 0;
            while (r < mat.length && i < mat[0].length) {
                data.add(mat[r++][i++]);
            }
            Collections.sort(data);
            i = c; r = 0;
            int idx = 0;
            while (r < mat.length && i < mat[r].length) {
                mat[r++][i++] = data.get(idx++);
            }
        }
        return mat;
    }

}
