package com.sergeik.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally
 * or move outside the boundary (i.e., wrap-around is not allowed).
 */
public class LongestIncreasingPathInMatrix {

    public static void main(String[] args) {
        assert 4 == khanSolution(new int[][] {
                {9,9,4},
                {6,6,8},
                {2,1,1}
        });

        assert 4 == dpSolution(new int[][] {
                {9,9,4},
                {6,6,8},
                {2,1,1}
        });
    }

    private static int dpSolution(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[][] dp = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dp[r][c] = -1;
            }
        }

        int maxPathLength = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int curPathLength = dfs(matrix, visited, dp, r, c);
                maxPathLength = Math.max(maxPathLength, curPathLength);
            }
        }

        return maxPathLength;
    }

    private static int dfs(int[][] matrix, boolean[][] visited, int[][] dp, int x, int y) {
        if (dp[x][y] != -1)
            return dp[x][y];

        visited[x][y] = true;
        int[][] directions = new int[][] {
                {1,0},
                {-1,0},
                {0,1},
                {0,-1}
        };

        int maxPath = 0;
        for (int[] d: directions) {
            int r = x + d[0];
            int c = y + d[1];
            if (r >= 0 && r < matrix.length
                && c >= 0 && c < matrix[0].length
                && !visited[r][c]
                && matrix[x][y] < matrix[r][c]) {
                maxPath = Math.max(
                        maxPath,
                        dfs(matrix, visited, dp, r, c)
                );
            }
        }

        visited[x][y] = false;
        dp[x][y] = maxPath + 1;
        return dp[x][y];
    }

    private static int khanSolution(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        ArrayList<Integer>[] adjList = new ArrayList[rows * cols];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        //build graph
        int[][] directions = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int[] d: directions) {
                    int x = row + d[0];
                    int y = col + d[1];
                    if (x >= 0 && x < rows && y >= 0 && y < cols && matrix[row][col] < matrix[x][y]) {
                        int cellWithGreaterValue = x * cols + y;
                        int cellWithLowerValue = row * cols + col;
                        adjList[cellWithLowerValue].add(cellWithGreaterValue);
                    }
                }
            }
        }

        int maxPath = 0;
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[adjList.length];

        for (int i = 0; i < inDegree.length; i++) {
            for (int v: adjList[i]){
                inDegree[v]++;
            }
        }

        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cell = queue.poll();
                for (int neighbour : adjList[cell]) {
                    if (--inDegree[neighbour] == 0) {
                        queue.offer(neighbour);
                    }
                }
            }
            maxPath++;
        }

        return maxPath;
    }

}
