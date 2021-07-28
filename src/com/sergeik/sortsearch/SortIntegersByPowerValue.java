package com.sergeik.sortsearch;

import javafx.util.Pair;

import java.util.Arrays;

/**
 * The power of an integer x is defined as the number of steps needed to transform x into 1 using the following steps:
 *
 * if x is even then x = x / 2
 * if x is odd then x = 3 * x + 1
 * For example, the power of x = 3 is 7 because 3 needs 7 steps to become
 * 1 (3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1).
 *
 * Given three integers lo, hi and k. The task is to sort all integers in the interval [lo, hi] by the power
 * value in ascending order, if two or more integers have the same power value sort them by ascending order.
 *
 * Return the k-th integer in the range [lo, hi] sorted by the power value.
 *
 * Notice that for any integer x (lo <= x <= hi) it is guaranteed that x will transform into 1 using these steps
 * and that the power of x is will fit in 32 bit signed integer.
 */
public class SortIntegersByPowerValue {

    private static int max = 0;

    public static void main(String[] args) {
        assert 811 == getKth(118, 974, 825);
        assert 16 == getKth(1, 1000, 5);
        assert 13 == getKth(12, 15, 2);
        assert 7 == getKth(7, 11, 4);
    }

    public static int getKth(int lo, int hi, int k) {
        int[] memo = new int[250505];
        int[] count = new int[hi - lo + 1];
        Integer[] res = new Integer[count.length];
        for (int i = lo; i <= hi; i++) {
            count[i - lo] = getPower(i, memo);
            res[i - lo] = i;
        }
        Arrays.sort(res, (a, b) -> {
            if (count[a - lo] == count[b - lo])
                return a - b;
            return count[a - lo] - count[b - lo];
        });
        return res[k - 1];
    }

    private static int getPower(int n, int[] memo) {
        if (memo[n] != 0)
            return memo[n];
        int nextInt = n;
        if (n % 2 == 0) {
            nextInt /= 2;
        } else {
            nextInt = 3 * nextInt + 1;
        }
        if (nextInt == 1)
            return 1;
        int power = getPower(nextInt, memo) + 1;
        memo[n] = power;
        return power;
    }

}
