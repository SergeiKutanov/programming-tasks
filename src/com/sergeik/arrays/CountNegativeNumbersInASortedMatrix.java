package com.sergeik.arrays;

/**
 * Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the
 * number of negative numbers in grid.
 */
public class CountNegativeNumbersInASortedMatrix {

    public static void main(String[] args) {
        assert 8 == solution(new int[][]{
                {4,3,2,-1},
                {3,2,1,-1},
                {1,1,-1,-2},
                {-1,-1,-2,-3}
        });
    }

    private static int solution(int[][] grid) {
        int c = grid[0].length - 1, res = 0, rows = grid.length, cols = grid[0].length;
        for (int r = 0; r < grid.length; r++) {
            while (c >= 0 && grid[r][c] < 0) {
                res += rows - r;
                c--;
            }
        }
        return res;
    }

}
