package com.sergeik.arrays;

import java.util.Arrays;

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 */
public class RotateImage {

    public static void main(String[] args) {
        int[][] matrix = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        int[][] expected = {
                {15, 13, 2, 5},
                {14, 3, 4, 1},
                {12, 6, 8, 9},
                {16, 7, 10, 11}
        };
        solution(matrix);
        for (int i = 0; i < matrix.length; i++) {
            assert Arrays.equals(expected[i], matrix[i]);
        }
    }

    /**
     * Time - O(n*n)
     * Memory = O(1)
     * @param matrix
     *
     *
     * To remember
     * get size
     * iterate layers from 0 to half size
     *  get first (last) and last (size - 1 - layer)
     *  iterate cells in the layer from first to last
     *      calculate offset = i - first
     *      save top = m[first][i] - fi
     *      move left to top -> m[fi] = m[lo][f]
     *      move bottom to left -> m[lof] = m[l][lo]
     *      move right to bottom -> m[l][lo] = m[i][l]
     *      move top to right -> m[i][l] = m[f][i]
     *
     * layer = 0 > size / 2
     *  first = layer
     *  last = size - layer - 1
     *  i = first -> last
     *      offset = i - first
     *      fi -> lof -> llo -> il
     *
     */
    private static void solution(int[][] matrix) {
        int size = matrix.length;
        for (int layer = 0; layer < size / 2; layer++) {
            int first = layer;
            int last = size - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int top = matrix[first][i]; //save top

                //left bottom -> top
                matrix[first][i] = matrix[last - offset][first];
                //bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];
                //right -> bottom
                matrix[last][last - offset] = matrix[i][last];
                //top -> right
                matrix[i][last] = top;
            }
        }
    }



}
