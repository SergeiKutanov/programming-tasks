package com.sergeik.dynamic;

/**
 * Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row,
 * and each pile has a positive integer number of stones piles[i].
 *
 * The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
 *
 * Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones
 * from either the beginning or the end of the row.  This continues until there are no more piles left,
 * at which point the person with the most stones wins.
 *
 * Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
 */
public class StoneGame {

    public static void main(String[] args) {
        assert stoneGame(new int[] {3,7,2,3});
        assert stoneGame(new int[] {5,3,4,5});
    }

    private static boolean stoneGame(int[] piles) {
        Integer[][][] memo = new Integer[piles.length][piles.length][2];
        int res = dfs(piles, 0, piles.length - 1, true, memo);
        return res > 0;
    }

    private static int dfs(int[] piles, int left, int right, boolean isAlex, Integer[][][] memo) {
        if (left > right)
            return 0;
        if (isAlex && memo[left][right][0] != null)
            return memo[left][right][0];
        if (!isAlex && memo[left][right][1] != null)
            return memo[left][right][1];
        if (isAlex) {
            int leftPoints = dfs(piles, left + 1, right, false, memo);
            int rightPoints = dfs(piles, left, right - 1, false, memo);
            memo[left][right][0] = Math.max(piles[left] + leftPoints, piles[right] + rightPoints);
        } else {
            int leftPoints = dfs(piles, left + 1, right, true, memo);
            int rightPoints = dfs(piles, left, right - 1, true, memo);
            memo[left][right][1] = Math.max(piles[left] + leftPoints, piles[right] + rightPoints);
        }
        return isAlex ? memo[left][right][0] : memo[left][right][1];
    }

}
