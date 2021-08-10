package com.sergeik.dynamic;

/**
 * A binary string is monotone increasing if it consists of some number of 0's (possibly none), followed by
 * some number of 1's (also possibly none).
 *
 * You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
 *
 * Return the minimum number of flips to make s monotone increasing.
 */
public class FlipStringToMonotoneIncreasing {

    public static void main(String[] args) {
        assert 5 == solution("10011111110010111011");
        assert 3 == solution("0101100011");
        assert 0 == solution("111");
        assert 0 == solution("000");
        assert 0 == solution("0011");
        assert 2 == solution("00011000");
        assert 2 == solution("010110");
        assert 1 == solution("00110");
    }

    private static int solution(String s) {
        int idx = 0, oneCount = 0, zeroCount = 0;
        while (idx < s.length() && s.charAt(idx) == '0')
            idx++;
        for (; idx < s.length(); idx++) {
            if (s.charAt(idx) == '0')
                zeroCount++;
            else
                oneCount++;
            zeroCount = Math.min(oneCount, zeroCount);
        }
        return zeroCount;
    }

    private static int dpSolution(String s) {
        int ones = 0;

        int[] dp = new int[s.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            dp[i] = dp[i - 1];
            if (s.charAt(i - 1) == '0') {
                dp[i] = Math.min(ones, dp[i] + 1);
            } else {
                ones++;
                dp[i] = Math.min(ones, dp[i]);
            }
        }
        return dp[s.length()];
    }

}
