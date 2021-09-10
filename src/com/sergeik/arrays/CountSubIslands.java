package com.sergeik.arrays;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's
 * (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells
 * outside of the grid are considered water cells.
 *
 * An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that
 * make up this island in grid2.
 *
 * Return the number of islands in grid2 that are considered sub-islands.
 */
public class CountSubIslands {

    public static void main(String[] args) {
        assert 3 == solution(
                new int[][] {{1,1,1,0,0},{0,1,1,1,1},{0,0,0,0,0},{1,0,0,0,0},{1,1,0,1,1}},
                new int[][] {{1,1,1,0,0},{0,0,1,1,1},{0,1,0,0,0},{1,0,1,1,0},{0,1,0,1,0}}    
            );
    }

    private static int solution(int[][] grid1, int[][] grid2) {
        int res = 0;
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[i].length; j++) {
                if (grid2[i][j] == 1) {
                   res += countIsland(grid1, grid2, i, j);
                }
            }
        }
        return res;
    }

    private static int countIsland(int[][] grid1, int[][] grid2, int i, int j) {
        boolean validIsland = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});
        grid2[i][j] = 0;
        while (!queue.isEmpty()) {
            int[] coor = queue.poll();
            int x = coor[0], y = coor[1];
            if (grid1[x][y] != 1)
                validIsland = false;
            for (int[] dir: new int[][] {{0,1}, {0,-1}, {1,0}, {-1, 0}}) {
                int r = x + dir[0], c = y + dir[1];
                if (r >= 0 && r < grid2.length && c >= 0 && c < grid2[r].length && grid2[r][c] == 1) {
                    queue.offer(new int[] {r, c});
                    grid2[r][c] = 0;
                }
            }
        }
        return validIsland ? 1: 0;
    }

}
