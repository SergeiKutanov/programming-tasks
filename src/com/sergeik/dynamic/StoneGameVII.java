package com.sergeik.dynamic;

/**
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * There are n stones arranged in a row. On each player's turn, they can remove either the leftmost stone
 * or the rightmost stone from the row and receive points equal to the sum of the remaining stones' values in the row.
 * The winner is the one with the higher score when there are no stones left to remove.
 *
 * Bob found that he will always lose this game (poor Bob, he always loses), so he decided to minimize the score's
 * difference. Alice's goal is to maximize the difference in the score.
 *
 * Given an array of integers stones where stones[i] represents the value of the ith stone from the left, return
 * the difference in Alice and Bob's score if they both play optimally.
 */
public class StoneGameVII {

    public static void main(String[] args) {
        assert 6 == solution(new int[] {5,3,1,4,2});
        assert 122 == solution(new int[]{7,90,5,1,100,10,10,2});
    }

    private static int solution(int[] stones) {
        Integer[][] memo = new Integer[stones.length][stones.length];
        int sum = 0;
        for (int s: stones) {
            sum += s;
        }
        int res = dfs(stones, 0, stones.length - 1, sum, true, memo);
        return res;
    }

    private static int dfs(int[] stones, int left, int right, int sum, boolean isAlice, Integer[][] memo) {
        if (left == right)
            return 0;
        if (memo[left][right] != null)
            return memo[left][right];
        if (isAlice) {
            int leftGain = sum - stones[left] + dfs(stones, left + 1, right, sum - stones[left], false, memo);
            int rightGain = sum - stones[right] + dfs(stones, left, right - 1, sum - stones[right], false, memo);
            memo[left][right] = Math.max(leftGain, rightGain);
            return memo[left][right];
        }
        int leftGain = dfs(stones, left + 1, right, sum - stones[left], true, memo) - sum + stones[left];
        int rightGain = dfs(stones, left, right - 1, sum - stones[right], true, memo) - sum + stones[right];
        memo[left][right] = Math.min(leftGain, rightGain);
        return memo[left][right];
    }

}
