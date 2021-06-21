package com.sergeik.sortsearch;

import java.util.Stack;

/**
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can
 * go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.
 * When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol]
 * and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination,
 * otherwise return false.
 *
 * You may assume that the borders of the maze are all walls (see examples).
 */
public class TheMaze {

    public static void main(String[] args) {
        assert !solution(new int[][]{
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}
        }, new int[] {0,4}, new int[]{3,2});
        assert solution(new int[][]{
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}
        }, new int[] {0,4}, new int[] {4,4});
    }

    private static boolean solution(int[][] maze, int[] start, int[] destination) {
        int[][] dirs = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Stack<int[]> dfs = new Stack<>();
        visited[start[0]][start[1]] = true;
        dfs.push(start);
        while (!dfs.isEmpty()) {
            int[] point = dfs.pop();
            if (point[0] == destination[0] && point[1] == destination[1])
                return true;
            for (int[] dir: dirs) {
                int x = point[0] + dir[0];
                int y = point[1] + dir[1];
                while (x >= 0 && x < maze.length && y >= 0 && y < maze[x].length && maze[x][y] == 0) {
                    x += dir[0]; y += dir[1];
                }
                x -= dir[0];
                y -= dir[1];
                if (!visited[x][y]) {
                    dfs.push(new int[]{x,y});
                    visited[x][y] = true;
                }

            }
        }
        return false;
    }

}
