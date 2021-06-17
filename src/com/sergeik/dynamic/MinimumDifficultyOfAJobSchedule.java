package com.sergeik.dynamic;

/**
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job, you have
 * to finish all the jobs j where 0 <= j < i).
 *
 * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties
 * of each day of the d days. The difficulty of a day is the maximum difficulty of a job done in that day.
 *
 * Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].
 *
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 */
public class MinimumDifficultyOfAJobSchedule {

    public static void main(String[] args) {
        assert 843 == solution(new int[] {11,111,22,222,33,333,44,444}, 6);
        assert 15 == solution(new int[]{7,1,7,1,7,1}, 3);
        assert -1 == solution(new int[]{9,9,9}, 4);
        assert 7 == solution(new int[]{6,5,4,3,2,1}, 2);
    }

    private static int solution(int[] jobDifficulty, int d) {
        Integer[][] dp = new Integer[jobDifficulty.length][d + 1];
        int res = dfs(jobDifficulty, 0, d, dp);
        return res;
    }

    private static int dfs(int[] jobDifficulty, int idx, int daysLeft, Integer[][] dp) {
        if (dp[idx][daysLeft] != null)
            return dp[idx][daysLeft];
        if (daysLeft == 1 && idx < jobDifficulty.length) {
            int max = Integer.MIN_VALUE;
            for (int i = idx; i < jobDifficulty.length; i++)
                max = Math.max(max, jobDifficulty[i]);
            dp[idx][daysLeft] = max;
            return max;
        }
        int curMax = Integer.MIN_VALUE;
        int minDiff = -1;
        for (int i = idx; i < jobDifficulty.length - daysLeft + 1; i++) {
            curMax = Math.max(curMax, jobDifficulty[i]);
            int min = dfs(jobDifficulty, i + 1, daysLeft - 1, dp);
            if (minDiff == -1)
                minDiff = min + curMax;
            else
                minDiff = Math.min(minDiff, curMax + min);
        }
        dp[idx][daysLeft] = minDiff;
        return minDiff;
    }

}
