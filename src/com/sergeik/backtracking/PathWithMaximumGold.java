package com.sergeik.backtracking;

public class PathWithMaximumGold {

    private static int max = 0;

    public static void main(String[] args) {
        assert 24 == solution(new int[][] {
                {0,6,0},
                {5,8,7},
                {0,9,0}
        });
    }

    private static int solution(int[][] grid) {
        max = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) continue;
                int sum = grid[i][j];
                grid[i][j] = 0;
                backtrack(grid, i, j, sum);
                grid[i][j] = sum;
            }
        return max;
    }

    private static void backtrack(int[][] grid, int r, int c, int sum) {
        max = Math.max(max, sum);
        int[][] dirs = new int[][] {{0,1}, {1,0}, {-1,0}, {0,-1}};
        for (int[] dir: dirs) {
            int x = r + dir[0], y = c + dir[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[x].length && grid[x][y] > 0) {
                int val = grid[x][y];
                grid[x][y] = 0;
                backtrack(grid, x, y, sum + val);
                grid[x][y] = val;
            }
        }
    }

}
