package com.sergeik.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ou are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive
 * at any food cell.
 *
 * You are given an m x n character matrix, grid, of these different types of cells:
 *
 * '*' is your location. There is exactly one '*' cell.
 * '#' is a food cell. There may be multiple food cells.
 * 'O' is free space, and you can travel through these cells.
 * 'X' is an obstacle, and you cannot travel through these cells.
 * You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.
 *
 * Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach
 * food, return -1.
 */
public class ShortestPathToGetFood {

    public static void main(String[] args) {
        assert -1 == solution(new char[][]{
                {'X', '*'},
                {'#', 'X'}
        });
        assert 2 == solution(new char[][]{
                {'O', '*'},
                {'#', 'O'}
        });
        assert 6 == solution(new char[][]{
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', '*', 'O', 'X', 'O', '#', 'O', 'X'},
                {'X', 'O', 'O', 'X', 'O', 'O', 'X', 'X'},
                {'X', 'O', 'O', 'O', 'O', '#', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        });
        assert -1 == solution(new char[][]{
                {'X', 'X', 'X', 'X', 'X'},
                {'X', '*', 'X', 'O', 'X'},
                {'X', 'O', 'X', '#', 'X'},
                {'X', 'X', 'X', 'X', 'X'}
        });
        assert 3 == solution(new char[][]{
                {'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', '*', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'O', '#', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X'}
        });
    }

    private static int solution(char[][] grid) {
        int[] source = new int[2];
        for (int r = 0; r < grid.length; r++)
            for (int c = 0; c < grid[r].length; c++)
                if (grid[r][c] == '*') {
                    source[0] = r; source[1] = c;
                    break;
                }

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> bfs = new LinkedList<>();
        int[][] dirs = new int[][] {{0,1},{0,-1},{1,0},{-1,0}};
        visited[source[0]][source[1]] = true;
        bfs.offer(source);
        int res = 0;
        while (!bfs.isEmpty()) {
            int levelSize = bfs.size();
            for (int i = 0; i < levelSize; i++) {
                int[] point = bfs.poll();
                if (grid[point[0]][point[1]] == '#')
                    return res;
                for (int[] dir: dirs) {
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];
                    if (x >= 0 && x < grid.length && y >= 0 && y < grid[x].length && !visited[x][y] && grid[x][y] != 'X') {
                        bfs.offer(new int[]{x,y});
                        visited[x][y] = true;
                    }
                }
            }
            res++;
        }
        return -1;
    }

}
