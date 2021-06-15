package com.sergeik.dynamic;

/**
 * There is a row of n houses, where each house can be painted one of three colors: red, blue, or green.
 * The cost of painting each house with a certain color is different. You have to paint all the houses such
 * that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.
 *
 * For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of
 * painting house 1 with color green, and so on...
 * Return the minimum cost to paint all houses.
 */
public class PaintHouse {

    public static void main(String[] args) {
        assert 10 == solution(new int[][]{{17,2,17},{16,16,5},{14,3,19}});
    }

    private static int solution(int[][] costs) {
        int[][] dp = new int[costs.length + 1][3];
        int res = backtrack(1, -1, costs, dp);
        return res;
    }

    private static int backtrack(int idx, int prevColor, int[][] costs, int[][] dp) {
        if (idx > costs.length)
            return 0;
        if (prevColor >= 0 && dp[idx][prevColor] > 0)
            return dp[idx][prevColor];
        int min = Integer.MAX_VALUE;
        for (int c = 0; c < 3; c++) {
            if (prevColor == c)
                continue;
            int cMin = backtrack(idx + 1, c, costs, dp) + costs[idx - 1][c];
            min = Math.min(min, cMin);
        }
        if (prevColor >= 0)
            dp[idx][prevColor] = min;
        return min;
    }

}
