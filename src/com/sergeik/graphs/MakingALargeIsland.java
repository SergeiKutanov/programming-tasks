package com.sergeik.graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MakingALargeIsland {

    private static int res = 0;

    public static void main(String[] args) {
        assert 4 == solution(new int[][]{
                {1,1},
                {1,0}
        });
        assert 4 == solution(new int[][]{
                {1,1},
                {1,1}
        });
        assert 3 == solution(new int[][]{
                {1,0},
                {0,1}
        });
    }

    private static int solution(int[][] grid) {
        res = 0;
        int[] size = new int[grid.length * grid[0].length];
        int[] parent = new int[grid.length * grid[0].length];
        boolean[][] seen = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < parent.length; i++)
            parent[i] = i;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0 || seen[i][j])
                    continue;
                bfs(i, j, grid, seen, size, parent);
            }
        }

        int[][] dirs = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1)
                    continue;
                int cMax = 0;
                Set<Integer> parents = new HashSet<>();
                for (int[] dir: dirs) {
                    int x = i + dir[0], y = j + dir[1];
                    if (x >= 0 && x < grid.length && y >= 0 && y < grid[x].length && grid[x][y] == 1) {
                        int coor = x * grid[x].length + y;
                        int p = findParent(coor, parent);
                        if (parents.add(p)) {
                            cMax += size[p];
                        }
                    }
                }
                res = Math.max(res, cMax + 1);
            }
        }

        return res;
    }

    private static void bfs(int i, int j, int[][] grid, boolean[][] seen, int[] size, int[] parent) {
        int[][] dirs = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i,j});
        seen[i][j] = true;
        int islandParent = i * grid[i].length + j;
        while (!queue.isEmpty()) {
            int[] coor = queue.poll();
            int cParent = coor[0] * grid[0].length + coor[1];
            int p = findParent(cParent, parent);
            if (p != islandParent)
                parent[cParent] = islandParent;
            size[islandParent]++;
            res = Math.max(res, size[islandParent]);
            for (int[] dir: dirs) {
                int x = coor[0] + dir[0], y = coor[1] + dir[1];
                if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && !seen[x][y] && grid[x][y] == 1) {
                    queue.offer(new int[] {x,y});
                    seen[x][y] = true;
                }
            }
        }

    }

    private static int findParent(int i, int[] parents) {
        while (parents[i] != i)
            i = parents[i];
        return i;
    }

}
