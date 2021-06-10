package com.sergeik.dynamic;

/**
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 *
 * A falling path starts at any element in the first row and chooses the element in the next row that
 * is either directly below or diagonally left/right. Specifically, the next element from position (row, col)
 * will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 */
public class MinimumFallingPathSum {

    public static void main(String[] args) {
        assert -48 == solution(new int[][]{
                {-48}
        });
        assert -59 == solution(new int[][] {
                {-19, 57},
                {-40, -5}
        });
        assert 13 == solution(new int[][]{
                {2,1,3},
                {6,5,4},
                {7,8,9}
        });
    }

    private static int solution(int[][] matrix) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < matrix[0].length; i++) {
            int cMin = dfs(matrix, 0, i, new Integer[matrix.length][matrix[0].length]);
            res = Math.min(res, cMin);
        }
        return res;
    }

    private static int dfs(int[][] matrix, int r, int c, Integer[][] memo) {
        if (r >= matrix.length)
            return 0;
        if (memo[r][c] != null)
            return memo[r][c];
        int[] dirs = new int[] {-1, 0, 1};
        int res = Integer.MAX_VALUE;
        for (int dir: dirs) {
            int nc = c + dir;
            if (nc >= 0 && nc < matrix[0].length) {
                int cMin = dfs(matrix, r + 1, c + dir, memo);
                res = Math.min(res, cMin);
            }

        }
        res += matrix[r][c];
        memo[r][c] = res;
        return res;
    }

}
