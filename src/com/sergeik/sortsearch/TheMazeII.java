package com.sergeik.sortsearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1).
 * The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling
 * until hitting a wall. When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol]
 * and destination = [destinationrow, destinationcol], return the shortest distance for the ball to stop
 * at the destination. If the ball cannot stop at destination, return -1.
 *
 * The distance is the number of empty spaces traveled by the ball from the start position (excluded)
 * to the destination (included).
 *
 * You may assume that the borders of the maze are all walls (see examples).
 */
public class TheMazeII {

    public static void main(String[] args) {
        assert 12 == bfsSolution(new int[][] {
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}
        }, new int[] {0,4}, new int[]{4,4});
    }

    private static int bfsSolution(int[][] maze, int[] start, int[] destination) {
        int[][] dirs = new int[][] {{0,1}, {0,-1},{1,0},{-1,0}};
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] dist: distance)
            Arrays.fill(dist, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        Queue<int[]> bfsQueue = new LinkedList<>();
        bfsQueue.offer(start);
        while (!bfsQueue.isEmpty()) {
            int[] point = bfsQueue.poll();
            for (int[] dir: dirs) {
                int x = point[0] + dir[0];
                int y = point[1] + dir[1];
                int count = 0;
                while (x >= 0 && x < maze.length && y >= 0 && y < maze[x].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                x -= dir[0];
                y -= dir[1];
                int dist = distance[point[0]][point[1]] + count;
                if (dist < distance[x][y]) {
                    distance[x][y] = dist;
                    bfsQueue.offer(new int[]{x,y});
                }
            }
        }
        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
    }

    private static int dfSsolution(int[][] maze, int[] start, int[] destination) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] dist: distance)
            Arrays.fill(dist, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        dfs(maze, distance, start);
        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
    }

    private static void dfs(int[][] maze, int[][] distance, int[] start) {
        int[][] dirs = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};
        for (int[] dir: dirs) {
            int x = start[0] + dir[0];
            int y = start[1] + dir[1];
            int count = 0;
            while (x >= 0 && x < maze.length && y >= 0 && y < maze[x].length && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
                count++;
            }
            if (distance[start[0]][start[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                distance[x - dir[0]][y - dir[1]] = distance[start[0]][start[1]] + count;
                dfs(maze, distance, new int[]{x - dir[0], y - dir[1]});
            }
        }
    }

}
