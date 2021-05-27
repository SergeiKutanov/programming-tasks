package com.sergeik.greedy;

import java.util.Arrays;

/**
 * You are given an integer array coins of length n which represents the n coins that you own.
 * The value of the ith coin is coins[i]. You can make some value x if you can choose some of
 * your n coins such that their values sum up to x.
 *
 * Return the maximum number of consecutive integer values that you can make with your coins
 * starting from and including 0.
 *
 * Note that you may have multiple coins of the same value.
 */
public class MaximumNumberOfConsectutiveValuesYouCanMake {

    public static void main(String[] args) {
        assert 20 == solution(new int[]{1,4,10,3,1});
    }

    /**
     * The intuition for this one is cool. Got a few TLE before figuring it out.
     *
     * This is the realization: if we reached number i, that means that we can make all values 0...i.
     * So, if we add a coin with value j, we can also make values i+1...i+j.
     *
     * The only case when we can have a gap is the coin value is greater than i + 1. So we sort the coins to
     * make sure we process smaller coins first.
     *
     * For example, let's assume that we can make all values 0...7. Adding a nickel allows us
     * to make values 8..12 (7+1...7+5).
     *
     * @param coins
     * @return
     */
    private static int solution(int[] coins) {
        Arrays.sort(coins);
        int count = 1;

        for (int n: coins) {
            if (n > count)
                break;
            count += n;
        }

        return count;
    }

}
