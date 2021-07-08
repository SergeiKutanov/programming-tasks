package com.sergeik.math;

import java.util.Arrays;

/**
 * You are given an integer n. We reorder the digits in any order (including the original order)
 * such that the leading digit is not zero.
 *
 * Return true if and only if we can do this so that the resulting number is a power of two.
 */
public class ReorderedPowerOf2 {

    public static void main(String[] args) {
        assert solution(1);
        assert !solution(10);
        assert solution(16);
        assert !solution(24);
        assert solution(46);
    }

    private static boolean solution(int n) {
        if (n == 1)
            return true;
        int[] freq = new int[10];
        int maxValue = 1;
        while (n > 0) {
            freq[n % 10]++;
            n /= 10;
            maxValue *= 10;
        }
        int power = 2;
        while (power <= maxValue) {
            int[] powerFreq = buildFreq(power);
            if (Arrays.equals(powerFreq, freq))
                return true;
            power <<= 1;
        }
        return false;
    }

    private static int[] buildFreq(int n) {
        int[] freq = new int[10];
        while (n > 0) {
            freq[n % 10]++;
            n /= 10;
        }
        return freq;
    }

}
