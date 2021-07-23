package com.sergeik.greedy;

/**
 * We have a two dimensional matrix grid where each value is 0 or 1.
 *
 * A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s
 * to 1s, and all 1s to 0s.
 *
 * After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of
 * the matrix is the sum of these numbers.
 *
 * Return the highest possible score.
 */
public class ScoreAfterFlippingMatrix {

    public static void main(String[] args) {
        assert 11 == solution(new int[][] {
            {0,1},
            {0,1},
            {0,1},
            {0,0}
        });
        assert 39 == solution(new int[][] {
                {0,0,1,1},
                {1,0,1,0},
                {1,1,0,0}
        });
    }

    private static int solution(int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            if (grid[r][0] == 0)
                flipRow(r, grid);
        }
        for (int c = 1; c < grid[0].length; c++) {
            int numberOfOnes = 0;
            for (int r = 0; r < grid.length; r++)
                numberOfOnes += grid[r][c];
            if (numberOfOnes * 2 < grid.length)
                flipColumn(c, grid);
        }

        int res = 0;
        for (int r = 0; r < grid.length; r++)
            for (int c = 0; c < grid[r].length; c++)
                res += grid[r][c] * (1 << (grid[r].length - c - 1));
        return res;
    }

    private static void flipColumn(int c, int[][] grid) {
        for (int r = 0; r < grid.length; r++)
            grid[r][c] = grid[r][c] ^ 1;
    }

    private static void flipRow(int r, int[][] grid) {
        for (int c = 0; c < grid[r].length; c++)
            grid[r][c] = grid[r][c] ^ 1;
    }

}
