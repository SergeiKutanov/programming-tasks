package com.sergeik.dynamic;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing
 * a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 */
public class MaxProfit {

    public static void main(String[] args) {
        int[] prices = new int[] {7,1,5,3,6,4};
        assert 5 == bruteSolution(prices);
        assert 5 == onePass(prices);

        prices = new int[] {7,6,4,3,1};
        assert 0 == bruteSolution(prices);
        assert 0 == onePass(prices);
    }

    private static int bruteSolution(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (maxProfit < prices[j] - prices[i])
                    maxProfit = prices[j] - prices[i];
            }
        }
        return maxProfit;
    }

    private static int onePass(int[] prices) {
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (min > prices[i]) {
                min = prices[i];
            } else {
                if (maxProfit < prices[i] - min)
                    maxProfit = prices[i] - min;
            }
        }

        return maxProfit;
    }

}
