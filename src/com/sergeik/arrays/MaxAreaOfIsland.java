package com.sergeik.arrays;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are
 * surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 *
 * Return the maximum area of an island in grid. If there is no island, return 0.
 */
public class MaxAreaOfIsland {

    public static void main(String[] args) {
        assert 4 == solution(new int[][]{
                {1,1,0,0},
                {1,1,0,0},
                {0,0,1,1},
                {0,0,1,1}
        });
        assert 6 == solution(new int[][]{
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        });
    }

    private static int solution(int[][] grid) {
        int maxArea = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == 1) {
                    grid[r][c] = 0;
                    int area = 1 + getArea(grid, r + 1, c) + getArea(grid, r - 1, c)
                            + getArea(grid, r, c + 1) + getArea(grid, r, c - 1);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private static int getArea(int[][] grid, int r, int c) {
        if (r >= 0 && r < grid.length && c >= 0 && c < grid[r].length && grid[r][c] == 1) {
            grid[r][c] = 0;
            return 1 + getArea(grid, r + 1, c) + getArea(grid, r - 1, c)
                    + getArea(grid, r, c + 1) + getArea(grid, r, c - 1);
        }
        return 0;
    }

    private static int modSolution(int[][] grid) {
        int maxArea = 0;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = new int[][] {
                {0,1},
                {0,-1},
                {1,0},
                {-1,0}
        };
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == 1) {
                    int area = 0;
                    queue.offer(new int[]{r,c});
                    grid[r][c] = 0;
                    while (!queue.isEmpty()) {
                        int[] point = queue.poll();
                        area++;
                        for (int[] d: dirs) {
                            int x = point[0] + d[0];
                            int y = point[1] + d[1];
                            if(x >= 0 && x < grid.length && y >= 0 && y < grid[x].length && grid[x][y] == 1) {
                                grid[x][y] = 0;
                                queue.offer(new int[]{x,y});
                            }
                        }
                    }
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private static int noModSolution(int[][] grid) {
        if (grid.length == 0)
            return 0;
        int maxArea = 0;
        Queue<int[]> bfsQueue = new LinkedList<>();
        int[][] dirs = new int[][] {
                {0,1},
                {0,-1},
                {-1, 0},
                {1,0}
        };
        boolean[] visited = new boolean[grid.length * grid[0].length];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                int idx = r * grid[r].length + c;
                if (!visited[idx] && grid[r][c] == 1) {
                    visited[idx] = true;
                    bfsQueue.offer(new int[]{r,c});
                    int area = 0;
                    while (!bfsQueue.isEmpty()) {
                        area++;
                        int[] point = bfsQueue.poll();
                        for (int[] dir: dirs) {
                            int x = point[0] + dir[0];
                            int y = point[1] + dir[1];
                            if (x >= 0 && x < grid.length && y >= 0 && y < grid[x].length && grid[x][y] == 1) {
                                int i = x * grid[x].length + y;
                                if (!visited[i]) {
                                    visited[i] = true;
                                    bfsQueue.offer(new int[]{x, y});
                                }
                            }
                        }
                    }
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

}
