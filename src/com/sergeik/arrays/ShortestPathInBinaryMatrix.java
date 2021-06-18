package com.sergeik.arrays;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix.
 * If there is no clear path, return -1.
 *
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell
 * (i.e., (n - 1, n - 1)) such that:
 *
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share
 * an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 */
public class ShortestPathInBinaryMatrix {

    public static void main(String[] args) {
        assert 3 == solution(new int[][]{
                {0,0,0},
                {1,0,0},
                {1,1,0}
        });
        assert -1 == solution(new int[][]{
                {0,0,0},
                {1,1,1},
                {1,1,0}
        });
        assert 4 == solution(new int[][]{
                {0,0,0},
                {1,1,0},
                {1,1,0}
        });
    }

    private static int solution(int[][] grid) {
        Queue<int[]> bfs = new LinkedList<>();
        if (grid[0][0] != 0)
            return -1;
        bfs.offer(new int[] {0,0});
        grid[0][0] = 1;
        int[][] dirs = new int[][] {
                {1,0},{-1,0},{0,1},{0,-1},{-1,1},{-1,-1},{1,-1},{1,1}
        };
        int res = 1;
        while (!bfs.isEmpty()) {
            int levelSize = bfs.size();
            for (int i = 0; i < levelSize; i++) {
                int[] point = bfs.poll();
                if (point[0] == grid.length - 1 && point[1] == grid[0].length - 1)
                    return res;
                for (int[] dir: dirs) {
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];
                    if (x >= 0 && x < grid.length && y >= 0 && y < grid[x].length && grid[x][y] == 0) {
                        grid[x][y] = 1;
                        bfs.offer(new int[] {x,y});
                    }
                }
            }
            res++;
        }
        return -1;
    }

}
