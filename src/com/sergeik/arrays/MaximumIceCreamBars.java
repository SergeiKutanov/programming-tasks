package com.sergeik.arrays;

import java.util.Arrays;

/**
 * It is a sweltering summer day, and a boy wants to buy some ice cream bars.
 *
 * At the store, there are n ice cream bars. You are given an array costs of length n,
 * where costs[i] is the price of the ith ice cream bar in coins. The boy initially has coins coins to spend,
 * and he wants to buy as many ice cream bars as possible.
 *
 * Return the maximum number of ice cream bars the boy can buy with coins coins.
 *
 * Note: The boy can buy the ice cream bars in any order.
 */
public class MaximumIceCreamBars {

    public static void main(String[] args) {
        assert 6 == solution(new int[] {1,6,3,1,2,5}, 20);
        assert 4 == solution(new int[] {1,3,2,4,1}, 7);
        assert 0 == solution(new int[] {10,6,8,7,7,8}, 5);
    }

    private static int solution(int[] costs, int coins) {
        Arrays.sort(costs);
        int count = 0;
        int idx = 0;
        while (coins > 0 && idx < costs.length) {
            if (coins >= costs[idx]) {
                coins -= costs[idx++];
                count++;
            } else {
                break;
            }
        }
        return count;
    }

}
