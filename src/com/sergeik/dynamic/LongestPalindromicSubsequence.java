package com.sergeik.dynamic;

/*
Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without
changing the order of the remaining elements.
 */
public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        assert 5 == solution("abcdba");
    }

    private static int solution(String s) {
        String r = new StringBuilder(s).reverse().toString();
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp.length; j++) {
                if (s.charAt(i - 1) == r.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[s.length()][s.length()];
    }

}
