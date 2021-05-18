package com.sergeik.sortsearch;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 */
public class Search2DMatrix {

    public static void main(String[] args) {
        assert searchMatrix(new int[][]{
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        }, 16);
    }

    private static boolean searchMatrix(int[][] matrix, int target) {
        int col = matrix[0].length - 1;;
        int row = 0;
        while (col >= 0 && row < matrix.length) {
            if (matrix[row][col] == target)
                return true;
            if (matrix[row][col] < target)
                row++;
            else
                col--;
        }
        return false;
    }

}
