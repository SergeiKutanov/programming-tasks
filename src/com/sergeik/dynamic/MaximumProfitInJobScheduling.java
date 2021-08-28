package com.sergeik.dynamic;

import java.util.Arrays;

/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit
 * of profit[i].
 *
 * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there
 * are no two jobs in the subset with overlapping time range.
 *
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 */
public class MaximumProfitInJobScheduling {

    public static void main(String[] args) {
        assert 150 == solution(
                new int[] {1,2,3,4,6},
                new int[] {3,5,10,6,9},
                new int[] {20,20,100,70,60}
        );
    }

    private static int solution(int[] startTime, int[] endTime, int[] profit) {
        int[][] data = new int[startTime.length][3];
        for (int i = 0; i < data.length; i++) {
            data[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(data, (a,b) -> a[0] - b[0]);
        Integer[] memo = new Integer[startTime.length];
        return dfs(0, data, memo);
    }

    private static int dfs(int cur, int[][] data, Integer[] memo) {
        if (cur == data.length)
            return 0;
        //insert memo
        if (memo[cur] != null)
            return memo[cur];

        int nextJobId = -1;
        for (int i = cur + 1; i < data.length; i++) {
            if (data[cur][1] <= data[i][0]) {
                nextJobId = i;
                break;
            }
        }
        int includeNext = data[cur][2] + (nextJobId == -1 ? 0 : dfs(nextJobId, data, memo));
        int excludeJob = dfs(cur + 1, data, memo);

        memo[cur] = Math.max(includeNext, excludeJob);

        return memo[cur];
    }


}
