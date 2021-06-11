package com.sergeik;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Play {

    private static int alice = 0;
    private static int bob = 0;
    private static List<Integer> diffs = new LinkedList<>();

    public static void main(String[] args) {
        assert 6 == solution(new int[]{5,3,1,4,2});
//        assert 122 == solution(new int[]{7,90,5,1,100,10,10,2});
    }

    public static int solution(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        Integer[][] dp = new Integer[stones.length][stones.length];
        return dfs(stones, 0, stones.length - 1, true, sum, dp);
    }


    /**
     *  5   3   1
     *  dp[l][r][a] = 0
     *  dp[l][r][b] = 0
     *  -----------------
     *  a - 5 | b - 3   dp[0][2][a] - dp[1][2][b]
     *  a - 1 | b - 0   dp[2][2][a] - dp[3][2][b]
     *
     *  5   3   1   4   2
     *  -----------------
     *  A - 5, B - 3 |  1  4   2
     *  A - 1, B - 4 |  2
     *  A - 2  B - 0 |  -
     *  diff - 1
     *  -----------------
     *  A - 5, B - 3 |  1  4   2
     *  A - 2, B - 1 |  4
     *  A - 4  B - 0 |  -
     *  diff - 7
     *  -----------------
     *  A - 5, B - 3 |  1  4   2
     *  A - 2, B - 4 |  4
     *  A - 1  B - 0 |  -
     *  diff - 1
     *
     *  5   3   1   4   2
     *  -----------------
     *
     *  B - 4 - dp[3][4][b] = 4
     *  A - 2 - dp[4][4][a] = 2
     *      B - 0 - dp[5][4][b] = 0
     *
     */
    private static int dfs(int[] stones, int left, int right, boolean isAlice, int sum, Integer[][] dp) {
        if (left == right)
            return 0;
        if (dp[left][right] != null)
            return dp[left][right];
        if (isAlice) {
            int leftScore = sum - stones[left] + dfs(stones, left + 1, right, false, sum - stones[left], dp);
            int rightScore = sum - stones[right] + dfs(stones, left, right - 1, false, sum - stones[right], dp);
            return dp[left][right] = Math.max(leftScore, rightScore);
        } else {
            int leftScore = dfs(stones, left + 1, right, true, sum - stones[left], dp) - (sum - stones[left]);
            int rightScore = dfs(stones, left, right - 1, true, sum - stones[right], dp) - (sum - stones[right]);
            return dp[left][right] = Math.min(leftScore, rightScore);
        }
    }

}
