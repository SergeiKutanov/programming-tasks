package com.sergeik.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group
 * of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
 *
 * Return the number of closed islands.
 */
public class NumberOfClosedIslands {

    public static void main(String[] args) {
        assert 3 == solution(new int[][] {
                {1,0,1,1,1,1,0,0,1,0},
                {1,0,1,1,0,0,0,1,1,1},
                {0,1,1,0,0,0,1,0,0,0},
                {1,0,1,1,0,1,0,0,1,0},
                {0,1,1,1,0,1,0,1,0,0},
                {1,0,0,1,0,0,1,0,0,0},
                {1,0,1,1,1,0,0,1,1,0},
                {1,1,0,1,1,0,1,0,1,1},
                {0,0,1,1,1,0,1,0,1,1},
                {1,0,0,1,1,1,1,0,1,1}
        });
        assert 2 == solution(new int[][] {
                {1,1,1,1,1,1,1,0},
                {1,0,0,0,0,1,1,0},
                {1,0,1,0,1,1,1,0},
                {1,0,0,0,0,1,0,1},
                {1,1,1,1,1,1,1,0}
        });
    }

    private static int solution(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int res = 0;
        boolean[][] seen = new boolean[n][m];
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (grid[i][j] == 0 && ! seen[i][j]) {
                    if (dfs(grid, i, j, seen))
                        res++;
                }

            }
        }
        return res;
    }

    private static boolean dfs(int[][] grid, int i, int j, boolean[][] seen) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});
        seen[i][j] = true;
        int[][] dirs = new int[][] {{0,1}, {0,-1},{1,0}, {-1,0}};
        boolean isClosed = true;
        while (!queue.isEmpty()) {
            int[] coord = queue.poll();
            int r = coord[0], c = coord[1];
            if (r == 0 || r == grid.length - 1 || c == 0 || c == grid[0].length - 1)
                isClosed = false;
            for (int[] d: dirs) {
                int x = r + d[0], y = c + d[1];
                if (x >= 0 && x < grid.length && y >= 0 && y < grid[x].length && grid[x][y] == 0 && !seen[x][y]) {
                    seen[x][y] = true;
                    queue.offer(new int[] {x, y});
                }
            }
        }
        return isClosed;
    }

}
