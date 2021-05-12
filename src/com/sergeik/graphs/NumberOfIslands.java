package com.sergeik.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 * You may assume all four edges of the grid are all surrounded by water.
 */
public class NumberOfIslands {

    public static void main(String[] args) {
        assert 3 == solution(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        });
        assert 1 == solution(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        });
        assert 2 == solution(new char[][]{
                {'1', '1', '0', '1', '1'}
        });
        assert 2 == solution(new char[][]{
                {'1'},
                {'1'},
                {'0'},
                {'1'},
                {'1'}
        });
    }

    private static int solution(char[][] grid) {
        int res = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == '1') {
                    res++;
                    dfs(row, col, grid);
                }
            }
        }

        return res;
    }

    private static void dfs(int r, int c, char[][] grid) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length)
            return;
        if (grid[r][c] != '1')
            return;
        int[][] dirs = new int[][]{
                {0,1},
                {0,-1},
                {1,0},
                {-1,0}
        };
        grid[r][c] = '0';
        for (int[] dir: dirs) {
            dfs(r + dir[0], c + dir[1], grid);
        }
    }

    private static int bfSsolution(char[][] grid) {
        int[][] directions = new int[][] {
                {0,1},
                {1,0},
                {-1,0},
                {0,-1}
        };
        int result = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[] visited = new boolean[rows * cols];
        Queue<Integer> queue = new LinkedList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] != '1')
                    continue;
                int vectorCoor = row * cols + col;
                if (visited[vectorCoor])
                    continue;
                queue.offer(row);
                queue.offer(col);
                visited[vectorCoor] = true;
                //bfs
                while (!queue.isEmpty()) {
                    int r = queue.poll();
                    int c = queue.poll();
                    for (int[] d: directions) {
                        int x = r + d[0];
                        int y = c + d[1];
                        if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == '1') {
                            int vCoor = x * cols + y;
                            if (!visited[vCoor]) {
                                visited[vCoor] = true;
                                queue.offer(x);
                                queue.offer(y);
                            }
                        }
                    }
                }
                result++;
            }
        }
        return result;
    }

}
