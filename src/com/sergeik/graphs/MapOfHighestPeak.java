package com.sergeik.graphs;

import java.util.*;

/**
 * You are given an integer matrix isWater of size m x n that represents a map of land and water cells.
 *
 * If isWater[i][j] == 0, cell (i, j) is a land cell.
 * If isWater[i][j] == 1, cell (i, j) is a water cell.
 * You must assign each cell a height in a way that follows these rules:
 *
 * The height of each cell must be non-negative.
 * If the cell is a water cell, its height must be 0.
 * Any two adjacent cells must have an absolute height difference of at most 1. A cell is adjacent to another
 * cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).
 * Find an assignment of heights such that the maximum height in the matrix is maximized.
 *
 * Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height. If there are
 * multiple solutions, return any of them.
 */
public class MapOfHighestPeak {

    public static void main(String[] args) {
        int[][] res;
        int[][] exp;

        res = solution(new int[][] {
                {0},{0},{0},{0},{1},{0},{0},{1},{1}
        });
        exp = new int[][] {
                {4},{3},{2},{1},{0},{1},{1},{0},{0}
        };
        for (int i = 0; i < exp.length; i++) {
            for (int j = 0; j < exp[i].length; j++) {
                assert exp[i][j] == res[i][j];
            }
        }

        res = solution(new int[][]{
                {0,1},
                {0,0}
        });
        exp = new int[][] {
                {1,0},
                {2,1}
        };
        for (int i = 0; i < exp.length; i++) {
            assert Arrays.equals(exp[i], res[i]);
        }

        res = solution(new int[][]{
                {0,0,1},
                {1,0,0},
                {0,0,0}
        });
        exp = new int[][] {
                {1,1,0},
                {0,1,1},
                {1,2,2},
        };
        for (int i = 0; i < exp.length; i++) {
            assert Arrays.equals(exp[i], res[i]);
        }
    }

    private static int[][] solution(int[][] isWater) {
        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < isWater.length; r++) {
            for (int c = 0; c < isWater[r].length; c++) {
                if (isWater[r][c] == 1) {
                    queue.offer(new int[]{r, c});
                    isWater[r][c] = 0;
                } else {
                    isWater[r][c] = - 1;
                }
            }
        }

        int[][] dirs = new int[][] {
                {1,0},
                {-1,0},
                {0,1},
                {0,-1}
        };

        for (int height = 1; !queue.isEmpty(); height++) {
            for (int size = queue.size(); size > 0; size--) {
                int[] point = queue.poll();
                for (int[] dir: dirs) {
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];
                    if (x >= 0 && x < isWater.length && y >= 0 && y < isWater[x].length && isWater[x][y] < 0) {
                        isWater[x][y] = height;
                        queue.offer(new int[] {x,y});
                    }
                }
            }
        }

        return isWater;
    }

    private static int[][] tleSolution(int[][] isWater) {
        int[][] res = new int[isWater.length][isWater[0].length];
        for (int r = 0; r < isWater.length; r++) {
            for (int c = 0; c < isWater[r].length; c++) {
                res[r][c] = isWater[r][c] == 1 ? 0 : Integer.MAX_VALUE - 1;
            }
        }

        int[][] dirs = new int[][] {
                {1,0},
                {-1,0},
                {0,1},
                {0,-1}
        };

        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < res.length; r++) {
            for (int c = 0; c < res[r].length; c++) {
                if (res[r][c] == 0) {
                    Set<Integer> seen = new HashSet<>();
                    queue.offer(new int[]{r,c});
                    seen.add(r * res[r].length + c);
                    while (!queue.isEmpty()) {
                        int[] point = queue.poll();
                        int max = Integer.MAX_VALUE;
                        for (int[] d: dirs) {
                            int x = point[0] + d[0];
                            int y = point[1] + d[1];
                            if (x < res.length && x >= 0 && y >= 0 && y < res[x].length) {
                                if (res[x][y] != 0) {
                                    int coor = x * res[x].length + y;
                                    if (!seen.contains(coor)) {
                                        seen.add(coor);
                                        queue.offer(new int[]{x, y});
                                    }
                                }
                                max = Math.min(max, res[x][y] + 1);
                            }
                        }
                        if (res[point[0]][point[1]] != 0)
                            res[point[0]][point[1]] = max;
                    }
                }
            }
        }

        return res;
    }

}
