package com.sergeik.dynamic;

import com.sergeik.trees.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * You are given an integer n. You have an n x n binary grid grid with all values initially 1's except for some
 * indices given in the array mines. The ith element of the array mines is defined as mines[i] = [xi, yi] where
 * grid[xi][yi] == 0.
 *
 * Return the order of the largest axis-aligned plus sign of 1's contained in grid. If there is none, return 0.
 *
 * An axis-aligned plus sign of 1's of order k has some center grid[r][c] == 1 along with four arms of length k - 1
 * going up, down, left, and right, and made of 1's. Note that there could be 0's or 1's beyond the arms of the plus
 * sign, only the relevant area of the plus sign is checked for 1's.
 */
public class LargestPlusSign {

    public static void main(String[] args) {
        /*
            1   0
            0   0

         */
        assert 1 == solution(2, new int[][]{{0,1},{1,0},{1,1}});
        assert 2 == solution(5, new int[][] {{4,2}});
    }


    private static int solution(int n, int[][] mines) {
        Set<Integer> banned = new HashSet<>();
        int[][] dp = new int[n][n];

        for (int[] mine: mines)
            banned.add(mine[0] * n + mine[1]);
        int ans = 0, count;

        for (int r = 0; r < n; ++r) {
            count = 0;
            for (int c = 0; c < n; ++c) {
                count = banned.contains(r*n + c) ? 0 : count + 1;
                dp[r][c] = count;
            }

            count = 0;
            for (int c = n-1; c >= 0; --c) {
                count = banned.contains(r*n + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }
        }

        for (int c = 0; c < n; ++c) {
            count = 0;
            for (int r = 0; r < n; ++r) {
                count = banned.contains(r*n + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }

            count = 0;
            for (int r = n-1; r >= 0; --r) {
                count = banned.contains(r*n + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
                ans = Math.max(ans, dp[r][c]);
            }
        }

        return ans;
    }

    private static int treeSetSolution(int n, int[][] mines) {
        HashMap<Integer, TreeSet<Integer>> x = new HashMap<>(), y = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            TreeSet<Integer> set = new TreeSet<>();
            set.add(-1);
            set.add(n);
            x.put(i, set);

            TreeSet<Integer> setY = new TreeSet<>();
            setY.add(-1);
            setY.add(n);
            y.put(i, setY);
        }

        for (int i = 0; i < mines.length; i++) {
            int[] coor = mines[i];
            x.get(coor[0]).add(coor[1]);
            y.get(coor[1]).add(coor[0]);
        }

        int res = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                Integer up = row - y.get(col).floor(row);
                Integer down = y.get(col).ceiling(row) - row;
                Integer left = col - x.get(row).floor(col);
                Integer right = x.get(row).ceiling(col) - col;
                int min = Math.min(left, Math.min(right, Math.min(up, down)));
                res = Math.max(res, min);
            }
        }

        return res;
    }

}
