package com.sergeik.dynamic;

/**
 * You have planned some train traveling one year in advance. The days of the year in which you
 * will travel are given as an integer array days. Each day is an integer from 1 to 365.
 *
 * Train tickets are sold in three different ways:
 *
 * a 1-day pass is sold for costs[0] dollars,
 * a 7-day pass is sold for costs[1] dollars, and
 * a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.
 *
 * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 */
public class MinimumCostForTickets {

    public static void main(String[] args) {
        assert 50 == solution(
                new int[] {1,2,3,4,6,8,9,10,13,14,16,17,19,21,24,26,27,28,29},
                new int[] {3,14,50}
        );
        assert 17 == solution(new int[]{1,2,3,4,5,6,7,8,9,10,30,31}, new int[]{2,7,15});
        assert 11 == solution(new int[]{1,4,6,7,8,20}, new int[] {2,7,15});
    }

    private static int solution(int[] days, int[] costs) {
        /*
        dp = 2  0   0   0   0   0
        dp = 2  4   0   0   0   0
        dp = 2  4   6   0   0   0
        dp = 2  4   6   7   0   0
        dp = 2  4   6   7   9   0   - 9 = min(dp[0] + 7, dp[3] + 2, dp[-1] + 15)
        dp = 2  4   6   7   9   11  - 11 = min(dp[4] + 2, dp[4] + 2, dp[-1] + 15)
         */
        int[] dp = new int[days.length];
        for (int i = 0; i < days.length; i++) {
            int oneDayCost = costs[0] + (i > 0 ? dp[i - 1] : 0);
            //find price not covered by the ticket
            int prevDay = i;
            int limit = days[i] - 7;
            while (prevDay >= 0 && days[prevDay] > limit)
                prevDay--;
            int daysCovered = i - prevDay; //find for 7 days
            int sevenDaysCost = costs[1] + (i - daysCovered >= 0 ? dp[i - daysCovered] : 0);

            limit = days[i] - 30;
            while (prevDay >= 0 && days[prevDay] > limit)
                prevDay--;
            daysCovered = i - prevDay; //find for 30 days
            int thirtyDaysCost = costs[2] + (i - daysCovered >= 0 ? dp[i - daysCovered] : 0);
            int min = Math.min(oneDayCost, Math.min(sevenDaysCost, thirtyDaysCost));
            dp[i] = min;
        }
        return dp[days.length - 1];
    }

}
