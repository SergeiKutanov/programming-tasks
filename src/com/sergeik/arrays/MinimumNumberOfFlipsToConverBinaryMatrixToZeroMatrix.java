package com.sergeik.arrays;

/**
 * Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbors of
 * it if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighbors if they share one edge.
 *
 * Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.
 *
 * A binary matrix is a matrix with all cells equal to 0 or 1 only.
 *
 * A zero matrix is a matrix with all cells equal to 0.
 */
public class MinimumNumberOfFlipsToConverBinaryMatrixToZeroMatrix {

    public static void main(String[] args) {
        assert -1 == solution(new int[][]{
                {1,0,0},
                {1,0,0}
        });
        assert 6 == solution(new int[][]{
                {1,1,1},
                {1,0,1},
                {0,0,0}
        });
        assert 3 == solution(new int[][] {
                {0,0},
                {0,1}
        });
    }

    private static int solution(int[][] mat) {
        int mask = 0, rows = mat.length, cols = mat[0].length;
        for (int r = 0; r < mat.length; r++)
            for (int c = 0; c < mat[r].length; c++) {
                if (mat[r][c] == 1) {
                    int idx = r * cols + c;
                    mask |= 1 << idx;
                }

            }
        int res = maskDfs(mask, new boolean[rows * cols], 0, rows * cols, cols, rows);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private static int maskDfs(int mask, boolean[] used, int flips, int n, int cols, int rows) {
        if (mask == 0)
            return flips;
        if (flips == n)
            return Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                used[i] = true;
                int r = i / cols, c = i % cols;
                mask = flip(mask, r, c, rows, cols, 0);
                int cRes = maskDfs(mask, used, flips + 1, n, cols, rows);
                if (cRes != Integer.MAX_VALUE)
                    res = Math.min(res, cRes);
                mask = flip(mask, r, c, rows, cols, 0);
                used[i] = false;
            }
        }
        return res;
    }

    private static int flip(int mask, int r, int c, int rows, int cols, int level) {
        if (r >= 0 && r < rows && c >= 0 && c < cols) {
            int idx = r * cols + c;
            mask ^= 1 << idx;
            if (level == 0) {
                mask = flip(mask, r + 1, c, rows, cols, level + 1);
                mask = flip(mask, r - 1, c, rows, cols, level + 1);
                mask = flip(mask, r, c + 1, rows, cols, level + 1);
                mask = flip(mask, r, c - 1, rows, cols, level + 1);
            }
        }
        return mask;
    }

}
