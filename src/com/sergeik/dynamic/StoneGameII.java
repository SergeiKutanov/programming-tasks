package com.sergeik.dynamic;

import java.util.Arrays;

/**
 * Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row,
 * and each pile has a positive integer number of stones piles[i].  The objective of the game is
 * to end with the most stones.
 *
 * Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
 *
 * On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.
 * Then, we set M = max(M, X).
 *
 * The game continues until all the stones have been taken.
 *
 * Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
 */
public class StoneGameII {

    public static void main(String[] args) {
        assert 10 == solution(new int[] {2,7,9,4,4});
        assert 104 == solution(new int[] {1,2,3,4,5,100});
    }

    private static int solution(int[] piles) {
        int[] presum = Arrays.copyOf(piles, piles.length);
        for (int i = piles.length - 2; i >= 0; i--)
            presum[i] += presum[i + 1];
        return dfs(presum, 1, 0, new int[piles.length][piles.length]);
    }

    private static int dfs(int[] presum, int m, int p, int[][] memo) {
        if (p == presum.length)
            return 0;

        if (p + 2 * m >= presum.length)
            return presum[p];

        if (memo[p][m] > 0)
            return memo[p][m];

        int res = 0, take = 0;
        for (int i = 1; i <= 2 * m; i++) {
            take = presum[p] - presum[p + i];
            int nextMax = dfs(presum, Math.max(i, m), p + i, memo);
            int cRes = take + presum[p + i] - nextMax;
            res = Math.max(res, cRes);
        }
        memo[p][m] = res;
        return res;
    }

}
