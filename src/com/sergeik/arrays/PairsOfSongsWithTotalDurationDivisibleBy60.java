package com.sergeik.arrays;

/**
 * You are given a list of songs where the ith song has a duration of time[i] seconds.
 *
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally,
 * we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
 */
public class PairsOfSongsWithTotalDurationDivisibleBy60 {

    public static void main(String[] args) {
        assert 3 == solution(new int[]{60,60,60});
        assert 3 == solution(new int[] {30,20,150,100,40});
    }

    private static int solution(int[] time) {
        int[] dp = new int[60];
        int res = 0;
        for (int n: time) {
            int mod = n % 60;
            int diff = mod == 0 ? mod : 60 - mod;
            res += dp[diff];
            dp[mod]++;
        }
        return res;
    }

}
