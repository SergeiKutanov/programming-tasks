package com.sergeik.dynamic;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        assert 3 == solution(3);
        assert 3 == dynamicSolution(3);
        assert 3 == fibSolution(3);

        assert 5 == solution(4);
        assert 5 == dynamicSolution(4);
        assert 5 == fibSolution(4);

        assert 1836311903 == solution(45);
        assert 1836311903 == dynamicSolution(45);
        assert 1836311903 == fibSolution(45);
    }

    private static int solution(int n) {
        int[] memo = new int[n+1];
        return climb(0, n, memo);
    }

    /**
     * Memoization
     * @param i
     * @param n
     * @param memo
     * @return
     */
    private static int climb(int i, int n, int[] memo) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb(i + 1, n, memo) + climb(i + 2, n, memo);
        return memo[i];
    }

    private static int dynamicSolution(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    private static int fibSolution(int n) {
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

}
