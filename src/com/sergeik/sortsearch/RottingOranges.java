package com.sergeik.sortsearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n grid where each cell can have one of three values:
 *
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1.
 */
public class RottingOranges {


    public static void main(String[] args) {
        assert 0 == solution(new int[][]{
                {0}
        });
        assert 0 == solution(new int[][]{
                {0,2}
        });
        assert -1 == solution(new int[][]{
                {2,1,1},
                {0,1,1},
                {1,0,1}
        });
        assert 4 == solution(new int[][]{
                {2,1,1},
                {1,1,0},
                {0,1,1}
        });
    }

    private static int solution(int[][] grid) {
        int[][] dirs = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};
        Queue<int[]> bfs = new LinkedList<>();
        for (int r = 0; r < grid.length; r++)
            for (int c = 0; c < grid[r].length; c++)
                if (grid[r][c] == 2) {
                    bfs.offer(new int[]{r,c});
                    grid[r][c] = 0;
                }
        int days = 0;
        while (!bfs.isEmpty()) {
            int levelSize = bfs.size();
            while (levelSize-- > 0) {
                int[] cell = bfs.poll();
                for (int[] dir: dirs) {
                    int x = cell[0] + dir[0];
                    int y = cell[1] + dir[1];
                    if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                        bfs.offer(new int[]{x,y});
                        grid[x][y] = 0;
                    }
                }
            }
            days++;
        }

        for (int r = 0; r < grid.length; r++)
            for (int c = 0; c < grid[r].length; c++)
                if (grid[r][c] == 1)
                    return -1;
        return days > 0 ? --days : days;
    }

}
