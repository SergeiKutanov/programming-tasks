package com.sergeik.arrays;

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
        int res = dfs(mat, new boolean[mat.length][mat[0].length], mat.length * mat[0].length);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private static int dfs(int[][] mat, boolean[][] mask, int n) {
        if (getSum(mat) == 0) {
            return numOfOnes(mask);
        }
        int left = numOfOnes(mask);
        if (left == n)
            return Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        for (int r = 0; r < mask.length; r++) {
            for (int c = 0; c < mask[r].length; c++) {
                if (mask[r][c])
                    continue;
                mask[r][c] = true;
                flip(mat, r, c, 0);
                int cRes = dfs(mat, mask, n);
                if (cRes != Integer.MAX_VALUE) {
                    res = Math.min(res, cRes);
                }
                flip(mat, r, c, 0);
                mask[r][c] = false;
            }
        }
        return res;
    }

    private static void flip(int[][] mat, int r, int c, int level) {
        if (r >= 0 && r < mat.length && c >= 0 && c < mat[r].length) {
            mat[r][c] = mat[r][c] == 1 ? 0 : 1;
        }
        if (level < 1) {
            flip(mat, r + 1, c, level + 1);
            flip(mat, r - 1, c, level + 1);
            flip(mat, r, c + 1, level + 1);
            flip(mat, r, c - 1, level + 1);
        }
    }

    private static int numOfOnes(boolean[][] n) {
        int res = 0;
        for (int i = 0; i < n.length; i++)
            for (int j = 0; j < n[i].length; j++)
                res += n[i][j] ? 1 : 0;
        return res;
    }

    private static int getSum(int[][] mat) {
        int sum = 0;
        for (int r = 0; r < mat.length; r++)
            for (int c = 0; c < mat[r].length; c++)
                sum += mat[r][c];
        return sum;
    }

}
