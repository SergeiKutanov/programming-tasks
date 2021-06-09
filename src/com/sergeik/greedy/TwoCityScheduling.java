package com.sergeik.greedy;

import java.util.Arrays;

/**
 * A company is planning to interview 2n people. Given the array costs where costs[i] = [aCosti, bCosti],
 * the cost of flying the ith person to city a is aCosti, and the cost of flying the ith person to city b is bCosti.
 *
 * Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.
 */
public class TwoCityScheduling {

    public static void main(String[] args) {

        assert 4723 == solution(new int[][] {
                {70,311},{74,927},{732,711},{126,583},{857,118},{97,928},{975,843},
                {175,221},{284,929},{816,602},{689,863},{721,888}
        });

        assert 110 == solution(new int[][] {
                {10,20},
                {30,200},
                {400,50},
                {30,20}
        });
    }

    private static int solution(int[][] costs) {
        int len = costs.length / 2;
        int[] dp = new int[costs.length];
        int minCost = 0, idx = 0;
        for (int[] cost: costs) {
            dp[idx++] = cost[1] - cost[0];
            minCost += cost[0];
        }
        Arrays.sort(dp);
        for (int i = 0; i < len; i++) {
            minCost += dp[i];
        }
        return minCost;
    }

}
