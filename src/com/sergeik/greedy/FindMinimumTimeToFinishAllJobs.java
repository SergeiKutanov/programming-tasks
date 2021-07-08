package com.sergeik.greedy;

import java.util.Arrays;

/**
 * You are given an integer array jobs, where jobs[i] is the amount of time it takes to complete the ith job.
 *
 * There are k workers that you can assign jobs to. Each job should be assigned to exactly one worker.
 * The working time of a worker is the sum of the time it takes to complete all jobs assigned to them.
 * Your goal is to devise an optimal assignment such that the maximum working time of any worker is minimized.
 *
 * Return the minimum possible maximum working time of any assignment.
 */
public class FindMinimumTimeToFinishAllJobs {

    private static int res = Integer.MAX_VALUE;

    public static void main(String[] args) {
        assert 12 == solution(new int[] {5,5,4,4,4}, 2);
        assert 3 == solution(new int[] {3,2,3}, 3);
        assert 11 == solution(new int[] {1,2,4,7,8}, 2);
    }

    private static int solution(int[] jobs, int k) {
        res = Integer.MAX_VALUE;
        Arrays.sort(jobs);
        dfs(jobs, jobs.length - 1, new int[k]);
        return res;
    }

    private static void dfs(int[] jobs, int jobIdx, int[] workers) {
        if (jobIdx < 0) {
            res = Math.min(res, Arrays.stream(workers).max().getAsInt());
            return;
        }
        if (Arrays.stream(workers).max().getAsInt() > res)
            return;
        for (int i = 0; i < workers.length; i++) {
            if (i > 0 && workers[i] == workers[i - 1])
                continue;
            workers[i] += jobs[jobIdx];
            dfs(jobs, jobIdx - 1, workers);
            workers[i] -= jobs[jobIdx];
        }
    }

}
