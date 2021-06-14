package com.sergeik.dynamic;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day,
 * and an integer fee representing a transaction fee.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need
 * to pay the transaction fee for each transaction.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock
 * before you buy again).
 */
public class BestTimeToBuyAndSellStocksWithTransactionFee {

    public static void main(String[] args) {
        assert 8 == solution(new int[]{1,3,2,8,4,9}, 2);
        assert 6 == solution(new int[]{1,3,7,5,10,3}, 3);
    }

    private static int solution(int[] prices, int fee) {
        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        //buy at first day
        buy[0] -= prices[0];
        for (int i = 1; i < prices.length; i++) {
            buy[i] = Math.max(sell[i - 1] - prices[i], buy[i - 1]);
            sell[i] = Math.max(buy[i - 1] + prices[i] - fee, sell[i - 1]);
        }
        return sell[prices.length - 1];
    }

    private static int dpDfsSolution(int[] prices, int fee) {
        Integer[][] dp = new Integer[prices.length][2];
        int res = dfs(prices, fee, 1, 0, dp);
        return res;
    }

    private static int dfs(int[] prices, int fee, int buy, int day, Integer[][] dp) {
        if (day == prices.length) {
            return 0;
        }

        if (dp[day][buy] != null)
            return dp[day][buy];

        int profitB = 0;
        int profitS = 0;
        int profitN = 0;
        if (buy == 1) {
            profitB = dfs(prices, fee, 0, day + 1, dp) - prices[day];
            profitN = dfs(prices, fee, 1, day + 1, dp);
        } else {
            profitS = dfs(prices, fee, 1, day + 1, dp) + prices[day] - fee;
            profitN = dfs(prices, fee, 0, day + 1, dp);
        }
        dp[day][buy] = Math.max(profitB, Math.max(profitS, profitN));
        return dp[day][buy];
    }

}
