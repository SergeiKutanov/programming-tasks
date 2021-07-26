package com.sergeik.arrays;

/**
 * You start at the cell (rStart, cStart) of an rows x cols grid facing east. The northwest corner is at the first
 * row and column in the grid, and the southeast corner is at the last row and column.
 *
 * You will walk in a clockwise spiral shape to visit every position in this grid. Whenever you move outside the
 * grid's boundary, we continue our walk outside the grid (but may return to the grid boundary later.). Eventually,
 * we reach all rows * cols spaces of the grid.
 *
 * Return an array of coordinates representing the positions of the grid in the order you visited them.
 */
public class SpiralMatrixIII {

    public static void main(String[] args) {
        int[][] exp = new int[][] {
                {0,0},{0,1},{0,2},{0,3}
        };
        int[][] res = solution(1, 4, 0, 0);
        for (int i = 0; i < exp.length; i++)
            for (int j = 0; j < exp[i].length; j++)
                assert exp[i][j] == res[i][j];
    }

    private static int[][] solution(int rows, int cols, int rStart, int cStart) {
        int side = 1;
        int[][] dirs = new int[][] {
                {0,1},{1,0},{0,-1},{-1,0}
        };
        int cells = rows * cols - 1;
        int[][] res = new int[rows * cols][2];
        int idx = 0;
        res[idx++] = new int[]{rStart, cStart};
        while (cells > 0) {
            for (int i = 0; i < dirs.length; i++) {
                int[] dir = dirs[i];
                for (int step = 0; step < side; step++) {
                    rStart += dir[0];
                    cStart += dir[1];
                    if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
                        cells--;
                        res[idx++] = new int[] {rStart, cStart};
                    }
                }
                if (i % 2 == 1)
                    side++;
            }
        }
        return res;
    }

}
