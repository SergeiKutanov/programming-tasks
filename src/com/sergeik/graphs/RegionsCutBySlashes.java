package com.sergeik.graphs;

/**
 * An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or
 * blank space ' '. These characters divide the square into contiguous regions.
 *
 * Given the grid grid represented as a string array, return the number of regions.
 *
 * Note that backslash characters are escaped, so a '\' is represented as '\\'.
 */
public class RegionsCutBySlashes {

    public static void main(String[] args) {
        assert 3 == solution(new String[] {"//","/ "});
        assert 4 == solution(new String[] {"\\/","/\\"});
    }

    /**
     * Idea is to transform each slash into 3*3 region and put 1s as slash direction
     * @param grid
     * @return
     */
    private static int solution(String[] grid) {
        int n = grid.length, res = 0;
        int[][] g = new int[n * 3][n * 3];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (grid[i].charAt(j) == '/')
                    g[i * 3][j * 3 + 2] = g[i * 3 + 1][j * 3 + 1] = g[i * 3 + 2][j * 3] = 1;
                else if (grid[i].charAt(j) == '\\') {
                    g[i * 3][j * 3] = g[i * 3 + 1][j * 3 + 1] = g[i * 3 + 2][j * 3 + 2] = 1;
                }
            }
        for (int i = 0; i < g.length; i++)
            for (int j = 0; j < g.length; j++)
                res += dfs(i, j, g) > 0 ? 1 : 0;
        return res;
    }

    private static int dfs(int i, int j, int[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid.length || grid[i][j] != 0)
            return 0;
        grid[i][j] = 1;
        return 1 + dfs(i - 1, j, grid) + dfs(i + 1, j, grid) + dfs(i, j - 1, grid) + dfs(i, j + 1, grid);
    }

}
