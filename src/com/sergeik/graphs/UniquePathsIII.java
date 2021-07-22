package com.sergeik.graphs;

/**
 * On a 2-dimensional grid, there are 4 types of squares:
 *
 * 1 represents the starting square.  There is exactly one starting square.
 * 2 represents the ending square.  There is exactly one ending square.
 * 0 represents empty squares we can walk over.
 * -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over
 * every non-obstacle square exactly once.
 */
public class UniquePathsIII {

    private static int res = 0;

    public static void main(String[] args) {
        assert 2 == solution(new int[][] {
                {1,0,0,0},
                {0,0,0,0},
                {0,0,2,-1}
        });
    }

    private static int solution(int[][] grid) {
        res = 0;
        int[] start = new int[0];
        int cells = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1)
                    start = new int[]{i,j};
                if (grid[i][j] != -1)
                    cells++;
            }


        grid[start[0]][start[1]] = -2;
        backtrack(grid, start[0], start[1], cells, 1);
        return res;
    }

    private static void backtrack(int[][] grid, int x, int y, int cells, int visitedCount) {
        if (grid[x][y] == 2) {
            if (visitedCount == cells)
                res++;
            return;
        }
        int[][] dirs = new int[][] {{0,1},{0,-1},{1,0},{-1,0}};
        grid[x][y] = -2;
        for (int[] dir: dirs) {
            int r = x + dir[0], c = y + dir[1];
            if (r >= 0 && r < grid.length && c >= 0 && c < grid[r].length && grid[r][c] >= 0) {
                backtrack(grid, r, c, cells, visitedCount + 1);
            }
        }
        grid[x][y] = 0;
    }

}
