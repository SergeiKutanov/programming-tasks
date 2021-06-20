package com.sergeik.backtracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).
 *
 * Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square
 * to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t.
 * You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid
 * during your swim.
 *
 * You start at the top left square (0, 0). What is the least time until you can reach the bottom
 * right square (N-1, N-1)?
 */
public class SwimInRisingWater {

    public static void main(String[] args) {
        assert 3 == heapSolution(new int[][] {
                {3,2},
                {0,1}
        });
        assert 16 == heapSolution(new int[][] {
                {0,1,2,3,4},
                {24,23,22,21,5},
                {12,13,14,15,16},
                {11,17,18,19,20},
                {10,9,8,7,6}
        });
    }

    private static int heapSolution(int[][] grid) {
        int N = grid.length;
        Set<Integer> seen = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((k1, k2) ->
                grid[k1 / N][k1 % N] - grid[k2 / N][k2 % N]);
        pq.offer(0);
        int ans = 0;

        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        while (!pq.isEmpty()) {
            int k = pq.poll();
            int r = k / N, c = k % N;
            ans = Math.max(ans, grid[r][c]);
            if (r == N-1 && c == N-1) return ans;

            for (int i = 0; i < 4; ++i) {
                int cr = r + dr[i], cc = c + dc[i];
                int ck = cr * N + cc;
                if (0 <= cr && cr < N && 0 <= cc && cc < N && !seen.contains(ck)) {
                    pq.offer(ck);
                    seen.add(ck);
                }
            }
        }

        throw null;
    }

    private static int solution(int[][] grid) {
        boolean[][] seen = new boolean[grid.length][grid[0].length];
        int[][][] dp = new int[grid.length][grid[0].length][grid.length * grid[0].length];
        for (int r = 0; r < dp.length; r++)
            for (int c = 0; c < dp[r].length; c++)
                Arrays.fill(dp[r][c], -1);
        seen[0][0] = true;
        int res = dfs(0,0,grid[0][0], seen, grid, dp);
        return res;
    }

    private static int dfs(int r, int c, int t, boolean[][] seen, int[][] grid, int[][][] dp) {
        if (r == grid.length - 1 && c == grid[r].length - 1)
            return t;
        if (dp[r][c][t] > -1)
            return dp[r][c][t];
        int[][] dirs = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};
        int minT = Integer.MAX_VALUE;
        for (int[] dir: dirs) {
            int x = r + dir[0], y = c + dir[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[r].length && !seen[x][y]) {
                seen[x][y] = true;
                int newT  = Math.max(t, grid[x][y]);
                minT = Math.min(minT, dfs(x, y, newT, seen, grid, dp));
                seen[x][y] = false;
            }
        }
        dp[r][c][t] = minT;
        return minT;
    }

}
