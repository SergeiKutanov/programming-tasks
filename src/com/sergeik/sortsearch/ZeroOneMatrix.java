package com.sergeik.sortsearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 */
public class ZeroOneMatrix {

    public static void main(String[] args) {
        int[][] exp, res;
        exp = new int[][] {
                {0,0,0},
                {0,1,0},
                {1,2,1}
        };
        res = solution(new int[][]{
                {0,0,0},
                {0,1,0},
                {1,1,1}
        });
        for (int i = 0; i < exp.length; i++)
            for (int j = 0; j < exp[i].length; j++)
                assert exp[i][j] == res[i][j];
    }

    private static int[][] solution(int[][] mat) {
        int rows = mat.length;
        if (rows == 0)
            return mat;
        int cols = mat[0].length;
        int[][] res = new int[rows][cols];
        for (int[] r: res)
            Arrays.fill(r, Integer.MAX_VALUE - 10000);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] != 0) {
                    if (i > 0)
                        res[i][j] = Math.min(res[i][j], res[i - 1][j] + 1);
                    if (j > 0)
                        res[i][j] = Math.min(res[i][j], res[i][j - 1] + 1);
                } else
                    res[i][j] = 0;
            }
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (i < rows - 1)
                    res[i][j] = Math.min(res[i][j], res[i + 1][j] + 1);
                if (j < cols - 1)
                    res[i][j] = Math.min(res[i][j], res[i][j + 1] + 1);
            }
        }

        return res;
    }


    private static int[][] bfsSolution(int[][] mat) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = new int[][] {{0,1},{0,-1},{1,0},{-1,0}};
        int[][] res = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0)
                    continue;
                boolean[][] seen = new boolean[mat.length][mat[0].length];
                queue.offer(new int[]{i,j});
                seen[i][j] = true;
                int dist = 0;
                while (!queue.isEmpty()) {
                    int levelSize = queue.size();
                    while (levelSize-- > 0) {
                        int[] coor = queue.poll();
                        if (mat[coor[0]][coor[1]] != 0) {
                            for (int[] dir: dirs) {
                                int x = coor[0] + dir[0], y = coor[1] + dir[1];
                                if (x >= 0 && x < mat.length && y >= 0 && y < mat[x].length && !seen[x][y]) {
                                    queue.offer(new int[]{x,y});
                                    seen[x][y] = true;
                                }
                            }
                        } else {
                            res[i][j] = dist;
                            queue.clear();
                            levelSize = 0;
                        }
                    }
                    dist++;
                }
            }
        return res;
    }

}
