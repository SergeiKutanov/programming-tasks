package com.sergeik.arrays;

/**
 * Given a 2D matrix matrix, handle multiple queries of the following type:
 *
 * Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1)
 * and lower right corner (row2, col2).
 * Implement the NumMatrix class:
 *
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix
 * inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 */
public class RangeSumQuery2D {

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {3,0,1,4,2},
                {5,6,3,2,1},
                {1,2,0,1,5},
                {4,1,0,1,7},
                {1,0,3,0,5},
        };
        NumMatrix numMatrix = new NumMatrix(matrix);
        assert 8 == numMatrix.sumRegion(2,1,4,3);
        assert 11 == numMatrix.sumRegion(1,1,2,2);
        assert 12 == numMatrix.sumRegion(1,2,2,4);
        assert 3 == numMatrix.sumRegion(2,1, 2,3);
    }

    static class NumMatrix {

        private int[][] dp;

        /**
         * +-----+-+-------+     +--------+-----+     +-----+---------+     +-----+--------+
         * |     | |       |     |        |     |     |     |         |     |     |        |
         * |     | |       |     |        |     |     |     |         |     |     |        |
         * +-----+-+       |     +--------+     |     |     |         |     +-----+        |
         * |     | |       |  =  |              |  +  |     |         |  -  |              |
         * +-----+-+       |     |              |     +-----+         |     |              |
         * |               |     |              |     |               |     |              |
         * |               |     |              |     |               |     |              |
         * +---------------+     +--------------+     +---------------+     +--------------+
         *
         *    sums[i][j]      =    sums[i-1][j]    +     sums[i][j-1]    -   sums[i-1][j-1]   +
         *
         *                         matrix[i-1][j-1]
         * @param matrix
         */
        NumMatrix(int[][] matrix) {
            if (matrix == null ||
                matrix.length == 0 ||
                matrix[0].length == 0) {
                return;
            }

            dp = new int[matrix.length + 1][matrix[0].length + 1];
            for (int r = 1; r < dp.length; r++) {
                for (int c = 1; c < dp[r].length; c++) {
                    dp[r][c] = dp[r-1][c] + dp[r][c-1] - dp[r-1][c-1] + matrix[r-1][c-1];
                }
            }
        }

        /**
         * +---------------+   +---------+----+   +---+-----------+   +---------+----+   +---+----------+
         * |               |   |         |    |   |   |           |   |         |    |   |   |          |
         * |   (r1,c1)     |   |         |    |   |   |           |   |         |    |   |   |          |
         * |   +------+    |   |         |    |   |   |           |   +---------+    |   +---+          |
         * |   |      |    | = |         |    | - |   |           | - |      (r1,c2) | + |   (r1,c1)    |
         * |   |      |    |   |         |    |   |   |           |   |              |   |              |
         * |   +------+    |   +---------+    |   +---+           |   |              |   |              |
         * |        (r2,c2)|   |       (r2,c2)|   |   (r2,c1)     |   |              |   |              |
         * +---------------+   +--------------+   +---------------+   +--------------+   +--------------+
         * @param row1
         * @param col1
         * @param row2
         * @param col2
         * @return
         */
        public int sumRegion(int row1, int col1, int row2, int col2) {
            int rowMin = Math.min(row1, row2);
            int colMin = Math.min(col1, col2);
            int rowMax = Math.max(row1, row2);
            int colMax = Math.max(col1, col2);
            return dp[rowMax + 1][colMax + 1] - dp[rowMax + 1][colMin] - dp[rowMin][colMax + 1] + dp[rowMin][colMin];
        }
    }

}
