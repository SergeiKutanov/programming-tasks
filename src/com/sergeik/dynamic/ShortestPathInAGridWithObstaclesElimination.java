package com.sergeik.dynamic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up,
 * down, left, or right from and to an empty cell in one step.
 *
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner
 * (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 */
public class ShortestPathInAGridWithObstaclesElimination {

    public static void main(String[] args) {
        assert 6 == solution(new int[][] {
                {0,0,0},
                 {1,1,0},
                 {0,0,0},
                 {0,1,1},
                 {0,0,0}
        }, 1);
    }

    private static int solution(int[][] grid, int k) {
        int[][] dirs = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0, k});
        boolean[][][] seen = new boolean[grid.length][grid[0].length][k + 1];
        seen[0][0][k] = true;
        int steps = 0;
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            while (qSize-- > 0) {
                int[] coor = queue.poll();
                if (coor[0] == grid.length - 1 && coor[1] == grid[0].length - 1)
                    return steps;
                int curK = coor[2];
                for (int[] dir: dirs) {
                    int row = coor[0] + dir[0], col = coor[1] + dir[1], nextK = curK;
                    if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && !seen[row][col][curK]) {
                        if (grid[row][col] != 0) {
                            nextK--;
                        }
                        if (nextK >= 0 && !seen[row][col][nextK]) {
                            seen[row][col][nextK] = true;
                            queue.offer(new int[] {row, col, nextK});
                        }
                    }
                }
            }
            steps++;
        }
        return -1;
    }

}
