package com.sergeik.dynamic;

/**
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost,
 * you can either climb one or two steps.
 *
 * You can either start from the step with index 0, or the step with index 1.
 *
 * Return the minimum cost to reach the top of the floor.
 */
public class MinCostClimbingStairs {

    public static void main(String[] args) {
        assert 6 == solution(new int[]{1,100,1,1,1,100,1,1,100,1});
        assert 15 == solution(new int[]{10,15,20});
    }

    private static int solution(int[] cost) {
        int[] memo = new int[cost.length];
        int c1 = costFrom(0, cost, memo);
        int c2 = costFrom(1, cost, memo);
        return Math.min(c1, c2);
    }

    private static int costFrom(int start, int[] cost, int[] memo) {
        if (memo[start] > 0)
            return memo[start];
        if (start >= cost.length - 2)
            return cost[start];
        else {
            int costWith1Step = costFrom(start + 1, cost, memo);
            int costWith2Step = costFrom(start + 2, cost, memo);
            int minCost = Math.min(costWith1Step, costWith2Step);
            memo[start] = minCost + cost[start];
            return memo[start];
        }
    }

}
