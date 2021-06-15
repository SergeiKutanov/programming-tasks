package com.sergeik.sortsearch;

import java.util.Arrays;

/**
 * You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick.
 * You want to use all the matchsticks to make one square. You should not break any stick, but you can link them up,
 * and each matchstick must be used exactly one time.
 *
 * Return true if you can make this square and false otherwise.
 */
public class MatchsticksToSquare {

    public static void main(String[] args) {
        assert !solution(new int[]{5,5,5,5,16,4,4,4,4,4,3,3,3,3,4});
        assert !solution(new int[] {3,3,3,3,4});
        assert solution(new int[] {1,1,2,2,2});
    }

    private static boolean solution(int[] matchsticks) {
        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0)
            return false;
        int blockSize = sum / 4;
        int sums[] = new int[4];
        return dfs(matchsticks, 0, sums, blockSize);
    }

    private static boolean dfs(int[] matchsticks, int idx, int[] sums, int blockSize) {
        if (idx == matchsticks.length && sums[0] == sums[1] && sums[1] == sums[2] && sums[2] == sums[3])
            return true;
        int item = matchsticks[idx];
        for (int i = 0; i < 4; i++) {
            if (sums[i] + item <= blockSize) {
                sums[i] += item;
                if (dfs(matchsticks, idx + 1, sums, blockSize))
                    return true;
                sums[i] -= item;
            }
        }
        return false;
    }

}
