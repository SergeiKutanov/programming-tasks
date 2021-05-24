package com.sergeik.arrays;

/**
 * Write an efficient algorithm that searches for a target value in an m x n integer matrix. 
 * The matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 */
public class Search2DMatrixII {

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };
        assert dcSolution(matrix, 5);
        assert !dcSolution(matrix, 20);
    }

    private static boolean solution(int[][] matrix, int target) {
        if (matrix == null ||matrix.length == 0 || matrix[0].length == 0)
            return false;
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    private static boolean dcSolution(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        return divideAndConquer(matrix, new int[]{0,0}, new int[]{matrix.length - 1, matrix[0].length - 1}, target);
    }

    private static boolean divideAndConquer(int[][] matrix, int[] leftTop, int[] bottomRight, int target) {
        if (leftTop[0] > bottomRight[0] || leftTop[1] > bottomRight[1] || leftTop[0] >= matrix.length || bottomRight[1] > matrix[0].length)
            return false;
        if (bottomRight[0] - leftTop[0] == 0 && bottomRight[1] - leftTop[1] == 0) {
            return matrix[leftTop[0]][leftTop[1]] == target;
        }
        int rowMid = (leftTop[1] + bottomRight[1]) << 1;
        int colMid = (leftTop[0] + bottomRight[0]) << 1;
        if (target > matrix[rowMid][colMid]) {
            return divideAndConquer(matrix, leftTop, new int[]{rowMid, colMid}, target)
                    || divideAndConquer(matrix, new int[]{leftTop[0], colMid+1}, new int[]{rowMid, bottomRight[1]}, target)
                    || divideAndConquer(matrix, new int[]{rowMid+1, leftTop[1]}, new int[]{bottomRight[0], colMid}, target);
        } else if (target < matrix[rowMid][colMid]) {
            return divideAndConquer(matrix, leftTop, new int[]{rowMid, colMid}, target) ||
                    divideAndConquer(matrix, new int[]{leftTop[0], colMid + 1}, new int[]{rowMid, bottomRight[1]}, target) ||
                    divideAndConquer(matrix, new int[]{rowMid + 1, leftTop[1]}, new int[]{bottomRight[0], colMid}, target);
        } else {
            return true;
        }
    }

}
